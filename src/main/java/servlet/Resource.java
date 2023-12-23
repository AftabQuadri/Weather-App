package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Resource {
	private String city, dateTimestamp, weatherCondition, api_url, weather_icon_link;
	private int temperatureCelsius, humidity;
	private double windSpeed;
	private URL url;
	private HttpURLConnection con;
	private static final String base_url = "http://api.weatherstack.com/current?access_key=";
	private static final String api_key = "423d4dbd953e05ec5cfd18cae582c190";

	public String getCity() {
		return city;
	}

	public String getDateTimestamp() {
		return dateTimestamp;
	}

	public String getWeatherCondition() {
		return weatherCondition;
	}

	public int getTemperatureCelsius() {
		return temperatureCelsius;
	}

	public int getHumidity() {
		return humidity;
	}

	public String getWeatherIcon() {
		return this.weather_icon_link;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	// constructor
	public Resource(String city) {
		this.city = city;
		this.api_url = base_url + api_key + "&query=" + city;
		try {
			this.url = new URL(api_url);
			this.con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

		} catch (IOException e) {

		}

	}

	void fetchData() {
		// reading the data
		InputStream inputStream;
		try {
			inputStream = con.getInputStream();

			InputStreamReader isr = new InputStreamReader(inputStream);
			// Storing data
			StringBuilder sb = new StringBuilder();

			// input from reaader will create scanner object
			Scanner scanner = new Scanner(isr);
			while (scanner.hasNext()) {
				sb.append(scanner.nextLine());

			}
			scanner.close();

			// parsing data from json
			Gson gson = new Gson();
			JsonObject jsonobj = gson.fromJson(sb.toString(), JsonObject.class);
			System.out.println(jsonobj);
			if (jsonobj.getAsJsonObject("location") != null) {
				dateTimestamp = jsonobj.getAsJsonObject("location").get("localtime").getAsString();
				temperatureCelsius = jsonobj.getAsJsonObject("current").get("temperature").getAsInt();
				humidity = jsonobj.getAsJsonObject("current").get("humidity").getAsInt();
				windSpeed = jsonobj.getAsJsonObject("current").get("wind_speed").getAsDouble();
				String weatherConditions[] = jsonobj.getAsJsonObject("current").getAsJsonArray("weather_descriptions")
						.get(0).getAsString().split(",");
				weatherCondition = weatherConditions[0];
				city = jsonobj.getAsJsonObject("location").get("name").getAsString();
//			city=jsonobj.current.weather_descriptions[0].getAsString();
				weather_icon_link = jsonobj.getAsJsonObject("current").getAsJsonArray("weather_icons").get(0)
						.getAsString();

			} else {
				System.out.println("No data found");
			}
			// close the connection
			con.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
