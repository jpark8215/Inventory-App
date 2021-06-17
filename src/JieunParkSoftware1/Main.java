package JieunParkSoftware1;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        //Adds sample parts
        int partId = Inventory.getNewPartId();
        InhousePart tvScreen = new InhousePart(1,"TV Screen", 300.00, 5, 1, 20);

        partId = Inventory.getNewPartId();
        InhousePart tvShell = new InhousePart(2,"TV Shell", 100.00, 5, 1, 20);

        partId = Inventory.getNewPartId();
        InhousePart powerCord = new InhousePart(3,"Power Cord", 2.99, 5, 1, 20);

        partId = Inventory.getNewPartId();
        OutsourcedPart remote = new OutsourcedPart(5, "Remote Control",29.99, 50, 30, 100, "Panasonic");

        Inventory.addPart(tvScreen);
        Inventory.addPart(tvShell);
        Inventory.addPart(powerCord);
        Inventory.addPart(remote);

        //Add sample products
        int productId = Inventory.getNewProductId();
        Product television = new Product(productId, "LCD Television", 499.99, 20, 20, 100);
        television.addAssociatedPart(tvScreen);
        television.addAssociatedPart(tvShell);
        television.addAssociatedPart(powerCord);
        television.addAssociatedPart(remote);
        Inventory.addProduct(television);

        launch(args);
    }
}
