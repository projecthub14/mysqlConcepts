package sqlBasics;

import java.sql.*;

public class JDBCPrimer {

    public static void main(String[] args) {
        // write your code here

        try{
            //STEP 1: Obtain a connection
            //The method establishes connection to database
            //This method requires database URL , which varies depending on database

            //localhost : server
            //course3 : database name
            //based on type of database mysql appropriate driver manager is selected

            //create user in SQL workbench with script

            //try with resources block -> its
            String jdbcUrl = "jdbc:mysql://localhost:3306/JDNDC3?useSSL=false&serverTimezone=UTC";
            String user = "course3";
            String password = "course3";

            try(Connection conn = DriverManager.getConnection(jdbcUrl,user,password)){
                System.out.println("Connected to " + conn.getMetaData().getDatabaseProductName());

                //STEP 2 : Create Statement
                try(Statement stmt = conn.createStatement()){

                    //Step 3: Execute a SQL query
                    ResultSet rs = stmt.executeQuery("SELECT ORDER_ID, CUSTOMER_NAME FROM ORDERS");
                    //TIP : Use executeUpdate to run INSERT,UPDATE or DELETE queries

                    System.out.println("Executed SQL Query");

                    //STEP $: Process results
                    while(rs.next()){

                        int orderId = rs.getInt("ORDER_ID");
                        String courseName = rs.getString("CUSTOMER_NAME");

                        //TIP : You can also read by index of column in query
                        // int orderId = rs.getInt(1);
                        // String customerName = rs.getString(2);

                        System.out.println("Order Id " + orderId);
                        System.out.println("customer Name " + courseName);
                    }
                }

            }
        } catch (SQLException e){
            System.out.println("SQLException " + e.getMessage());
            System.out.println("SQLState " + e.getSQLState());
            System.out.println("VendorError " + e.getErrorCode());
        }
    }
}
