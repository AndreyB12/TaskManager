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

        $(document).ready(
            function () {
                loadData();
            });


        function loadData() {
            var s, i, sts, a
            sts = document.getElementsByClassName("fsts");
             s = "datatable?";
            for (i = 0; i < sts.length; i++) {
                a = sts[i];
                if (a.checked) {
                    s = s + "status[]=" + a.value + '&';
                }
            }
            $('#datatable').load(s + '#datatable');
        }

        $(document).on('click', '#testb', function (event) {
            event.preventDefault();
            alert("test");
        });
        $(document).on('submit', '#editform', function (event) {
            event.preventDefault();
            var $form = $(event.parent),
                id = $form.find("input[name='id']").val(),
                text = $form.find("input[name='text']").val(),
                status = $form.find("input[name='status']").val(),
                url = $form.attr("action");

            // Send the data using post
            var posting = $.post(url, {id: term, text: text, status: status});
            // Put the results in a div
            posting.done(function (data) {
                loadData();
            });
        });

        $(document).on('click', '#remove', function (event) {
            event.preventDefault();
            var $form = $(this),
                url = $form.attr("value");

            // Send the data using post
            var posting = $.post(url);
            // Put the results in a div
            posting.done(function (data) {
                loadData();
            });
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

<button id="reloadBtn" onclick="loadData()">Update page</button>

<br/>
<br/>

<div id="parentdt">
    <div id="datatable">
    </div>
</div>

</body>
</html>
