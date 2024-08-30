package com.nagarro.service;
/*
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import com.nagarro.entity.Book;

@Component
public class BookService {

	// Get all the books
	public List<Book> getAllBooks() {
		// Stores the JSON data
		String data = "";
		try {
			// url of the backend file
			URL url = new URL("http://localhost:7071/books");

			// Open the HttpURLConnection with the help of url
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set the request as GET
			connection.setRequestMethod("GET");

			// Create the connection
			connection.connect();

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
			// Put data in JSONArray
			JSONArray array = (JSONArray) parser.parse(data);

			List<Book> allBooks = new LinkedList<Book>();

			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonObject = (JSONObject) array.get(i);

				// get the value from the json Object
				Long bookCode = (Long) jsonObject.get("bookCode");
				String bookName = (String) jsonObject.get("bookName");
				String author = (String) jsonObject.get("author");
				String dataAddedOn = (String) jsonObject.get("dataAddedOn");

				// Create the Book object
				Book book = new Book();
				book.setBookCode(bookCode);
				book.setBookName(bookName);
				book.setAuthor(author);
				book.setDataAddedOn(dataAddedOn);

				// Save the all books data
				allBooks.add(book);
			}
			return allBooks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Save the Book Details
	public void saveBook(Book book) {

		String json = "{\"bookCode\":" + book.getBookCode() + 
						",\"bookName\":\"" + book.getBookName() +
						"\",\"author\":\"" + book.getAuthor() + 
						"\",\"dataAddedOn\":\"" + book.getDataAddedOn() + 
						"\"}";
		try {
			// Set the URL
			URL url = new URL("http://localhost:7071/books");

			// Open the HttpURLConnection with the help of url
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			// Set the request as POST
			http.setRequestMethod("POST");
			// Set setDoOutput as true to use url connection for output
			http.setDoOutput(true);
			// Set the Request properties
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/json");

			byte[] out = json.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = http.getOutputStream();
			stream.write(out);

			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			http.disconnect();

			System.out.println("Created Json:" + json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// delete the book
	public void deleteBook(long bookCode) {

		try {
			// Set the URL
			URL url = new URL("http://localhost:7071/books/" + bookCode);
			// Open the HttpURLConnection with the help of url
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set the request as DELETE
			connection.setRequestMethod("DELETE");

			// Create the connection
			connection.connect();
			
			//Get the Response Code
			int responseCode = connection.getResponseCode();
			System.out.println("Response code is : " + responseCode);
			
			if(responseCode != 204) {
				throw new RuntimeException("HttpResponseCode : " + responseCode);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.entity.Book;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private RestTemplate restTemplate;

    // Get all the books
    public List<Book> getAllBooks() {
        try {
            String url = "http://localhost:7071/books";
            ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(url, Book[].class);
            Book[] booksArray = responseEntity.getBody();
            return Arrays.asList(booksArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Save the Book Details
    public void saveBook(Book book) {
        try {
            String url = "http://localhost:7071/books";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Book> request = new HttpEntity<>(book, headers);
            restTemplate.postForEntity(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete the book
    public void deleteBook(long bookCode) {
        try {
            String url = "http://localhost:7071/books/" + bookCode;
            restTemplate.delete(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
