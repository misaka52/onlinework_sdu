<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/1 0001
  Time: 下午 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<div class="homePageDiv" style="margin:70px 50px;">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>教师</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${scs}" var="sc">
            <tr>
                <td>${sc.cid}</td>
                <td>${sc.course.name}</td>
                <td>${sc.teacher.name}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>