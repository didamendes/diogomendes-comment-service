package br.com.diogomendes.comment.service.api.exception;

import br.com.diogomendes.comment.service.domain.exception.ModerationRejectedException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;

@ResponseStatus
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            SocketTimeoutException.class,
            ConnectException.class,
            ClosedChannelException.class
    })
    public ProblemDetail handle(IOException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(GATEWAY_TIMEOUT);

        problemDetail.setTitle("Gateway Timeout");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("/errors/gateway-timeout"));

        return problemDetail;
    }

    @ExceptionHandler(ModerationRejectedException.class)
    public ProblemDetail handle(ModerationRejectedException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(BAD_REQUEST);

        problemDetail.setTitle("Bad Gateway");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("/errors/bad-gateway"));

        return problemDetail;
    }

}
