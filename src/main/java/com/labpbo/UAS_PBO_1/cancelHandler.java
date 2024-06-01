package com.labpbo.UAS_PBO_1;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class cancelHandler
{
    static void actCancel(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
