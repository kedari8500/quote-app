package com.example;

import java.util.List;

public class QuoteService {

    private static final List<String> QUOTES = List.of(
        "Automate everything you do twice.",
        "Small steps every day beat big steps once.",
        "A failing test is a gift, not a problem.",
        "Ship small, ship often."
    );

    public String quoteOfTheDay(int dayNumber) {
        if (dayNumber < 0) {
            throw new IllegalArgumentException("Day must not be negative");
        }
        return QUOTES.get(dayNumber % QUOTES.size());
    }

    public int count() {
        return QUOTES.size();
    }
}
