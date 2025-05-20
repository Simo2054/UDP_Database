package application;

import java.util.Scanner;

/*
 * Entry point of the application
 * 
 * Provides a command-line interface to either start the server or the client
 */

public class Main 
{
    public static void main(String[] args) 
    {
        // Inform the user that the application has started
        System.out.println("Start application...");
        System.out.println("Type 's' to start server or 'c' to start client.");

        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine(); // Read the user's choice

        try 
        {
            // Convert the input to lowercase and determine the desired mode
            switch (choice.toLowerCase()) 
            {
                // If the user chooses 's' => initialize and start the server
                case "s":
                    Server server = new Server();
                    server.start();
                    break;

                // If the user chooses 'c', initialize and start the client
                case "c":
                    // Default to 'localhost' if no address is provided as a command-line argument
                    String address = "localhost";
                    if (args.length > 0) 
                    {
                        address = args[0];
                    }
                    Client client = new Client(address);
                    client.start();
                    break;

                // If the input is not 's' or 'c', inform the user of the valid options
                default:
                    System.out.println("Invalid option. Use 's' or 'c'.");
            }
        } 
        catch (Exception e) 
        {
            // Catch and display any unexpected errors during the execution
            System.out.println("Application error: " + e);
        }
    }
}
