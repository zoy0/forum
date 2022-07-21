function getQueryVariable(variable)
{

    var query = window.location.search.substring(1);

    var vars = query.split("&");

    for (var i=0;i<vars.length;i++) {

        var pair = vars[i].split("=");

        if(pair[0] == variable){return pair[1];}

    }

    return(false);

}

function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate() + ' ';
    var h = date.getHours() + ':';
    var m = date.getMinutes() + ':';
    var s = date.getSeconds();
    return Y+M+D+h+m+s;
}


var Cookie = {
    set: function (key, value, exdays) {
        let exdate = new Date() // 获取时间
        exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * exdays) // 保存的天数
        // 字符串拼接cookie
        // eslint-disable-next-line camelcase
        window.document.cookie = key + '=' + value + ';path=/;expires=' + exdate.toGMTString()
    },

    get: function (key) {
        if (document.cookie.length > 0) {
            var arr = document.cookie.split('; ') // 这里显示的格式需要切割一下自己可输出看下
            for (let i = 0; i < arr.length; i++) {
                let arr2 = arr[i].split('=') // 再次切割
                // 判断查找相对应的值
                if (arr2[0] === key) {
                    return arr2[1]
                }
            }
        }
    },

    remove: function (key) {
        set(key, '', -1);
    }
};