var weatherIcon = document.getElementById("weather-icon");

var val = document.getElementById("wc").value;
switch (val) {
	case 'Clouds':
		weatherIcon.src = "images/cloudy.png"
		break;
	case 'Clear':
		weatherIcon.src = "images/clear.png";
		break;
	case 'Rain':
		weatherIcon.src = "images/rain.png"
		break;
	case 'Mist':
		weatherIcon.src = "images/mist.png"
		break;
	case 'Snow':
		weatherIcon.src = "images/snow2.png";
		break;
	case 'Haze':
		weatherIcon.src = "images/haze.png";
		break;
	case 'Partly cloudy':
		weatherIcon.src = "images/partly_cloudy.png";
		break;
	case 'Mist':
		weatherIcon.src = "images/mist.png";
		break;
	case 'Overcast':
		weatherIcon.src = "images/overcast.png";
		break;
	case 'Shallow Fog':
		weatherIcon.src = "images/shallow_fog.png";
		break;
	case 'Sunny':
		weatherIcon.src = "images/clear.png";
		break;
	default:
		weatherIcon.src = "images/neutral.png";
		break;
}
