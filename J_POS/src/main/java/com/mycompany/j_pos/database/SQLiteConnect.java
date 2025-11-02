package com.mycompany.j_pos.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SQLiteConnect {
    static URL resource = SQLiteConnect.class.getResource("/database/POS.db");
    
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        
        try {
            String dbPath = URLDecoder.decode(resource.getPath(), StandardCharsets.UTF_8.name());
            
            File dbFile = new File(dbPath);
            if (!dbFile.exists()) {
                System.err.println("❌ Database file does not exist at: " + dbPath);
                System.err.println("File absolute path: " + dbFile.getAbsolutePath());
                System.err.println("File exists check: " + dbFile.exists());
                throw new SQLException("Database file not found: " + dbPath);
            }
            
            String url = "jdbc:sqlite:" + dbPath.replace("\\", "/");
            
            Class.forName("org.sqlite.JDBC");
            
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(true);
            
            System.out.println("✅ Connected to SQLite at: " + dbPath);
            
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found!");
            e.printStackTrace();
            throw new SQLException("SQLite JDBC driver not found", e);
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.err.println("Error connecting to SQLite: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Error connecting to database", e);
        }
        
        return conn;
    }
}