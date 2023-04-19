<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<header class="header">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
</script>
<script src="js/weather.js"></script>
	<div class="container">
		<a href="ControllerServlet?action=home"><img src="images/logo.png" height="80"></a>
		<ul class="weather-box">
			<li><span id="degree"></span><li>
			<li><p>&emsp;<p><li>
			<li><p>Weather:&nbsp;<p><li>
			<li><span id="weather"></span><li>
		</ul>	
	</div>
</header>
</html>
