package functor;

/**
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public class Student extends Person {
    private Grade grade;
    private int daysAbsent;

    public Student(Grade grade, int daysAbsent) {
        super("name");
        
        this.grade = grade;
        this.daysAbsent = daysAbsent;
    }

    public Grade getGrade() {
        return this.grade;
    }

    public int getDaysAbsent() {
        return this.daysAbsent;
    }
}
