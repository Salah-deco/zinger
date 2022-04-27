package fsac.ms3i.zinger.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection="Reports")
public class Report {
    @Id
    private String id;

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "postId cannot be null")
    private String postId;

    @NotNull(message = "body cannot be null")
    private String body;

    private Date reportAt;

	public Report() {
		super();
	}

	public Report(String id, @NotNull(message = "userId cannot be null") String userId,
			@NotNull(message = "postId cannot be null") String postId,
			@NotNull(message = "body cannot be null") String body, Date reportAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.postId = postId;
		this.body = body;
		this.reportAt = reportAt;
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

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getReportAt() {
		return reportAt;
	}

	public void setReportAt(Date reportAt) {
		this.reportAt = reportAt;
	}
	
	
    
}
