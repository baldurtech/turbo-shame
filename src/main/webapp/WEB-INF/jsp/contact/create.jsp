<%@ page import="java.util.List, com.baldurtech.turbo.shame.Contact" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Contact contact = (Contact) request.getAttribute("contact");
String message = (String) request.getAttribute("flash.message");
if(null == message) message = "";
%>
<html>
  <head>
    <title>Create contact</title>
  </head>
  <body>
    <h1>Create contact</h1>
    <div><%=  message %></div>
    <form method="POST">
      <label>Name:
        <input type="text" name="name" value="${contact.name}"/>
      </label><br/>
      <label>Mobile:
        <input type="text" name="mobile" value="${contact.mobile}"/>
      </label><br/>
      <label>Vpmn:
        <input type="text" name="vpmn" value="${contact.vpmn}"/>
      </label><br/>
      <label>Email:
        <input type="text" name="email" value="${contact.email}"/>
      </label><br/>
      <label>Home Addr:
        <input type="text" name="homeAddress" value="${contact.homeAddress}"/>
      </label><br/>
      <label>Office Addr:
        <input type="text" name="officeAddress" value="${contact.officeAddress}"/>
      </label><br/>
      <label>Memo:
        <input type="text" name="memo" value="${contact.memo}"/>
      </label><br/>
      <label>Job:
        <input type="text" name="job" value="${contact.job}"/>
      </label><br/>
      <label>Job Level:
        <input type="text" name="jobLevel" value="${contact.jobLevel}"/>
      </label><br/>
      <input type="submit" value="Create"/><br/>
    </form>
  </body>
</html>
