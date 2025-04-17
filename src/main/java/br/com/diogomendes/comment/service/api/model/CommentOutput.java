package br.com.diogomendes.comment.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentOutput {
    private UUID id;
    private String text;
    private String author;
    private OffsetDateTime createdAt;
}
