import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Scanner;

public class Interface {
    private Journal journal;
    private boolean exit;
    private Scanner reader;
    private final String[] VALID_INPUT_OPTIONS = {"q", "i", "a", "d", "p", "e", "s", "l"};
    private final static String FILE_PATH = "./saveFile";
    //private final String ERROR_MESSAGE = "Error, invalid input. Please try again";

    // Default constructor
    public Interface() {
        journal = new Journal();
        reader = new Scanner(System.in);
        exit = false;
    }

    // Main function of program
    // Loops indefinitely until user exits
    public void run() {
        System.out.println("Welcome to Personal Journal!\n");

        // Loop until user chooses to quit
        while (!exit) {
            printInstructions();

            // Get option choice from user
            String userInput = reader.nextLine();
            userInput = userInput.trim();
            userInput = userInput.toLowerCase();

            // Invalid input, loop
            if (!validMenuInput(userInput)) {
                System.out.println("Error, invalid input. Please try again");
                continue;
            }

            switch (userInput) {
                // User quits program
                case "q":
                    exit = true;
                    break;
                // User prints instructions
                case "i":
                    continue;
                // User deletes post
                case "d":
                    deletePost();
                    break;
                // User adds a post
                case "a":
                    addPost();
                    break;
                // User prints all journal posts
                case "p":
                    journal.printAllPosts();
                    break;
                // User edits existing post
                case "e":
                    editPost();
                    break;
                // User saves journal
                case "s":
                    saveJournal();
                    break;
                // User loads journal
                case "l":
                    loadJournal();
                    break;
            }
        }
        System.out.println("Exiting Program");
    }

    /**
     * Gets the name or post content from user
     * @param lookingFor String representing what the user is to input
     * @return String representing name of post or content of post
     */
    private String getSpecifiedInput(String lookingFor) {
        String toReturn = null;
        boolean validInput = false;

        // Get valid input from user
        // Can not be empty String or null
        while (!validInput) {
            System.out.println("Please enter " + lookingFor + " of post here: ");
            toReturn = reader.nextLine();

            // Invalid input error
            if (toReturn == null || toReturn.isEmpty()) {
                System.out.println("Error, invalid input");
                continue;
            }

            validInput = true;
        }
        return toReturn;
    }

    /**
     * Edits an existing post, updates last date post was edited
     */
    private void editPost() {
        String name = null;
        String post;

        boolean validInput = false;

        // Get name of post to edit
        while (!validInput) {
            name = getSpecifiedInput("name");

            // Post name already exists in journal
            if (journal.getPost(name) == null) {
                System.out.println("Error, post does not exit");
                continue;
            }
            validInput = true;
        }

        // Print out post before editing
        System.out.println("Post before editing:");
        System.out.println(journal.getPost(name));

        // Get the content of the new post
        post = getSpecifiedInput("new post content");

        // Find post in journal and edit content
        journal.getPost(name).setPost(post);
        journal.getPost(name).setLastEditedDate(new Date().toString());
        System.out.println(name + " has been edited.\n");
    }

    /**
     * Adds a post to the journal
     */
    private void addPost() {
        String name = null;
        String post;

        boolean validInput = false;

        /* Get name of post */
        while (!validInput) {
            name = getSpecifiedInput("name");

            // Post name already exists in journal
            if (journal.getPost(name) != null) {
                System.out.println("Error, post with name already exits");
                continue;
            }
            validInput = true;
        }

        // Get the content of the post
        post = getSpecifiedInput("the content");

        // Create new post with user supplied information
        journal.addPost(new Post(name, new Date().toString(), post));
        System.out.println(name + " has been added to journal.\n");
    }

    /**
     * Deletes a post specified by name that the user inputs
     */
    private void deletePost() {
        // Get name input from user
        String name = getSpecifiedInput("name");

        // Post not found in journal, return
        if (journal.getPost(name) == null) {
            System.out.println("Error, specified post does not exist");
            return;
        }

        // Post is found, remove it
        journal.removePost(name);
        System.out.println(name + " has been removed.\n");
    }

    /**
     * Prints the menu instructions for user options
     */
    private void printInstructions() {
        System.out.println("/****************************/");
        System.out.println("Press q to quit the program");
        System.out.println("Press i to print instructions");
        System.out.println("Press a to add a post to journal");
        System.out.println("Press d to delete a post from the journal");
        System.out.println("Press e to edit an existing journal post");
        System.out.println("Press p to list journal postings");
        System.out.println("Press s to save journal");
        System.out.println("Press l to load journal");
        System.out.println("/****************************/");
    }

    /**
     * Determines if user menu input is a valid option
     */
    /**
     * Determines if user input is valid
     * @param input String of user input
     * @return boolean where true is valid input and false is invalid
     */
    private boolean validMenuInput(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        // Check if user input is in the list of valid options
        for (String s : VALID_INPUT_OPTIONS) {
            if (input.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Uses ObjectOutputStream to load journal object from file
     */
    private void saveJournal() {
        // Save journal to file
        try {
            ObjectOutputStream outObject = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            outObject.writeObject(journal);
            outObject.close();
            System.out.println("Journal has been saved successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Uses ObjectInputStream to load journal object from file
     */
    private void loadJournal() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            setJournal((Journal) (objectInputStream.readObject()));
            objectInputStream.close();
            System.out.println("Journal has been loaded successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setJournal(Journal journal) {
        if (journal != null ) {
            this.journal = journal;
        }
    }
}
