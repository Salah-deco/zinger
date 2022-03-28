package fsac.ms3i.zinger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Comments")
public class Comment {
    @Id
    private String id;

    @NotNull(message = "body of comment cannot be null")
    private String body;

    @NotNull(message = "Auth to comment")
    private String userId;

    @NotNull(message = "postId cannot be null")
    private String postId;

    private Date createdAt;
    private Date updateAt;
}
