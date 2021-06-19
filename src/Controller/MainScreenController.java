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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable{
    /**Create global variable*/
    Stage stage;
    Parent scene;

    /** The part object selected in the table view by the user.*/
    private static Part partToModify;

    /** Gets the part object selected by the user in the part table.
     * @return A part object, null if no part selected.*/
    public static Part getPartToModify() {
        return partToModify;
    }


    /** The product object selected in the table view by the user.*/
    private static Product productToModify;

    /** Gets the product object selected by the user in the product table.
     * @return A product object, null if no product selected.*/
    public static Product getProductToModify() {
        return productToModify;
    }


    @FXML
    private TextField partSearchText;

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
    private TextField productSearchText;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    void onActionAddParts(ActionEvent event) throws IOException {
        /** Loads the AddPartController.
         * @param event Add button action.*/
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddPartScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionAddProducts(ActionEvent event) throws IOException {
        /** Loads the AddProductController.
         * @param event Add button action.*/
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddProductScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void onActionDeleteParts(ActionEvent event) {
        /** Deletes the part selected by the user in the part table.
         * The method displays an error message if no part is selected and a confirmation dialog before deleting the selected part.
         * @param event Part delete button action.*/

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No parts selected.");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    @FXML
    void onActionDeleteProducts(ActionEvent event) {
        /** Deletes the product selected by the user in the product table.
         * The method displays an error message if no product is selected and a confirmation dialog before deleting the selected product.
         * @param event Product delete button action.*/

        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No products selected.");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deleteProduct(selectedProduct);
            }
        }
    }


    @FXML
    void onActionExit(ActionEvent event) {
        /** Exits the program.
         * @param event Exit button action.*/
        System.exit(0);

    }


    @FXML
    void onActionModifyParts(ActionEvent event) throws IOException {

        /** Loads the ModifyPartController.
         * The method displays an error message if no part is selected.
         * @param event Part modify button action.*/

        partToModify = partTableView.getSelectionModel().getSelectedItem();

        /**correcting a runtime error by preventing null from being passed to the ModifyPartController.*/
        if (partToModify == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No parts selected.");
        }
        else {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyPartScreen.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }


    @FXML
    void onActionModifyProducts(ActionEvent event) throws IOException {
        /** Loads the ModifyProductController.
         * The method displays an error message if no product is selected.
         * @param event Product modify button action.*/

        productToModify = productTableView.getSelectionModel().getSelectedItem();

        /**correcting a runtime error by preventing null from being passed to the ModifyProductController.*/
        if (productToModify == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No products selected.");
        }
        else {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyProductScreen.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
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
            if (String.valueOf(part.getId()).contains(searchString) || part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        partTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Parts are not found.");
        }
    }


    @FXML
    void onActionSearchProducts(ActionEvent event) {
        /** Initiates a search based on value in products search text field and refreshes the products table view with search results.
         * Products can be searched for by ID or name.
         * @param event Products search button action.*/

        ObservableList<Product> allProducts= Inventory.getAllProducts();
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String searchString = productSearchText.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) || product.getName().contains(searchString)) {
                productsFound.add(product);
            }
        }

        productTableView.setItems(productsFound);

        if (productsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Products are not found.");
        }

    }


    @FXML
    void partSearchTextKeyPressed(KeyEvent event) {
        /** Refreshes part table view to show all parts when parts search text field is empty.
         * @param event Parts search text field key pressed.*/

        if (partSearchText.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }
    }

    @FXML
    void productSearchTextKeyPressed(KeyEvent event) {
        /** Refreshes product table view to show all products when products search text field is empty.
         * @param event Product search text field key pressed.*/

        if (productSearchText.getText().isEmpty()) {
            productTableView.setItems(Inventory.getAllProducts());
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

