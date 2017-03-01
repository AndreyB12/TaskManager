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
            loadDataNextId(0);
        }

        function loadDataNextId(firstId) {
            var s, i, sts, a, rowsOnPage
            sts = document.getElementsByClassName("fsts");
            rowsOnPage = document.getElementById("rowsOnPg").value;
            s = "datatable?";

            for (i = 0; i < sts.length; i++) {
                a = sts[i];
                if (a.checked) {
                    s = s + "status=" + a.value + '&';
                }
            }
            s += "rowsOnPage=" + rowsOnPage;
            s += "&firstId=" + firstId;

            $('#datatable').load(s + '#datatable');
        }


        $(document).on('click', '#link_first', function (event) {
            event.preventDefault();
            loadDataNextId(0);
        });
        $(document).on('click', '#link_next', function (event) {
            event.preventDefault();
            var firstId = document.getElementById("lastId").value;
            loadDataNextId(firstId);
        });

        $(document).on('click', '#remove', function (event) {
            event.preventDefault();
            var a = this;
            var url = a.href;

            // Send the data using post
            var posting = $.post(url);
            // Put the results in a div
            posting.done(function (data) {
                loadData();
            });
        });

        $(document).on('click', '#addnewtask', function (event) {
            event.preventDefault();
            var $form = $(this),
                url = "todolist/add",
                id = "0",
                text = document.getElementById("newtext").value,
                status = document.getElementById("newstatus").value;


            // Send the data using post
            var posting = $.post(url, {'id': id, 'text': text, 'status': status});
            // Put the results in a div
            posting.done(function (data) {
                loadData();
            });
        });

        $(document).on('click', '.editbtn', function (event) {
            event.preventDefault();
            var $form = $(this),
                url = "todolist/add",
                id = this.id,
                text = document.getElementById("text_" + id).value,
                status = document.getElementById("status_" + id).value;


            // Send the data using post
            var posting = $.post(url, {'id': id, 'text': text, 'status': status});
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

<body>
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
<br/>
<a>Rows on page: </a>
<input type="number" id="rowsOnPg" value="10" style="width:10mm">
<button id="reloadBtn" onclick="loadData()">Update page</button>

<br/>
<br/>

<div id="parentdt">
    <div id="datatable">
    </div>
</div>

</body>
</html>
