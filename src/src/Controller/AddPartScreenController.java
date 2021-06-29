package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Class AddPartScreenController.java
 @author Jieun Park
 */

public class AddPartScreenController implements Initializable {
    Stage stage;
    Parent scene;

    Random random = new Random();
    int partId;
    private boolean isMatch;

    //Build random id generator in controller instead of setting id method in Inventory.java.

    /**
     * Generate a random number for product.
     * Check existing product numbers to confirm for duplicates.
     * @return Random product ID.
     */
    private int assignPartId(){
        int randomPartId;
        randomPartId = 1 + random.nextInt(100);

        for(Part part : Inventory.getAllParts()){
            if(part.getId() == randomPartId){
                isMatch = true;
                assignPartId();
            }
        }
        return randomPartId;
    }



    /**
     * Load MainScreenController.
     *
     * @param event Passed from parent method.
     * @throws IOException From FXMLLoader.
     */
    private void returnToMainScreen(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Confirm that minimum is greater than zero and less than maximum.
     *
     * @param min Minimum value for the part.
     * @param max Maximum value for the part.
     * @return Boolean indicating if minimum is valid.
     */
    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Minimum cannot be greater than maximum value.");
            alert.showAndWait();
        }
        return isValid;
    }


    /**
     * Confirm that inventory is equal to or between minimum and maximum.
     *
     * @param min   Minimum value for the part.
     * @param max   Maximum value for the part.
     * @param stock Inventory level for the part.
     * @return Boolean indicating if inventory is valid.
     */
    private boolean inventoryValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("The inventory is less than minimum or larger than maximum value.");
            alert.showAndWait();
        }
        return isValid;
    }


    /**
     * Inhouse radio button.
     */
    @FXML
    private RadioButton inhouseRadioButton;

    /**
     * Part toggle group radio button.
     */
    @FXML
    private ToggleGroup partTypeTG;

    /**
     * Outsourced radio button.
     */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * Part ID text field
     */
    @FXML
    private TextField partIdText;

    /**
     * Part name text field.
     */
    @FXML
    private TextField partNameText;

    /**
     * Part inventory text field.
     */
    @FXML
    private TextField partInventoryText;

    /**
     * Part price text field.
     */
    @FXML
    private TextField partPriceText;

    /**
     * Part maximum text field.
     */
    @FXML
    private TextField partMaxText;

    /**
     * Part machine ID/company name text field.
     */
    @FXML
    private TextField machineIdNameText;

    /**
     * Part minimum text field.
     */
    @FXML
    private TextField partMinText;

    /**
     * Part machine ID/company name label field.
     */
    @FXML
    private Label machineIdNameLabel;


    /**
     * Confirmation dialog and MainScreenController loader.
     *
     * @param event Cancel button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void onActionCancelAddParts(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setContentText("Do you want to return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }


    /**
     * Set machine ID/company name label to "Machine ID".
     *
     * @param event Inhouse radio button action.
     */
    @FXML
    void onActionInhouseRadio(ActionEvent event) {

        machineIdNameLabel.setText("Machine ID");
    }


    /**
     * Set machine ID/company name label to "Company Name".
     *
     * @param event Outsourced radio button.
     */
    @FXML
    void onActionOutsourcedRadio(ActionEvent event) {

        machineIdNameLabel.setText("Company Name");
    }


    /**
     * Save new part to inventory and load MainScreenController.
     * Text fields are checked.
     * Display error messages preventing empty and/or invalid values.
     *
     * @param event Save button action.
     */
    @FXML
    void onActionSaveAddParts(ActionEvent event) {

        try {
//            int id = Inventory.getNewPartId();
//            id++;
//            Inventory.setPartId(id);

            int newPartId = Integer.parseInt(partIdText.getText());


            String name = partNameText.getText();
            double price = Double.parseDouble(partPriceText.getText());
            int stock = Integer.parseInt(partInventoryText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            int machineId;
            String companyName;
            boolean partAdded = false;

            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Part Name is empty.");
                alert.showAndWait();
            } else if (minValid(min, max) && inventoryValid(min, max, stock)) {
                if (inhouseRadioButton.isSelected()) {
                    try {
                        machineId = Integer.parseInt(machineIdNameText.getText());
                        InhousePart newInhousePart = new InhousePart(newPartId, name, price, stock, min, max, machineId);
//                        newInhousePart.setId(Inventory.getNewPartId());
                        Inventory.addPart(newInhousePart);
                        partAdded = true;
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setContentText("Please enter valid machine ID number.");
                        alert.showAndWait();
                    }
                } else if (outsourcedRadioButton.isSelected()) {
                    try {
                        companyName = machineIdNameText.getText();
                        OutsourcedPart newOutsourcedPart = new OutsourcedPart(newPartId, name, price, stock, min, max, companyName);
//                        newOutsourcedPart.setId(Inventory.getNewPartId());
                        Inventory.addPart(newOutsourcedPart);
                        partAdded = true;
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setContentText("Please enter valid company name.");
                        alert.showAndWait();
                    }
                }
            }

            if (partAdded) {
                returnToMainScreen(event);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Invalid value or Empty field. Please enter valid data.\n" +
                    "ID: Integer\n" +
                    "Name: String\n" +
                    "Price: Double\n" +
                    "Inventory: Integer\n" +
                    "Min/ Max: Integer\n" +
                    "Machine ID: Integer\n" +
                    "Company Name: String");
            alert.showAndWait();
        }
    }


    /**
     * Assign part ID and disable the id text field.
     * Initialize controller and pre-select the radio button.
     * @param location  Location used to resolve relative paths for the root object, or null for unknown location.
     * @param resources Resources used to localize the root object, or null for un localized root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partId = assignPartId();
        partIdText.setText(Integer.toString(partId));
        partIdText.setEditable(false);

        inhouseRadioButton.setSelected(true);

    }
}