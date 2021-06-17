package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductScreenController implements Initializable {

    @FXML
    private TableView<?> associatedPartTableView;

    @FXML
    private TableColumn<?, ?> associatedPartIdColumn;

    @FXML
    private TableColumn<?, ?> associatedPartNameColumn;

    @FXML
    private TableColumn<?, ?> associatedPartInventoryColumn;

    @FXML
    private TableColumn<?, ?> associatedPartPriceColumn;

    @FXML
    private TableView<?> partTableView;

    @FXML
    private TableColumn<?, ?> partIdColumn;

    @FXML
    private TableColumn<?, ?> partNameColumn;

    @FXML
    private TableColumn<?, ?> partInventoryColumn;

    @FXML
    private TableColumn<?, ?> partPriceColumn;

    @FXML
    private TextField partSearchText;

    @FXML
    private TextField productIdText;

    @FXML
    private TextField productNameText;

    @FXML
    private TextField productInventoryText;

    @FXML
    private TextField productPriceText;

    @FXML
    private TextField productMaxText;

    @FXML
    private TextField productMinText;

    @FXML
    void onActionAddParts(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionRemoveAssociatedParts(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

    }

    @FXML
    void onActionSearchParts(ActionEvent event) {

    }

    @FXML
    void searchKeyPressed(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
