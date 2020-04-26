var MapUtils=new Object();
MapUtils.isNotEmpty = function(map){
    if(map == undefined){
        return false;
    }
    var count = 0;
    for(var key in map){
        count++;
    }
    if(count > 0){
        return true;
    }
    return false;
}
MapUtils.isEmpty = function(map){
    return !isNotEmpty(map);
}

Array.prototype.minus = function (arr) {
    var result = new Array();
    var hash = {};
    for (var i = 0; i < arr.length; i++) {
        hash[arr[i]] = 1;
    }
    for (var j = 0; j < this.length; j++) {
        if (!hash[this[j]]) {
            hash[this[j]] = 1;
            result.push(this[j]);
        }
    }
    return result;
};

function isRepeat(arr){
    var hash = {};
    for(var i in arr) {
        if(hash[arr[i]]){
            return true;
        }
        hash[arr[i]] = true;
    }
    return false;
}

String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

function parseResponse(data) {
    try {
        return JSON.parse(data);
    } catch (error) {
    }
    return null;
}

function checkStringEmpty(str) {
    return !!(str == undefined || str.length == 0 || str.replace(/(^\s*)|(\s*$)/g, "").length == 0);

}

function checkArrayEmpty(array) {
    return !!(array == undefined || array == null || array.length == 0);

}