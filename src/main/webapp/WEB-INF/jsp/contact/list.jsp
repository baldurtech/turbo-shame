<%@ page import="java.util.List, com.baldurtech.turbo.shame.Contact" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<Contact> contactList = (List<Contact>) request.getAttribute("contactList");
%>
<html>
  <head>
    <title>Contact List</title>
  </head>
  <body>
    <h1>All Contacts</h1>
    <table border="1">
      <tr>
        <td>Name</td>
        <td>phone</td>
      </tr>

      <c:forEach var="contact" items="${contactList}">
      <tr>
        <td><a href="show?id=${contact.id}">${contact.name}</a></td>
        <td>${contact.mobile} ${contact.vpmn}</td>
      </tr>
      </c:forEach>

    </table>
    <a href="create">Create</a>
  </body>
</html>
