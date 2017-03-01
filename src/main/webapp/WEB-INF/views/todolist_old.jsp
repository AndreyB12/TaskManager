<%--
  Created by IntelliJ IDEA.
  User: butkoav
  Date: 24.02.2017
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>

<head>
    <title>ToDoList</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>


    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        function reloadPage() {
            var s, i, sts, a
            sts = document.getElementsByClassName("fsts");
            s =  document.location.search.split('?')[0];
           // s = "./todolist?";
            for (i = 0; i < sts.length; i++) {
                a = sts[i];
                if (a.checked) {
                    s = s + "status[]=" + a.value + '&';
                }
            }

            document.location.search = s;
        }

        function loadPage() {
            $('#datatable').load('datatable' +  '#datatable');
        }

        $('toDoTask').submit(function () {
            this.submit();
            loadPage();
        });
    </script>
</head>
<c:url var="addAction" value="/todolist/add"/>
<c:url var="reloadAction" value="/todolist"/>
<c:url var="deleteAction" value="/"/>


<br/>
<h1>ToDo List</h1>

<c:forEach items="${listStatus}" var="stasus">
    <c:choose>
        <c:when test="${stasus.selected}">
            <input type="checkbox"
                   class="fsts"
                   name="${stasus.name}"
                   value="${stasus.id}"
                   checked>${stasus.name}
        </c:when>
        <c:otherwise>
            <input type="checkbox"
                   class="fsts"
                   name="${stasus.name}"
                   value="${stasus.id}">${stasus.name}
        </c:otherwise>

    </c:choose>
</c:forEach>

<button id="reloadBtn" onclick="reloadPage()">Update page</button>

<br/>
<br/>

<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="200">ToDo</th>
        <th width="80">Status</th>
        <th width="80"></th>
        <th width="80"></th>
    </tr>
    <c:forEach items="${listToDo}" var="toDoTask">
        <tr>
            <form:form action="${addAction}" commandName="toDoTask">
                <td align="center"><form:input path="id"
                                               readonly="true"
                                               size="8"
                                               value="${toDoTask.id}"
                                               style="border: none"
                                               align="center"/>
                </td>
                <td><form:input path="text" value="${toDoTask.text}" style="border: none"/></td>
                <td><form:select path="status" style="border: none">
                    <c:forEach items="${listStatus}" var="status">
                        <c:choose>
                            <c:when test="${status.id == toDoTask.status}">
                                <form:option value="${status.id}"
                                             selected="selected">${status.name}</form:option></c:when>
                            <c:otherwise>
                                <form:option value="${status.id}">${status.name}</form:option></c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select></td>
                <td align="center"><input type="submit"
                                          value="<spring:message text="Edit Task"/>"/></td>
            </form:form>
            <form:form action="remove/${toDoTask.id}" method="get">
                <td align="center">
                    <input type="submit" value="Delete"/></td>
            </form:form>
        </tr>
    </c:forEach>
    <tr>
        <form:form action="${addAction}" commandName="toDoTask">
            <td></td>
            <td><form:input path="text" style="border: none" placeholder="new ToDo text here..."/></td>
            <td><form:select path="status" style="border: none">
                <c:forEach items="${listStatus}" var="status">
                    <form:option value="${status.id}">${status.name}</form:option>
                </c:forEach>
            </form:select></td>
            <td></td>
            <td align="center"><input type="submit"
                                      value="<spring:message text="Add Task"/>"/></td>
        </form:form>
    </tr>

</table>


<button onclick="loadPage()" >LoadData</button>
<div id="datatable">
</div>

</body>
</html>
