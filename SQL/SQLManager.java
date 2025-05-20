package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * SQLManager handles connecting to a PostgreSQL database,
 * executing SQL queries, and returning results or update statuses
 */

public class SQLManager
{
    // JDBC URL for connecting to the PostgreSQL database.
    // Format: jdbc:<database manager>://<host>:<port>/<database_name>
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/rcldb";

    // Database login credentials
    private static final String DB_USER = "myuser"; // dummy example
    private static final String DB_PASSWORD = "mypassword"; // dummy example

    // Connection object for managing DB session
    private Connection connection; 

    // Statement object used for executing SQL queries
    private Statement statement;

    /**
     * Constructor for SQLManager
     * Loads the PostgreSQL JDBC driver and establishes a database connection
     * 
     * throws Exception if the driver is not found or connection fails
     */
    public SQLManager() throws Exception 
    {
        // Load the PostgreSQL JDBC driver
        Class.forName("org.postgresql.Driver");

        // Connect to the database using credentials
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        // Create a statement object to execute SQL queries
        statement = connection.createStatement();
    }

    // Executes a given SQL query and returns results or update information
    public String executeQuery(String query)
    {
        try 
        {
            // Executes the SQL query. Returns true if a ResultSet is returned (i.e. SELECT, INSERT, UPDATE, DELETE query)
            boolean hasResultSet = statement.execute(query);


            if (hasResultSet)
            {
                // Get the ResultSet from the query
                ResultSet rs = statement.getResultSet();

                // Use StringBuilder to construct output String
                StringBuilder sb = new StringBuilder();

                // Get the number of columns in the result
                // which is needed to print each row fully
                int columnCount = rs.getMetaData().getColumnCount();

                // Loop through each row in the result set
                while (rs.next())
                {
                    // Loop through each column in the current row
                    for (int i = 1; i <= columnCount; i++)
                    {
                        sb.append(rs.getString(i)); // Append column value

                        if (i != columnCount)
                            sb.append(" | "); // Add separator if not the last column

                    }
                    sb.append("\n"); // Add a new line after each row
                }

                // if no rows were returned, say "no results"
                // if there are rows returned, return the formatted output
                return sb.length() == 0 ? "No results." : sb.toString();
            }

            else
            {
                // if the query was not a SELECT (i.e INSERT/UPDATE/DELETE)
                // return how many rows were affected
                int updateCount = statement.getUpdateCount();
                return "Update count: " + updateCount;
            }
        }
        catch (Exception e)
        {
            // Return the error message if an exception occurs
            return "Error: " + e.getMessage();
        }
    }

    /*
     * Closes the database resources
     * Ensure statement and connection are properly released
     */
    public void close()
    {
        // close statement and connection after we are done
        try
        {
            if (statement != null)
                statement.close();
        }
        catch (Exception ignored)
        {
            // Ignored to avoid error chaining on close
        }

        try
        {
            if (connection != null)
                connection.close();
        }
        catch (Exception ignored)
        {
            // Ignored to avoid error chaining on close
        }
    }
}
