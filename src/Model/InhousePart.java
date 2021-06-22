package Model;

/**Class InhousePart.java*/
/** @author Jieun Park*/

public class InhousePart extends Part{
    private int machineId;

    /**
     * InhousePart constructor
     * */
    public InhousePart(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = getMachineId();
    }

    /**
     * Return the machineId
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * @param machineId the machineId to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
