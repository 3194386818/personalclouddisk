import QMCCrypto from "@jixun/qmc2-crypto"

const DETECTION_SIZE = 40;
// Process in 2m buffer size
const DECRYPTION_BUF_SIZE = 2 * 1024 * 1024;

/**
 * 根据解密后的文件解析，获得新的文件名及对应 mimetype。
 * @param  {Uint8Array[]} u8Array 解密后的文件片段集合
 * @param  {string} fileName 原始文件名
 * @return {[string, string]} 新的文件名以及 mimetype。
 */
export function fileDetection(u8Array, fileName: string):[string, string] {
    const oggMagic = 0x5367674f;
    const flacMagic = 0x43614c66;

    const magic = getMagic(u8Array[0]);

    // 未能识别时的返回内容
    let ext = ".bin";
    let mimeType = "application/octet-stream";

    switch (magic) {
        case oggMagic:
            ext = ".ogg";
            mimeType = "audio/ogg";
            break;
        case flacMagic:
            ext = ".flac";
            mimeType = "audio/flac";
            break;
    }
    const newFileName = fileName.replace(/(\.[^.]+)?$/, ext);
    return [newFileName, mimeType];
}

/**
 * 解密一个 QMC2 加密的文件。
 *
 * 如果检测并解密成功，返回解密后的 Uint8Array 数组，按顺序拼接即可得到完整文件。
 * 若失败，返回 `null`。
 * @param  {ArrayBuffer} mggBlob 读入的文件 ArrayBuffer
 * @param  {string}         name 文件名
 * @return {Promise<Uint8Array[]|null>}
 */
export async function decryptMGG(mggBlob) {
    // 初始化模组
    const qmcCrypto = await QMCCrypto()

    // 申请内存块，并文件末端数据到 WASM 的内存堆
    const detectionBuf = new Uint8Array(mggBlob.slice(-DETECTION_SIZE))
    const pDetectionBuf = qmcCrypto._malloc(detectionBuf.length)
    qmcCrypto.writeArrayToMemory(detectionBuf, pDetectionBuf)

    // 检测结果内存块
    const pDetectionResult = qmcCrypto._malloc(qmcCrypto.sizeof_qmc_detection());

    // 进行检测
    const detectOK = qmcCrypto.detectKeyEndPosition(pDetectionResult, pDetectionBuf, detectionBuf.length)

    // 提取结构体内容：
    // (pos: i32; len: i32; error: char[??])
    const position = qmcCrypto.getValue(pDetectionResult, "i32");
    const len = qmcCrypto.getValue(pDetectionResult + 4, "i32");
    const detectionError = qmcCrypto.UTF8ToString(
        pDetectionResult + qmcCrypto.offsetof_error_msg(),
        qmcCrypto.sizeof_error_msg()
    );
    const songId = qmcCrypto.UTF8ToString(
        pDetectionResult + qmcCrypto.offsetof_song_id(),
        qmcCrypto.sizeof_song_id()
    );
    console.info("Deceted song id: %s", songId);

    // 释放内存
    qmcCrypto._free(pDetectionBuf);
    qmcCrypto._free(pDetectionResult);

    if (detectOK) {
        // 计算解密后文件的大小。
        const decryptedSize = mggBlob.byteLength - DETECTION_SIZE + position

        // 提取嵌入到文件的 EKey
        const ekey = new Uint8Array(
            mggBlob.slice(decryptedSize, decryptedSize + len)
        )

        // 解码 UTF-8 数据到 string
        const decoder = new TextDecoder()
        const ekey_b64 = decoder.decode(ekey)

        // 初始化加密与缓冲区
        const hCrypto = qmcCrypto.createInstWidthEKey(ekey_b64)
        const buf = qmcCrypto._malloc(DECRYPTION_BUF_SIZE)

        const decryptedParts = []
        let offset = 0
        let bytesToDecrypt = decryptedSize
        while (bytesToDecrypt > 0) {
            // 块的大小
            const blockSize = Math.min(bytesToDecrypt, DECRYPTION_BUF_SIZE)

            //解密块
            const blockData = new Uint8Array(mggBlob.slice(offset, offset + blockSize))
            qmcCrypto.writeArrayToMemory(blockData, buf)
            qmcCrypto.decryptStream(hCrypto, buf, offset, blockSize)
            decryptedParts.push(qmcCrypto.HEAPU8.slice(buf, buf + blockSize))

            offset += blockSize
            bytesToDecrypt -= blockSize

            // 避免网页卡死，让 event loop 处理一下其它事件。
            await new Promise((resolve) => setTimeout(resolve))
        }
        qmcCrypto._free(buf)
        hCrypto.delete()
        return decryptedParts
    } else {
        console.error('文件解析错误!')
        return null
    }
}

export function fileConversion(file: File):Blob|null {
    let filename = file.name
    let fileReader = new FileReader();

    fileReader.addEventListener("load", (ev => {
        decryptMGG(ev).then((decryptedParts => {
            if (!decryptedParts) return;
            const [newFileName, mimeType] = fileDetection(decryptedParts, filename)
            const blob = new Blob(decryptedParts, {
                type: mimeType
            })
            return blob;
        }))
    }))

    return null
}

const getMagic = (u8) => {
    return u8.slice(0, 4).reduce((result, byte, position) => {
        return result | (byte << (position * 8));
    }, 0);
}
