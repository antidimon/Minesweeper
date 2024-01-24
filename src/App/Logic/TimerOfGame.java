package App.Logic;

import App.GUI.Panels;

import java.util.TimerTask;

public class TimerOfGame extends TimerTask {
    private long startTime;
    private boolean working;
    TimerOfGame(){

    }

    @Override
    public void run() {
        if (!working) this.cancel();
        long time = System.currentTimeMillis() - startTime;
        String minutes = ((time/1000/60) < 10) ? "0"+(time/1000/60) : String.valueOf(time/1000/60);
        String seconds = (((time/1000) %60) < 10) ? "0"+((time/1000)%60) : String.valueOf((time/1000)%60);
        Panels.timerLabel.setText(minutes + ":" + seconds);
    }
    public void setStartTime(long startTime){
        this.working = true;
        this.startTime = startTime;
    }
    public void stop(){this.working = false;}
    public void start(){
        this.working = true;
        this.run();
    }
}
