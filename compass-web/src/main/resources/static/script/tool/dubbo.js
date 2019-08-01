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
        success:function (result) {
            console.info(result);
            if(result.success){
                var ipList = result.obj;
                var insertStr;
                ipList.forEach(function (v) {
                    insertStr += "<option value='" + v + "'>" + v + "</option>";
                })
                $("#ipPort").html(insertStr);
                $("#ipPort").selectpicker("refresh");
            }
            else {
                alert(result.msg);
            }
        }
    })
}