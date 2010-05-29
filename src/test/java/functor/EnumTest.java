package functor;

import junit.framework.TestCase;

/**
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public class EnumTest extends TestCase {
    public void testEnum() {
        final SessionId mySessionId = new SessionId(13);

        for (final CommandEnum command : CommandEnum.values())
            command.execute(mySessionId);

        final SessionId yourSessionId = new SessionId(31);

        CommandEnum.valueOf("START").execute(yourSessionId);
        CommandEnum.valueOf("FINISH").execute(yourSessionId);
    }

    /**
     * Demonstrates using JDK5 {@code enum}s for the Command Pattern.
     *
     * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
     */
    public enum CommandEnum {
        /**
         * The {@code START} command.
         */
        START() {
            @Override
            public void execute(final SessionId sessionId) {
                System.out.println("Start: " + sessionId);
            }
        },
        /**
         * The {@code FINISH} command.
         */
        FINISH() {
            @Override
            public void execute(final SessionId sessionId) {
                System.out.println("Finish: " + sessionId);
            }
        };

        /**
         * Executes the command.
         */
        public abstract void execute(final SessionId sessionId);
    }

    private static final class SessionId {
        private final int id;

        SessionId(final int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return Integer.toString(id);
        }
    }
}
