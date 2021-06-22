package Model;

/**Class OutsourcedPart.java*/
/** @author Jieun Park*/

public class OutsourcedPart extends Part {

    private String companyName;

    /**
     * OutsourcedPart constructor
     * */
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;

    }

    /**
     * Return the companyName
     * */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}