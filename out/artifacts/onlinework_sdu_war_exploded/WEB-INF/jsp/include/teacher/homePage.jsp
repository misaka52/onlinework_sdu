<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/1 0001
  Time: 下午 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<script>
    function confirmDialog() {
        if(confirm("将删除该课程所有相关信息，确定删除？"))
            return true;
        return false;
    }
    $(function() {
        $("#addcourse").submit(function () {
            if(!checkEmpty("name", "课程名"))
                return false;
            return true;
        })
    })
</script>
<div class="homePageDiv" style="margin:70px 50px;">
    <div class="nav" style="float:right;">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
            <span class="glyphicon glyphicon-plus"></span>
        </button>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="addcourse" action="addcourse" method="post">
                        <div class="modal-header">
                            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">添加课程</h4>
                        </div>
                        <div class="modal-body">
                            <h5>课程名：</h5>
                            <input id="name" type="text" name="name" class="form-control"/>
                        </div>
                        <div class="modal-footer">
                            <%--<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>--%>
                            <input type="submit" class="btn btn-primary" value="提交" />
                        </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>课程号</th>
                <th>课程名</th>
                <th>学生数</th>
                <th>删除</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cs}" var="c">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td><a class="cntOfStudent" href="liststudent?cid=${c.id}">${c.cntOfStudent}</a></td>
                    <td><a href="deletecourse?cid=${c.id}" onclick="return confirmDialog();"><span class="glyphicon glyphicon-trash"/></a></td>
                </tr>
            </c:forEach>
        </tbody>

    </table>
</div>