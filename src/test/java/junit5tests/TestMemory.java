package junit5tests;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMemory {
    private final Memory mem = new Memory();
    private Operation o;
    private Operation o2;
    @BeforeEach
    public void setUp() throws Exception {
        List<Expression> params1 =
                new ArrayList<>(Arrays.asList(new MyNumber(3), new MyNumber(4), new MyNumber(5)));
        List<Expression> params2 =
                new ArrayList<>(Arrays.asList(new MyNumber(5), new MyNumber(4)));
        List<Expression> params3 =
                new ArrayList<>(Arrays.asList(new Plus(params1), new Minus(params2), new MyNumber(7)));
        o = new Divides(params3);
        o2 = new Divides(params2);
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
        Memory mem2 = new Memory();
        mem2.load("Save1.txt");
        assertEquals(mem.getLog(false),mem2.getLog(false));
    }



}
