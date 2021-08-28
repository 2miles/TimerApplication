import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//One task: a named list of stopwatches and their total duration summed up
public class Task implements Comparable<Task>{
    private String name;               //used to compare tasks for order and equality
    private Duration totalElapsed;              //sum of individual timer totals for a task
    private List<Stopwatch> watches = new ArrayList<>();  //list of stopwatches for a task


    public Task(){
        readName();
        totalElapsed = Duration.ZERO;
        watches.add(new Stopwatch());
    }

    public void readName(){
        //Get Task name from user
        System.out.print("Enter a name: ");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
    }

    @Override
    public int compareTo(Task t) {
        return this.name.compareTo(t.name);
    }

    @Override
    //print Task as (Name, totalTime)
    public String toString() {
        String result = name + ": "
                + Stopwatch.formatDuration(totalElapsed)
                + "\n" + watches;

        return result;

    }
}
