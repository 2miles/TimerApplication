import java.time.Duration;
import java.util.*;  //ArrayList, List, Scanner


//One task: a named list of stopwatches and their total duration summed up
public class Task implements Comparable<Task>{
    private String name;
    private final Duration totalElapsed;        //sum of individual timer totals for a task
    private final List<Stopwatch> watches = new ArrayList<>();  //list of stopwatches for a task

    public Task(){
        readName();
        totalElapsed = Duration.ZERO;
        watches.add(new Stopwatch());
    }

    public void readName(){
        System.out.print("Enter task name: ");
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
        return name + ": " + Stopwatch.DurationToString(totalElapsed)
                + "\n" + watches;
    }

}
