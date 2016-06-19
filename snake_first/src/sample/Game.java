package sample;

import javafx.fxml.FXML;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;




public class Game {
    private static boolean isActive;
    public static int size = 10;
    private boolean isBeFirstStart;
    public static Canvas canvashref;
    public static Slider sliderhref;
    @FXML
    Slider slider;
    @FXML
    Canvas canvas;
    @FXML
    Button btn;
    @FXML
    Label showText;
    @FXML
     Label rating;

    public static int cellWidth = 0;


    @FXML
    public void handleButtonAction() {
        // Button was clicked, do something...
         // прозрачный
        if(isActive){
            stop();
        } else {
            btn.setText("Stop");
            start();

        }

    }

    @FXML
    public void keyPressed(KeyEvent e){
        switch ( e.getCode().toString() ){
            case "A": {UserSnake.addMove("left");};break;
            case "S": {UserSnake.addMove("down");};break;
            case "D": {UserSnake.addMove("right");};break;
            case "W": {UserSnake.addMove("top");};break;
            case "LEFT": break;
            case "RIGTH": break;
            case "SPACE": { if( isActive ) stop(); else start(); } break;
            default: break;
        }
    }


    private void start()  {

        isActive = true;

        showText.setOpacity(0);
        canvas.setOpacity(1);

        if(!isBeFirstStart) {
            canvashref = this.canvas;
            sliderhref = this.slider;
            GridShow();
            MyThread thread1 = new MyThread("first");
            thread1.start();
        }
        isBeFirstStart = true;

    }

    public  void stop(){
        isActive = false;
        btn.setText("Start");
        //canvas.setOpacity(0);
        showText.setOpacity(1);
    }

    public static boolean isActive(){
        return isActive;
    }

    public static void go(UserSnake userSnake){
        ArrayList<Point> snakeCoords = userSnake.move( UserSnake.getThisMove() , size);
        showPoint(snakeCoords.get(0));
        if( userSnake.isCrashSelf()) {
            isActive = false;
        }
        clearPoint( snakeCoords.get(snakeCoords.size() - 1) );
        UserSnake.clearThisMove(); // Удаляем  движение
    }

    public void GridShow(){
        GraphicsContext  gc = canvashref.getGraphicsContext2D();
        gc.clearRect(0,0, canvashref.getWidth() , canvashref.getHeight() );
        gc.setFill(Color.WHITE);
        //gc.fillRect(1,1,canvas.getWidth() , canvas.getHeight());

        gc.setFill(Color.BLACK);
        gc.setLineWidth(1);

        cellWidth = (int) (canvashref.getWidth()/size);

        for (int i = 0; i < size; i++){
            canvashref.getGraphicsContext2D().strokeLine(0,i*cellWidth, canvashref.getHeight() , i * cellWidth);
            canvashref.getGraphicsContext2D().strokeLine(i*cellWidth, 0  , i * cellWidth , canvashref.getHeight());
        }

        gc.strokeLine(0, canvashref.getHeight() , canvashref.getWidth() ,  canvashref.getHeight() );
        gc.strokeLine( canvashref.getWidth()  , 0 , canvashref.getWidth() ,  canvashref.getHeight()  );

        gc.stroke();
    }

        public static void showPoint(Point point) {
            GraphicsContext gc = canvashref.getGraphicsContext2D();
            gc.setFill(Color.BURLYWOOD);
            gc.fillRect(point.getX() * cellWidth + 1 , point.getY() * cellWidth + 1 , cellWidth - 3 + 1, cellWidth - 3 + 1);
        }

       public static void showPoints(ArrayList<Point> points) {
           GraphicsContext gc = canvashref.getGraphicsContext2D();
           gc.setFill(Color.BURLYWOOD);
           for (Point point : points) {
               gc.fillRect(point.getX() * cellWidth + 1 , point.getY() * cellWidth + 1 , cellWidth - 3 + 1, cellWidth - 3 + 1);
           }

       }
        public static void clearPoint( Point point ){
            GraphicsContext gc = canvashref.getGraphicsContext2D();
            gc.clearRect(point.getX() * cellWidth + 1 + 1, point.getY() * cellWidth + 1 + 1 , cellWidth - 3, cellWidth -3);

        }


        public static void showApple(Apple apple){
            GraphicsContext gc = canvashref.getGraphicsContext2D();
            if( apple.getType() == 1 ) gc.setFill(Color.PALEVIOLETRED);
            else gc.setFill(Color.ROYALBLUE);
            gc.fillRect(apple.getX() * cellWidth + 1 , apple.getY() * cellWidth + 1  , cellWidth -3 + 1, cellWidth -3 + 1);

        }

    public static int getSpeed(){
        return (int) (3500 / sliderhref.getValue() );
    }


}
