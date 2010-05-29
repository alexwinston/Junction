package functor;

/**
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public abstract class Conjunction<T> extends Predicate<T> {
    public static <T> Conjunction<T> and(final Predicate<T> p1, final Predicate<T> p2) {
        return new Conjunction<T>() {
            public boolean evaluate(T t) {
                return p1.evaluate(t) && p2.evaluate(t);
            }
        };
    }

    public static <T> Conjunction<T> or(final Predicate<T> p1, final Predicate<T> p2) {
        return new Conjunction<T>() {
            public boolean evaluate(T t) {
                return p1.evaluate(t) || p2.evaluate(t);
            }
        };
    }
}
