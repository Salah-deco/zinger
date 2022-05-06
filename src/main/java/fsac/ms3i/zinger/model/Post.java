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

    public void addReport(String reportId) {
        this.reports.add(reportId);
    }
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public List<String> getReports() {
		return reports;
	}

	public void setReports(List<String> reports) {
		this.reports = reports;
	}

	public Map<String, Object> getReactions() {
		return reactions;
	}

	public void setReactions(Map<String, Object> reactions) {
		this.reactions = reactions;
	}

	public Post(String id, @NotNull(message = "need auth to create a post") String userId,
			@NotNull(message = "type cannot be null") String type, String body, String url, Date createdAt,
			Date updatedAt, boolean isBlocked, List<String> comments, List<String> reports,
			Map<String, Object> reactions) {
		super();
		this.id = id;
		this.userId = userId;
		this.type = type;
		this.body = body;
		this.url = url;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isBlocked = isBlocked;
		this.comments = comments;
		this.reports = reports;
		this.reactions = reactions;
	}
}
