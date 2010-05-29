package functor;

/**
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public abstract class Predicate<T> {
    public abstract boolean evaluate(T t);
    
    public Conjunction<T> and(final Predicate<T> p) {
        return Conjunction.and(this, p);
    }

    public Conjunction<T> or(final Predicate<T> p) {
        return Conjunction.or(this, p);
    }
}
