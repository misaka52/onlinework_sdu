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
    <div class="nav">
        <ol class="breadcrumb">
            <li> <a href="teacher_work">所有作业</a></li>
            <li> ${w.course.name}</li>
            <li> ${w.title}</li>
        </ol>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="float:right">
            未提交
        </button>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">未提交</h4>
                    </div>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>学号</th>
                                <th>姓名</th>
                                <th>班级</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${noWorkStudents}" var="sw">
                                <tr>
                                    <td>${sw.student.sno}</td>
                                    <td>${sw.student.name}</td>
                                    <td>${sw.student.class_.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>班级</th>
                <th>作业文档</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${sws}" var="sw">
                <tr>
                    <td>${sw.student.sno}</td>
                    <td>${sw.student.name}</td>
                    <td>${sw.student.class_.name}</td>
                    <td><a href="file/${sw.filename}">${sw.filename}</a></td>
                </tr>
            </c:forEach>
        </tbody>

    </table>
</div>