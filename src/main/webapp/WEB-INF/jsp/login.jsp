<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<html>

<head>
    <title>Onlinework</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <script>
        function checkEmpty(id, name){
            var value = $("#"+id).val();
            if(value.length==0){
                alert(name+ "不能为空");
                $("#"+id)[0].focus();
                return false;
            }
            return true;
        }
        $(function(){
            <c:if test="${!empty msg}">
                $("span.errorMessage").html("${msg}");
                $(".loginErrorMessageDiv").show();
            </c:if>

            $("#loginForm").submit(function() {
                if(!checkEmpty("id", "账号"))
                    return false;
                if(!checkEmpty("password", "密码"))
                    return false;
                return true;
            })
            $("#identity2").bind("change", function(){
                //console.log(this.value);
                if(this.value == 1)
                    $("#classDiv").show();
                else
                    $("#classDiv").hide();
            })
            $("#class_").bind("change", function() {
                if(this.value == 0)
                    $("#inputClassDiv").show();
                else
                    $("#inputClassDiv").hide();
            })
            $("button.addUser").click(function(){
                if(!checkEmpty("id2", "账号"))
                    return false;
                if(!checkEmpty("name2", "姓名"))
                    return false;
                if($("#class_").val() == 0 && !checkEmpty("inputClass", "班级"))
                    return false;
                if(!checkEmpty("password2", "密码"))
                    return false;
                if(!checkEmpty("password22", "确认密码"))
                    return false;
                if($("#password2").val() != $("#password22").val()){
                    alert("两次密码输入不一致");
                    $("#password2").val("");
                    $("#password22").val("");
                    return false;
                }

                var id = $("#id2").val();
                var name = $("#name2").val();
                var identity = $("#identity2").val();
                var class_  = $("#class_").val();
                var inputClass = $("#inputClass").val();
                var password = $("#password2").val();
                var page = "register";
                //提交中可能有中文，必须使用post
                console.log(inputClass);
                $.post(
                    page,
                    {"id":id, "name":name, "identity":identity, "class_":class_, "inputClass":inputClass, "password":password},
                    function(result) {
                        if(result == "fail") {
                            $("span.errorMessage").html("此账号已存在");
                            $("div.errorMessageDiv").show();
                        }
                        else
                            location.reload();
                    }
                );
                return true;
            })
        })
    </script>
</head>
<body>
    <div class="topDiv" style="margin:0px auto;width:300px;">
        <img src="img/logoOline.png" height="100px" />
    </div>

    <div class="bodyDiv" style="margin:50px auto; width:300px;">
        <form id="loginForm" class="loginForm" action="login1" method="post">
            <div id="loginSmallDiv" class="loginSmallDiv">
                <div class="loginErrorMessageDiv" style="display: none;">
                    <div class="alert alert-danger" style="padding:8px;margin:0px;">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                        <span class="errorMessage"></span>
                    </div>
                </div>
                <div class="loginInput " >
                    <h5>账号：</h5>
                    <input id="id" class="form-control" name="id" placeholder="学号/职工号" type="text" value="${id}" />
                </div>
                <div class="loginInput " >
                    <h5>密码：</h5>
                    <input id="password" class="form-control" name="password" type="password" placeholder="密码" />
                </div>
                <div class="loginInput " >
                    <h5>身份：</h5>
                    <select name="identity" class="form-control">
                        <option value="1">学生</option>
                        <option value="2">教师</option>
                    </select>
                </div>
                <div style="margin-top:20px">
                    <input type="submit" class="btn btn-primary" value="登录"/>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                        注册
                    </button>
                </div>
            </div>
        </form>
        <br/>

            <div class="modal fade" id="myModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">注册</h4>
                        </div>
                        <div class="modal-body">
                            <input id="id2" type="text" name="id" class="form-control" placeholder="学号/职工号"/><br />
                            <input id="name2" type="text" name="name" class="form-control" placeholder="姓名"/><br />
                            <select id="identity2" name="identity" class="form-control">
                                <option value="1">学生</option>
                                <option value="2">教师</option>
                            </select><br/>
                            <div id="classDiv">
                                <select id="class_" name="class_" class="form-control">
                                    <c:forEach items="${cs}" var="c">
                                        <option value="${c.id}">${c.name}</option>
                                    </c:forEach>
                                    <option value="0">其他</option>
                                </select><br/>
                                <div id="inputClassDiv" style="display:none;">
                                    <input id="inputClass" type="text" name="inputClass" class="form-control" placeholder="班级名"/><br/>
                                </div>
                            </div>
                            <input id="password2" type="password" name="password" class="form-control" placeholder="密码"/><br />
                            <input id="password22" type="password" name="password" class="form-control" placeholder="确认密码"/><br />
                            <div class="alert alert-danger errorMessageDiv" style="display: none;margin-top:10px;padding:8px;height: 35px;" >
                                <%--<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>--%>
                                <span class="errorMessage"></span>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <%--<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>--%>
                            <button type="submit" class="btn btn-primary addUser" >提交</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div>
        </div>
</body>
</html>