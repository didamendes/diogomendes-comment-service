package br.com.diogomendes.comment.service.api.controller;

import br.com.diogomendes.comment.service.api.model.CommentInput;
import br.com.diogomendes.comment.service.api.model.CommentOutput;
import br.com.diogomendes.comment.service.domain.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public CommentOutput createComment(@RequestBody CommentInput input) {
        return service.createComment(input);
    }

    @GetMapping("/{id}")
    public CommentOutput getCommentById(@PathVariable String id) {
        return service.getCommentById(UUID.fromString(id));
    }

    @GetMapping
    public Page<CommentOutput> getAllComments(Pageable pageable) {
        return service.getAllComments(pageable);
    }

}
