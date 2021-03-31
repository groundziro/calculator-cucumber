package junit5tests;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMemory {
    private final Memory mem = new Memory(2);
    private Operation o;
    private Operation o2;
    @BeforeEach
    public void setUp() throws Exception {
        List<Expression> params1 =
                new ArrayList<>(Arrays.asList(new MyNumber(3), new MyNumber(4), new MyNumber(5)));
        List<Expression> params2 =
                new ArrayList<>(Arrays.asList(new Plus(params1), new Plus(params1)));
        List<Expression> params3 =
                new ArrayList<>(Arrays.asList(new Plus(params1), new Minus(params2), new MyNumber(7)));
        ArrayList<Expression> params4 = new ArrayList<>(Arrays.asList(new Implies(new ArrayList<>(Arrays.asList(new And(new ArrayList<>(Arrays.asList(new MyNumber(1), new MyNumber(1)))), new MyNumber(0)))),new MyNumber(1)));
        o = new Times(params3);
        o2 = new Or(params4);
        mem.setMax(2);
        mem.add(o);
        mem.add(o2);
    }

    @Test
    public void test(){
        Expression test = mem.get(0);
        Expression test2= mem.get(1);
        assertEquals(o2,test);
        assertEquals(o,test2);
        mem.add(new MyNumber(3));
        assertEquals(new MyNumber(3),mem.get(0));
        assertEquals(o2,mem.get(1));
    }

    @Test
    public void testMax(){
        assertEquals(2,mem.size());
        mem.print();
        mem.setMax(1);
        assertEquals(1,mem.size());
        System.out.println("------------");
        mem.print();
    }

    @Test
    public void testSave(){
        mem.save("Save1.txt");
    }

    @Test
    public void testLoad(){
        testSave();
        Memory mem2 = new Memory();
        mem2.load("Save1.txt");
        assertEquals(mem.getLog(),mem2.getLog());
    }



}
