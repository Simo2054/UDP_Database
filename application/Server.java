package application;

import SQL.SQLManager;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * The Server class implements a UDP server that listens for incoming SQL queries,
 * executes them using SQLManager, and sends back the result to the client
 */

public class Server 
{
    private static final int port = 50000; // Port the server listens on
    private DatagramSocket serverSocket; // UDP socket for communication
    private SQLManager dbManager; // Manages SQL execution

    // Constructs the server by initializing the UDP socket and SQL manager
    public Server() throws Exception 
    {
        serverSocket = new DatagramSocket(port); // Bind socket to the specified port
        dbManager = new SQLManager(); // Initialize the database manager
    }

    /*
     * Starts the server to listen for incoming queries indefinitely
     * 
     * Each received query is executed and the response is sent back to the client
     */
    public void start() 
    {
        System.out.println("Server started. Listening for queries...");

        try 
        {
            // Buffer for receiving packets
            DatagramPacket prcv = new DatagramPacket(new byte[4096], 0, 4096);

            while (true) 
            {
                serverSocket.receive(prcv); // Receive an incoming UDP packet

                // Extract and trim the SQL query from the received packet
                String query = new String(prcv.getData(), 0, prcv.getLength()).trim();
                System.out.println("Received query: " + query);

                // Execute the SQL query using the SQL manager
                String response = dbManager.executeQuery(query);

                // Send the response back to the client
                byte[] buf = response.getBytes();
                InetAddress to = prcv.getAddress();
                int toPort = prcv.getPort();
                DatagramPacket psend = new DatagramPacket(buf, 0, buf.length, to, toPort);

                // Send the response packet
                serverSocket.send(psend);
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Server error: " + e);
        } 
        finally 
        {
            shutdown(); // shut down to release resources on termination
        }
    }

    // Shuts down the server by closing the socket and database manager
    public void shutdown() 
    {
        try 
        { 
            if(serverSocket != null) 
                serverSocket.close(); // Close the UDP socket
        } 
        catch (Exception ignored) 
        {
            // Exception during socket close is ignored
        }
        try 
        { 
            if(dbManager != null) 
                dbManager.close(); // Close the database manager
        } 
        catch(Exception ignored) 
        {
            // Exception during DB manager close is ignored
        }
    }
}
