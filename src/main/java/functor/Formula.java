package functor;

import java.util.ArrayList;

import functor.function.Conjunction;
import functor.function.Disjunction;
import functor.function.Negation;
import functor.function.Assertion;

/**
 * <code>Predicate<Boolean> predicate = new Predicate<Boolean>() {
 *     public boolean evaluate(Boolean[] bools) {
 *         return (!bools[0] && bools[2]) ||
 *             (!bools[1] && bools[2]) ||
 *             (bools[0] && bools[1] && !bools[2]);
 *     }
 * };
 *
 * Predicate<Boolean> predicate = new Predicate<Boolean>() {
 *     public boolean evaluate(Boolean[] bools) {
 *         return new Or(
 *             new And(new Not(0), new Is(2)),
 *             new And(new Not(1), new Is(2)),
 *             new And(new Is(0), new Is(1), new Not(2))).evaluate(bools);
 *         }
 *     }
 * }
 *
 * return predicate.evaluate(args);</code>
 *
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public class Formula {
    public static Function createFunction(boolean[][] booleans) {
        int[][] terms = new int[booleans.length][booleans[0].length];
        for (int i = 0; i < booleans.length; i++) {
            for (int j = 0; j < booleans[i].length; j++) {
                terms[i][j] = booleans[i][j] ? 1 : 0;
            }
        }

        return createFunction(terms);
    }

    public static Function createFunction(int[][] integers) {
        String[] variables = new String[integers[0].length];
        for (int i = 0; i < variables.length; i++)
            variables[i] = String.valueOf(i);

        return createFunction(variables, integers);
    }

    public static Function createFunction(String[] variables, int[][] integers) {
        Function[] ors = new Function[integers.length];
        for (int i = 0; i < integers.length; i++) {
            ArrayList<Function> ands = new ArrayList<Function>(integers[i].length);
            for (int j = 0; j < integers[i].length; j++) {
                if (integers[i][j] != -1) {
                    if (integers[i][j] == 1)
                        ands.add(new Assertion(variables[j], j));
                    else
                        ands.add(new Negation(variables[j], j));
                }
            }
            ors[i] = new Conjunction(ands.toArray(new Function[0]));
        }
        return new Disjunction(ors);
    }
}
