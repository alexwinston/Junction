package functor;

/**
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public class AccountImpl implements Account {
    private int number;
    private Status status;

    public AccountImpl(int number, Status status) {
        this.number = number;
        this.status = status;
    }

    public int getNumber() {
        return this.number;
    }

    public Status getStatus() {
        return this.status;
    }
}
