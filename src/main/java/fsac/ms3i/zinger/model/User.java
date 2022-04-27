package fsac.ms3i.zinger.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Document(collection="users")
public class User {
    @Id
    private String id;

    @NotNull(message = "first name cannot be null")
    private String first_name;

    @NotNull(message = "last name cannot be null")
    private String last_name;

    @NotNull(message = "email cannot be null")
    @Indexed(unique = true)
    private String email;

    @NotNull(message = "passowrd cannot be null")
    private String password;
    private String bio;
    private Date createdAt;
    private boolean isBlocked;
    private boolean isAdmin;
    List<String> idsPosts;
    List<String> idsComments;
    
    
    private String photos;
    
    
    
	public User() {
		super();
	}



	public User(String id, @NotNull(message = "first name cannot be null") String first_name,
			@NotNull(message = "last name cannot be null") String last_name,
			@NotNull(message = "email cannot be null") String email,
			@NotNull(message = "passowrd cannot be null") String password, String bio, Date createdAt,
			boolean isBlocked, boolean isAdmin, List<String> idsPosts, List<String> idsComments, String photos) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.bio = bio;
		this.createdAt = createdAt;
		this.isBlocked = isBlocked;
		this.isAdmin = isAdmin;
		this.idsPosts = idsPosts;
		this.idsComments = idsComments;
		this.photos = photos;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getFirst_name() {
		return first_name;
	}



	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	public String getLast_name() {
		return last_name;
	}



	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getBio() {
		return bio;
	}



	public void setBio(String bio) {
		this.bio = bio;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public boolean isBlocked() {
		return isBlocked;
	}



	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public List<String> getIdsPosts() {
		return idsPosts;
	}



	public void setIdsPosts(List<String> idsPosts) {
		this.idsPosts = idsPosts;
	}



	public List<String> getIdsComments() {
		return idsComments;
	}



	public void setIdsComments(List<String> idsComments) {
		this.idsComments = idsComments;
	}



	public String getPhotos() {
		return photos;
	}



	public void setPhotos(String photos) {
		this.photos = photos;
	}



	
    
}
