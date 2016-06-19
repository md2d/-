package sample;

/**
 * Created by window on 18.06.2016.
 */
import java.util.concurrent.TimeUnit;

class MyThread extends Thread implements Runnable {


    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        UserSnake userSnake = new UserSnake();
        Apple apple = new Apple(Game.size);
        Point sapple = (Point) apple;
        Game.showPoints(userSnake.getPosition());
        Game.showApple(apple);
        while( this.isAlive()  ) {
            if(Game.isActive()) {

                Game.go(userSnake);
                if(userSnake.isEatApple(sapple)){
                    Game.showPoint(sapple);
                    apple = new Apple(Game.size);
                    sapple = (Point) apple;
                    Game.showApple(apple);
                } else
                    userSnake.deleteTail(); // Удаляем хвост
                try {
                   this.sleep( Game.getSpeed() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}