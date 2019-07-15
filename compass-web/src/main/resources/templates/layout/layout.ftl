<#macro compassMethod>
<html>
<head>
    <meta charset="utf-8"> <!-- 指定ie的特殊样式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>指南针</title>
    <!-- 告诉浏览器响应屏幕宽度 -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" ßname="viewport">
    <!--jquery-->
    <script src="/lib/jquery/jquery-3.1.1.min.js"></script>
    <!--bootstrap-->
    <link href="/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/lib/bootstrap/js/bootstrap.min.js"></script>
    <!--AdminLTE，布局模板-->
    <link href="/lib/adminLte-2.4.5/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="/lib/adminLte-2.4.5/Ionicons/css/ionicons.min.css" rel="stylesheet">
    <link href="/lib/adminLte-2.4.5/dist/css/AdminLTE.min.css" rel="stylesheet">
    <link href="/lib/adminLte-2.4.5/dist/css/skins/skin-blue.min.css" rel="stylesheet">
    <script src="/lib/adminLte-2.4.5/dist/js/adminlte.min.js"></script>
</head>

<#--布局-->
<body class="hold-transition skin-blue sidebar-mini">
<!--包装器-->
<div class="wrapper">
    <!-- 头部信息,也就是上面的横条 -->
    <header class="main-header">
        <a href="/index" class="logo"> <!-- Logo -->
            <span class="logo-mini"><b>指</b></span> <!-- 边栏的迷你徽标迷你50x50像素 -->
            <span class="logo-lg"><b>指南针</b></span><!-- 常规状态的徽标 --> </a>
        <nav class="navbar navbar-static-top" role="navigation"><!-- 头部导航 -->
            <!-- 头部切换按钮-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span> </a>
            <div class="navbar-custom-menu"> <!-- 右侧导航菜单 -->
                <ul class="nav navbar-nav"> <!-- 用户账户菜单 -->
                    <li class="dropdown user user-menu"> <!-- 菜单切换按钮 -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <!-- 用户的图片-->
                            <#--<img src="" class="user-image" alt="User Image">-->
                            <#--获取设置的session中的值-->
                            <span>${Session.loginUser.userName}</span>
                            <span class="hidden-xs">
                            欢迎您!
                            </span>
                        </a>
                        <ul class="dropdown-menu" style="left: 7%">
                            <li class="user-header" style="height: 30px;width: 100px ;padding: 3px; text-align: left">
                                <a style="color: white" href="/login/loginout" class="dropdown-toggle"> <i class="fa fa-sign-out"
                                                                                            title="退出"></i>
                                    <span>退出</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!--左侧的列. logo和侧边栏 -->
    <aside class="main-sidebar">
        <!-- 侧边栏:样式可以在侧边栏中找到。-->
        <section class="sidebar">
            <!-- 侧栏菜单 -->
            <ul class="sidebar-menu" data-widget="tree">
                <li class="header">HEADER</li>
                <li>
                    <#--需要加页面跳转链接-->
                    <a href=""><i class="fa fa-link"></i><span>接口管理</span></a>
                </li>
                <li>
                    <a href=""><i class="fa fa-link"></i><span>用例管理</span></a>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-pie-chart"></i> <span>工具箱</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li>
                            <a href=""><i class="fa fa-circle-o"></i>dubbo接口测试</a>
                        </li>
                        <li>
                            <a href=""><i class="fa fa-circle-o"></i>http接口测试</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </section>
    </aside>
    <!-- 包装的内容.页面的内容 -->
    <div class="content-wrapper">
        <#nested>
    </div>
    <!--页脚 -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            <strong>Copyright &copy; 2019 <a href="#">指南针</a>.</strong> All rights reserved.
        </div>
        <!-- 左侧默认内容 -->
        指南针
    </footer>
</div>


</body>

</html>
</#macro>