package com.labpbo.uts_harry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class mainController {

    @FXML
    private TableView<data> tableView;
    private ObservableList<data> dataList;

    public void initialize() {
        dataList = FXCollections.observableArrayList();
        tableView.setItems(dataList);

        TableColumn<data, String> namaColumn = new TableColumn<>("Nama");
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        namaColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<data, String> nimColumn = new TableColumn<>("NIM");
        nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));
        nimColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<data, String> telColumn = new TableColumn<>("No. Telepon");
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        telColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<data, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<data, Boolean> selectedColumn = new TableColumn<>("");
        selectedColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        selectedColumn.setCellFactory(col -> new CheckBoxTableCell<>());

        namaColumn.setPrefWidth(200);
        nimColumn.setPrefWidth(100);
        telColumn.setPrefWidth(150);
        emailColumn.setPrefWidth(200);
        selectedColumn.setPrefWidth(50);

        tableView.getColumns().addAll(namaColumn, nimColumn, telColumn, emailColumn, selectedColumn);
    }

    @FXML
    void tambahData(ActionEvent event) throws IOException
    {
        pindah("tambah.fxml", "Tambah Data");
    }

    @FXML
    void hapusData(ActionEvent event) {
        List<data> rowsToRemove = new ArrayList<>();
        for (data rowData : dataList) {
            if (rowData.isSelected()) {
                rowsToRemove.add(rowData);
            }
        }
        dataList.removeAll(rowsToRemove);
    }

    void pindah(String address, String title) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(address));
        Parent root = fxmlLoader.load();

        Stage addStage = new Stage();
        addStage.setTitle(title);
        addStage.getIcons().add(new Image(Objects.requireNonNull(main.class.getResourceAsStream("icon.png"))));
        addStage.setScene(new Scene(root));

        tambahController controller = fxmlLoader.getController();
        controller.setMainController(this);

        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.show();
    }

    public void addDataToTable(String nama, String nim, String tel, String email) {
        int newIndex = dataList.size() + 1;
        data newData = new data(newIndex, nama, nim, tel, email);
        dataList.add(newData);
        System.out.println("adding data to list");
        tableView.refresh();
        System.out.println("refreshed");
    }
}