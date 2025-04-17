package br.com.diogomendes.comment.service.domain.exception;

public class ModerationRejectedException extends RuntimeException {
    public ModerationRejectedException(String message) {
        super(message);
    }
}
