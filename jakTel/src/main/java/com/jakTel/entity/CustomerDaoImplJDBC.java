package com.jakTel.entity;


import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class CustomerDaoImplJDBC implements CustomerDao{
    static final Logger logger = Logger.getLogger(CustomerDaoImplJDBC.class);
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet res = null;

    @Override
    public void insert(Customer customer) {
        try(FileInputStream fis = new FileInputStream("src/main/resources/application.properties")){
            Properties prop = new Properties();
            prop.load(fis);
            String driverName = prop.getProperty("JDBC_DRIVER");
            String url = prop.getProperty("JDBC_URL");
            String username = prop.getProperty("JDBC_USERNAME");
            String password = prop.getProperty("JDBC_PASSWORD");
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO customer_jakTel VALUES (?,?,?,?,?,?)";

            stmt = connection.prepareStatement(query);
            stmt.setLong(1, customer.getPhoneNumber());
            stmt.setString(2, customer.getName());
            stmt.setInt(3, customer.getAge());
            stmt.setString(4, customer.getGender().toString());
            stmt.setString(5, customer.getAddress());
            stmt.setInt(6, customer.getPlanId());

            stmt.executeUpdate();
            logger.info("Record inserted with phone number = "+ customer.getPhoneNumber());

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            logger.error("Driver class not found");
        } catch (SQLException throwables) {
            logger.error("Unable to create JDBC connection");
        }


    }

    @Override
    public int remove(Long phoneNumber) {
        int result = -1;

        try(FileInputStream fis = new FileInputStream("src/main/resources/application.properties")){
            Properties prop = new Properties();
            prop.load(fis);
            String driverName = prop.getProperty("JDBC_DRIVER");
            String url = prop.getProperty("JDBC_URL");
            String username = prop.getProperty("JDBC_USERNAME");
            String password = prop.getProperty("JDBC_PASSWORD");
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM customer_jakTel WHERE phone_no = ?";

            stmt = connection.prepareStatement(query);
            stmt.setLong(1, phoneNumber);
            result = stmt.executeUpdate();

            stmt.executeUpdate();
            logger.info("Record deleted with phone number = "+ phoneNumber);

        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } catch (ClassNotFoundException e) {
            logger.error("Driver class not found");
        } catch (SQLException throwables) {
            logger.error("Unable to create JDBC connection");
        }

        return result;
    }
}
