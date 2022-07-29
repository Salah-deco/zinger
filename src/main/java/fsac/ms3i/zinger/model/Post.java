package fsac.ms3i.zinger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Posts")
public class Post {
    @Id
    private String id;

    @NotNull(message = "need auth to create a post")
    private String userId;

    @NotNull(message = "type cannot be null")
    private String type;

    private String body;
    private String url;
    private Date createdAt;
    private Date updatedAt;
    private boolean isBlocked;
    private List<String> comments;
    private List<String> reports;
    private List<String> likes; // id of user

    public void addReport(String reportId) {
        this.reports.add(reportId);
    }

    public boolean addLike(String userId) {
        Boolean liked = this.likes.stream().anyMatch(str -> str.equals(userId));
        if (!liked) {
            System.out.println("from Post Model addLike call with userId = " + userId);
            this.likes.add(userId);
        }
        return !liked;
    }
    public boolean removeLike(String userId) {
        return this.likes.remove(userId);
    }
}
