function formatTime(timeStamp) {
    let date = new Date(timeStamp);
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();
    return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

/**
 * 格式化秒
 * @param  value int 总秒数
 * @return result string 格式化后的字符串
 */
function formatSeconds(value) {
    let theTime = parseInt(value);// 需要转换的时间秒
    let theTime1 = 0;// 分
    let theTime2 = 0;// 小时
    let theTime3 = 0;// 天
    if (theTime > 60) {
        theTime1 = parseInt(String(theTime / 60));
        theTime = parseInt(String(theTime % 60));
        if (theTime1 > 60) {
            theTime2 = parseInt(String(theTime1 / 60));
            theTime1 = parseInt(String(theTime1 % 60));
            if (theTime2 > 24) {
                //大于24小时
                theTime3 = parseInt(String(theTime2 / 24));
                theTime2 = parseInt(String(theTime2 % 24));
            }
        }
    }
    let result = '';
    if (theTime > 0) {
        result = "" + parseInt(String(theTime)) + "秒";
    }
    if (theTime1 > 0) {
        result = "" + parseInt(String(theTime1)) + "分" + result;
    }
    if (theTime2 > 0) {
        result = "" + parseInt(String(theTime2)) + "小时" + result;
    }
    if (theTime3 > 0) {
        result = "" + parseInt(String(theTime3)) + "天" + result;
    }
    return result;
}

function formatNumber(n) {
    n = n.toString();
    return n[1] ? n : '0' + n
}

/**
 * 数据分页
 * @param jsonList 列表
 * @param start 开始的位置
 * @param count 个数
 */
function limit(jsonList, start, count) {
    return jsonList.filter((ele, index) => {
        return index >= start && index <= start + count - 1
    })
}

/**
 * 数据精确筛选
 *  @param jsonList 列表
 * @param propertyName 属性名
 * @param value 想要的的值
 */
function query(jsonList, propertyName, value) {
    return jsonList.filter((ele) => {
        return ele[propertyName] === value
    })
}

/**
 * 数据精确筛选
 * @param jsonList 列表
 * @param propertyName 属性名
 * @param value 不要的值
 */
function remove(jsonList, propertyName, value) {
    return jsonList.filter((ele) => {
        return ele[propertyName] !== value
    })
}

/**
 * 数据模糊筛选
 * @param jsonList 列表
 * @param propertyName 属性名
 * @param value 包含的值
 */
function contain(jsonList, propertyName, value) {
    return jsonList.filter((ele) => {
        return ele[propertyName].indexOf(value) !== -1
    })
}

/**
 * 时间戳转化为年 月 日 时 分 秒
 * number: 传入时间戳
 * format：返回格式，支持自定义，但参数必须与formateArr里保持一致
 */
function formatTimeTwo(number, format) {

    let formateArr = ['Y', 'M', 'D', 'h', 'm', 's'];
    let returnArr = [];

    let date = new Date(number);
    returnArr.push(date.getFullYear());
    returnArr.push(formatNumber(date.getMonth() + 1));
    returnArr.push(formatNumber(date.getDate()));

    returnArr.push(formatNumber(date.getHours()));
    returnArr.push(formatNumber(date.getMinutes()));
    returnArr.push(formatNumber(date.getSeconds()));
    for (let i in returnArr) {
        format = format.replace(formateArr[i], returnArr[i]);
    }
    return format;
}

function ISODateStringToTimestamp(isoDateString) {
    let strings = isoDateString.split("-")
    return new Date(strings[0], strings[1] - 1, strings[2]).getTime()
}

/**
 * 已知两个时间戳
 * 计算两个时间差
 */
function diffTime(startDate, endDate) {
    //时间差的毫秒数
    let diff = endDate - startDate;
    //计算出相差天数
    let days = Math.floor(diff / (24 * 3600 * 1000));
    //计算天数后剩余的毫秒数
    let leave1 = diff % (24 * 3600 * 1000);
    //计算出小时数
    let hours = Math.floor(leave1 / (3600 * 1000));
    //计算小时数后剩余的毫秒数
    let leave2 = leave1 % (3600 * 1000);
    //计算相差分钟数
    let minutes = Math.floor(leave2 / (60 * 1000));
    //计算分钟数后剩余的毫秒数
    let leave3 = leave2 % (60 * 1000);
    //计算相差秒数
    let seconds = Math.round(leave3 / 1000);
    return {days: days, hours: hours, minutes: minutes, seconds: seconds};
}

module.exports = {
    formatSeconds: formatSeconds,
    formatTime: formatTime,
    formatTimeTwo: formatTimeTwo,
    diffTime: diffTime,
    limit: limit,
    query: query,
    remove: remove,
    contain: contain,
    ISODateStringToTimestamp: ISODateStringToTimestamp,
};