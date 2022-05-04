package fsac.ms3i.zinger.controller;


import fsac.ms3i.zinger.Model.Post;
import fsac.ms3i.zinger.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
public class PostController {

    @Autowired
    private PostRepository PostRepo;
    @GetMapping("/Posts")
    public ResponseEntity<?> getPosts() {
        List<Post> po = PostRepo.findAll();

        return new ResponseEntity<>(po, po.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @GetMapping("/Postby/{id}")
    public ResponseEntity<?> getPostsbyId(@PathVariable String id ) {
        Optional<Post> po = PostRepo.findById(id);
        if(po.isPresent()){
            return new ResponseEntity<>(po, HttpStatus.OK);
        }
        else{

            return new ResponseEntity<>(po,HttpStatus.NOT_FOUND );}
    }
    @PostMapping("/Posts")
    public ResponseEntity<?> postComment(@RequestBody Post po){
        try {


            po.setCreatedAt(new Date(System.currentTimeMillis()));
            PostRepo.save(po);
            return new ResponseEntity<Post>(po , HttpStatus.OK );
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }
    @PutMapping("/Post/{id}")
    public ResponseEntity<?> Update(@PathVariable("id") String id, @RequestBody Post po ) {

        Optional<Post> PostOpt =PostRepo.findById(id);
        if (PostOpt.isPresent()){
            Post postSave = PostOpt.get();
            postSave.setBody(po.getBody()!=null ? po.getBody() :postSave.getBody());
            postSave.setUpdatedAt(new Date(System.currentTimeMillis()));
            PostRepo.save(postSave);
            return new ResponseEntity<>(postSave, HttpStatus.OK );
        }
        else
            return new ResponseEntity<>("Not found " + id ,HttpStatus.NOT_FOUND);

    }


}
