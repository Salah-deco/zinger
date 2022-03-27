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
@Document(collection="Reports")
public class Report {
    @Id
    private String Id;

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "postId cannot be null")
    private String postId;

    @NotNull(message = "body cannot be null")
    private String body;

    private Date reportAt;
}
