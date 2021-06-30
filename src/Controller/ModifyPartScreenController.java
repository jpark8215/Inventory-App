package Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
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
import java.util.ResourceBundle;

/**
 * Class ModifyPartScreenController.java
 @author Jieun Park
 */

public class ModifyPartScreenController implements Initializable {
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
            alert.setContentText("Minimum must be greater than or equal to zero, or less than Maximum.");
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
            alert.setContentText("Inventory must be between the minimum or maximum value.");
            alert.showAndWait();
        }
        return isValid;
    }


    /**
     * Part selected in MainScreenController.
     */
    private Part selectedPart;

    /**
     * Machine ID/company name label for the part.
     */
    @FXML
    private Label machineIdNameLabel;

    /**
     * Inhouse radio button.
     */
    @FXML
    private RadioButton inhouseRadioButton;

    /**
     * Toggle group for the radio buttons.
     */
    @FXML
    private ToggleGroup partTypeTG;

    /**
     * Outsourced radio button.
     * */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * Part ID text field.
     */
    @FXML
    private TextField partIdText;

    /**
     * Part name text field.
     */
    @FXML
    private TextField partNameText;

    /**
     * Inventory text field.
     */
    @FXML
    private TextField partInventoryText;

    /**
     * Part price text field.
     */
    @FXML
    private TextField partPriceText;

    /**
     * Maximum text field.
     */
    @FXML
    private TextField partMaxText;

    /**
     * Machine ID/company name text field.
     */
    @FXML
    private TextField machineIdNameText;

    /**
     * Minimum text field.
     */
    @FXML
    private TextField partMinText;


    /**
     * Confirmation dialog and MainScreenController loader.
     @param event Cancel button action.
     */
    @FXML
    void onActionCancelModifyParts(ActionEvent event) throws IOException{

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
     @param event Inhouse radio button action.
     */
    @FXML
    void onActionInhouseRadio(ActionEvent event) {

        machineIdNameLabel.setText("Machine ID");
    }


    /**
     * Set machine ID/company name label to "Company Name".
     @param event Outsourced radio button.
     */
    @FXML
    void onActionOutsourcedRadio(ActionEvent event) {

        machineIdNameLabel.setText("Company Name");
    }


    /**
     * Modify part in inventory and load MainScreenController.
     * Text fields are checked.
     * Display error messages preventing empty and/or invalid values.
     @param event Save button action.
     */
    @FXML
    void onActionSaveModifyParts(ActionEvent event)  {

        try {
            int id = selectedPart.getId();
 //           int modifiedPartId = Integer.parseInt(partIdText.getText());
            String name = partNameText.getText();
            double price = Double.parseDouble(partPriceText.getText());
            int stock = Integer.parseInt(partInventoryText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            int machineId;
            String companyName;
            boolean partModified = false;

            if (minValid(min, max) && inventoryValid(min, max, stock)) {

                if (inhouseRadioButton.isSelected()) {
                    try {
                        machineId = Integer.parseInt(machineIdNameText.getText());
                        InhousePart newInHousePart = new InhousePart(id, name, price, stock, min, max, machineId);
                        Inventory.addPart(newInHousePart);
                        partModified = true;
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setContentText("Please enter valid machine ID number.");
                        Optional<ButtonType> result = alert.showAndWait();
                    }
                }
                else if (outsourcedRadioButton.isSelected()) {
                    try {
                        companyName = machineIdNameText.getText();
                        OutsourcedPart newOutsourcedPart = new OutsourcedPart(id, name, price, stock, min, max,
                                companyName);
                        Inventory.addPart(newOutsourcedPart);
                        partModified = true;
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setContentText("Please enter valid company name.");
                        Optional<ButtonType> result = alert.showAndWait();
                    }
                }

                if (partModified) {
                    Inventory.deletePart(selectedPart);
                    returnToMainScreen(event);
                }
            }
        }
        catch(Exception e) {
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
            Optional<ButtonType> result = alert.showAndWait();
        }
    }


    /**
     * Disable the id text field.
     * Initialize controller and pre-select the radio button.
     * Initialize controller and populate text fields with selected parts in MainScreenController.
     @param location Location used to resolve relative paths for the root object, or null for unknown location.
     @param resources Resources used to localize the root object, or null for un localized root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partIdText.setEditable(false);

        inhouseRadioButton.setSelected(true);

        selectedPart = MainScreenController.getPartToModify();

        if (selectedPart instanceof InhousePart) {
            inhouseRadioButton.setSelected(true);
            machineIdNameLabel.setText("Machine ID");
            machineIdNameText.setText(String.valueOf(((InhousePart) selectedPart).getMachineId()));
        }

        if (selectedPart instanceof OutsourcedPart){
            outsourcedRadioButton.setSelected(true);
            machineIdNameLabel.setText("Company Name");
            machineIdNameText.setText(((OutsourcedPart) selectedPart).getCompanyName());
        }

        partIdText.setText(String.valueOf(selectedPart.getId()));
        partNameText.setText(selectedPart.getName());
        partInventoryText.setText(String.valueOf(selectedPart.getStock()));
        partPriceText.setText(String.valueOf(selectedPart.getPrice()));
        partMaxText.setText(String.valueOf(selectedPart.getMax()));
        partMinText.setText(String.valueOf(selectedPart.getMin()));

    }
}