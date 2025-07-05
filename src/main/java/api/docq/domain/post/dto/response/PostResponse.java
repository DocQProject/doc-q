package api.docq.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {

    private final String title;

    private final String content;

    private final String name;

    private final Integer viewCount;

    @Builder
    public PostResponse(String title, String content, String name, Integer viewCount) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.viewCount = viewCount;
    }
}
