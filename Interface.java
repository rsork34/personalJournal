import java.util.Date;
import java.util.Scanner;

public class Interface {
    private Journal journal;
    private boolean exit;
    private Scanner reader;
    private final String[] VALID_INPUT_OPTIONS = {"q", "i", "a", "d", "p"};
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
            String userInput = reader.nextLine();
            userInput = userInput.trim();

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
                // USer prints instructions
                case "i":
                    printInstructions();
                    break;
                case "d":
                    deletePost();
                    break;
                case "a":
                    addPost();
                    break;
                case "p":
                    journal.printAllPosts();
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
     * Adds a post to the journal. Takes in name of post and date.
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
        System.out.println(name + " has been added to journal.");
    }

    /**
     * Deletes a post specified by name that the user inputs
     */
    private void deletePost() {
        String name = getSpecifiedInput("name");

        // Post not found in journal, return
        if (journal.getPost(name) == null) {
            System.out.println("Error, specified post does not exist");
            return;
        }

        // Post is found, remove it
        journal.removePost(name);
        System.out.println(name + " has been removed.");
    }

    /**
     * Prints the menu instructions for user choices
     */
    private void printInstructions() {
        System.out.println("/****************************/");
        System.out.println("Press q to quit the program");
        System.out.println("Press i to print instructions");
        System.out.println("Press a to add a post to journal");
        System.out.println("Press d to delete a post from the journal");
        System.out.println("Press p to list journal postings");
        System.out.println("/****************************/");
    }

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
}
