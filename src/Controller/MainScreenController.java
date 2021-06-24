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
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Class MainScreenController.java
 @author Jieun Park
 */

public class MainScreenController implements Initializable{
    /**
     * Create global variable
     */
    Stage stage;
    Parent scene;

    /**
     * Part search text field.
     */
    @FXML
    private TextField partSearchText;

    /**
     * Part table view.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * Part ID column for the part table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     * Part name column for the part table.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * Part inventory column for the part table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    /**
     * Part price column for the part table.
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     * Product search text field.
     */
    @FXML
    private TextField productSearchText;

    /**
     * Product table view.
     */
    @FXML
    private TableView<Product> productTableView;

    /**
     * Product ID column for the part table.
     */
    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    /**
     * Product name column for the part table.
     */
    @FXML
    private TableColumn<Product, String> productNameColumn;

    /**
     * Product inventory column for the part table.
     */
    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    /**
     * Product price column for the part table.
     */
    @FXML
    private TableColumn<Product, Double> productPriceColumn;


    /**
     * Load AddPartController.
     @param event Add button action.
     */
    @FXML
    void onActionAddParts(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /** Load AddProductController.
     @param event Add button action.
     */
    @FXML
    void onActionAddProducts(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Delete the part selected in the part table.
     * Confirmation dialog to remove selected part from associated part table.
     * Display error message when part is not selected.
     @param event Part delete button action.
     */
    @FXML
    void onActionDeleteParts(ActionEvent event) {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select a part.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }


    /**
     * Delete the product selected in the part table.
     * Confirmation dialog to remove selected part from associated product table.
     * Display error message when product is not selected.
     @param event Product delete button action.
     */
    @FXML
    void onActionDeleteProducts(ActionEvent event) {

        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select a product.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("Do you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deleteProduct(selectedProduct);
            }
        }
    }


    /**
     * Exit the program.
     @param event Exit button action.
     */
    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0);

    }


    /**
     * Loads the ModifyPartController.
     * Display error message when part is not selected.
     @param event Part modify button action
     */
    @FXML
    void onActionModifyParts(ActionEvent event) throws IOException {

        partToModify = partTableView.getSelectionModel().getSelectedItem();

        /**
         * Prevent null from being passed to the ModifyPartController.
         */
        if (partToModify == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select a part.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/ModifyPartScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    /**
     * Loads the ModifyProductController.
     * Display error message when product is not selected.
     @param event Product modify button action
     */
    @FXML
    void onActionModifyProducts(ActionEvent event) throws IOException {

        productToModify = productTableView.getSelectionModel().getSelectedItem();

        /**
         * Prevent null from being passed to the ModifyProductController.
         */
        if (productToModify == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please select a product.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/ModifyProductScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    /**
     * Search the value in the search text field and renew the part table view with search result.
     * Part can be searched by ID or name.
     @param event Part search button action.
     */
    @FXML
    void onActionSearchParts(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partFound = FXCollections.observableArrayList();
        String searchString = partSearchText.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) || part.getName().toLowerCase(Locale.ROOT).contains(searchString)) {
                partFound.add(part);
            }
        }

        partTableView.setItems(partFound);

        if (partFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Parts not found.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }


    /**
     * Search the value in the search text field and renew the product table view with search result.
     * Product can be searched by ID or name.
     @param event Product search button action.
     */
    @FXML
    void onActionSearchProducts(ActionEvent event) {

        ObservableList<Product> allProducts= Inventory.getAllProducts();
        ObservableList<Product> productFound = FXCollections.observableArrayList();
        String searchString = productSearchText.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) || product.getName().toLowerCase(Locale.ROOT).contains(searchString)) {
                productFound.add(product);
            }
        }

        productTableView.setItems(productFound);

        if (productFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Products not found.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }


    /**
     * Renew part table to show all parts when part search text field is empty.
     @param event Part search text field key pressed.
     */
    @FXML
    void partSearchTextKeyPressed(KeyEvent event) {

        if (partSearchText.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }
    }


    /**
     * Renew product table to show all products when product search text field is empty.
     @param event Product search text field key pressed.
     */
    @FXML
    void productSearchTextKeyPressed(KeyEvent event) {

        if (productSearchText.getText().isEmpty()) {
            productTableView.setItems(Inventory.getAllProducts());
        }
    }



    /**
     * Part selected in the table view.
     */
    private static Part partToModify;
    /**
     * Load the selected part in the part table.
     @return A part to modify.
     */
    public static Part getPartToModify() {
        return partToModify;
    }


    /**
     * Product selected in the table view.
     */
    private static Product productToModify;
    /**
     * Load the selected product in the product table.
     @return A product to modify.
     */
    public static Product getProductToModify() {
        return productToModify;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * Populate part table view.
         */
        partTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        /**
         * Populate product table view.
         */
        productTableView.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}

