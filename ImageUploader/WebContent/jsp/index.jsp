<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>Отправка файла на сервер</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-11">
            <h1 class="page-header" align="center">Image uploader</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-11">
            <div class="panel panel-primary">
                <div class="panel-body">
                    <c:if test="${requestScope.exception == true}">
                        <div class="alert alert-danger">
                            <c:forEach var="exceptionMessage" items="${requestScope.exceptionMessages}">
                                <p><strong>Warning! </strong>${exceptionMessage}</p>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.info == true}">
                        <div class="alert alert-success">
                            <c:forEach var="infoMessage" items="${requestScope.infoMessages}">
                                <p><strong>Success! </strong>${infoMessage}</p>
                            </c:forEach>
                        </div>
                    </c:if>

                    <form method="POST" action="/upload" enctype="multipart/form-data">
                        <input type="file" name="file" id="file" accept="image/jpeg, image/png" multiple/>
                        <br/>
                        <input type="submit" value="Upload" name="upload" id="upload"/>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-lg-11">
            <div class="panel panel-default">
                <div class="panel-body">
                    <c:forEach var="image" items="${requestScope.uploadedImg}">
                        <a href="<c:url value="${image}"/>"><img height="200" src="<c:url value="${image}"/>"/></a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>