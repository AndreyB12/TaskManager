<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<table class="tg">
    <tr>
        <th width="60">ID</th>
        <th width="200">ToDo</th>
        <th width="80">Status</th>
        <th width="80"></th>
        <th width="80"></th>
    </tr>
    <c:forEach items="${listToDo}" var="toDoTask">
        <tr>
            <form:form action="${addAction}" commandName="toDoTask" method="post">
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
                <td align="center"><input id="editform" type="submit"
                                          value="<spring:message text="Edit Task"/>"/>
                    <button id="testb">test</button>
                </td>
            </form:form>
            <td align="center">
                <button id="remove" value="remove/${toDoTask.id}">Delete</button>
            </td>
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

</html>