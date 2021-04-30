package junit5tests;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnitsConvertor {

    private UnitsConvertor uc;
    private Unit u;
    private String goal;
    private double value;
    private String answer;

    private String format_answer(String answer) {
        String returns = answer;
        while (true) {
            if (returns.endsWith("0")) {
                returns = returns.substring(0, returns.length() - 1);
            }
            else if (returns.endsWith(".")) {
                returns = returns.substring(0, returns.length() - 1);
                break;
            }
            else {
                break;
            }
        }
        return returns;
    }

    @BeforeEach
    public void setUp() {
        uc = new UnitsConvertor();
        value = 345.0;
    }

    // ================================================== Millimetres ==================================================
    @Test
    public void millimetres_to_millimetres() {
        goal = "345";
        u = new Unit("length", "Millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void millimetres_to_centimetres() {
        goal = "34.5";
        u = new Unit("length", "Millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void millimetres_to_metres() {
        goal = "0.345";
        u = new Unit("length", "Millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void millimetres_to_kilometres() {
        goal = "0.000345";
        u = new Unit("length", "Millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void millimetres_to_inches() {
        goal = "13.582677";
        u = new Unit("length", "Millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void millimetres_to_feet() {
        goal = "1.13189";
        u = new Unit("length", "Millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void millimetres_to_yards() {
        goal = "0.377297";
        u = new Unit("length", "Millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void millimetres_to_miles() {
        goal = "0.000214";
        u = new Unit("length", "Millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void millimetres_to_nauticalMiles() {
        goal = "0.000186";
        u = new Unit("length", "Millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Nautical Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // ================================================== Centimetres ==================================================
    @Test
    public void centimetres_to_millimetres() {
        goal = "3450";
        u = new Unit("length", "Centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void centimetres_to_centimetres() {
        goal = "345";
        u = new Unit("length", "Centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void centimetres_to_metres() {
        goal = "3.45";
        u = new Unit("length", "Centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void centimetres_to_kilometres() {
        goal = "0.00345";
        u = new Unit("length", "Centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void centimetres_to_inches() {
        goal = "135.826772";
        u = new Unit("length", "Centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void centimetres_to_feet() {
        goal = "11.318898";
        u = new Unit("length", "Centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void centimetres_to_yards() {
        goal = "3.772966";
        u = new Unit("length", "Centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void centimetres_to_miles() {
        goal = "0.002144";
        u = new Unit("length", "Centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void centimetres_to_nauticalMiles() {
        goal = "0.001863";
        u = new Unit("length", "Centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Nautical Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // ===================================================== Metres ====================================================
    @Test
    public void metres_to_millimetres() {
        goal = "345000";
        u = new Unit("length", "Metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void metres_to_centimetres() {
        goal = "34500";
        u = new Unit("length", "Metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void metres_to_metres() {
        goal = "345";
        u = new Unit("length", "Metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void metres_to_kilometres() {
        goal = "0.345";
        u = new Unit("length", "Metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void metres_to_inches() {
        goal = "13582.677165";
        u = new Unit("length", "Metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void metres_to_feet() {
        goal = "1131.889764";
        u = new Unit("length", "Metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void metres_to_yards() {
        goal = "377.296588";
        u = new Unit("length", "Metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void metres_to_miles() {
        goal = "0.214373";
        u = new Unit("length", "Metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void metres_to_nauticalMiles() {
        goal = "0.186285";
        u = new Unit("length", "Metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Nautical Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // =================================================== Kilometres ==================================================
    @Test
    public void kilometres_to_millimetres() {
        goal = "345000000";
        u = new Unit("length", "Kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void kilometres_to_centimetres() {
        goal = "34500000";
        u = new Unit("length", "Kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void kilometres_to_metres() {
        goal = "345000";
        u = new Unit("length", "Kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void kilometres_to_kilometres() {
        goal = "345";
        u = new Unit("length", "Kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void kilometres_to_inches() {
        goal = "13582677.165354";
        u = new Unit("length", "Kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void kilometres_to_feet() {
        goal = "1131889.76378";
        u = new Unit("length", "Kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void kilometres_to_yards() {
        goal = "377296.587927";
        u = new Unit("length", "Kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void kilometres_to_miles() {
        goal = "214.373061";
        u = new Unit("length", "Kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void kilometres_to_nauticalMiles() {
        goal = "186.285097";
        u = new Unit("length", "Kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Nautical Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // ===================================================== Inches ====================================================
    @Test
    public void inches_to_millimetres() {
        goal = "8763";
        u = new Unit("length", "Inches", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void inches_to_centimetres() {
        goal = "876.3";
        u = new Unit("length", "Inches", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void inches_to_metres() {
        goal = "8.763";
        u = new Unit("length", "Inches", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void inches_to_kilometres() {
        goal = "0.008763";
        u = new Unit("length", "Inches", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void inches_to_inches() {
        goal = "345";
        u = new Unit("length", "Inches", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void inches_to_feet() {
        goal = "28.75";
        u = new Unit("length", "Inches", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void inches_to_yards() {
        goal = "9.583333";
        u = new Unit("length", "Inches", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void inches_to_miles() {
        goal = "0.005445";
        u = new Unit("length", "Inches", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void inches_to_nauticalMiles() {
        goal = "0.004732";
        u = new Unit("length", "Inches", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Nautical Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // ====================================================== Feet =====================================================
    @Test
    public void feet_to_millimetres() {
        goal = "105156";
        u = new Unit("length", "Feet", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void feet_to_centimetres() {
        goal = "10515.6";
        u = new Unit("length", "Feet", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void feet_to_metres() {
        goal = "105.156";
        u = new Unit("length", "Feet", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void feet_to_kilometres() {
        goal = "0.105156";
        u = new Unit("length", "Feet", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void feet_to_inches() {
        goal = "4140";
        u = new Unit("length", "Feet", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void feet_to_feet() {
        goal = "345";
        u = new Unit("length", "Feet", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void feet_to_yards() {
        goal = "115";
        u = new Unit("length", "Feet", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void feet_to_miles() {
        goal = "0.065341";
        u = new Unit("length", "Feet", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void feet_to_nauticalMiles() {
        goal = "0.05678";
        u = new Unit("length", "Feet", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Nautical Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // ===================================================== Yards =====================================================
    @Test
    public void yards_to_millimetres() {
        goal = "315468";
        u = new Unit("length", "Yards", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void yards_to_centimetres() {
        goal = "31546.8";
        u = new Unit("length", "Yards", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void yards_to_metres() {
        goal = "315.468";
        u = new Unit("length", "Yards", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void yards_to_kilometres() {
        goal = "0.315468";
        u = new Unit("length", "Yards", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void yards_to_inches() {
        goal = "12420";
        u = new Unit("length", "Yards", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void yards_to_feet() {
        goal = "1035";
        u = new Unit("length", "Yards", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void yards_to_yards() {
        goal = "345";
        u = new Unit("length", "Yards", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void yards_to_miles() {
        goal = "0.196023";
        u = new Unit("length", "Yards", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void yards_to_nauticalMiles() {
        goal = "0.170339";
        u = new Unit("length", "Yards", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Nautical Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // ===================================================== Miles =====================================================
    @Test
    public void miles_to_millimetres() {
        goal = "555223680";
        u = new Unit("length", "Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void miles_to_centimetres() {
        goal = "55522368";
        u = new Unit("length", "Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void miles_to_metres() {
        goal = "555223.68";
        u = new Unit("length", "Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void miles_to_kilometres() {
        goal = "555.22368";
        u = new Unit("length", "Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void miles_to_inches() {
        goal = "21859200";
        u = new Unit("length", "Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void miles_to_feet() {
        goal = "1821600";
        u = new Unit("length", "Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void miles_to_yards() {
        goal = "607200";
        u = new Unit("length", "Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void miles_to_miles() {
        goal = "345";
        u = new Unit("length", "Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void miles_to_nauticalMiles() {
        goal = "299.79666";
        u = new Unit("length", "Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Nautical Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // ================================================= Nautical Miles ================================================
    @Test
    public void nauticalMiles_to_millimetres() {
        goal = "638940000";
        u = new Unit("length", "Nautical Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void nauticalMiles_to_centimetres() {
        goal = "63894000";
        u = new Unit("length", "Nautical Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void nauticalMiles_to_metres() {
        goal = "638940";
        u = new Unit("length", "Nautical Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void nauticalMiles_to_kilometres() {
        goal = "638.94";
        u = new Unit("length", "Nautical Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void nauticalMiles_to_inches() {
        goal = "25155118.101";
        u = new Unit("length", "Nautical Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void nauticalMiles_to_feet() {
        goal = "2096259.84405";
        u = new Unit("length", "Nautical Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void nauticalMiles_to_yards() {
        goal = "698753.28135";
        u = new Unit("length", "Nautical Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void nauticalMiles_to_miles() {
        goal = "397.0191";
        u = new Unit("length", "Nautical Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void nauticalMiles_to_nauticalMiles() {
        goal = "345";
        u = new Unit("length", "Nautical Miles", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Nautical Miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }
}