<%@ page import="java.util.List, com.baldurtech.turbo.shame.Contact" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Contact contact = (Contact) request.getAttribute("contact");
%>
<html>
  <head>
    <title>Contact</title>
  </head>
  <body>
    <h1>${contact}</h1>
    <table border="1">
      <tr>
        <td>Name</td>
        <td>${contact.name}</td>
      </tr>
      <tr>
        <td>Mobile</td>
        <td>${contact.mobile}</td>
      </tr>
      <tr>
        <td>Vpmn</td>
        <td>${contact.vpmn}</td>
      </tr>
      <tr>
        <td>Email</td>
        <td>${contact.email}</td>
      </tr>
      <tr>
        <td>HomeAddress</td>
        <td>${contact.homeAddress}</td>
      </tr>
      <tr>
        <td>OfficeAddress</td>
        <td>${contact.officeAddress}</td>
      </tr>
      <tr>
        <td>Memo</td>
        <td>${contact.memo}</td>
      </tr>
      <tr>
        <td>Job</td>
        <td>${contact.job}</td>
      </tr>
      <tr>
        <td>JobLevel</td>
        <td>${contact.jobLevel}</td>
      </tr>
    </table>
    <a href="list">List all contacts</a>
  </body>
</html>
