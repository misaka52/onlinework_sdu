<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="include/header.jsp"%>
<%@include file="include/teacher/top.jsp"%>

<div id="layout-body" class="container" style="margin:70px 50px;">
    <div class="row">

        <h3>Message</h3>
        <hr />
        <h4 class="text-center text-muted">${msg}</h4>
        <p class="text-center">
            <a href="javascript:history.go(-1)" class="btn btn-primary">
                <span class="glyphicon glyphicon-arrow-left"></span>
                Go Back
            </a>
        </p>
    </div>
</div>

<%@include file="include/footer.jsp"%>

