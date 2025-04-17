package br.com.diogomendes.comment.service.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModerationResponse {
    private UUID commentId;
    private Boolean approved;
    private String reason;

    public Boolean isApproved() {
        return Boolean.TRUE.equals(getApproved());
    }
}
