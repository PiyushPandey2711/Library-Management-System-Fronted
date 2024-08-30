package com.nagarro.service;
/*
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class LoginService {

	public boolean validateUser(String username, String password) {
		String data = "";
		try {
			//Set the URL
			URL url = new URL("http://localhost:7071/user/" + username);

			// Open the HttpURLConnection with the help of url
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set the request as GET
			connection.setRequestMethod("GET");
			// Create the connection
			connection.connect();
			System.out.println(connection);
			int responseCode = connection.getResponseCode();
			System.out.println("Response code is : " + responseCode);
			
			// check whether the response code is 200 or not
			if (responseCode == 200) {
				Scanner scan = new Scanner(url.openStream());
				// Iterate and store the JSON data in the books
				while (scan.hasNext()) {
					data += scan.nextLine();
				}

				scan.close();
			} else {
				// Throw Runtime Exception
				throw new RuntimeException("HttpResonseCode : " + responseCode);
			}

			// JSONParser reads the data and convert it into key-value pair
			JSONParser parser = new JSONParser();
			// parse data in JSONObject
			JSONObject jsonObject = (JSONObject) parser.parse(data);
			
			String password2 = (String) jsonObject.get("password");
			if(password2.equals(password)) {
				return true;
			}
			//Disconnect the HttpURLConnection stream
			connection.disconnect();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class LoginService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean validateUser(String username, String password) {
        try {
            // Make the HTTP GET request using RestTemplate
            String url = "http://localhost:7071/user/" + username;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // Check response status
            if (response.getStatusCode().is2xxSuccessful()) {
                String data = response.getBody();

                // JSONParser reads the data and converts it into key-value pair
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(data);

                // Check if the password matches
                String password2 = (String) jsonObject.get("password");
                return password2 != null && password2.equals(password);
            } else {
                // Handle other HTTP status codes if needed
                System.out.println("Error: Received status code " + response.getStatusCode());
                return false;
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Handle specific HTTP errors (like 404, 500, etc.)
            System.out.println("HTTP Error: " + e.getStatusCode() + " - " + e.getStatusText());
            return false;
        } catch (Exception e) {
            // Handle general exceptions
            e.printStackTrace();
            return false;
        }
    }
}



