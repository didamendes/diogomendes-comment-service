package br.com.diogomendes.comment.service.domain.service;

import br.com.diogomendes.comment.service.api.client.ModerationClient;
import br.com.diogomendes.comment.service.api.model.CommentInput;
import br.com.diogomendes.comment.service.api.model.CommentOutput;
import br.com.diogomendes.comment.service.api.model.ModerationRequest;
import br.com.diogomendes.comment.service.api.model.ModerationResponse;
import br.com.diogomendes.comment.service.domain.exception.CommentNotFoundException;
import br.com.diogomendes.comment.service.domain.exception.ModerationRejectedException;
import br.com.diogomendes.comment.service.domain.model.Comment;
import br.com.diogomendes.comment.service.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModerationClient moderationClient;

    public CommentOutput createComment(CommentInput input) {
        Comment comment = Comment.builder()
                .text(input.getText())
                .author(input.getAuthor())
                .build();

        ModerationRequest request = new ModerationRequest(comment.getId(), input.getText());
        ModerationResponse response = moderationClient.moderateComment(request);

        if (!response.isApproved()) {
            throw new ModerationRejectedException(response.getReason());
        }

        Comment savedComment = commentRepository.saveAndFlush(comment);
        return mapToOutput(savedComment);
    }

    public CommentOutput getCommentById(UUID id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
        return mapToOutput(comment);
    }

    public Page<CommentOutput> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(this::mapToOutput);
    }

    private CommentOutput mapToOutput(Comment comment) {
        return CommentOutput.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
