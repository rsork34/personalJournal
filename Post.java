import java.io.Serializable;

public class Post implements Serializable {
    private String name;
    private String post;
    private String date;
    private String lastEditedDate;

    public Post() {
        this(null, null, null);
    }

    public Post(String name, String date, String post) {
        this.name = name;
        this.date = date;
        this.post = post;
        this.lastEditedDate = "Never";
    }

    /** SETTERS **/
    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public void setDate(String date) {
        if (date != null) {
            this.date = date;
        }
    }

    public void setLastEditedDate(String date) {
        if (date != null) {
            this.lastEditedDate = date;
        }
    }

    public void setPost(String post) {
        if (post != null) {
            this.post = post;
        }
    }

    /** GETTERS **/
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLastEditedDate() {
        return lastEditedDate;
    }

    public String getPost() {
        return post;
    }

    @Override
    public String toString() {
        String toReturn;

        // Name of post
        if (name != null) toReturn = "\nName: " + name + ".\n";
        else toReturn = "\nNo name. \n";

        // Date of post
        if (date != null) toReturn += "Date: " + date + ".\t";
        else toReturn += "No date.\t";

        // Date post was last edited
        toReturn += "Last edited: " + lastEditedDate + "\n";

        // Content of post
        if (post != null) toReturn += "Post: " + post + "\n";
        else toReturn += "No content. ";

        return toReturn;
    }
}
