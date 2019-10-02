package flyway;


import org.flywaydb.core.Flyway;

import java.sql.*;

public class Application {

    public static void main(String[] args) {
        // STEP 1: Create the JDBC URL for JDND-C3 database
        String jdbcUrl = "jdbc:mysql://localhost:3306/dummy?useSSL=false&serverTimezone=UTC";
        String user = "course3";
        String password = "course3";

    try{


        // STEP 2: Setup and Run Flyway migration that creates the member table using its Java API
        // https://flywaydb.org/getstarted/firststeps/api#integrating-flyway
        // Note the above link talks about connecting to H2 database, for this exercise, MySQL is used. Adapt the code accordingly.

        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource(jdbcUrl, user,password).load();

        // Start the migration
        flyway.migrate();


        // STEP 3: Obtain a connection to the JDND-C3 database
        try(Connection conn = DriverManager.getConnection(jdbcUrl,user,password)){
            System.out.println("Connected to " + conn.getMetaData().getDatabaseProductName());
            String insertSql = "insert into PERSON (ID, NAME) values (?,?)";

            //STEP 2 : Create Statement
            try(PreparedStatement ps = conn.prepareStatement(insertSql)){

                // STEP 4: Use Statement to INSERT 2 records into the member table
                // NOTE: The member table is created using Flyway by placing the migration file in src/main/resources/db/migration

                ps.setInt(1,4);
                ps.setString(2,"Sowmya");

                //Step 3: Execute a SQL query
                ps.executeUpdate();
                ps.setInt(1,5);
                ps.setString(2,"Shaarvi");
                ps.executeUpdate();


                System.out.println("Executed SQL Query");

                // STEP 5: Read ALL the rows from the member table and print them here
                Statement stm = conn.createStatement();

                ResultSet rs = stm.executeQuery("SELECT * FROM PERSON");
                while(rs.next()){

                    int id = rs.getInt("ID");
                    String name = rs.getString("NAME");


                    System.out.println(" Id " + id);
                    System.out.println(" Name " + name);
                }
            }

        }
    } catch (
    SQLException e){
        System.out.println("SQLException " + e.getMessage());
        System.out.println("SQLState " + e.getSQLState());
        System.out.println("VendorError " + e.getErrorCode());
    }

        // STEP 6: verify that all inserted rows have been printed
    }

}