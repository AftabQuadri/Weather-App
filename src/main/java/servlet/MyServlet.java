package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String city = request.getParameter("user_entered_city");
		System.out.println(city);
		if (city != null) {
			Resource resource = new Resource(city);
			resource.fetchData();

			// if data found on server
			if (resource.getDateTimestamp() != null) {
				request.setAttribute("date", resource.getDateTimestamp());
				request.setAttribute("city", resource.getCity());
				request.setAttribute("temperature", resource.getTemperatureCelsius());
				request.setAttribute("weatherCondition", resource.getWeatherCondition());
				System.out.println(resource.getWeatherCondition());
				request.setAttribute("humidity", resource.getHumidity());
				request.setAttribute("windSpeed", resource.getWindSpeed());
//		        request.setAttribute("weatherData", sb.toString());
				// Forwarding to jsp page

				request.getRequestDispatcher("index.jsp").forward(request, response);

			} else {
				// if no data found on server
				System.out.println("Invalid city name");
				response.sendRedirect("Error.html");
			}

		}

	}

}
