// 初始化jsonEditor插件的方法
function initJsonEditor(elementId, mode) {
    var jsonOptions = {
        mode: mode, modes: ["code", "tree", "text"], onError: function (err) {
            alert(err.toString());
        }
    }
    var container = document.getElementById(elementId);
    var jsonEditor = new JSONEditor(container, jsonOptions);
    return jsonEditor;
}