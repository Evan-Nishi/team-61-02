package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnecter {
    private static DBConnecter dbConn = new DBConnecter();
    private Connection conn;
    private String url = "jdbc:sqlite:journal.db";

    private DBConnecter(){
        connect();
    }

    public static DBConnecter getInstance(){
        return dbConn;
    }
    public void create(){

    }
    public void connect(){
        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS entries (" +
                "id INTEGER PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "body TEXT NOT NULL," +
                "date TEXT NOT NULL," +
                "time TEXT NOT NULL" +
                ");";
            stmt.execute(sql);
            System.out.println("Connected to DB");

        } catch (SQLException e) {
            System.out.println("oops");
            System.out.println(e.getMessage());
        } 
    }

    public void close(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }


    public void insertEntry(String title, String body, String date, String time) {
        String sql = "INSERT INTO entries(title, body, date, time) VALUES(?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, body);
            pstmt.setString(3, date);
            pstmt.setString(4, time);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<JournalEntry> searchEntries(String qString) {
        String sql = "SELECT * FROM entries WHERE title LIKE ? OR body LIKE ?";

        ArrayList<JournalEntry> entries = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + qString + "%");
            pstmt.setString(2, "%" + qString + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                entries.add(new JournalEntry(rs.getString("title"),
                    rs.getString("body"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getInt("id")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return entries;
    }

    public void deleteEntry(String title) {
        String sql = "DELETE FROM entries WHERE title = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editEntry(int ID, JournalEntry newEntry){
        String sql = "UPDATE entries SET title = ?, body = ?, date = ?, time = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newEntry.getTitle());
            pstmt.setString(2, newEntry.getContext());
            pstmt.setString(3, newEntry.getDate());
            pstmt.setString(4, newEntry.getTime());  
            pstmt.setInt(5, ID);  

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


