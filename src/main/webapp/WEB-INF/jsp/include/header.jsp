<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet"/>
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <script src="js/laydate/laydate.js"></script>
    <link href="css/laydate/need/laydate.css" rel="stylesheet"/>
    <link href="css/laydate/skins/default/laydate.css" rel="stylesheet" id="LayDateSkin"/>
    <title>Onlinework</title>
</head>
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
</script>
<body>