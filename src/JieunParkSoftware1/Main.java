package JieunParkSoftware1;

import Model.InhousePart;
import Model.Inventory;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        //Adds sample parts
        //int partId = Inventory.getNewPartId();
        InhousePart breaks = new InhousePart(1,"Breaks", 15.00, 10, 1, 20,0);

        //partId = Inventory.getNewPartId();
        InhousePart wheel = new InhousePart(2,"Wheel", 11.00, 16, 1, 20,0);

        //partId = Inventory.getNewPartId();
        InhousePart seat = new InhousePart(3,"Seat", 15.00, 10, 1, 20,0);

        Inventory.addPart(breaks);
        Inventory.addPart(wheel);
        Inventory.addPart(seat);

        //Add sample products
        //int productId = Inventory.getNewProductId();
        Product giantBike = new Product(1000, "Giant Bike",299.99, 5, 1, 100);
        Product tricycle = new Product(1001, "Tricycle",99.99, 3, 1, 100);

        Inventory.addProduct(giantBike);
        Inventory.addProduct(tricycle);

        launch(args);
    }
}
