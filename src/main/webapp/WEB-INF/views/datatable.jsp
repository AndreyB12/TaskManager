<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" pageEncoding="UTF-8"%>

<html>
<c:if test="${view.currentPage==1}">
    <button id="link_first" disabled>First<<</button>
    <button id="link_prev" disabled>Previous<<</button>
</c:if>
<c:if test="${view.currentPage>1}">
    <button id="link_first">First<<</button>
    <button id="link_prev">Previous<<</button>
</c:if>
<a>Page ${view.currentPage} of ${view.pages}</a>
<c:if test="${view.currentPage<view.pages}">
    <button id="link_next">>>Next</button>
    <button id="link_last">>>Last</button>
</c:if>
<c:if test="${view.currentPage==view.pages}">
    <button id="link_next" disabled>>>Next</button>
    <button id="link_last" disabled>>>Last</button>
</c:if>

<table class="tg">
    <tr>
        <th width="60">ID</th>
        <th width="250">ToDo</th>
        <th width="80">Status</th>
        <th width="80"></th>
        <th width="80"></th>
    </tr>
    <c:forEach items="${listToDo}" var="toDoTask">
        <tr>
            <td align="center"><input id="id_${toDoTask.id}"
                                      readonly="true"
                                      size="8"
                                      value="${toDoTask.id}"
                                      style="border: none"
                                      align="center"/>
            </td>
            <td><input id="text_${toDoTask.id}" value="${toDoTask.text}" style="border: none;width:100%"/></td>
            <td><select id="status_${toDoTask.id}" style="border: none">
                <c:forEach items="${listStatus}" var="status">
                    <c:choose>
                        <c:when test="${status.id == toDoTask.status}">
                            <option value="${status.id}"
                                    selected="selected">${status.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${status.id}">${status.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select></td>
            <td align="center">
                <button id="${toDoTask.id}" class="editbtn">Edit Task</button>
            </td>
            <td align="center">
                <button id="remove" value="remove/${toDoTask.id}">Delete</button>
            </td>
        </tr>
    </c:forEach>

    <!--   //Last row for adding new task-->
    <tr>
        <td></td>
        <td><input id="newtext" style="border: none;width:100%" placeholder="new ToDo text here..."/></td>
        <td><select id="newstatus" style="border: none;width:100%" placeholder="New">
            <c:forEach items="${listStatus}" var="status">
                <option value="${status.id}">${status.name}</option>
            </c:forEach>
        </select></td>
        <td></td>
        <td align="center">
            <button id="addnewtask">Add Task</button>
        </td>
    </tr>
</table>

<input id="firstId" hidden value="${view.firstId}"/>
<input id="lastId" hidden value="${view.lastId}"/>
</html>