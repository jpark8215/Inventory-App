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

public class MainScreenController implements Initializable{

    @FXML
    private TextField partSearchText;

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
    private TextField productSearchText;

    @FXML
    private TableView<?> productTableView;

    @FXML
    private TableColumn<?, ?> productIdColumn;

    @FXML
    private TableColumn<?, ?> productNameColumn;

    @FXML
    private TableColumn<?, ?> productInventoryColumn;

    @FXML
    private TableColumn<?, ?> productPriceColumn;

    @FXML
    void onActionAddParts(ActionEvent event) {

    }

    @FXML
    void onActionAddProducts(ActionEvent event) {

    }

    @FXML
    void onActionDeleteParts(ActionEvent event) {

    }

    @FXML
    void onActionDeleteProducts(ActionEvent event) {

    }

    @FXML
    void onActionExit(ActionEvent event) {

    }

    @FXML
    void onActionModifyParts(ActionEvent event) {

    }

    @FXML
    void onActionModifyProducts(ActionEvent event) {

    }

    @FXML
    void onActionSearchParts(ActionEvent event) {

    }

    @FXML
    void onActionSearchProducts(ActionEvent event) {

    }

    @FXML
    void partSearchTextKeyPressed(KeyEvent event) {

    }

    @FXML
    void productSearchTextKeyPressed(KeyEvent event) {

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

