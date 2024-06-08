package com.labpbo.UAS_PBO_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import com.labpbo.UAS_PBO_1.model.contact_model;
import com.labpbo.UAS_PBO_1.Data;

public class tambahController {

    @FXML
    private Label fileName;
    @FXML
    private TextField nama;
    @FXML
    private TextField nim;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    FileChooser fileChooser = new FileChooser();
    File selectedFile;

    @FXML
    void actTambah () throws IOException {
        String namaText = nama.getText();
        String nimText = nim.getText();
        String telText = tel.getText();
        String emailText = email.getText();

        // cek error field kosong
        if(checkIfNull(namaText) || checkIfNull(nimText) || checkIfNull(telText) || checkIfNull(emailText))
        {
            showAlert("Pastikan semua field terisi!", "Kau pun paten kali ga isi semua.");
        }
        // cek error nim bukan integer
        else if(!nimText.matches("\\d{9}"))
        {
            showAlert("Pastikan NIM berupa angka sepanjang 9 digit!", "Contoh: 221401042");
        }
        // cek error notelp bukan integer
        else if(!telText.matches("\\d+"))
        {
            showAlert("Pastikan No. Telepon berupa angka!", "Contoh: 082123456789");
        }
        // cek error email bukan email
        else if(!emailText.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$"))
        {
            showAlert("Pastikan Email berupa email!", "Contoh: harryhamara@iklc.or.id");
        }
//        cek error apabila gambar kosong
        else if(selectedFile == null){
            showAlert("Pastikan Photo sudah diupload!", "");
        }
        else
        {
            Stage stage = (Stage) nama.getScene().getWindow();
            stage.close();


            Path targetImgDir = Paths.get("src/main/resources/com/labpbo/UAS_PBO_1/img");

            Files.copy(selectedFile.getAbsoluteFile().toPath(), targetImgDir.resolve(selectedFile.getName()));

//            importing current contact to database while getting contact object and then store to table
            Data currContact;
            currContact = contact_model.Register(namaText, nimText,telText,emailText,selectedFile.getName());

            mainController.addDataToTable(currContact.getId(),namaText, nimText, telText, emailText, selectedFile.getName());
        }
    }

    @FXML
    void actCancel(ActionEvent event) {
        cancelHandler.actCancel(event);
    }

    boolean checkIfNull(String text)
    {
        if (text != null && !text.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error!");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private mainController mainController;

    public void setMainController(mainController mainController) {
        this.mainController = mainController;
    }

    public void openFileChooser(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

//        set inital image directory
        fileChooser.setInitialDirectory(new File("C://"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG Files", "*.jpg")
                ,new FileChooser.ExtensionFilter("PNG Files", "*.png")
                ,new FileChooser.ExtensionFilter("JPEG Files", "*.jpeg")
        );

        selectedFile = fileChooser.showOpenDialog(thisStage);
        fileName.setText(selectedFile.getName());


    }
}
