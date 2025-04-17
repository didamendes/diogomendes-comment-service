package br.com.diogomendes.comment.service.api.client;

import br.com.diogomendes.comment.service.api.model.ModerationRequest;
import br.com.diogomendes.comment.service.api.model.ModerationResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface ModerationClient {
    @PostExchange("/api/moderate")
    ModerationResponse moderateComment(@RequestBody ModerationRequest request);
}
