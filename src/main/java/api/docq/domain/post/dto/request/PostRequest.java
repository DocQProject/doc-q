package api.docq.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostRequest {

    @NotBlank(message = "제목을 입력하세요")
    private final String title;

    private final String author;

    @NotBlank(message = "내용을 입력하세요")
    private final String content;

    public PostRequest(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
