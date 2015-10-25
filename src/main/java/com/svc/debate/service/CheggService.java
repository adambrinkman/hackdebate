package com.svc.debate.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.svc.debate.model.Book;

import javax.json.Json;
import javax.json.stream.JsonParser;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Adam on 15-10-25.
 */
public class CheggService {
    OkHttpClient client = new OkHttpClient();

    public List<Book> findBook(String guess) {
        Set<String> eans = findEansFromGuess(guess);

        List<Book> books = new LinkedList();
        for (String ean : eans) {
            books.add(bookFromEan(ean));
        }

        return books;
    }

    private Set<String> findEansFromGuess (String guess) {
        Request request = new Request.Builder()
                .url("https://hackingedu.chegg.com/hacking-edu/search?q=" + guess)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("authorization", "Basic TjRzWVRrc0Z5RG9iWFcyVFZYTHg1QVZuWjE2ZXJVODU6M2VtRnlqaTRrQ3p0 YWY5aA==")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "65e57573-0572-a34c-f427-bfae33efdf6e")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return getEansFromJson(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Set<String> getEansFromJson(String json) {
        boolean saveVal = false;
        Set<String> eans = new HashSet<String>();

        JsonParser parser = Json.createParser(new StringReader(json));
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    saveVal = parser.getString().equals("ean");
                    break;
                case VALUE_STRING:
                    if (saveVal) {
                        eans.add(parser.getString());
                        saveVal = false;
                    }
                    break;
            }
        }
        return eans;
    }


    private Book bookFromEan(String ean) {
        Request request = new Request.Builder()
                .url("https://hackingedu.chegg.com/hacking-edu/catalog/priced/byEan/" + ean)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Basic TjRzWVRrc0Z5RG9iWFcyVFZYTHg1QVZuWjE2ZXJVODU6M2VtRnlqaTRrQ3p0 YWY5aA==")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "36cc9a08-6e83-78aa-7373-2e9ccecb6a0c")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return jsonToBook(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Book jsonToBook (String json) {
        boolean saveVal = false;
        String keyVal = "";
        Book book = new Book();

        JsonParser parser = Json.createParser(new StringReader(json));
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    saveVal = (parser.getString().equals("title") ||
                            parser.getString().equals("primaryAuthor") ||
                            parser.getString().equals("isbn"));
                    keyVal = parser.getString();
                    break;
                case VALUE_STRING:
                    if (saveVal) {
                        if (keyVal.equals("title")) {
                            book.setTitle(parser.getString());
                            saveVal = false;
                        } if (keyVal.equals("primaryAuthor")) {
                            book.setAuthor(parser.getString());
                            saveVal = false;
                        } if (keyVal.equals("isbn")) {
                            book.setISBN(parser.getString());
                            saveVal = false;
                        }
                    }
                    break;
            }
        }
        return book;
    }

}

