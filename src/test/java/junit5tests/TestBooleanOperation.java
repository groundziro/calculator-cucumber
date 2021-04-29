package junit5tests;
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class TestBooleanOperation {
    private BooleanOperation b1;
    private BooleanOperation b2;
    private final Calculator c = new Calculator();

    @BeforeEach
    public void setUp()throws Exception{
        List<Expression> params1 = new ArrayList<>(Collections.singletonList(new MyBoolean(true)));
        List<Expression> params2 = new ArrayList<>(Collections.singletonList(new MyBoolean(false)));



        b1 = new Not(params1);
        List<Expression> params3 = new ArrayList<>(Arrays.asList(b1,new MyBoolean(true)));
        b2 = new And(params3);
        List<Expression> params4 = new ArrayList<>(Collections.singletonList(b2));
        b1 = new Not(params4);
    }

    @Test
    public void test(){
        assertEquals(1,c.eval(b2));
        assertEquals(0,c.eval(b1));
    }
}
