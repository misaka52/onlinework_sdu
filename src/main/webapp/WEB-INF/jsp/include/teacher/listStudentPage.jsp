<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<script>
    $(function() {
        $("button.addStudentButton").click(function(){
            //console.log("进入");
            var sno = $("#sno").val().trim();
            if(sno.length == 0) {
                $("span.errorMessage").html("请输入学号");
                $("div.errorMessageDiv").show();
                return false;
            }

            var cid = $("#cid").val();
            var page = "addstudent";
            $.get(
                page,
                {"cid":cid, "sno":sno},
                function(result) {
                    if("success" == result)
                        location.reload();
                    else if("repetion" == result){
                        $("span.errorMessage").html("重复加入");
                        $("div.errorMessageDiv").show();
                    }
                    else{
                        $("span.errorMessage").html("不存在该学生");
                        $("div.errorMessageDiv").show();
                    }
                }
            );
            return true;
        });

    })
    function confirmDialog() {
        if(confirm("确认删除？"))
            return true;
        return false;
    }
</script>

<div class="homePageDiv" style="margin:70px 50px;">
    <div class="nav">
        <div>
            <ol class="breadcrumb">
                <li> <a href="teacher_home">所有课程</a></li>
                <li> ${c.name}</li>
            </ol>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="float:right">
                <span class="glyphicon glyphicon-plus"></span>
            </button>
        </div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">添加学生</h4>
                    </div>
                    <div class="modal-body">
                        <h5>学号：</h5>
                        <input id="sno" type="text" name="sno" class="form-control"/>
                        <input id="cid" type="hidden" name="cid" value="${c.id}" />

                        <div class="alert alert-danger errorMessageDiv" style="display: none;margin-top:10px;padding:8px;height: 35px;" >
                            <%--<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>--%>
                            <span class="errorMessage"></span>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <%--<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>--%>
                        <button type="submit" class="btn btn-primary addStudentButton" >提交</button>
                    </div>
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
                <th>删除</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${ss}" var="s">
            <tr>
                <td>${s.sno}</td>
                <td>${s.name}</td>
                <td>${s.class_.name}</td>
                <td><a href="deletestudent?cid=${c.id}&sid=${s.id}" onclick="return confirmDialog();"><span class="glyphicon glyphicon-trash"/></a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
<script>
    function handleJumpPage(event) {
        var t = event.target.value;
        console.log(t)
        if(isNaN(t) || t < 1 || t > ${page.totalPage})
            return;
        toPage(t);
    }
    function toPage(page) {
        var start;
        if(page <= 1)
            start = 0;
        else
            start = (page-1) * ${page.count};
        window.location.href="?cid=" + ${c.id} + "&start=" + start;
    }
</script>
<div class="form-inline" style="margin:0px 50px 0px 50px;">
    <ul class="pager">
        <li class="previous <c:if test='${!page.hasPreviouse}'>disabled</c:if>">
            <a onclick="toPage(${page.currentPage}-1)">Previous</a>
        </li>
        <li>
            <input onkeypress="handleJumpPage(event)" type="text" size="3" placeholder="${page.currentPage}" class="form-control input-sm" />
            /
            ${page.totalPage}
        </li>

        <li class="next <c:if test='${!page.hasNext}'>disabled</c:if>">
            <a onclick="toPage(${page.currentPage}+1)">Next</a>
        </li>
    </ul>
</div>