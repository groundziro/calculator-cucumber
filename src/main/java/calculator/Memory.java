package calculator;

import visitor.Evaluator;
import visitor.Printer;

import java.io.*;
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
    private final Evaluator ev = new Evaluator();
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
     * Sert à ajouter une expression à la mémoire
     * @param e Expression à rajouter
     */
    public void add(Expression e){
        mem.add(e);
        updateMem();

    }

    private void updateMem(){
        while (isFull() && mem.size()!=max){
            mem = new ArrayList<>(mem.subList(1,mem.size()));
        }
    }

    public void addAll(List<Expression> elist){
        for (Expression e: elist) {
            add(e);
        }
    }

    /**
     * Vérifie si notre mémoire est pleine
     * @return Vrai si celle-ci est pleine
     */
    public boolean isFull(){
        return mem.size()>=max;
    }

    /**
     * Cette fonction sert à récupérer une expression dans la mémoire
     * @param index Indice de la profondeur où on doit aller rechercher l'expression
     *              => size-1 c'est le dessus de la pile et 0 c'est la base de la pile
     * @return L'expression recherchée
     */
    public Expression get(int index){
        int _max = mem.size()-1;
        int cur = _max - index;
        return mem.get(cur);
    }

    /**
     * Sert à mettre à jour la taille max de notre mémoire si on se rend compte en cours d'exécution
     * que celle-ci est trop petite
     * @param max Taille maximum de la mémoire
     */
    public void setMax(int max) {
        this.max = (Math.max(max, 1));
        updateMem();
    }

    //Pour le testing
    public int size(){
        return mem.size();
    }

    public void print(){
        System.out.println(getLog(true));
    }

    /**
     * Sert à obtenir un log cohérent pour la sauvegarde
     * @return Un log de ce que contient la pile
     */
    public String getLog(boolean res){
        StringBuilder log = new StringBuilder();
        for (Expression e : mem){
            e.accept(p);
            e.accept(ev);
            log.append(p.getStr()).append("\n");
            if (res){
                log.append(String.format(" = %d\n",ev.getResult()));
            }
        }
        return log.toString();
    }


    /**
     * Sert à charger un fichier de mémoire
     * @param path Chemin du fichier en question
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
            System.out.println(Arrays.toString(expressions.toArray()));
            setMax(expressions.size());
            addAll(expressions);
            print();
        }catch (FileNotFoundException | IllegalConstruction ignored){

        }
    }

    private Expression parse(String data) throws IllegalConstruction {
        ArrayList<String> str = new ArrayList<>(Arrays.asList(data.split(" | , ")));
        String op = str.get(0);
        ArrayList<String> expression = getExp(str.subList(1,str.size()));
        expression.remove(0);
        ArrayList<Expression> total = new ArrayList<>();
        ArrayList<ArrayList<String>> exps = getExps(expression);
        //System.out.println(Arrays.toString(exps.get(0).toArray()));
        for (ArrayList<String> exp:exps) {
            total.add(parseRec(exp,new ArrayList<>()));
        }
        System.out.println(total);
        return getOp(op,total);
    }



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
            str.add(s);
        }
        return str;
    }

    private ArrayList<ArrayList<String>> getExps(ArrayList<String> expression) {
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

    private Expression parseRec(List<String> data, ArrayList<Expression> list){
        if (!isAlphaNum(data.get(0)) && !data.get(0).equals("(")){
            parseRec(getExp(data.subList(1,data.size())),list);
            return getOp(data.get(0),list);
        }
        else if (isAlphaNum(data.get(0)) && data.size()==1){
            return new MyNumber(Integer.parseInt(data.get(0)));
        }else{
            for (String s : data) {
                if (isAlphaNum(s)){
                    list.add(new MyNumber(Integer.parseInt(s)));
                }else if (!s.equals(",") && !s.equals(")") && !s.equals("(")){
                    list.add(parseRec(getExp(data.subList(data.indexOf(s),data.size())),list));
                }
            }
            return null;
        }
    }

    private Expression getOp(String s, ArrayList<Expression> total) {
        try {
            Expression exp = null;
            switch (s) {
                case "+":
                    exp = new Plus(total);
                    break;
                case "-":
                    exp = new Minus(total);
                    break;
                case "/":
                    exp = new Divides(total);
                    break;
                case "*":
                    exp = new Times(total);
                    break;
                case "xor":
                    exp = new Xor(total);
                    break;
                case "not":
                    exp = new Not(total);
                    break;
                case "and":
                    exp = new And(total);
                    break;
                case "or":
                    exp = new Or(total);
                    break;
                case "implies":
                    exp = new Implies(total);
                    break;
            }
            return exp;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private boolean isAlphaNum(String s) {
        try{
            Integer.parseInt(s);
            return true;
        }catch (Exception ignored) {
            return false;
        }
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
            writer.write(getLog(false));
            writer.close();
            alreadySaved = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
