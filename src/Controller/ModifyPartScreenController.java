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
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**Class ModifyPartScreenController.java*/
/** @author Jieun Park*/

public class ModifyPartScreenController implements Initializable {
    Stage stage;
    Parent scene;

    /** The machine ID/company name label for the part.*/
    @FXML
    private Label partIdNameLabel;

    /** The in-house radio button.*/
    @FXML
    private RadioButton inhouseRadioButton;

    /** The toggle group for the radio buttons.*/
    @FXML
    private ToggleGroup partTypeTG;

    /** The outsourced radio button.*/
    @FXML
    private RadioButton outsourcedRadioButton;

    /** The part ID text field.*/
    @FXML
    private TextField partIdText;

    /** The part name text field.*/
    @FXML
    private TextField partNameText;

    /** The inventory level text field.*/
    @FXML
    private TextField partInventoryText;

    /** The part price text field.*/
    @FXML
    private TextField partPriceText;

    /** The maximum level text field.*/
    @FXML
    private TextField partMaxText;

    /** The machine ID/company name text field.*/
    @FXML
    private TextField partIdNameText;

    /** The minimum level text field.*/
    @FXML
    private TextField partMinText;

    @FXML
    void onActionCancelModifyParts(ActionEvent event) throws IOException{
        /** Displays confirmation dialog and loads MainScreenController.
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
        /** Sets machine ID/company name label to "Machine ID".
         * @param event In-house radio button action.*/
        partIdNameLabel.setText("Machine ID");
    }


    @FXML
    void onActionOutsourcedRadio(ActionEvent event) {
        /** Sets machine ID/company name label to "Company Name".
         * @param event Outsourced radio button.*/
        partIdNameLabel.setText("Company Name");
    }


    @FXML
    void onActionSaveModifyParts(ActionEvent event)  {
         /** Replaces part in inventory and loads MainScreenController.
         * Text fields are validated with error messages displayed preventing empty and/or invalid values.
         * @param event Save button action.
         * @throws IOException From FXMLLoader.*/

        try {
            int id = selectedPart.getId();
            String name = partNameText.getText();
            double price = Double.parseDouble(partPriceText.getText());
            int stock = Integer.parseInt(partInventoryText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            int machineId;
            String companyName;
            boolean partAdded = false;

            if (minValid(min, max) && inventoryValid(min, max, stock)) {

                if (inhouseRadioButton.isSelected()) {
                    try {
                        machineId = Integer.parseInt(partIdNameText.getText());
                        InhousePart newInHousePart = new InhousePart(id, name, price, stock, min, max, machineId);
                        Inventory.addPart(newInHousePart);
                        partAdded = true;
                    }
                    catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setContentText("Please enter valid machine ID number.");
                        Optional<ButtonType> result = alert.showAndWait();
                    }
                }

                if (outsourcedRadioButton.isSelected()) {
                    companyName = partIdNameText.getText();
                    OutsourcedPart newOutsourcedPart = new OutsourcedPart(id, name, price, stock, min, max,
                            companyName);
                    Inventory.addPart(newOutsourcedPart);
                    partAdded = true;
                }

                if (partAdded) {
                    Inventory.deletePart(selectedPart);
                    returnToMainScreen(event);
                }
            }
        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Invalid value or Empty field. Please enter valid data.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    /** The part object selected in the MainScreenController.*/
    private Part selectedPart;


    private void returnToMainScreen(ActionEvent event) throws IOException {
        /** Loads MainScreenController.
         * @param event Passed from parent method.
         * @throws IOException From FXMLLoader.*/
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
            alert.setContentText("Minimum must be greater than or equal to zero, or less than Maximum.");
            alert.showAndWait();        }
        return isValid;
    }


    private boolean inventoryValid(int min, int max, int stock) {
        /** Validates that inventory level is equal too or between min and max.
         * @param min The minimum value for the part.
         * @param max The maximum value for the part.
         * @param stock The inventory level for the part.
         * @return Boolean indicating if inventory is valid.*/
        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Inventory must be between the minimum or maximum value.");
            alert.showAndWait();        }
        return isValid;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**Initializes controller and populates text fields with part selected in MainScreenController.
         * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
         * @param resources The resources used to localize the root object, or null if the root object was not localized.*/
        selectedPart = MainScreenController.getPartToModify();

        if (selectedPart instanceof InhousePart) {
            inhouseRadioButton.setSelected(true);
            partIdNameLabel.setText("Machine ID");
            partIdNameText.setText(String.valueOf(((InhousePart) selectedPart).getMachineId()));
        }

        if (selectedPart instanceof OutsourcedPart){
            outsourcedRadioButton.setSelected(true);
            partIdNameLabel.setText("Company Name");
            partIdNameText.setText(((OutsourcedPart) selectedPart).getCompanyName());
        }

        partIdText.setText(String.valueOf(selectedPart.getId()));
        partNameText.setText(selectedPart.getName());
        partInventoryText.setText(String.valueOf(selectedPart.getStock()));
        partPriceText.setText(String.valueOf(selectedPart.getPrice()));
        partMaxText.setText(String.valueOf(selectedPart.getMax()));
        partMinText.setText(String.valueOf(selectedPart.getMin()));
    }
}