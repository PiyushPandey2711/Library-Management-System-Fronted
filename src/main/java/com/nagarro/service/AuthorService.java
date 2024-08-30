package com.nagarro.service;
/*
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import com.nagarro.entity.Author;

@Component
public class AuthorService {

    public List<Author> getAllAuthors() {
        String data = "";
        List<Author> allAuthor = null;
        try {
            URL url = new URL("http://localhost:7071/author");
            HttpURLConnection connection = getConnection(url);
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            System.out.println("Author Response code is : " + responseCode);

            if (responseCode == 200) {
                Scanner scan = new Scanner(connection.getInputStream());
                while (scan.hasNext()) {
                    data += scan.nextLine();
                }
                scan.close();
            } else {
                throw new RuntimeException("HttpResonseCode : " + responseCode);
            }

            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(data);
            allAuthor = new LinkedList<Author>();

            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = (JSONObject) array.get(i);
                String authorName = (String) jsonObject.get("authorName");
                System.out.println(authorName);
                Author author = new Author();
                author.setAuthorName(authorName);
                allAuthor.add(author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allAuthor;
    }

    public HttpURLConnection getConnection(URL url) throws Exception {
        return (HttpURLConnection) url.openConnection();
    }
}
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.entity.Author;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Service
public class AuthorService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Author> getAllAuthors() {
        String data = "";
        List<Author> allAuthor = null;
        try {
            // Make the HTTP GET request using RestTemplate
            data = restTemplate.getForObject("http://localhost:7071/author", String.class);

            // JSONParser reads the data and converts it into key-value pair
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(data);
            allAuthor = new LinkedList<>();

            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = (JSONObject) array.get(i);
                String authorName = (String) jsonObject.get("authorName");
                System.out.println(authorName);
                Author author = new Author();
                author.setAuthorName(authorName);
                allAuthor.add(author);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allAuthor;
    }
}

