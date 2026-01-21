package ipbhalle.de.webassemblydemo.dynamicOptimisation;

public class State {

    private final float fixCosts;
    private final float efficiency;

    public State(float fixCosts, float efficiency) {
        this.fixCosts = fixCosts;
        this.efficiency = efficiency;
    }

    public float caclulateValue(float price1, float price2) {
        return (price1 - price2) * efficiency - fixCosts;
    }
}
