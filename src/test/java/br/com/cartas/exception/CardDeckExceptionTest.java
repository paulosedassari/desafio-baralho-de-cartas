package br.com.cartas.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardDeckExceptionTest {

    @Test
    public void test_exception_message_is_correctly_set() {
        String message = "Test message";
        CardDeckException exception = new CardDeckException(message, new Throwable());
        assertEquals(message, exception.getMessage());
    }

}