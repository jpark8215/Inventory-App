package Model;

public class InhousePart extends Part{
    private int machineId;

    public InhousePart(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    public int getMachineId() {
        return machineId;
    }
    
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
