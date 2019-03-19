<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<body>

	<h2>This page should not be displayed</h2>
	<div id="wines">
		<form action="">
			<c:forEach items="${wines}" var="wine">
				<div class="wine_slot">
					<div class="mark">
						<input class="wine_check" type="checkbox" value=<c:out value="${wine.sku}"/>>
					</div>
					<div class="wine_image">
						<img class="wine_icon" src="img/bottle.svg" alt="simple black bottle svg" />
					</div>
					<div class="description">
						<span class="title"><c:out value="${wine.title}" /></span> 
						<span class="sku"><c:out value="${wine.sku}" /></span><br> 
					</div>
					<div>
						<span class="price">€<c:out value="${wine.price}" /></span><br>
					</div>
				</div>
			</c:forEach>
		</form>
	</div>
</body>
</html>
