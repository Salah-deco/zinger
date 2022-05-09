package m3i.fsac.ZingerApi.controller;

import m3i.fsac.ZingerApi.model.Home;
import m3i.fsac.ZingerApi.model.Post;
import m3i.fsac.ZingerApi.repository.PostRepository;
import m3i.fsac.ZingerApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home/post")
    public List<Home> getPosts() {
        List<Post> posts = postRepository.findAll();
        List<Home> listHome = new ArrayList<>();
        for (Post post : posts) {
            Home home =  new Home(
                    post.getId(),
                    post.getUserId(),
                    userRepository.findById(post.getId()).get().getFirst_name(),
                    userRepository.findById(post.getId()).get().getLast_name(),
                    userRepository.findById(post.getId()).get().getImage(),
                    post.getBody(),
                    post.getType(),
                    post.getUrl(),
                    post.getCreatedAt().toString(),
                    post.getIdComments().size(),
                    post.getReactions().size()
            );
            listHome.add(home);
        }
        return listHome;
    }
}
