package Model;

/**
 * Class InhousePart.java
 @author Jieun Park
 */

public class InhousePart extends Part{
    private int machineId;

    /**
     * InhousePart constructor
     @param id Initialize ID object
     @param name Initialize name object
     @param price Initialize price object
     @param stock Initialize inventory object
     @param min Initialize minimum object
     @param max Initialize maximum object
     @param machineId Initialize machine ID object
     */
    public InhousePart(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = getMachineId();
    }

    /**
     @return The machine ID
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     @param machineId The machine ID to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
