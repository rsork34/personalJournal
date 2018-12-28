public class Post {
    private String name;
    private String post;
    private String date;

    public Post() {
        this(null, null, null);
    }

    public Post(String name, String date, String post) {
        this.name = name;
        this.date = date;
        this.post = post;
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
        if (date != null) toReturn += "Date: " + date + ".\n";
        else toReturn += "No date.\n";

        // Content of post
        if (post != null) toReturn += "Post: " + post + "\n";
        else toReturn += "No content. ";

        return toReturn;
    }
}
