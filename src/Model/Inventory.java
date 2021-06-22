package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class Inventory.java
 @author Jieun Park
 */

public class Inventory {

    /**
     * A list of all parts in the inventory.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();


    /**
     * A list of all products in the inventory.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


//TO DO : Generate auto increment ids
    /**
     * Get a new part ID.
     @return A unique part ID.
     */
    private static int partId = 0;
    public static int getNewPartId() {return ++partId;}


    /**
     * Add a part to the inventory.
     @param newPart The part object to add.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }


//TO DO : Generate auto increment ids
    /**
     * Get a new product ID.
     @Return A unique product ID.
     */
    private static int productId = 0;
    public static int getNewProductId() {return ++productId;}


    /**
     * Add a product to the inventory.
     @param newProduct The product object to add.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }


    /**
     * Look up the list of parts by ID.
     @param partId The part ID.
     @return The part object if found, null if not found.
     */
    public static Part lookupPart(int partId) {
        Part partFound = null;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                partFound = part;
            }
        }
        return partFound;
    }


    /**
     * Look up the list of products by ID.
     @param productId The product ID.
     @return The product object if found, null if not found.
     */
    public static Product lookupProduct(int productId) {
        Product productFound = null;

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                productFound = product;
            }
        }
        return productFound;
    }


    //TO DO :  Look up the list of parts by name/ partial name/ case insensitive
    /**
     * Look up the list of parts by name.
     @param partName The part name.
     @return A list of parts found.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partFound = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().equals(partName)) {
                partFound.add(part);
            }
        }
        return partFound;
    }


    //TO DO :  Look up the list of parts by name/ partial name/ case insensitive
    /**
     * Look up the list of products by name.
     @param productName The product name.
     @return A list of products found.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productFound = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                productFound.add(product);
            }
        }
        return productFound;
    }


    /** Update a part in the list of parts.
     @param index Index of the part to be replaced.
     @param selectedPart The part used for replacement.
     */
    public static void updatePart (int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }


    /**
     * Update a product in the list of products.
     @param index Index of the product to be replaced.
     @param selectedProduct The product used for replacement.
     */
    public static void updateProduct (int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }


    /**
     * Delete a part from the list of parts.
     @param selectedPart The part to be removed.
     @return A boolean indicating status of part removal.
     */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Delete a product from the list of parts.
     @param selectedProduct The product to be removed.
     @return A boolean indicating status of product removal.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Get a list of all parts in the inventory.
     @return A list of parts.
     */
    public static ObservableList<Part> getAllParts() { return allParts; }


    /**
     * Get a list of all products in the inventory.
     @return A list of products.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}

