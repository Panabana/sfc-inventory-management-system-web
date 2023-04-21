$.ajax({
	method: "GET",
	url: "http://api.openweathermap.org/data/2.5/weather?lat=55.70465850830078&lon=13.191010475158691&units=metric&APPID=a6c0d777f56389caaafa96fbc3ccdf87",
	error: ajaxWeatherReturn_Error,
	success: ajaxWeatherReturn_Success
});

function ajaxWeatherReturn_Success(result, status, xhr) {
	$("#weather").text(result.weather[0].main);
	$("#degree").text(result.main.temp.toFixed(0) + " \u2103");
} // ajaxWeatherReturn_Success

function ajaxWeatherReturn_Error(result, status, xhr) {
	alert("Error in OpenWeaterMap Ajax");
	console.log("Ajax-find weather: " + status);
} // ajaxWeatherReturn_Error