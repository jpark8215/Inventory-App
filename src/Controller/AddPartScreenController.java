package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartScreenController implements Initializable {

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private ToggleGroup partTypeTG;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField partIdText;

    @FXML
    private TextField partNameText;

    @FXML
    private TextField partInventoryText;

    @FXML
    private TextField partPriceText;

    @FXML
    private TextField partMaxText;

    @FXML
    private TextField partIdNameText;

    @FXML
    private TextField partMinText;

    @FXML
    private Label partIdNameLabel;

    @FXML
    void onActionCancelAddParts(ActionEvent event) {

    }

    @FXML
    void onActionInhouseRadio(ActionEvent event) {

    }

    @FXML
    void onActionOutsourcedRadio(ActionEvent event) {

    }

    @FXML
    void onActionSaveAddParts(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
