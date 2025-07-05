package api.docq.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {

    private final String title;

    private final String content;

    private final String author;

    private final Integer viewCount;

    @Builder
    public PostResponse(String title, String content, String author, Integer viewCount) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.viewCount = viewCount;
    }
}
