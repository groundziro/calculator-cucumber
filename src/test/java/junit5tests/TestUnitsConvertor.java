package junit5tests;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnitsConvertor {

    private UnitsConvertor uc;
    private Unit u1;
    private String goal1;

    @BeforeEach
    public void setUp(){
        uc = new UnitsConvertor();
    }

    @Test
    public void test1(){
        double value = 39.37008;
        u1 =  new Unit("length", "Metres", 1.0);
        assertEquals(value, uc.convert(u1, "Inches").value.doubleValue());
    }

    @Test
    public void test2(){
        double value = 1000.0;
        u1 = new Unit("length", "Metres", 1.0);
        assertEquals(value, uc.convert(u1, "Kilometres").value.doubleValue());
    }

    @Test
    public void test3(){
        double value = 0.08333333333333333;
        u1 = new Unit("length", "Inches", 1.0);
        assertEquals(value, uc.convert(u1, "Feet").value.doubleValue());
    }

    @Test
    public void testTemperature(){
        double value = 33.8;
        u1 = new Unit("temperature", "Celsius", 1.0);
        assertEquals(value, uc.convert(u1, "Fahrenheit").value.doubleValue());
    }

    @Test
    public void testTemperature2(){
        double value = 274.15;
        u1 = new Unit("temperature", "Celsius", 1.0);
        assertEquals(value, uc.convert(u1, "Kelvin").value.doubleValue());
    }

    @Test
    public void testTemperature3(){
        double value = -17.22222222222222;
        u1 = new Unit("temperature", "Fahrenheit", 1.0);
        assertEquals(value, uc.convert(u1, "Celsius").value.doubleValue());
    }

    @Test
    public void testTemperature4(){
        double value = 255.92777777777775;
        u1 = new Unit("temperature", "Fahrenheit", 1.0);
        assertEquals(value, uc.convert(u1, "Kelvin").value.doubleValue());
    }

    @Test
    public void testTemperature5(){
        double value = -272.15;
        u1 = new Unit("temperature", "Kelvin", 1.0);
        assertEquals(value, uc.convert(u1, "Celsius").value.doubleValue());
    }

    @Test
    public void testTemperature6(){
        double value = -457.86999999999995;
        u1 = new Unit("temperature", "Kelvin", 1.0);
        assertEquals(value, uc.convert(u1, "Fahrenheit").value.doubleValue());
    }
}