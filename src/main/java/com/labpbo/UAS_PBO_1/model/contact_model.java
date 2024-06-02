package com.labpbo.UAS_PBO_1.model;

import com.labpbo.UAS_PBO_1.db_config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.labpbo.UAS_PBO_1.Data;

public class contact_model {

    public static Data Register(String name,String nim ,String phone_number, String email, String img_str){

        try{
            String query = "INSERT INTO `contact`(`id`, `name`,`nim` ,`phone_number`, `email`, `img_str`) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = db_config.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setNull(1,java.sql.Types.NULL);
            statement.setString(2, name);
            statement.setString(3, nim);
            statement.setString(4, phone_number);
            statement.setString(5, email);
            statement.setString(6, img_str);

            int affectedRows = statement.executeUpdate();


            if(affectedRows >= 1){
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("Inserted record's ID: " + rs.getInt(1));
                        Data currContact = new Data(rs.getInt(1),name,nim,phone_number, email,img_str);
                        return currContact;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
