package ipbhalle.de.webassemblydemo;

import org.teavm.jso.JSExport;

public class WebAssemblyDemo {

    public static void main(String[] args) {

    }

    @JSExport
    public static FrankJo add(int a, int b) {
        return new FrankJo(42);
    }

    @JSExport
    public static float doDynamicOptimisation(int timepoints, int simulations) {
        return DynamicOptimisation.start(timepoints, simulations);
    }

}
