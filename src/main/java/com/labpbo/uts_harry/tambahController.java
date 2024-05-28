package com.labpbo.uts_harry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class tambahController {

    @FXML
    private TextField nama;
    @FXML
    private TextField nim;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;

    @FXML
    void actTambah ()
    {
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
        else
        {
            Stage stage = (Stage) nama.getScene().getWindow();
            stage.close();

            mainController.addDataToTable(namaText, nimText, telText, emailText);
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

}
