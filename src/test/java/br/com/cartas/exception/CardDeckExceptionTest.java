package br.com.cartas.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardDeckExceptionTest {

    @Test
    public void testExceptionMessageIsCorrectlySet() {
        String message = "Test message";
        CardDeckException exception = new CardDeckException(message, new Throwable());
        assertEquals(message, exception.getMessage());
    }

}