package ipbhalle.de.webassemblydemo;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

public class FrankJo implements JSObject {

    private int alter;

    public FrankJo(int alter) {
        this.alter = alter;
    }

    @JSProperty
    public int getAlter() {
        return alter;
    }
}
