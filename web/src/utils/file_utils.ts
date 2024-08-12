import SparkMD5 from 'spark-md5'
import {splitSize} from '../configuration'

/**
 * 这个对象是用来准备和后台对接的对象
 */
export interface BlobObj {
    index: number,
    blog: Blob,
    md5: string
}

/**
 * 这个是用来添加文件切割后的数组
 */
let blobs: BlobObj[] = []

/**
 * 这个会对应着每个blob对象的处理状态
 * 状态表示:
 *      0: 处理完成
 *      1: 表示准备创建对象
 *      2: 开始封装对象
 *      -1: 处理异常
 */
let stats: number[] = []


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
    clear()
    const fileSize = blob.size


    if (fileSize > aSplitSize) {
        splitHandle(blob, aSplitSize)
    } else {
        simpleHandle(blob)
    }
}

/**
 * 简单的处理
 * @param blob
 * @param index 这个是为了兼容大文件的切割处理, 也是数据的索引
 */
export function simpleHandle(blob: Blob, index: number = 0) {
    stats[index] = 1
    const fileReader = new FileReader()
    fileReader.readAsArrayBuffer(blob)
    fileReader.onload = ev => {
        stats[index] = 2
        let spark = new SparkMD5.ArrayBuffer()
        spark.append(<ArrayBuffer>ev.target.result)
        const md5 = spark.end()

        const obj = <BlobObj>{
            index: index,
            blog: blob,
            md5: md5
        }
        blobs[index] = obj
        stats[index] = 0
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

/**
 * 获取数据的处理状态
 */
export function getStats() {
    return stats
}

/**
 * 将保存的数据清空
 */
function clear() {
    blobs = []
    stats = []
}





