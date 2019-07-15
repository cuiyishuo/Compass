<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>指南针测试开发平台</title>

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link href="/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/lib/login2/font-awesome.min.css">
    <link rel="stylesheet" href="/lib/login2/form-elements.css">
    <link rel="stylesheet" href="/lib/login2/style.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Javascript -->
    <script src="/lib/jquery/jquery-3.1.1.min.js"></script>
    <script src="/lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="/lib/login2/jquery.backstretch.min.js"></script>
    <script src="/lib/login2/retina-1.1.0.min.js"></script>
    <script src="/lib/login2/scripts.js"></script>
    <script src="/lib/vue/dist/vue.js"></script>
    <script src="/lib/vue-resource/dist/vue-resource.min.js"></script>

    <!--[if lt IE 10]>
    <script src="assets/js/placeholder.js"></script>
    <![endif]-->

</head>

<body>

<!-- Top menu -->
<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">指南针接口测试MOCK平台</a>
        </div>
    </div>
</nav>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-7 text">
                    <h1><strong>指南针</strong>接口测试MOCK平台</h1>
                    <div class="description">
                        <p>
                            这是一款集成多种协议的接口测试与MOCK平台，希望该平台给与您提供帮助
                        </p>
                    </div>

                </div>
                <div class="col-sm-5 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>指南针</h3>
                            <p>欢迎使用指南针接口测试MOCK平台</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-pencil"></i>
                        </div>
                    </div>
                    <#--name属性需要和dao中的user数据结构名称一致-->
                    <div class="form-bottom">
                        <form role="form" action="/login/login" method="post" class="registration-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-first-name">用户名</label>
                                <input type="text" name="userName" placeholder="用户名..."
                                       class="form-first-name form-control" id="userName"
                                       value="<#if user??>${user.userName!}</#if>">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-last-name">密码</label>
                                <input type="text" name="passWord" placeholder="密码..."
                                       class="form-last-name form-control" id="passWord"
                                       value="<#if user??>${user.passWord!}</#if>">
                            </div>
                            <div class="form-group">
                                <span style="color: red">${msg!}</span>
                            </div>
                            <button type="submit" class="btn">登 录</button>
                            <#--onckicl方法中可以直接进行跳转-->
                            <button id="toRegisterBtn" type="button" class="btn"
                                    onclick="window.location.href='/login/registeredPage'" style="margin-left: 15px;background-color: deeppink;">注 册
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>


</html>

<script type="text/javascript">

</script>

