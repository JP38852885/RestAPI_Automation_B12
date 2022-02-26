package com.api.framework.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

public class commonMethods {
static JSONObject jsonObject= new JSONObject();
static  JSONArray jsonArray=new JSONArray();
    public static Properties readProperties(String fileName) {
        FileInputStream fileInputStream = null;
        Properties properties = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
    public static JSONArray covertIntoJsonArray(ResultSet resultSet) throws SQLException {
        while(resultSet.next()){
            int columns = resultSet.getMetaData().getColumnCount();

            for(int i=0; i<columns; i++){
                jsonObject.put(resultSet.getMetaData().getColumnLabel(i+1).toLowerCase(), resultSet.getObject(i));
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }
}
