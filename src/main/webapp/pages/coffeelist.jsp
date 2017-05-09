<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <style>
      BODY { font-family: verdana, arial; font-size: 11px; color: black; margin: 10px;}      

      TABLE { font-family: verdana, arial; font-size: 12px; margin: 0px; border: 0px;}

      TH { padding: 5px 5px 5px 5px;}

      TD { padding: 2px 5px 2px 5px; height: 23px;}

      INPUT.field { height: 18px; margin: 0px; font-family: verdana, arial; font-size: 12px;}
    </style>
  </head>
  <body>
    <form method="get" action="OrderList">
      <table cellspacing="0px" cellpadding="0px" border="0px" style="border: 1px #B0B0B0 solid">
        <tr style="background-color: #C0C0C7">
          <th/>
          <th>Название</th>
          <th>Цена</th>
          <th>Количество</th>
        </tr>
        <c:forEach var="coffee" items="${allCoffee}">
          <tr style="background-color: #F0F0F0">
            <td><input type="checkbox" name="coffeeGrade_${coffee.id}"/></td>
            <td nowrap="true">${coffee.title}</td>
            <td nowrap="true">${coffee.cost} TGR</td>
            <td align="right"><input class="field" type="text" name="amountCoffeeGrade_${coffee.id}" size="5"/></td>
          </tr>
        </c:forEach>
        <tr style="background-color: #F0F0F0">
            <td colspan="4" align="right"><input type="submit" value="Заказать"/></td>
        </tr>
      </table>
      <font color="red">*</font> - каждая третья чашка бесплатно.
    </form>
  </body>
</html>
