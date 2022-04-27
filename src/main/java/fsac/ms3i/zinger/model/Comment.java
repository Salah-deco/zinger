package fsac.ms3i.zinger.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

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
	public Comment() {
		super();
	}
	public Comment(String id, @NotNull(message = "body of comment cannot be null") String body,
			@NotNull(message = "Auth to comment") String userId,
			@NotNull(message = "postId cannot be null") String postId, Date createdAt, Date updateAt) {
		super();
		this.id = id;
		this.body = body;
		this.userId = userId;
		this.postId = postId;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
    
    
}
