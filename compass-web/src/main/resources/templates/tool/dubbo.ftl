<#include "/layout/layout.ftl"/>

<@compassMethod>

    <link href="/css/toolPage.css" rel="stylesheet"/>
    <link href="/lib/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet"/>
    <script src="/lib/bootstrap-select/js/bootstrap-select.min.js"></script>
<#--引入jsoneditor的js和css文件-->
    <link href="/lib/jsoneditor/css/jsoneditor.min.css" rel="stylesheet"/>
    <script src="/lib/jsoneditor/js/jsoneditor.min.js"></script>
    <script src="/script/tool/dubbo.js"></script>
<#--引入自己封装的初始化jsonEditor的js文件-->
    <script src="/script/tool/jsonEditorUtil.js"></script>

    <div class="container-fluid">
        <div class="row row-margin" style="margin-top: 20px;">
            <div class="col-md-12">
                <div class="col-md-1">
                    <label>zookeeper地址：</label>
                </div>
                <div class="col-md-9">
                    <input id="zkAddress" type="text" class="form-control" value="${zkAddress}"/>
                </div>
            </div>
        </div>
        <div class="row row-margin">
            <div class="col-md-12">
                <div class="col-md-1">
                    <label>接口地址：</label>
                </div>
                <div class="col-md-9">
                    <input id="interfaceName" type="text" class="form-control" value="${interfaceName}"/>
                </div>
            </div>
        </div>
        <div class="row row-margin">
            <div class="col-md-12">
                <div class="col-md-1">
                    <label>ip端口：</label>
                </div>
                <div class="col-md-3">
                    <select id="ipPort" class="form-control selectpicker show-tick" style="width: 300px;"
                            data-live-search="true"></select>
                </div>
                <div class="col-md-1">
                    <input type="button" value="解析ip" class="btn btn-primary btn-margin" onclick="resolveIp()">
                </div>
            </div>
        </div>
        <div class="row row-margin">
            <div class="col-md-12">
                <div class="col-md-1">
                    <label>方法名：</label>
                </div>
                <div class="col-md-7">
                    <select id="methodName" class="form-control selectpicker show-tick" style="width: 600px;"
                            data-live-search="true"></select>
                </div>
                <div class="col-md-1">
                    <input type="button" value="解析方法" class="btn btn-primary btn-margin" onclick="resolveMethod()">
                </div>
            </div>
        </div>
        <div class="row row-margin">
            <div class="col-md-12">
                <div class="col-md-1">
                    <label>编码：</label>
                </div>
                <div class="col-md-2">
                    <select id="enCode" class="form-control" style="width: 100px;">
                        <option value="utf-8">UTF-8</option>
                        <option value="unicode">unicode</option>
                        <option value="GBK">GBK</option>
                    </select>
                </div>
                <div class="col-md-1">
                    <label>超时(单位：s)：</label>
                </div>
                <div class="col-md-1">
                    <select id="timeOut" class="form-control" style="width: 100px;">
                        <option value="1000">1</option>
                        <option value="2000">2</option>
                        <option value="3000">3</option>
                        <option value="4000">4</option>
                        <option value="5000">5</option>
                        <option value="6000">6</option>
                        <option value="7000">7</option>
                        <option value="8000">8</option>
                        <option value="9000">9</option>
                        <option value="10000">10</option>
                    </select>
                </div>
                <div class="col-md-1">
                    <input type="button" value="调用接口" class="btn btn-primary btn-margin" onclick="invoke()">
                </div>
            </div>
        </div>
        <#--json组件-->
        <div class="row row-margin">
            <div class="col-md-12">
                <div class="col-md-6">
                    <h5>传入参数：</h5>
                </div>
                <div class="col-md-6">
                    <h5>返回结果：</h5>
                </div>
            </div>
        </div>
        <div class="row row-margin">
            <div class="col-md-12">
                <div class="col-md-6">
                    <div id="jsonParam"></div>
                </div>
                <div class="col-md-6">
                    <div id="jsonResult"></div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        var paramJsonEditor;
        var resultJsonEditor;
        <#--页面初始化方法-->
        $(function () {
            $(".selectpicker").selectpicker();
            // 初始化jsonidetor
            paramJsonEditor = initJsonEditor("jsonParam", "tree");
            resultJsonEditor = initJsonEditor("jsonResult", "tree");
        })
    </script>

</@compassMethod>