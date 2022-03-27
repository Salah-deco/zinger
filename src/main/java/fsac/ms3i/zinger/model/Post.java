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
import java.util.Map;

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
    private Map<String, Object> reactions;
}
