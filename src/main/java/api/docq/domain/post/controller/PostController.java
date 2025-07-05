package api.docq.domain.post.controller;

import api.docq.common.dto.AuthUser;
import api.docq.domain.post.dto.request.PostRequest;
import api.docq.domain.post.dto.response.PostResponse;
import api.docq.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public ResponseEntity<PostResponse> createPost(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody PostRequest request
    ) {
        return ResponseEntity.ok(postService.createPost(authUser, request));
    }

    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    public ResponseEntity<PostResponse> updatePost(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long postId,
            @RequestBody PostRequest request) {
        return ResponseEntity.ok(postService.updatePost(authUser, postId, request));
    }
}
