<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/1 0001
  Time: 下午 8:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="row">
            <a class="navbar-brand" href="${contextPath}">OnlineWork</a>
            <ul class="nav navbar-nav">
                <li><a href="teacher_work">作业</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <li><a href="#"><span class="glyphicon glyphicon-user"></span>${user.name}</a></li>
                <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> 退出</a></li>
            </ul>
        </div>
    </div>
</nav>