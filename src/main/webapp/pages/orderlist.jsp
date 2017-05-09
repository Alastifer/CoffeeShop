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
    <table cellspacing="0px" cellpadding="2px" border="0px" style="border: 1px #B0B0B0 solid">
      <tr style="background-color: #C0C0C7">
        <th colspan="2">Доставка</th>
      </tr>
      <tr style="background-color: #F0F0F0">
        <td><b>ФИО</b></td>
        <td nowrap="true"><input class="field" type="text" size="30"/></td>
      </tr>
      <tr style="background-color: #F0F0F0">
        <td><b>Адрес</b></td>
        <td nowrap="true"><input class="field" type="text" size="30"/></td>
      </tr>
      <tr style="background-color: #F0F0F0">
        <td colspan="2" align="right"><input type="button"/></td>
      </tr>
    </table>
    <br/>
    <table cellspacing="0px" cellpadding="2px" border="0px" style="border: 1px #B0B0B0 solid">
      <tr style="background-color: #C0C0C7">
        <th>Название</th>
        <th>Цена</th>
        <th>Количество</th>
        <th>Всего</th>
      </tr>

      <c:forEach var="orderElement" items="${orderElements}">
        <tr style="background-color: #F0F0F0">
          <td nowrap="true">${orderElement.coffee.title}</td>
          <td nowrap="true">${orderElement.coffee.cost} TGR</td>
          <td align="right">${orderElement.amount}</td>
          <td><font color="red">${orderElement.total}</font> TGR</td>
        </tr>
      </c:forEach>

      <tr style="background-color: #E0E0E0">
        <td colspan="3" align="right"><b>Сумма:</b></td>
        <td align="right">${totalCostOfOrderElements} TGR</td>
      </tr>
      <tr style="background-color: #F0F0F0">
        <td colspan="3" align="right"><b>Доставка:</b></td>
        <td align="right">${deliveryCost} TGR</td>
      </tr>
      <tr style="background-color: #E0E0E0">
        <td colspan="3" align="right"><b>Всего:</b></td>
        <td align="right">${costOfOrder} TGR</td>
      </tr>
    </table>
    <br/>
    <a href="/CoffeeShop/Index"><link>Вернуться в магазин</link></a>
  </body>
</html>
