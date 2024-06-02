package com.labpbo.UAS_PBO_1.model;

import com.labpbo.UAS_PBO_1.db_config;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.labpbo.UAS_PBO_1.Data;

public class contact_model {
    static ArrayList<Data> tempArrItem;

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
                        return new Data(rs.getInt(1),name,nim,phone_number, email,img_str);
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

    public static ArrayList<Data> getAllItems(){
        tempArrItem = new ArrayList<Data>();

        try {
            Statement statement = db_config.conn.createStatement();
            String query = "SELECT * FROM contact";

            ResultSet result = statement.executeQuery(query);
            System.out.println(result);


            while (result.next()) {
                Data barang = new Data(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("nim"),
                        result.getString("phone_number"),
                        result.getString("email"),
                        result.getString("img_str")
                        );

                tempArrItem.add(barang);
            }

            return tempArrItem;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteData(List<Data> rowsToDelete){
        for (Data singleData: rowsToDelete) {
            try{
                String query = "DELETE FROM `contact` WHERE id = ?";
                PreparedStatement statement = db_config.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                statement.setInt(1,singleData.getId());
                int affectedRows = statement.executeUpdate();

                String filePath = "src/main/resources/com/labpbo/UAS_PBO_1/img/" + singleData.getImgStr();
                File file = new File(filePath);

                if(affectedRows >= 1 && file.delete()){
                    System.out.println("Deleted data");
                    try (ResultSet rs = statement.getGeneratedKeys()) {
                        if (rs.next()) {
                            System.out.println("Deleted record's ID: " + rs.getInt(1));
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else {
                    return;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
