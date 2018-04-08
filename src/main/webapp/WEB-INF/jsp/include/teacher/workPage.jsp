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
    $(function(){
        $("#addwork").submit(function(){
            if(!checkEmpty("title", "标题"))
                return false;
            if(!checkEmpty("startTime", "开始时间"))
                return false;
            if(!checkEmpty("endTime", "结束时间"))
                return false;
            if(!checkEmpty("file", "作业文档"))
                return false;
            return true;
        })
    })
    function confirmDialog() {
        if(confirm("确认删除？"))
            return true;
        return false;
    }
</script>
<div class="homePageDiv" style="margin:70px 50px;">
    <div class="nav">
        <form action="teacher_work" method="post" class="form-inline">
            <select name="cid" class="form-control">
                <option value="0">All</option>
                <c:forEach items="${cs}" var="c">
                    <option value="${c.id}" <c:if test="${cid==c.id}">selected</c:if>>${c.name}</option>
                </c:forEach>
            </select>&nbsp;
            <button type="submit" class="btn btn-primary">
                <span class="glyphicon glyphicon-search"></span>
            </button>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="float:right">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
        </form>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="addwork" action="addwork" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">添加作业</h4>
                        </div>
                        <div class="modal-body">
                            <h5>课程名：</h5>
                            <select name="cid" class="form-control">
                                <c:forEach items="${cs}" var="c">
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </select>
                            <h5>标题：</h5>
                            <input id="title" type="text" name="title" class="form-control" />
                            <h5>时间：</h5>
                            <div class="form-inline">
                                <input id="startTime" type="text" name="startTime" onfocus="laydate({istime: true, format: 'YYYY-MM-DD hh:mm'})" placeholder="YYYY-MM-DD hh:mm" class="form-control" />
                                &nbsp; ~ &nbsp;
                                <input id="endTime" type="text" name="endTime" onfocus="laydate({istime: true, format: 'YYYY-MM-DD hh:mm'})" placeholder="YYYY-MM-DD hh:mm" class="form-control" />
                            </div>
                            <h5>作业文档：</h5>
                            <input id="file" type="file" name="file" class="form-control" />
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
            <th>标题</th>
            <th>作业文档</th>
            <th>课程</th>
            <th>时间</th>
            <th>状态</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ws}" var="w">
            <tr>
                <td><a href="workdetail?wid=${w.id}">${w.title}</a></td>
                <td><a href="file/${w.filename}">${w.filename}</a></td>
                <td>${w.course.name}</td>
                <td>${w.getTime()}</td>
                <td class="${w.status}">${w.status}</td>
                <td><a href="deletework?wid=${w.id}" onclick="return confirmDialog();"><span class="glyphicon glyphicon-trash"/></a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>