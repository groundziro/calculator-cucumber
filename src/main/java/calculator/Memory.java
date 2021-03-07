package calculator;

import visitor.Evaluator;
import visitor.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Cette classe contient la mémoire de notre programme de manière LIFO
 */
public class Memory {
    private ArrayList<Expression> mem;
    private int max;
    private final Printer p = new Printer(Notation.INFIX);
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
        if (isFull()){
            List<Expression> newMem = mem.subList(1,mem.size());
            newMem.add(e);
            mem = (ArrayList<Expression>) newMem;
        }
        else
            mem.add(e);
    }

    /**
     * Vérifie si notre mémoire est pleine
     * @return Vrai si celle-ci est pleine
     */
    public boolean isFull(){
        return mem.size()==max;
    }

    /**
     * Cette fonction sert à récupérer une expression dans la mémoire
     * @param index Indice de la profondeur où on doit aller rechercher l'expression
     *              => 0 c'est le dessus de la pile et size-1 c'est la base de la pile
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
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    public void print(){
        System.out.println(getLog());
    }

    /**
     * Sert à obtenir un log cohérent pour la sauvegarde
     * @return Un log de ce que contient la pile
     */
    public String getLog(){
        StringBuilder log = new StringBuilder();
        for (Expression e : mem){
            e.accept(p);
            e.accept(ev);
            log.append(p.getStr()).append(String.format(" = %d\n",ev.getResult()));
        }
        return log.toString();
    }


    /**
     * Sert à charger un fichier de mémoire
     * @param path Chemin du fichier en question
     */
    public void load(String path){
        //TODO: Cette fonction est à terminer
        try{
            File reader = new File(path);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
            writer.write(getLog());
            writer.close();
            alreadySaved = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
