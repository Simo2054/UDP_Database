package application;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*
 * The Client class implements a UDP client that sends SQL queries to a server
 * and prints the server's response
 * 
 * It uses DatagramSocket for communication
 */

public class Client 
{
    private static final int serverPort = 50000; // Port on which the server is listening
    private DatagramSocket clientSocket; // Socket for sending and receiving UDP packets
    private InetAddress serverAddress; // IP address of the server

    // Constructs the client with the given server address
    public Client(String address) throws Exception 
    {
        clientSocket = new DatagramSocket();
        serverAddress = InetAddress.getByName(address);
    }

    /*
     * Starts the client interaction loop.
     * Continuously accepts SQL input from the user, sends it to the server,
     * and displays the response
     */
    public void start() 
    {
        System.out.println("Connected to server at " + serverAddress.getHostAddress() + ". Enter SQL queries below.");
        
        Scanner sc = new Scanner(System.in);

        try
        {
            while (true)
            {
                System.out.print("\nSQL> ");
                String sql;
                sql = sc.nextLine(); // Read SQL input from the user
                

                if (sql.trim().isEmpty()) // Skip if input is empty
                    continue;

                sendQuery(sql); // Send the SQL query to the server
                String response = receiveResponse(); // Wait for and read the server's response
                System.out.println("Response:\n" + response);
            }
        }
        catch (Exception e)
        {
            System.out.println("Client error: " + e);
        }
        finally
        {
            sc.close(); // Close the input scanner
            shutdown(); // Shutdown the client socket
        }
    }

    // Sends the SQL query to the server via UDP
    private void sendQuery(String query) throws Exception 
    {
        byte[] buf = query.getBytes(); // Convert the query string to bytes
        DatagramPacket psend = new DatagramPacket(buf, 0, buf.length, serverAddress, serverPort);
        clientSocket.send(psend); // Send the packet to the server
    }

    // Receives the server's response via UDP
    private String receiveResponse() throws Exception 
    {
        DatagramPacket prcv = new DatagramPacket(new byte[4096], 0, 4096);
        clientSocket.receive(prcv); // Receive the response packet
        return new String(prcv.getData(), 0, prcv.getLength()).trim(); // Convert bytes to string
    }

    // Closes the client socket to release resources
    public void shutdown() 
    {
        try 
        { 
            if(clientSocket != null) 
                clientSocket.close(); 
        } 
        catch (Exception ignored) 
        {
            // Ignoring exception during shutdown
        }
    }
}