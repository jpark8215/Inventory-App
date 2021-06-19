package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductScreenController implements Initializable {

    /** A list containing parts associated with the product.*/
    private final ObservableList<Part> associatedPart = FXCollections.observableArrayList();


    private void returnToMainScreen(ActionEvent event) throws IOException {
/** Loads MainScreenController.
 * @param event Passed from parent method.
 * @throws IOException From FXMLLoader.*/
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    private boolean minValid(int min, int max) {
        /** Validates that min is greater than 0 and less than max.
         * @param min The minimum value for the part.
         * @param max The maximum value for the part.
         * @return Boolean indicating if min is valid.*/
        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid number. Minimum cannot be larger than maximum value.");
        }
        return isValid;
    }


    private boolean inventoryValid(int min, int max, int stock) {
        /** Validates that inventory level is equal too or between min and max.
         * @param min   The minimum value for the part.
         * @param max   The maximum value for the part.
         * @param stock The inventory level for the part.
         * @return Boolean indicating if inventory is valid.*/
        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            Alert alert = new Alert(Alert.AlertType.WARNING, "The inventory is less than minimum or larger than maximum value.");
        }
        return isValid;
    }



    @FXML
    private TableView<Part> associatedPartTableView;

    @FXML
    private TableColumn<Part, Integer> associatedPartIdColumn;

    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

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
        /** Adds part object selected in the all parts table to the associated parts table.
         * Displays error message if no part is selected.
         * @param event Add button action.*/

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Part is not selected.");
        } else {
            associatedPart.add(selectedPart);
            associatedPartTableView.setItems(associatedPart);
        }
    }


    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        /** Displays confirmation dialog and loads MainScreenController.
         * @param event Cancel button action.
         * @throws IOException From FXMLLoader.*/

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }


    @FXML
    void onActionRemoveAssociatedParts(ActionEvent event) {
        /** Displays confirmation dialog and removes selected part from associated parts table.
         * Displays error message if no part is selected.
         * @param event Remove button action.*/

        Part selectedPart = associatedPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Parts not selected.");
        }
        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedPart.remove(selectedPart);
                associatedPartTableView.setItems(associatedPart);
            }
        }
    }


    @FXML
    void onActionSave(ActionEvent event) {
        /** Adds new product to inventory and loads MainScreenController.
         * Text fields are validated with error messages displayed preventing empty and/or invalid values.
         * @param event Save button action.
         * @throws IOException From FXMLLoader.*/

        try {
            int id = 0;
            String name = productNameText.getText();
            Double price = Double.parseDouble(productPriceText.getText());
            int stock = Integer.parseInt(productInventoryText.getText());
            int min = Integer.parseInt(productMinText.getText());
            int max = Integer.parseInt(productMaxText.getText());

            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid name.");
            }

            else {
                if (minValid(min, max) && inventoryValid(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part part : associatedPart) {
                        newProduct.addAssociatedPart(part);
                    }

                    newProduct.setId(Inventory.getNewProductId());
                    Inventory.addProduct(newProduct);
                    returnToMainScreen(event);
                }
            }
        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid value or Empty field. Please enter valid data.");
        }
    }


    @FXML
    void onActionSearchParts(ActionEvent event) {
        /** Initiates a search based on value in parts search text field and refreshes the parts table view with search results.
         * Parts can be searched for by ID or name.
         * @param event Part search button action.*/

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = partSearchText.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        partTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Parts not found.");
        }
    }

    @FXML
    void partSearchKeyPressed(KeyEvent event) {
        /**Refreshes part table view to show all parts when parts search text field is empty.
         * @param event Parts search text field key pressed.*/

        if (partSearchText.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**Initializes controller and populates table views.
         * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
         * @param resources The resources used to localize the root object, or null if the root object was not localized.*/
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTableView.setItems(Inventory.getAllParts());

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
