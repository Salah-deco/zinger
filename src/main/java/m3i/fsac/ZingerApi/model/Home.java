package m3i.fsac.ZingerApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Home {
    private String postId;
    private String userId;
    private String first_name;
    private String last_name;
    private String image;
    private String body;
    private String type;
    private String url;
    private String createdAt;
    private int number_comments;
    private int number_likes;
}
