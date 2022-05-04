package fsac.ms3i.zinger.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="User")
public class User {


    @Id
    private String id;

    @NotNull
    private String first_name;

    @NotNull
    private String last_name;

    @NotNull
    @Indexed(unique = true)
    private String email;

    @NotNull
    private String password;
    private String bio;
    private Date createdAt;
    private boolean isBlocked;
    private boolean isAdmin;
    List<String> idsPosts;
    private String images;
    private List<Comment> Comments;
}
