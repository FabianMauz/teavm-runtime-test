package ipbhalle.de.webassemblydemo;

import org.teavm.interop.Export;
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
    
     @Export(name = "doDynamicOptimisationWasm")
      public static int doDynamicOptimisationWasm(int timepoints, int simulations) {       
        return (int) DynamicOptimisation.start(timepoints, simulations);
    }

}
