package Model;

/**
 * Supplied Class Part.java
 @author Jieun Park
 */

public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Part Constructor
     @param id Initialize ID object
     @param name Initialize name object
     @param price Initialize price object
     @param stock Initialize inventory object
     @param min Initialize minimum object
     @param max Initialize maximum object
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     Return the ID
     */
    public int getId() {
        return id;
    }

    /**
     @param id The ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     Return the name
     */
    public String getName() {
        return name;
    }

    /**
     @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     Return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     @param price The price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     Return the inventory
     */
    public int getStock() {
        return stock;
    }

    /**
     @param stock The stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     Return the minimum
     */
    public int getMin() {
        return min;
    }

    /**
     @param min The minimum to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     Return the maximum
     */
    public int getMax() {
        return max;
    }

    /**
     @param max The maximum to set
     */
    public void setMax(int max) {
        this.max = max;
    }

}
