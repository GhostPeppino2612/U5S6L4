package giuseppeacquaviva.U5S6L4.payloads;

import lombok.Getter;

@Getter
public class NewBlogPost {
    private long authorId;
    private String category;
    private String content;
    private double readingTime;
    private String title;
}
