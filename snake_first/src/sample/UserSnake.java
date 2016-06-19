package sample;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by window on 18.06.2016.
 */
public class UserSnake extends Snake {
    private int score;

    private static LinkedList<String> movesList;

    protected UserSnake (){
        movesList = new LinkedList<String>();
        addMove("right");
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incScore() {
        this.score = this.score + 1;
    }

    public static void addMove(String type){
        movesList.addLast(type);
    }

    public static String getThisMove(){
        String t = movesList.get(0);
        return t;
    }

    public static void clearThisMove(){
        if(movesList.size() > 1)
            movesList.remove(0);
    }
}
