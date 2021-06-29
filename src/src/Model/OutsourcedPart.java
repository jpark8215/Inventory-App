package Model;

/**
 * Class OutsourcedPart.java
 @author Jieun Park
 */

public class OutsourcedPart extends Part {

    private String companyName;

    /**
     * OutsourcedPart constructor
     @param id Initialize ID object
     @param name Initialize name object
     @param price Initialize price object
     @param stock Initialize inventory object
     @param min Initialize minimum object
     @param max Initialize maximum object
     @param companyName Initialize company name object
     */
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;

    }

    /**
     * Return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     @param companyName The company name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}