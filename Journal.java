import java.io.Serializable;
import java.util.ArrayList;

public class Journal implements Serializable {
    private ArrayList<Post> postList;

    public Journal() {
        postList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return postList.isEmpty();
    }

    public Post getPost(String name) {
        for (Post p : postList) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void addPost(Post post) {
        if (post == null) {
            System.out.println("Error: invalid post");
            return;
        }
        postList.add(post);
    }

    public void removePost(String name) {
        if (postList == null || postList.isEmpty()) {
            return;
        }

        for (Post p : postList) {
            if (p.getName().equals(name)) {
                postList.remove(p);
                return;
            }
        }
        System.out.println("Error, specified post does not exist");
    }

    public void printAllPosts() {
        if (postList.isEmpty()) {
            System.out.println("Journal has no posts");
            return;
        }

        for (Post p : postList) {
            System.out.println(p.toString());
        }
    }
}
