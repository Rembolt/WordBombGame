/**
 * BombTimer
 */
import java.util.Timer;
import java.util.TimerTask;

public class BombTimer {

    public static int interval = 100;
    static Timer timer;
    static int currentTime;
    public static int delay = 1000;
    public static int period = 1000;

    public static void main(String[] args) {
        
    }

    public void timerinnit() {
        
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                currentTime = setInterval();
            }

        }, delay, period);
    }

    public void resetTimer(int secs){
        
        interval = secs;
        
    }

    private static int setInterval() {
        if (interval == 1){
            timer.cancel();
            if(currentTime == 1)
                WordBomb.endGame();
        }
                   
        return --interval;
    }
    

}