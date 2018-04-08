<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/7 0007
  Time: 上午 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
    <style>
        body{
            padding: 0;
            margin:0;
        }
        .topDiv{
            /*background-color: green;*/
            height: 80px;
        }
        .middleDiv{
            background-image: url("img/yBG.png");
            background-repeat: no-repeat;
            background-size:cover;
            /*background-color: #f29b76;*/
            position: relative;
            z-index: 1;
            height: 500px;
        }
        .mainDiv{
            background-image:url("img/yBG2.png");
            background-size:cover;
            height: 350px;
            width: 530px;
            position:absolute;
            z-index: 2;
            bottom:0;
            left:30%;
        }
        .mainDiv div{
            /*background-color:yellow;
    */		position:absolute;
            z-index: 3;
        }
        .login{
            /*padding: 50px;*/
            margin:120px 0 0 130px;
            font-size:16px;
        }
        input:focus{
            border:solid 1px #4D90FE;
            box-shadow: 3px #4D90FE;
        }
        input[type="radio"]{    /*   单选按钮*/
            width: 15px;
            height: 15px;
        }
        input[type="button"]{/*		按钮*/
            width: 80px;
            height: 40px;
            border-radius: 3px;
            background-color: #4D90FE;
            color:white;
            font-size: 18px;
        }
        input[type="button"]:hover{ 	/*	按钮划过*/
            background-color: #6A6AFF;
        }
        .text{
            height: 25px;
            width: 200px;
            margin-top: 0;
            border-radius: 3px;
        }
        .theme-popover {
            z-index: 9999;
            position: fixed;
            top: 50%;
            left: 50%;
            width: 660px;
            height: 360px;
            margin: -180px 0 0 -330px;
            border-radius: 5px;
            border: solid 2px #666;
            background-color: #fff;
            display: none;
            box-shadow: 0 0 10px #666;
            font-size: 25px;
        }
    </style>
</head>

<body>
<div>
    <img src="img/logoOline.png" height="100px" />
</div>
<div class="middleDiv">
    <div class="mainDiv">
        <div class="mainDivImg">
        </div>
        <div class="login">
            <font size="3">
                <form action="login1" method="post">
                    <strong>账号：</strong><input class="text" type="text" name="id" placeholder="学号/职工号"
                                               value="${id}"/><br/><br/>
                    <strong>密码：</strong><input class="text" type="password" name="password" />&nbsp;<a href="">忘记密码</a><br/><br/>
                    <strong>身份：</strong>
                    <input name="identity" type="radio" value="1" checked size="5"/><strong>学生</strong>&nbsp;&nbsp;&nbsp;
                    <input name="identity" type="radio" value="2" /><strong>教师</strong><br/><br/>
                    <input type="submit" class="loginBtn0s" value="登 录"/>&nbsp;&nbsp;&nbsp;
                    <input type="button" value="注 册" class="theme-login"/><br/><br/>
                </form>
            </font>
        </div>
    </div>
</div>
<div class="theme-popover">
    <div class="theme-poptit">
        <a href="javascript:;" title="关闭" class="close">×</a>
        <h3>请填写注册信息：</h3>
    </div>
    <div class="theme-popbod dform">
        <form class="theme-signin" name="loginform" action="login1" method="post">
            <ol>
                <li><strong>账号：</strong><input class="ipt" type="text" name="log" value="lanrenzhijia" size="20" /></li>
                <li><strong>密码：</strong><input class="ipt" type="password" name="pwd" value="***" size="20" /></li>
                <li><strong>确认密码：</strong><input class="ipt" type="password" name="pwd" value="***" size="20" /></li>
                <li><input class="btn btn-primary" type="submit" name="submit" value=" 确认注册 " /></li>
            </ol>
        </form>
    </div>
</div>
<div class="theme-popover-mask"></div>
</div>


<div id="footer"  class="footer" style="display: block;"></div>
</body>
</html>