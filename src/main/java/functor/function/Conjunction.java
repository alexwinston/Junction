package functor.function;

import functor.Function;

/**
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public class Conjunction implements Function {
    private Function[] functions;
    public Conjunction(Function... functions) {
        this.functions = functions;
    }
    public boolean evaluate(boolean... bools) {
        for (Function function : functions) {
            if (!function.evaluate(bools)) return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Function function : functions) {
            if (sb.length() != 0)
                sb.append('*');
            sb.append(function.toString());
        }
        return '(' + sb.toString() + ')';
    }
}
