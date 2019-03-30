<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="/./riojavino/css/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/./riojavino/js/effects.js"></script>
<script defer src="/./riojavino/js/buy.js"></script>
<title>Rioja Vino Basket</title>
</head>
<body>
	<img id="background" src="/./riojavino/img/shop.jpg" alt="home page wineyard">
	<div id="shop_banner">
		<h2 id="cellar">Here are your wines</h2>
		<h3 class="shop-link"><a href="/./riojavino/shop" id="s-link">Back to cellar</a></h3>
		<button id="basket" onclick='buy();'>Buy</button>
	</div>

	<div id="wines">
		<form method="post" action="/./riojavino/buyService?items=" id="post_form">
			<c:forEach items="${selected}" var="wine">
				<div class="wine_slot" style=<c:if test="${wine.value == false}"><c:out value="background-color:darkred;"/> </c:if>>
					<div class="description" >
						<span class="title"><c:out value="${wine.key.title}" /></span> 
						<span class="sku"><c:out value="${wine.key.sku}" /></span><br> 
					</div>
					<div>
						<span class="price">€<c:out value="${wine.key.price}" /></span><br>
					</div>
				</div>
			</c:forEach>
		</form>
	</div>
</body>
</html>