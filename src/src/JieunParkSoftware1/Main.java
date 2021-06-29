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

/**
 * Class Main.java
 @author Jieun Park
 */

public class Main extends Application {
    /**
     * Initiate main screen.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    /**
     * Add sample parts and products.
     */
    public static void main(String[] args) {

        InhousePart part1 = new InhousePart(1, "Motherboard", 15.00, 10, 1, 20, 100);
        InhousePart part2 = new InhousePart(2, "Fan", 11.00, 16, 1, 20, 101);

        Inventory.addPart(part1);
        Inventory.addPart(part2);

        OutsourcedPart part4 = new OutsourcedPart( 4, "Samsung 980", 495.00, 12, 1, 20, "Samsung");
        OutsourcedPart part5 = new OutsourcedPart( 5, "Ryzen 9", 495.00, 8, 1, 20, "AMD");

        Inventory.addPart(part4);
        Inventory.addPart(part5);


        Product product1 = new Product(1, "Park1", 299.99, 5, 1, 100);
        Product product2 = new Product(2, "Park2", 199.99, 3, 1, 100);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);


        launch(args);

    }
}
