package Model;

/** Supplied Class Part.java*/
/** @author Jieun Park*/

public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Part Constructor
     * */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Return the id
     * */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the name
     * */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the price
     * */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     * */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Return the stock
     * */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     * */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Return the min
     * */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     * */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Return the max
     * */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     * */
    public void setMax(int max) {
        this.max = max;
    }
}
