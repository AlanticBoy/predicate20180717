package com.fusong.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  13:11 2018/5/1
 * @ModefiedBy:
 */
public class ConnectionUtils {

    private static Connection connection;
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/predicate";
    private static String user = "root";
    private static String pass = "fusong";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.close();
        }
        return connection;
    }

    public static ComboPooledDataSource dataSource=new ComboPooledDataSource();

    public static ComboPooledDataSource getDataSource()  {
        try {
            dataSource.setDriverClass(driverName);
            dataSource.setJdbcUrl(url);
            dataSource.setUser(user);
            dataSource.setPassword(pass);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            dataSource.close();
        }
        return dataSource;
    }
}
