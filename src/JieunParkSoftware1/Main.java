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
 * FUTURE ENHANCEMENT: Redirecting the page to Modify product page when user gets an alert for the product with associated part.
 * FUTURE ENHANCEMENT: Located in MainScreenController.java.
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
     * @param args Array of sequence of strings passed to the Main function.
     */
    public static void main(String[] args) {

        InhousePart part1 = new InhousePart(1, "Motherboard", 25.00, 10, 1, 20, 100);
        InhousePart part2 = new InhousePart(2, "Cooling Fan", 21.00, 16, 1, 20, 101);

        Inventory.addPart(part1);
        Inventory.addPart(part2);

        OutsourcedPart part4 = new OutsourcedPart( 4, "Samsung 980", 495.00, 12, 1, 20, "Samsung");
        OutsourcedPart part5 = new OutsourcedPart( 5, "Ryzen 9", 495.00, 8, 1, 20, "AMD");

        Inventory.addPart(part4);
        Inventory.addPart(part5);


        Product product1 = new Product(1, "Jieun", 299.99, 5, 1, 100);
        Product product2 = new Product(2, "Claudia", 199.99, 3, 1, 100);
        Product product3 = new Product(3, "Mica", 99.99, 6, 1, 100);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);



        launch(args);

    }
}
