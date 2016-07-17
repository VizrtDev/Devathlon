package com.github.vizrtdev.witchhunter.util;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Countdown {

    private int delay = 1000;
    private int period = 1000;
    private Timer timer;
    private TimerTask timerTask;
    private int interval;
    private int startInterval;

    public Countdown(int count) {
        interval = count;
        startInterval = count;
    }

    public abstract void tick(int count);

    public void start(){
        timer = new Timer( );
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if( interval == 0 )
                    reset();
                tick(interval);
                interval = setInterval();
            }
        };
        timer.schedule( timerTask, delay, period );
    }

    public void reset() {
        timer.cancel();
        timerTask.cancel();
        interval = startInterval;
    }

    public final int setInterval() {
        if( interval == 1 )
            timer.cancel();
        return --interval;
    }

    public void setInterval(int i) {
        interval = i;
    }

}
