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

    // =============================================== Square millimetres ==============================================
    @Test
    public void squareMillimetres_to_squareMillimetres() {
        goal = "345";
        u = new Unit("area", "Square millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMillimetres_to_squareCentimetres() {
        goal = "3.45";
        u = new Unit("area", "Square millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMillimetres_to_squareMetres() {
        goal = "0.000345";
        u = new Unit("area", "Square millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMillimetres_to_Hectares() {
        goal = "0";
        u = new Unit("area", "Square millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Hectares").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMillimetres_to_squareKilometres() {
        goal = "0";
        u = new Unit("area", "Square millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMillimetres_to_squareInches() {
        goal = "0.534751";
        u = new Unit("area", "Square millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMillimetres_to_squareFeet() {
        goal = "0.003714";
        u = new Unit("area", "Square millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMillimetres_to_squareYards() {
        goal = "0.000413";
        u = new Unit("area", "Square millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMillimetres_to_Acres() {
        goal = "0";
        u = new Unit("area", "Square millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Acres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMillimetres_to_squareMiles() {
        goal = "0";
        u = new Unit("area", "Square millimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // =============================================== Square centimetres ==============================================
    @Test
    public void squareCentimetres_to_squareMillimetres() {
        goal = "34500";
        u = new Unit("area", "Square centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareCentimetres_to_squareCentimetres() {
        goal = "345";
        u = new Unit("area", "Square centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareCentimetres_to_squareMetres() {
        goal = "0.0345";
        u = new Unit("area", "Square centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareCentimetres_to_Hectares() {
        goal = "0.000003";
        u = new Unit("area", "Square centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Hectares").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareCentimetres_to_squareKilometres() {
        goal = "0";
        u = new Unit("area", "Square centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareCentimetres_to_squareInches() {
        goal = "53.475104";
        u = new Unit("area", "Square centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareCentimetres_to_squareFeet() {
        goal = "0.371355";
        u = new Unit("area", "Square centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareCentimetres_to_squareYards() {
        goal = "0.041262";
        u = new Unit("area", "Square centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareCentimetres_to_Acres() {
        goal = "0.000009";
        u = new Unit("area", "Square centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Acres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareCentimetres_to_squareMiles() {
        goal = "0";
        u = new Unit("area", "Square centimetres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // ================================================= Square metres =================================================
    @Test
    public void squareMetres_to_squareMillimetres() {
        goal = "345000000";
        u = new Unit("area", "Square metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMetres_to_squareCentimetres() {
        goal = "3450000";
        u = new Unit("area", "Square metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMetres_to_squareMetres() {
        goal = "345";
        u = new Unit("area", "Square metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMetres_to_Hectares() {
        goal = "0.0345";
        u = new Unit("area", "Square metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Hectares").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMetres_to_squareKilometres() {
        goal = "0.000345";
        u = new Unit("area", "Square metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMetres_to_squareInches() {
        goal = "534751.069502";
        u = new Unit("area", "Square metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMetres_to_squareFeet() {
        goal = "3713.549094";
        u = new Unit("area", "Square metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMetres_to_squareYards() {
        goal = "412.616566";
        u = new Unit("area", "Square metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMetres_to_Acres() {
        goal = "0.085251";
        u = new Unit("area", "Square metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Acres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareMetres_to_squareMiles() {
        goal = "0.000133";
        u = new Unit("area", "Square metres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // ==================================================== Hectares ===================================================
    @Test
    public void hectares_to_squareMillimetres() {
        goal = "3450000000000";
        u = new Unit("area", "Hectares", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void hectares_to_squareCentimetres() {
        goal = "34500000000";
        u = new Unit("area", "Hectares", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void hectares_to_squareMetres() {
        goal = "3450000";
        u = new Unit("area", "Hectares", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void hectares_to_Hectares() {
        goal = "345";
        u = new Unit("area", "Hectares", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Hectares").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void hectares_to_squareKilometres() {
        goal = "3.45";
        u = new Unit("area", "Hectares", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void hectares_to_squareInches() {
        goal = "5347510695.021391";
        u = new Unit("area", "Hectares", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void hectares_to_squareFeet() {
        goal = "37135490.937649";
        u = new Unit("area", "Hectares", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void hectares_to_squareYards() {
        goal = "4126165.659739";
        u = new Unit("area", "Hectares", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void hectares_to_Acres() {
        goal = "852.513567";
        u = new Unit("area", "Hectares", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Acres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void hectares_to_squareMiles() {
        goal = "1.332052";
        u = new Unit("area", "Hectares", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    // =============================================== Square kilometres ===============================================
    @Test
    public void squareKilometres_to_squareMillimetres() {
        goal = "345000000000000";
        u = new Unit("area", "Square kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square millimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareKilometres_to_squareCentimetres() {
        goal = "3450000000000";
        u = new Unit("area", "Square kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square centimetres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareKilometres_to_squareMetres() {
        goal = "345000000";
        u = new Unit("area", "Square kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square metres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareKilometres_to_Hectares() {
        goal = "34500";
        u = new Unit("area", "Square kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Hectares").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareKilometres_to_squareKilometres() {
        goal = "345";
        u = new Unit("area", "Square kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square kilometres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareKilometres_to_squareInches() {
        goal = "534751069502.13904";
        u = new Unit("area", "Square kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square inches").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareKilometres_to_squareFeet() {
        goal = "3713549093.764854";
        u = new Unit("area", "Square kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square feet").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareKilometres_to_squareYards() {
        goal = "412616565.973873";
        u = new Unit("area", "Square kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square yards").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareKilometres_to_Acres() {
        goal = "85251.356657";
        u = new Unit("area", "Square kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Acres").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }

    @Test
    public void squareKilometres_to_squareMiles() {
        goal = "133.205245";
        u = new Unit("area", "Square kilometres", value);
        answer = String.format(Locale.ENGLISH,"%f", uc.convert(u, "Square miles").value);
        answer = format_answer(answer);
        assertEquals(goal, answer);
    }
}