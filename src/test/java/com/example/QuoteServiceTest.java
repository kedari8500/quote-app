package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuoteServiceTest {

    @Test
    void returnsAQuote() {
        QuoteService s = new QuoteService();
        assertFalse(s.quoteOfTheDay(1).isBlank());
    }

    @Test
    void quotesRepeatAfterTheLastOne() {
        QuoteService s = new QuoteService();
        assertEquals(s.quoteOfTheDay(0), s.quoteOfTheDay(s.count()));
    }

    @Test
    void negativeDayIsRejected() {
        QuoteService s = new QuoteService();
        assertThrows(IllegalArgumentException.class, () -> s.quoteOfTheDay(-1));
    }
}
