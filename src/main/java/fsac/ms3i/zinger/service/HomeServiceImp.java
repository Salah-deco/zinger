package fsac.ms3i.zinger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import fsac.ms3i.zinger.exception.CommentCollectionException;
import fsac.ms3i.zinger.model.Comment;
import fsac.ms3i.zinger.model.Post;
import fsac.ms3i.zinger.repository.PostRepository;
import fsac.ms3i.zinger.repository.CommentRepository;
@Service
public class HomeServiceImp {
	
    @Autowired
    private PostRepository PostRepository;
    @Autowired
    private CommentRepository CommentRepository;
    
	public List<Post> getPostOfUser(@RequestParam String userId) {
		List<Post> posts = PostRepository.findByuserId(userId);
		if (posts.size() > 0) {
            return posts;
        } else {
            return new ArrayList<Post>();
        }
	}
	
    public List<Comment> getCommentOfPost(String id) throws CommentCollectionException {
    	List<Comment> comments = CommentRepository.findBypostId(id);
    	 if (comments.size() > 0) {
             return comments;
         } else {
             return new ArrayList<Comment>();
         }
}
    
}
