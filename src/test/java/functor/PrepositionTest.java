package functor;

import junit.framework.TestCase;

import java.util.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static functor.Preposition.foreach;
import static functor.Preposition.when;
import static functor.Preposition.with;

/**
 * @author Alex Winston (alexwinston@dev.java.net)
 */
public class PrepositionTest extends TestCase {
    public void testPreposition() {
    	Predicate<Student> isPassing = new Predicate<Student>() {
            public boolean evaluate(Student s) {
                return ((s.getGrade() == Grade.A || s.getGrade() == Grade.B));
            }
        };

        Predicate<Student> isNotAbsent = new Predicate<Student>() {
            public boolean evaluate(Student s) {
                return s.getDaysAbsent() == 0;
            }
        };

        final List<Student> honorRoll = new ArrayList<Student>();
        Closure<Student> addToHonorRoll = new Closure<Student>() {
            public void execute(Student student) {
                honorRoll.add(student);
            }
        };

        Student student = new Student(Grade.D, 0);

        when(student, isNotAbsent, addToHonorRoll);
        assertEquals(1, honorRoll.size());
        assertEquals(0, honorRoll.get(0).getDaysAbsent());
        honorRoll.clear();

        List<Student> students = Arrays.asList(new Student[] {
            new Student(Grade.A, 1), new Student(Grade.B, 0),
                new Student(Grade.C, 0) });

        foreach(students, isPassing, addToHonorRoll);
        assertEquals(2, honorRoll.size());
        assertEquals(Grade.A, honorRoll.get(0).getGrade());
        assertEquals(Grade.B, honorRoll.get(1).getGrade());
        honorRoll.clear();
    }

    public void testRecursivePreposition() {
        final Predicate<Method> isPublic = new Predicate<Method>() {
                public boolean evaluate(Method m) {
                    return Modifier.isPublic(m.getModifiers());
                }
            };

        final Predicate<Method> isProtected = new Predicate<Method>() {
            public boolean evaluate(Method m) {
                return Modifier.isProtected(m.getModifiers());
            }
        };

        final List<Method> methods = new ArrayList<Method>();
        final Closure<Method> addMethod = new Closure<Method>() {
            public void execute(Method m)  {
                methods.add(m);
            }
        };

        Closure<Class> addMethods = new Closure<Class>() {
            public void execute(Class c)  {
                foreach(c.getDeclaredMethods(), isPublic.or(isProtected), addMethod);
            }
        };

        with(C.class, addMethods);
        assertEquals(4, methods.size());
        assertEquals("getA", methods.get(0).getName());
        assertEquals("getAA", methods.get(1).getName());
        assertEquals("getB", methods.get(2).getName());
        assertEquals("getBB", methods.get(3).getName());
        methods.clear();

        foreach(C.class.getDeclaredMethods(), isProtected, addMethod);
        assertEquals(2, methods.size());
        assertEquals("getAA", methods.get(0).getName());
        assertEquals("getBB", methods.get(1).getName());
        methods.clear();

        foreach(C.class.getInterfaces(), addMethods);
        assertEquals(2, methods.size());
        assertEquals("getA", methods.get(0).getName());
        assertEquals("getB", methods.get(1).getName());
        methods.clear();
    }

    interface A {
        public void getA();
    }

    interface B {
        public void getB();
    }

    class C implements A, B {
        public void getA() { }
        protected void getAA() { }
        private void getAAA() { }

        public void getB() { }
        protected void getBB() { }
        private void getBBB() { }
    }

    public void testInterfacePrepositions() {
        final Predicate<Account> isActive = new Predicate<Account>() {
            public boolean evaluate(Account a) {
                return a.getStatus() == Status.ACTIVE;
            }
        };

        final List<Account> activeAccounts = new ArrayList<Account>();
        final Closure<Account> addAccount = new Closure<Account>() {
            public void execute(Account a)  {
                activeAccounts.add(a);
            }
        };

        Account[] accounts = new AccountImpl[] {
            new AccountImpl(1, Status.INACTIVE),
            new AccountImpl(2, Status.ACTIVE) };
        foreach(accounts, isActive, addAccount);
        assertEquals(1, activeAccounts.size());
        assertEquals(2, activeAccounts.get(0).getNumber());
        activeAccounts.clear();
    }
}
