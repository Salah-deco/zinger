package fsac.ms3i.zinger.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class HomePost implements Serializable {
    // User(userId, first_name, last_name, image) post(postId, body, , number of likes, number of comments), for admin number of spam
    // info of user
    private String userId;
    private String first_name;
    private String last_name;
    private String profile_image;

    // info of post
    private String postId;
    private String body;
    private String type;
    private String url;
    private Date createdAt;
    private int number_likes;
    private int number_comments;

    public HomePost(String userId, String first_name, String last_name, String profile_image, String postId, String body, String type, String url, Date createdAt, int number_likes, int number_comments) {
        this.userId = userId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.profile_image = profile_image;
        this.postId = postId;
        this.body = body;
        this.type = type;
        this.url = url;
        this.createdAt = createdAt;
        this.number_likes = number_likes;
        this.number_comments = number_comments;
    }

}
