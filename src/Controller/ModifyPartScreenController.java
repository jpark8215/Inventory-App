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
/*
public class ModifyPartScreenController implements Initializable {

    @FXML
    private Label partIdNameLabel;

    @FXML
    private RadioButton inhouseRadioButton;

    @FXML
    private ToggleGroup partTypeTG;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField partIdText;

    @FXML
    private TextField partNameText;

    @FXML
    private TextField partInventoryText;

    @FXML
    private TextField partPriceText;

    @FXML
    private TextField partMaxText;

    @FXML
    private TextField partIdNameText;

    @FXML
    private TextField partMinText;

    @FXML
    void onActionCancelModifyParts(ActionEvent event) {

    }

    @FXML
    void onActionInhouseRadio(ActionEvent event) {

    }

    @FXML
    void onActionOutsourcedRadio(ActionEvent event) {

    }

    @FXML
    void onActionSaveModifyParts(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
*/


public class ModifyPartScreenController implements Initializable {

    /**
     * The part object selected in the MainScreenController.
     */
    private Part selectedPart;

    /**
     * The machine ID/company name lable for the part.
     */
    @FXML
    private Label partIdNameLabel;

    /**
     * The in-house radio button.
     */
    @FXML
    private RadioButton inhouseRadioButton;

    /**
     * The toggle group for the radio buttons.
     */
    @FXML
    private ToggleGroup partTypeTG;

    /**
     * The outsourced radio button.
     */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * The part ID text field.
     */
    @FXML
    private TextField partIdText;

    /**
     * The part name text field.
     */
    @FXML
    private TextField partNameText;

    /**
     * The inventory level text field.
     */
    @FXML
    private TextField partInventoryText;

    /**
     * The part price text field.
     */
    @FXML
    private TextField partPriceText;

    /**
     * The maximum level text field.
     */
    @FXML
    private TextField partMaxText;

    /**
     * The machine ID/company name text field.
     */
    @FXML
    private TextField partIdNameText;

    /**
     * The minimum level text field.
     */
    @FXML
    private TextField partMinText;

    /**
     * Displays confirmation dialog and loads MainScreenController.
     *
     * @param event Cancel button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }

    /**
     * Sets machine ID/company name label to "Machine ID".
     *
     * @param event In-house raido button action.
     */
    @FXML
    void inhouseRadioButtonAction(ActionEvent event) {

        partIdNameLabel.setText("Machine ID");
    }

    /**
     * Sets machine ID/company name label to "Company Name".
     *
     * @param event Outsourced radio button.
     */
    @FXML
    void outsourcedRadioButtonAction(ActionEvent event) {

        partIdNameLabel.setText("Company Name");
    }

    /**
     * Replaces part in inventory and loads MainScreenController.
     *
     * Text fields are validated with error messages displayed preventing empty and/or
     * invalid values.
     *
     * @param event Save button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {

        try {
            int id = selectedPart.getId();
            String name = partNameText.getText();
            Double price = Double.parseDouble(partPriceText.getText());
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
                    } catch (Exception e) {
                        displayAlert(2);
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
        } catch(Exception e) {
            displayAlert(1);
        }
    }

    /**
     * Loads MainScreenController.
     *
     * @param event Passed from parent method.
     * @throws IOException From FXMLLoader.
     */
    private void returnToMainScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Validates that min is greater than 0 and less than max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @return Boolean indicating if min is valid.
     */
    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            displayAlert(3);
        }

        return isValid;
    }

    /**
     * Validates that inventory level is equal too or between min and max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @param stock The inventory level for the part.
     * @return Boolean indicating if inventory is valid.
     */
    private boolean inventoryValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            displayAlert(4);
        }

        return isValid;
    }

    /**
     * Displays various alert messages.
     *
     * @param alertType Alert message selector.
     */
    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Modifying Part");
                alert.setContentText("Form contains blank fields or invalid values.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Machine ID");
                alert.setContentText("Machine ID may only contain numbers.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number greater than 0 and less than Max.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max");
                alert.showAndWait();
                break;
        }
    }

    /**
     * Initializes controller and populates text fields with part selected in MainScreenController.
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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