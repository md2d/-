package sample;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by window on 19.06.2016.
 */
public class Apple extends Point {

    private int type;

    public Apple(int x, int y, int type) {
        super(x, y);
        this.type = type;
    }

    protected   Apple(int size){
        super((int )(Math.random() * size  ) , (int )(Math.random() * size  ));

        this.type = (int) Math.round(Math.random());
    }

    public int getType(){
        return type;
    }

}
