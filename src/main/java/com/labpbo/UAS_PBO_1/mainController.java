package com.labpbo.UAS_PBO_1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private TableView<Data> tableView;
    private ObservableList<Data> dataList;

    public void initialize() {

        // Ensure tableView is not null before using it
        if (tableView == null) {
            System.out.println("TableView is null!");
            tableView = new TableView<>();
            tableView.setPlaceholder(new Label("Table is empty"));

        }

        dataList = FXCollections.observableArrayList();
        tableView.setItems(dataList);

        TableColumn<Data, String> namaColumn = new TableColumn<>("Nama");
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        namaColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Data, String> nimColumn = new TableColumn<>("NIM");
        nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));
        nimColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Data, String> telColumn = new TableColumn<>("No. Telepon");
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        telColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Data, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Data, Boolean> selectedColumn = new TableColumn<>("");
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
        List<Data> rowsToRemove = new ArrayList<>();
        for (Data rowData : dataList) {
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

    public void addDataToTable(Integer id, String nama, String nim, String tel, String email, String img_str) {
        int newIndex = dataList.size() + 1;
        Data newData = new Data(id, nama, nim, tel, email, img_str);
        dataList.add(newData);
        System.out.println("adding data to list");
        tableView.refresh();
        System.out.println("refreshed");
    }

    public void showData(ActionEvent actionEvent) throws IOException {
//        pindah ke scene main
        SceneController.switchToShowScene(actionEvent);
    }
}