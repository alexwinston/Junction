package functor;

import java.util.Collection;

/**
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public class Preposition {
    // TODO with(Collection<T>, Closure<Collection<T>>)
    public static <T> T with(T subject, Closure<T> c) {
        c.execute(subject);

        return subject;
    }

    public static <T> T when(T subject, Predicate<T> p, Closure<T> c) {
        if (p.evaluate(subject))
            Preposition.with(subject, c);

        return subject;
    }

    public static <T> T[] foreach(T[] subjects, Closure<T> c) {
        for (T subject : subjects) {
            Preposition.with(subject, c);
        }

        return subjects;
    }

    public static <T> T[] foreach(T[] subjects, Predicate<T> p, Closure<T> c) {
        for (T subject : subjects) {
            Preposition.when(subject, p, c);
        }

        return subjects;
    }

    public static <T> Collection<T> foreach(Collection<T> subjects, Closure<T> c) {
        for (T subject : subjects) {
            Preposition.with(subject, c);
        }

        return subjects;
    }

    public static <T> Collection<T> foreach(
            Collection<T> subjects, Predicate<T> p, Closure<T> c) {
        for (T subject : subjects) {
            Preposition.when(subject, p, c);
        }

        return subjects;
    }

    public static <T> boolean each(T subject, Predicate<T> p, Closure<T> c) {
        if (p.evaluate(subject))
            Preposition.with(subject, c);
        else
            return false;

        return true;
    }
}
