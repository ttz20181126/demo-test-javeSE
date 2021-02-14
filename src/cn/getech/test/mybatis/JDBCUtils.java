package cn.getech.test.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static{
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/mybatis";
        username = "root";
        password = "123456";
    }

    public static Connection open(){
        try{
            Class.forName(driver);
            return (Connection) DriverManager.getConnection(url,username,password);
        }catch(Exception e){
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}