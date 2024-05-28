package com.labpbo.uts_harry;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class cancelHandler
{
    static void actCancel(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
