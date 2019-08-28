// 解析ip的请求
function resolveIp() {
    var zkAddress = $("#zkAddress").val();
    var interfaceName = $("#interfaceName").val();
    var url = "/dubbo/resolveIp";
    $.ajax({
        url: url,
        method: "get",
        data: {
            zkAddress: zkAddress,
            interfaceName: interfaceName
        },
        success: function (result) {
            console.info(result);
            if (result.success) {
                var ipList = result.obj;
                var insertStr;
                ipList.forEach(function (v) {
                    insertStr += "<option value='" + v + "'>" + v + "</option>";
                })
                $("#ipPort").html(insertStr);
                $("#ipPort").selectpicker("refresh");
            } else {
                alert(result.msg);
            }
        }
    })
}

// 解析方法的请求
function resolveMethod() {
    // 配置请求信息
    var ipPort = $("#ipPort").val();
    var interfaceName = $("#interfaceName").val();
    var url = "/dubbo/resolveMethod";
    $.ajax({
        url: url,
        method: "get",
        data: {
            ipPort: ipPort,
            interfaceName: interfaceName
        },
        success: function (result) {
            if (result.success) {
                var methodList = result.obj;
                var insertStr;
                methodList.forEach(function (v) {
                    insertStr += "<option value='" + v + "'>" + v + "</option>";
                })
                $("#methodName").html(insertStr);
                $("#methodName").selectpicker("refresh");
            } else {
                alert(result.msg);
            }
        }
    })
}

// 调用接口
function invoke() {
    var ip = $("#ipPort").val();
    var interfaceName = $("#interfaceName").val();
    var interfaceMethod = $("#methodName").val();
    // 获取json组件中的数据
    var param = paramJsonEditor.get();
    var encoding = $("#enCode").val();
    var timeout = $("#timeOut").val();
    var url = "/dubbo/invoke";
    $.ajax({
        url: url,
        method: "post",
        data: {
            ip: ip,
            interfaceName: interfaceName,
            methodName: interfaceMethod,
            param: param,
            encoding: encoding,
            timeOut: timeout
        },
        success: function (result) {
            console.info(result);
        }

    })
}