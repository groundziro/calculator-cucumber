package calculator;

import visitor.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Cette classe contient la mémoire de notre programme de manière LIFO
 */
public class Memory {
    private ArrayList<Expression> mem;
    private int max;
    private final Printer p = new Printer(Notation.PREFIX);
    private boolean alreadySaved = false;


    public Memory(int size){
        mem = new ArrayList<>();
        max = size;
    }

    public Memory(){
        mem = new ArrayList<>();
        max = 1;
    }

    /**
     * Check if the memory is empty
     * @return true if the memory's empty
     */
    public boolean isEmpty(){
        return mem.isEmpty();
    }

    /**
     * Add an expression to the memory
     * @param e Expression to add
     */
    public void add(Expression e){
        mem.add(e);
        updateMem();

    }

    /**
     * Removes the oldest expression in our memory if it's full
     */
    private void updateMem(){
        while (isFull() && mem.size()!=max){
            mem = new ArrayList<>(mem.subList(1,mem.size()));
        }
    }

    /**
     * Add a list of expression to the memory
     * @param elist List containing the expressions to add
     */
    public void addAll(List<Expression> elist){
        for (Expression e: elist) {
            add(e);
        }
    }

    /**
     * Check if our memory is full
     * @return true if the memory's full
     */
    public boolean isFull(){
        return mem.size()>=max;
    }

    /**
     * This function returns an Expression at a particular index
     * @param index depth of the expression that we want
     *               size-1 is the top of the stack and 0 is the base
     * @return The wanted expression
     */
    public Expression get(int index){
        int _max = mem.size()-1;
        int cur = _max - index;
        return mem.get(cur);
    }

    /**
     * Updates the maximum size of our memory
     * @param max new size of the memory
     */
    public void setMax(int max) {
        this.max = (Math.max(max, 1));
        updateMem();
    }

    //For testing purposes
    public int size(){
        return mem.size();
    }

    public void print(){
        System.out.println(getLog());
    }

    /**
     * We obtain the log of the memory for saving or printing purposes
     * @return The log of the memory
     */
    public String getLog(){
        StringBuilder log = new StringBuilder();
        for (Expression e : mem){
            e.accept(p);
            log.append(p.getStr()).append("\n");
        }
        return log.toString();
    }


    /**
     * Used to load a memory
     * @param path path of the file that we wanna use
     */
    public void load(String path){
        try{
            File reader = new File(path);
            Scanner scan = new Scanner(reader);
            ArrayList<Expression> expressions = new ArrayList<>();
            while(scan.hasNextLine()){
                String data = scan.nextLine();
                expressions.add(parse(data));
            }
            scan.close();
            setMax(expressions.size());
            addAll(expressions);
        }catch (FileNotFoundException | IllegalConstruction ignored){

        }
    }

    /**
     * Used to parse a string of data in order to load a memory or to use in the calculator
     * @param data A string that we want to parse, corresponding to a line in a file
     * @return The expression that encapsulates all of the expressions in the String
     * @throws IllegalConstruction Caused by the construction of the Expression
     */
    public Expression parse(String data) throws IllegalConstruction {
        ArrayList<String> str = new ArrayList<>(Arrays.asList(data.split(" | , ")));
        String op = str.get(0);
        ArrayList<String> expression = getExp(str.subList(1,str.size()));
        expression.remove(0);
        ArrayList<Expression> total = new ArrayList<>();
        ArrayList<ArrayList<String>> exps = getExps(expression);
        for (ArrayList<String> exp:exps) {
            Expression e = parseRec(exp,new ArrayList<>());
            total.add(e);
        }
        return Calculator.getOp(op,total);
    }


    /**
     *
     * @param data A List containing the elements in a line
     * @return An array containing all of the part of the expression without the commas
     */
    private ArrayList<String> getExp(List<String> data){
        ArrayList<String> str = new ArrayList<>();
        int depth = 0;
        for (String s:data) {
            if (s.equals(")")) {
                depth -= 1;
                if (depth==0)
                    break;
            }
            if (s.equals("("))
                depth+=1;
            if (!s.equals(","))
                str.add(s);
        }
        return str;
    }

    /**
     * Used to return all the subexpressions of a particular expression
     * @param expression A List containing all the elements of a bigger expression
     * @return An array of arrays each containing the Strings representing the parts of an expression
     */
    private ArrayList<ArrayList<String>> getExps(List<String> expression) {
        int depth = 0;
        ArrayList<ArrayList<String>> expressions = new ArrayList<>();
        for (int i = 0; i<expression.size();i++) {
            String s = expression.get(i);
            if (s.equals("("))
                depth++;
            if (!isAlphaNum(s) && depth==0 && !s.equals(",")){
                expressions.add(getExp(expression.subList(i,expression.size())));
            }
            if (isAlphaNum(s) && depth==0) {
                ArrayList<String> num = new ArrayList<>();
                num.add(s);
                expressions.add(num);
            }
            if (s.equals(")"))
                depth--;
        }
        return expressions;
    }

    /**
     * Used to recursively parse a line of text containing an expression
     * @param data List containing all the different elements of the expression that we want to parse.
     *             If we have a subexpression at the start of our list then we recursively parse it inside the function.
     * @param list A list containing all the subexpressions that we encountered when parsing. It is filled via the
     *             recursion of the function
     * @return Finally returns the expression that contains all of the subexpressions contained in data.
     */
    private Expression parseRec(List<String> data, ArrayList<Expression> list) {
        if (!isAlphaNum(data.get(0)) && !data.get(0).equals("(")) {
            ArrayList<Expression> newL = new ArrayList<>();
            parseRec(getExp(data.subList(1, data.size())), newL);
            return Calculator.getOp(data.get(0), newL);
        } else if (isAlphaNum(data.get(0)) && data.size() == 1) {
            return new MyNumber(Integer.parseInt(data.get(0)));
        } else if (data.get(0).equals("(")){
            ArrayList<ArrayList<String>> exps = getExps(data.subList(1, data.size()));
            for (ArrayList<String> str :exps) {
                list.add(parseRec(str,new ArrayList<>()));
            }
        }
        return null;
    }


    /**
     * Check if the string can be parsed to number
     * @param s String to parse
     * @return True if s can be parsed to a number
     */
    private boolean isAlphaNum(String s) {
        return Calculator.isAlphaNum(s);
    }

    /**
     * Sert à sauver la mémoire dans un fichier
     * @param path Chemin du fichier où sauvegarder la mémoire
     */
    public void save(String path){
        if (alreadySaved)
            return;
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(getLog());
            writer.close();
            alreadySaved = true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
