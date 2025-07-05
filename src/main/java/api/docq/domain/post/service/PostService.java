package api.docq.domain.post.service;

import api.docq.common.dto.AuthUser;
import api.docq.domain.post.dto.request.PostRequest;
import api.docq.domain.post.dto.response.PostResponse;
import api.docq.domain.post.entity.Post;
import api.docq.domain.post.repository.PostRepository;
import api.docq.domain.user.entity.User;
import api.docq.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponse createPost(AuthUser authUser, PostRequest request) {

        User user = userRepository.findById(authUser.getUserId())
                .orElseThrow(() -> new RuntimeException());

        Post post = Post.of(
                authUser.getUserId(),
                request.getTitle(),
                user.getName(),
                request.getContent()
        );

        postRepository.save(post);

        return PostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .name(user.getName())
                .viewCount(post.getViewCount())
                .build();
    }

    @Transactional
    public PostResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException());

        post.increaseViewCount();

        return PostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .name(post.getAuthor())
                .viewCount(post.getViewCount())
                .build();
    }

    @Transactional
    public PostResponse updatePost(AuthUser authUser, Long postId, PostRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException());

        if (post.getUserId().equals(authUser.getUserId())) {
            post.updatePost(request.getTitle(), request.getContent());
        } else {
            throw new RuntimeException();
        }

        return PostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .name(post.getAuthor())
                .viewCount(post.getViewCount())
                .build();
    }

    @Transactional
    public void deletePost(AuthUser authUser, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException());

        if (post.getUserId().equals(authUser.getUserId())) {
            post.deletePost();
        }
    }


}
