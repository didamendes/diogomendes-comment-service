package br.com.diogomendes.comment.service.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class CommentNotFoundException extends RuntimeException {
}
