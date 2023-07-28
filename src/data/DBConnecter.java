package data;
import java.sql.Driver;
import java.sql.Connection;

public class DBConnecter {
    private static DBConnecter dbConn = new DBConnecter();
    private DBConnecter(){}

    private Connection conn;

    public static DBConnecter getInstance(){
        return dbConn;
    }

    public void connect(){
        try{
            
        } catch(Exception e){

        }

    }

    public void close(){

    }
}
