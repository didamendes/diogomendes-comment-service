package br.com.diogomendes.comment.service.api.client;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;

@ResponseStatus(BAD_GATEWAY)
public class ModerationClientBadGatewayException extends RuntimeException {
}
