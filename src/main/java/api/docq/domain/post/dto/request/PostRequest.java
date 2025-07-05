package api.docq.domain.post.dto.request;

import lombok.Getter;

@Getter
public class PostRequest {

    private final String title;

    private final String author;

    private final String content;

    public PostRequest(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
