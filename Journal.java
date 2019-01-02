import java.io.Serializable;
import java.util.ArrayList;

public class Journal implements Serializable {

    // All posts in journal
    private ArrayList<Post> postList;

    // Default constructor
    public Journal() {
        postList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return postList.isEmpty();
    }

    // Returns a post from the journal
    public Post getPost(String name) {
        if (name != null) {
            for (Post p : postList) {
                if (p.getName().equals(name)) {
                    return p;
                }
            }
        }
        return null;
    }

    // Adds a post to the journal
    public void addPost(Post post) {
        if (post == null) {
            System.out.println("Error: can not add post to journal");
            return;
        }
        postList.add(post);
    }

    // Removes a post from the journal
    public void removePost(String name) {
        if (postList != null && !postList.isEmpty()) {
            for (Post p : postList) {
                if (p.getName().equals(name)) {
                    postList.remove(p);
                    return;
                }
            }
        }
        System.out.println("Error, specified post does not exist");
    }

    // Print all posts in the journal
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
