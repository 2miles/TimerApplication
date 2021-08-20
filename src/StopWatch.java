
//An individual stop watch.
//Start, Stop, reset, display

public class StopWatch {

    long startTime = 0;
    long endTime = 0;
    long elapsedTime = 0;
    boolean active = false;

    // If inactive, activate and record startTime
    void start() {
        if(!active) {
            active = true;
            startTime = System.currentTimeMillis();
        }
    }

    // If active, make inactive and record current endTime,
    // then record elapsed time.
    void stop() {
        if(active) {
            active = false;
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
        }
    }

    //Set all times to zero, and active to false
    void reset() {
        active = false;
        startTime = endTime = 0;
    }

    long getStartTime(){ return startTime; }
    long getEndTime(){ return endTime; }
    long getElapsedTime() {return elapsedTime; }


    //





    void pause() {

    }

}
