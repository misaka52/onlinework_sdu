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
        $("#submitwork").submit(function(){
            if(!checkEmpty("file", "作业文档"))
                return false;
            return true;
        })
    })
</script>
<div class="homePageDiv" style="margin:70px 50px;">
    <div class="nav">
        <div>
            <ol class="breadcrumb">
                <li> <a href="student_work">所有作业</a></li>
                <li> ${work.course.name}</li>
            </ol>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="float:right">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
        </div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="submitwork" action="submitwork" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">提交作业</h4>
                        </div>
                        <div class="modal-body">
                            <h5>作业文档：</h5>
                            <input type="file" id="file" name="file" class="form-control"/>
                            <input type="hidden" name="wid" value="${work.id}" />
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
            <th>作业文档</th>
            <th>已提交文档</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td><a href="file/${work.filename}">${work.title}</a></td>
                <td><a href="file/${sw.filename}">${sw.filename}</a></td>
            </tr>
        </tbody>
    </table>
</div>