package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    private TableView<?> animalTableView;

    @FXML
    private TableColumn<?, ?> partIdCol;

    @FXML
    private TableColumn<?, ?> partNameCol;

    @FXML
    private TableColumn<?, ?> inventoryLevelCol;

    @FXML
    private TableColumn<?, ?> priceCol;

    @FXML
    private TableView<?> animalTableView1;

    @FXML
    private TableColumn<?, ?> partIdCol1;

    @FXML
    private TableColumn<?, ?> partNameCol1;

    @FXML
    private TableColumn<?, ?> inventoryLevelCol1;

    @FXML
    private TableColumn<?, ?> priceCol1;

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
    void onActionModifyParts(ActionEvent event) {

    }

    @FXML
    void onActionModifyProducts(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

