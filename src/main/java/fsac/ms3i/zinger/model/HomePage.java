package fsac.ms3i.zinger.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HomePage {
    private List<HomePost> homePost;
    private int size;
    private int number;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private boolean last;
    private boolean empty;

    public HomePage() {
        this.homePost = new ArrayList<>();
    }

    public HomePage(List<HomePost> homePost) {
        this.homePost = homePost;
    }

    public void addPost(HomePost post) {
        this.homePost.add(post);
    }
}
