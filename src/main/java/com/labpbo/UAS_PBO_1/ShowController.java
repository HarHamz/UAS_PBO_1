package com.labpbo.UAS_PBO_1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

public class ShowController {
    static String name;
    static String nim;
    static String email;
    static String phoneNumber;
    static String img_str;

    public Label labelName;
    public Label labelNim;
    public Label labelEmail;
    public Label labelPhoneNumber;
    public ImageView imgView;

    public void actCancel(ActionEvent actionEvent) throws IOException {
        SceneController.switchToMainScene(actionEvent);
    }

    public static void setDataView(Data selectedData){
        System.out.println(selectedData.getImgStr());
        System.out.println(selectedData.getNama());
        System.out.println(selectedData.getEmail());
        System.out.println(selectedData.getNim());
        System.out.println(selectedData.getTel());

        name = selectedData.getNama();
        nim = selectedData.getNim();
        email = selectedData.getEmail();
        phoneNumber = selectedData.getTel();
        img_str = selectedData.getImgStr();


//        System.out.println(labelName.getText());
//        String filePath = "src/main/resources/com/labpbo/UAS_PBO_1/img" + selectedData.getImgStr();
//        File imgFile = new File(filePath);
//        Image image = new Image(imgFile.toURI().toString());
//
//        imgView.setImage(image);
//        labelName.setText(selectedData.getNama());
//        labelNim.setText(selectedData.getNim());
//        labelEmail.setText(selectedData.getEmail());
//        labelPhoneNumber.setText(selectedData.getTel());

    }


    public void actKebenaran(ActionEvent actionEvent) {

        String filePath = "src/main/resources/com/labpbo/UAS_PBO_1/img/" + img_str;
        File imgFile = new File(filePath);
        Image image = new Image(imgFile.toURI().toString());

        imgView.setImage(image);
        labelName.setText(name);
        labelNim.setText(nim);
        labelEmail.setText(email);
        labelPhoneNumber.setText(phoneNumber);

    }
}
