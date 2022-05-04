package fsac.ms3i.zinger.Model;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Comments")
public class Comment {
    @Id
    private String id;

    @NotNull
    private String body;

    @NotNull
    private String userId;

    @NotNull
    private String postId;

    private Date createdAt;
    private Date updateAt;
}
