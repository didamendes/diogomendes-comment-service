package br.com.diogomendes.comment.service.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String text;
    private String author;

    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();

}
