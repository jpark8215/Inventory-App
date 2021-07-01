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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Class ModifyProductScreen.java
 @author Jieun Park
 */

public class ModifyProductScreenController implements Initializable {
    Stage stage;
    Parent scene;

    /**
     * Load MainScreenController.
     @param event Passed from parent method.
     */
    private void returnToMainScreen(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Confirm that minimum is greater than zero and less than maximum.
     @param min Minimum value for the part.
     @param max Maximum value for the part.
     @return Boolean indicating if minimum is valid.
     */
    private boolean minValid(int min, int max) {
        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Minimum cannot be less than 0 or greater than maximum value.");
            alert.showAndWait();
        }
        return isValid;
    }


    /**
     * Confirm that inventory is equal to or between minimum and maximum.
     * @param min Minimum value for the part.
     * @param max Maximum value for the part.
     * @param stock Inventory level for the part.
     * @return Boolean indicating if inventory is valid.
     */
    private boolean inventoryValid(int min, int max, int stock) {
        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("The inventory must be between minimum and maximum value.");
            alert.showAndWait();
        }
        return isValid;
    }


    /**
     * Product selected in MainScreenController.
     */
    Product selectedProduct;

    /**
     * List of associated parts with the product.
     */
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();


    /**
     * Associated part table view.
     */
    @FXML
    private TableView<Part> associatedPartTableView;

    /**
     * Part ID column for the associated part table.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartIdColumn;

    /**
     * Part name column for the associated part table.
     */
    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;

    /**
     * Inventory column for the associated part table.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryColumn;

    /**
     * Part price column for the associated part table.
     */
    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    /**
     * All parts table view.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * Part ID column for all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     * Part name column for the all parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * Inventory column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    /**
     * Part price column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     * Part search text field.
     */
    @FXML
    private TextField partSearchText;

    /**
     * Product ID text field.
     */
    @FXML
    private TextField productIdText;

    /**
     * Product name text field.
     */
    @FXML
    private TextField productNameText;

    /**
     * Product inventory text field.
     */
    @FXML
    private TextField productInventoryText;

    /**
     * Product price text field.
     */
    @FXML
    private TextField productPriceText;

    /**
     * Product maximum text field.
     */
    @FXML
    private TextField productMaxText;

    /**
     * Product minimum text field.
     */
    @FXML
    private TextField productMinText;


    /**
     * Add part selected in the all parts table to the associated parts table.
     * Display error message when part is not selected.
     @param event Add button action.
     */
    @FXML
    void onActionAddParts(ActionEvent event) {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select a part.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
            associatedPart.add(selectedPart);
//            selectedProduct.getAllAssociatedPart().add(selectedPart);
            associatedPartTableView.setItems(associatedPart);
        }
    }


    /**
     * Confirmation dialog and MainScreenController loader.
     @param event Cancel button action.
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setContentText("Do you want to return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }


    /**
     * Confirmation dialog to remove selected part from associated parts table.
     * Display error message when part is not selected.
     @param event Remove button action.
     */
    @FXML
    void onActionRemoveAssociatedParts(ActionEvent event) {

        Part selectedPart = associatedPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select a part.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("This will dissociate the selected part from the product. \n" +
                    "Do you wish to continue?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedPart.remove(selectedPart);
                associatedPartTableView.setItems(associatedPart);
            }
        }
    }


    /**
     * Renew product in inventory and load MainScreenController.
     * Text fields are checked.
     * Display error messages preventing empty and/or invalid values.
     @param event Save button action.
     */
    @FXML
    void onActionSave (ActionEvent event) {
        try {

            int newProductId = Integer.parseInt(productIdText.getText());
            String name = productNameText.getText();
            double price = 0.0;
            int stock = 0;
            int min = 0;
            int max = 0;

            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Product Name is empty. Please enter.");
                alert.showAndWait();
            }
            else {
                try {
                    price = Double.parseDouble(productPriceText.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Price must be a decimal. ##.##");
                    alert.showAndWait();

                }
                try {
                    stock = Integer.parseInt(productInventoryText.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Inventory must be an integer.");
                    alert.showAndWait();

                }
                try {
                    min = Integer.parseInt(productMinText.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Minimum must be an integer, and greater than 0 and less than maximum.");
                    alert.showAndWait();

                }
                try {
                    max = Integer.parseInt(productMaxText.getText());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Maximum must be an integer, and greater than 0 and minimum.");
                    alert.showAndWait();

                }

                Product newProduct = new Product(newProductId, name, price, stock, min, max);

                if (minValid(min, max) && inventoryValid(min, max, stock)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMATION");
                    alert.setContentText("Do you want to save?");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        for (Part associatedPart : associatedPart) {
                            newProduct.addAssociatedPart(associatedPart);
                        }
                        Inventory.addProduct(newProduct);
                        Inventory.deleteProduct(selectedProduct);
                        returnToMainScreen(event);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//        try {
//            int id = selectedProduct.getId();
// //           int modifiedProductId = Integer.parseInt(productIdText.getText());
//            String name = productNameText.getText();
//            double price = Double.parseDouble(productPriceText.getText());
//            int stock = Integer.parseInt(productInventoryText.getText());
//            int min = Integer.parseInt(productMinText.getText());
//            int max = Integer.parseInt(productMaxText.getText());
//            boolean productModified = false;
//
//            if (name.isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("ERROR");
//                alert.setContentText("Please enter valid name.");
//                Optional<ButtonType> result = alert.showAndWait();
//            }
//            else if (minValid(min, max) && inventoryValid(min, max, stock)) {
//
//                Product newProduct = new Product(id, name, price, stock, min, max);
//                for (Part associatedPart : associatedPart) { newProduct.addAssociatedPart(associatedPart); }
//
//                Inventory.addProduct(newProduct);
//                Inventory.deleteProduct(selectedProduct);
//
//                productModified = true;
//            }
//            if (productModified) {
//                returnToMainScreen(event);
//            }
//        }
//        catch (Exception e){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("ERROR");
//            alert.setContentText("Invalid value or Empty field. Please enter valid data.\n" +
//                    "ID: Integer\n" +
//                    "Name: String\n" +
//                    "Price: Double\n" +
//                    "Inventory: Integer\n" +
//                    "Min/ Max: Integer\n");
//            Optional<ButtonType> result = alert.showAndWait();
//        }
//    }


    /**
     * Search the value in the search text field and renew the part table view with search result.
     * Renew part table to show all parts when part search text field is empty.
     * Part can be searched by ID or name.
     @param event Parts search text field key pressed.
     */
    @FXML
    void onKeyPressedSearchPart(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {

            ObservableList<Part> allParts = Inventory.getAllParts();
            ObservableList<Part> partFound = FXCollections.observableArrayList();
            String searchString = partSearchText.getText();

            for (Part part : allParts) {
                if (String.valueOf(part.getId()).contains(searchString) || part.getName().toLowerCase(Locale.ROOT).contains(searchString)) {
                    partFound.add(part);
                }
                else if (partSearchText.getText().isEmpty()) {
                    partTableView.setItems(Inventory.getAllParts());
                }
            }

            partTableView.setItems(partFound);

            if (partFound.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Part not found.");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
    }


    /**
     * Disable the id text field.
     * Initialize controller and populate text fields with selected parts in MainScreenController.
     * Initialize controller and populate part and associated part table views.
     @param location Location used to resolve relative paths for the root object, or null for unknown location.
     @param resources Resources used to localize the root object, or null for un localized root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        productIdText.setEditable(false);

        selectedProduct = MainScreenController.getProductToModify();
        associatedPart = selectedProduct.getAllAssociatedPart();

        productIdText.setText(String.valueOf(selectedProduct.getId()));
        productNameText.setText(selectedProduct.getName());
        productInventoryText.setText(String.valueOf(selectedProduct.getStock()));
        productPriceText.setText(String.valueOf(selectedProduct.getPrice()));
        productMaxText.setText(String.valueOf(selectedProduct.getMax()));
        productMinText.setText(String.valueOf(selectedProduct.getMin()));

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTableView.setItems(Inventory.getAllParts());

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartTableView.setItems(associatedPart);

    }
}