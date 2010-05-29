package functor;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import junit.framework.TestCase;

import static functor.Preposition.foreach;

/**
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public class FunctionTest extends TestCase {
    public void testConjunction() {
        Predicate<Student> isPassing =
            new Predicate<Student>() {
                public boolean evaluate(Student s) {
                    return ((s.getGrade() == Grade.A || s.getGrade() == Grade.B));
                }
            };

        Predicate<Student> isNotAbsent = new Predicate<Student>() {
            public boolean evaluate(Student s) {
                return s.getDaysAbsent() == 0;
            }
        };
        
        Predicate<Student> isAbsent = new Predicate<Student>() {
            public boolean evaluate(Student s) {
                return s.getDaysAbsent() != 0;
            }
        };

        final List<Student> honorRoll = new ArrayList<Student>();
        Closure<Student> addToHonorRoll = new Closure<Student>() {
            public void execute(Student student) {
                honorRoll.add(student);
            }
        };

        List<Student> students = Arrays.asList(new Student[] {
            new Student(Grade.A, 1), new Student(Grade.B, 0),
            new Student(Grade.C, 0) });

        foreach(students, isPassing.and(isNotAbsent), addToHonorRoll);
        assertEquals(1, honorRoll.size());
        assertEquals(Grade.B, honorRoll.get(0).getGrade());
        honorRoll.clear();

        foreach(students, isPassing.or(isNotAbsent), addToHonorRoll);
        assertEquals(3, honorRoll.size());
        assertEquals(Grade.A, honorRoll.get(0).getGrade());
        assertEquals(Grade.B, honorRoll.get(1).getGrade());
        assertEquals(Grade.C, honorRoll.get(2).getGrade());
        honorRoll.clear();

        foreach(students, isPassing.and(isNotAbsent).or(
            isPassing.and(isAbsent)), addToHonorRoll);
        assertEquals(2, honorRoll.size());
        assertEquals(Grade.A, honorRoll.get(0).getGrade());
        assertEquals(Grade.B, honorRoll.get(1).getGrade());
        honorRoll.clear();
    }
}
