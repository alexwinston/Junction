package functor.function;

import functor.Function;

/**
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public class Negation implements Function {
    private String var;
    private int arg;
    public Negation(int arg) {
        this(String.valueOf(arg), arg);
    }
    public Negation(String var, int arg) {
        this.var = var;
        this.arg = arg;
    }
    public boolean evaluate(boolean... bools) {
        return !bools[arg];
    }

    public String toString() {
        return String.valueOf(var) + '\'';
    }
}
