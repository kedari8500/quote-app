package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class App {

    static final QuoteService quotes = new QuoteService();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(9100), 0);

        server.createContext("/", ex -> {
            int day = LocalDate.now().getDayOfYear();
            String html = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Quote App</title></head>"
                + "<body style=\"font-family:sans-serif;max-width:480px;margin:60px auto;text-align:center\">"
                + "<h1>Quote of the Day</h1>"
                + "<p style=\"font-size:1.4em\">" + quotes.quoteOfTheDay(day) + "</p>"
                + "<small>Version 1 - deployed automatically by Jenkins</small>"
                + "</body></html>";
            send(ex, "text/html; charset=UTF-8", html);
        });

        server.createContext("/health", ex -> send(ex, "text/plain", "OK"));

        server.start();
        System.out.println("Quote app running on http://localhost:9100");
    }

    private static void send(HttpExchange ex, String type, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", type);
        ex.sendResponseHeaders(200, bytes.length);
        try (var os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }
}
