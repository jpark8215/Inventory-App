package Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
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

public class AddPartScreenController implements Initializable {
    Stage stage;
    Parent scene;

    /** Inhouse radio button.*/
    @FXML
    private RadioButton inhouseRadioButton;

    /** Part toggle group radio button.*/
    @FXML
    private ToggleGroup partTypeTG;

    /** Outsourced radio button.*/
    @FXML
    private RadioButton outsourcedRadioButton;

    /** Part ID text field.*/
    @FXML
    private TextField partIdText;

    /** Part name text field.*/
    @FXML
    private TextField partNameText;

    /** Part inventory text field.*/
    @FXML
    private TextField partInventoryText;

    /** Part price text field.*/
    @FXML
    private TextField partPriceText;

    /** Part maximum level text field.*/
    @FXML
    private TextField partMaxText;

    /** Part machine ID/company name text field.*/
    @FXML
    private TextField partIdNameText;

    /** Part minimum level text field.*/
    @FXML
    private TextField partMinText;

    /** Part machine ID/company name label field.*/
    @FXML
    private Label partIdNameLabel;

    @FXML
    void onActionCancelAddParts(ActionEvent event) throws IOException {
        /**Displays confirmation dialog and loads MainScreenController.
         * @param event Cancel button action.
         * @throws IOException From FXMLLoader.*/

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setContentText("Changes will not be saved. Do you want to return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }

    @FXML
    void onActionInhouseRadio(ActionEvent event) {
        /**Sets machine ID/company name label to "Machine ID".
         * @param event In-house radio button action.*/
        partIdNameLabel.setText("Machine ID");
    }

    @FXML
    void onActionOutsourcedRadio(ActionEvent event) {
        /**Sets machine ID/company name label to "Company Name".
         * @param event Outsourced radio button.*/
        partIdNameLabel.setText("Company Name");
    }

    @FXML
    void onActionSaveAddParts(ActionEvent event) throws IOException {

        /**Adds new part to inventory and loads MainScreenController.
         * Text fields are validated with error messages displayed preventing empty and/or invalid values.
         * @param event Save button action.
         * @throws IOException From FXMLLoader.*/

        try {
            int id = 0;
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
                alert.setContentText("Part Name is empty. Please enter a valid entry.");
                alert.showAndWait();
            }

            else {
                if (minValid(min, max) && inventoryValid(min, max, stock)) {

                    if (inhouseRadioButton.isSelected()) {
                        try {
                            machineId = Integer.parseInt(partIdNameText.getText());
                            InhousePart newInhousePart = new InhousePart(id, name, price, stock, min, max, machineId);
                            newInhousePart.setId(Inventory.getNewPartId());
                            Inventory.addPart(newInhousePart);
                            partAdded = true;

                        }
                        catch (Exception e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setContentText("Please enter valid machine ID number.");
                            alert.showAndWait();
                        }
                    }
                    if (outsourcedRadioButton.isSelected()) {
                        companyName = partIdNameText.getText();
                        OutsourcedPart newOutsourcedPart = new OutsourcedPart(id, name, price, stock, min, max, companyName);
                        newOutsourcedPart.setId(Inventory.getNewPartId());
                        Inventory.addPart(newOutsourcedPart);
                        partAdded = true;
                    }

                    if (partAdded) {
                        returnToMainScreen(event);
                    }
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Invalid value or Empty field. Please enter valid data.");
            alert.showAndWait();
        }
    }

    private void returnToMainScreen(ActionEvent event) throws IOException {
        /**Loads MainScreenController.
         * @param event Passed from parent method.
         * @throws java.io.IOException From FXMLLoader.*/
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Invalid number. Minimum cannot be larger than maximum value.");
            alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("The inventory is less than minimum or larger than maximum value.");
            alert.showAndWait();
        }
        return isValid;
    }


        @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
