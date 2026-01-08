package ipbhalle.de.webassemblydemo;

import org.teavm.interop.Export;

public class WebAssemblyDemo {

    public static void main(String[] args) {
        System.out.println("TeaVM/WebAssembly Demo classpath is successfully loaded.");
    }

    @Export(name = "addNumbers")
    public static int add(int a, int b) {
        return a + b * 2;
    }
}
