package com.api.framework.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtility {
    private  Connection connection= null;
    private Statement statement=null;
    private ResultSet resultSet=null;
    private String dbURL, dbUserName, dbPassword=null;
    public ResultSet executeQuery(String query){
        try{
            dbURL=commonMethods.readProperties("config/Config.properties").getProperty("dbURL");
            dbUserName=commonMethods.readProperties("config/Config.properties").getProperty("dbUserName");
            dbPassword=commonMethods.readProperties("config/Config.properties").getProperty("dbPassword");
            Class.forName(commonMethods.readProperties("config/Config.properties").getProperty("dbDriver"));
            connection= DriverManager.getConnection(dbURL,dbUserName, dbPassword);
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
