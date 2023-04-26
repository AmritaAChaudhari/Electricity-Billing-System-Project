package electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class database {
    Connection connection;  //sql connection package
    Statement statement;
    database(){
        try{
     connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bill_system","root","mysql@123"); //static function getconnection
            statement =connection.createStatement();
    }
        catch (Exception e){
            e.printStackTrace();
        }}

}
