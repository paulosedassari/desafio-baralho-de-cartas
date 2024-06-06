package br.com.cartas.exception;

public class CardDeckException extends RuntimeException {

    public CardDeckException(String msg, Throwable e) {
        super(msg, e);
    }
}
