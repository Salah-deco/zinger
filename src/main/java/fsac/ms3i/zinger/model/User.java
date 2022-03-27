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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Users")
public class User {
    @Id
    private String id;

    @NotNull(message = "first name cannot be null")
    private String first_name;

    @NotNull(message = "last name cannot be null")
    private String last_name;

    @NotNull(message = "email cannot be null")
    private String email;

    @NotNull(message = "passowrd cannot be null")
    private String password;
    private String bio;
    private Date createdAt;
    private boolean isBlocked;
    private boolean isAdmin;
    List<String> idsPosts;
    List<String> idsComments;
}
