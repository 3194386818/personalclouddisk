import SparkMD5 from 'spark-md5'
import {splitSize} from '../configuration'
import {ref} from "vue";

/**
 * 这个对象是用来准备和后台对接的对象
 */
type blobObj = {
    index: number,
    blog: Blob,
    md5: string
}

/**
 * 这个是用来添加文件切割后的数组
 */
const blobs: blobObj[] = []


const aSplitSize = analyzeByteSymbol(splitSize)

/**
 * 解析带符号的字节大小
 * @returns {null|number}
 * @param byteSize 把带符号的字节类型传入
 */
function analyzeByteSymbol(byteSize) {
    // 判断是否是一个字节类型
    if (!(/^\d+[KMGT]?$/g.test(byteSize))) return null;

    // 获取最后一个符号
    let symbol = byteSize.charAt(byteSize.length - 1);
    // 获取大小
    let num: number = byteSize.match(/^\d+/)[0]
    // 根据符号，转换大小
    switch (symbol) {
        case 'K':
            num *= 1024;
            break;
        case 'M':
            num *= (1024 * 1024);
            break;
        case 'G':
            num *= (1024 * 1024 * 1024);
            break;
    }

    return num;
}

/**
 * 处理文件为对象数组的功能
 * @param blob
 */
export function handleBlog(blob: Blob) {
    if (aSplitSize == null) return null
    const fileSize = blob.size


    if (fileSize > aSplitSize) {
        simpleHandle(blob, 0)
    } else {
        splitHandle(blob, aSplitSize)
    }
}

/**
 * 简单的处理
 * @param blob
 * @param index 一般放入0就可以了，这个是为了兼容大文件的切割处理
 */
export function simpleHandle(blob: Blob, index: number) {
    const fileReader = new FileReader()
    fileReader.readAsArrayBuffer(blob)
    fileReader.onload = ev => {
        let spark = new SparkMD5.ArrayBuffer()
        spark.append(<ArrayBuffer>ev.target.result)
        const md5 = spark.end()

        const obj = <blobObj>{
            index: index,
            blog: blob,
            md5: md5
        }
        console.log(`处理完毕，md5 ${md5}`)
        blobs.push(obj)
    }
}

/**
 * 处理大文件，通过切割
 * @param blob 大文件
 * @param byteSize 这个是要切割后的文件大小
 */
export function splitHandle(blob: Blob, byteSize: number) {
    let start = 0;
    let index = 0;
    let count = 0;


    while (1) {
        start = index
        index = blob.size > index + byteSize ? index + byteSize : blob.size

        const splitBlob = blob.slice(start, index)
        simpleHandle(splitBlob, count)

        count++
        if (index == blob.size) break
    }
}

/**
 * 返回处理后的数据
 */
export function getBlobs() {
    return blobs
}





