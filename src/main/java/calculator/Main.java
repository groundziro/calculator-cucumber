package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***************************************
 * A very simple Calculator in Java    *
 * Tom Mens, February 2021             *
 * University of Mons - UMONS          *
 * Département d'Informatique          *
 * Faculté des Sciences                *
 ***************************************/

public class Main {

  public static void main(String[] args) {

  	Expression e;
  	Calculator c = new Calculator();

	try{
		// Here is an example of how to use the calculator:

		e = new MyNumber(8);
		c.formatPrint(e, Notation.PREFIX);
		c.print(e);
		c.eval(e);

	    List<Expression> params = new ArrayList<>();
	    Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
	    e = new Plus(params,Notation.POSTFIX);
		c.printExpressionDetails(e);
		c.eval(e);
	
		List<Expression> params2 = new ArrayList<>();
		Collections.addAll(params2, new MyNumber(5), new MyNumber(3));
		e = new Minus(params2, Notation.POSTFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params3 = new ArrayList<>();
		Collections.addAll(params3, new Plus(params,Notation.POSTFIX), new Minus(params2,Notation.POSTFIX));
		e = new Times(params3,Notation.POSTFIX);
		c.printExpressionDetails(e);
		c.eval(e);

		List<Expression> params4 = new ArrayList<>();
		Collections.addAll(params4, new Plus(params,Notation.POSTFIX), new Minus(params2,Notation.POSTFIX), new MyNumber(5));
		e = new Divides(params4,Notation.POSTFIX);
		c.print(e);
		c.formatPrint(e,Notation.POSTFIX);
		c.eval(e);

		List<Expression> params5 = new ArrayList<>();
		List<Expression> min=new ArrayList<>();
		Collections.addAll(min,new MyNumber(2),new MyNumber(2));
		Collections.addAll(params5,new MyNumber(8), new Minus(min));
		e=new Divides(params5,Notation.PREFIX);
		c.print(e);
		c.eval(e);
	}

	catch(IllegalConstruction exception) {
		System.out.println("cannot create operations without parameters");
		}
 	}

}
