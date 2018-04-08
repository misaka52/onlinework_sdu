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
            if(!checkEmpty("title", "title"))
                return false;
            if(!checkEmpty("startTime", "startTime"))
                return false;
            if(!checkEmpty("endTime", "endTime"))
                return false;
            if(!checkEmpty("file", "文件"))
                return false;
            return true;
        })
    })
</script>
<div class="homePageDiv" style="margin:70px 50px;">
    <div class="nav">
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="addwork" action="addwork" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">Add Work</h4>
                        </div>
                        <div class="modal-body">
                            <h5>CourseName:</h5>
                            <select name="cid" class="form-control">
                                <c:forEach items="${cs}" var="c">
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </select>
                            <h5>Title:</h5>
                            <input id="title" type="text" name="title" class="form-control" />
                            <h5>Time</h5>
                            <div class="form-inline">
                                <input id="startTime" type="text" name="startTime" onfocus="laydate({istime: true, format: 'YYYY-MM-DD hh:mm'})" placeholder="YYYY-MM-DD hh:mm" class="form-control" />
                                &nbsp; ~ &nbsp;
                                <input id="endTime" type="text" name="endTime" onfocus="laydate({istime: true, format: 'YYYY-MM-DD hh:mm'})" placeholder="YYYY-MM-DD hh:mm" class="form-control" />
                            </div>
                            <h5>File:</h5>
                            <input id="file" type="file" name="file" class="form-control" />
                        </div>
                        <div class="modal-footer">
                            <%--<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>--%>
                            <input type="submit" class="btn btn-primary" value="add" />
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
            <th>课程</th>
            <th>时间</th>
            <th>状态</th>
            <th>教师</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ws}" var="w">
            <tr>
                <td><a href="workfile?wid=${w.id}">${w.title}</a></td>
                <td>${w.course.name}</td>
                <td>${w.getTime()}</td>
                <td class="${w.status}">${w.status}</td>
                <td>${w.teacher.name}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>