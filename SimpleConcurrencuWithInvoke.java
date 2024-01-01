import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * In this example we created a thread pool of 5 thread and created 3 tasks
 * If we all everything all together the task will perform based on their sleep time
 * If we just pick invoke any it will pick based on the execution time or
 * based on the priority, here all 3 thread priority are same.
 *
 * @author amartya roy chowdhury
 */
public class SimpleConcurrencuWithInvoke {
    public  static void main(String args[]) throws Exception {

        ExecutorService exe = Executors.newFixedThreadPool(5);
        Callable<String> task1 = () -> {
            Thread.sleep(200);
            return "Task1 is running and completed";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(1000);
            return "Task2 is running and completed";
        };

        Callable<String> task3 = () -> {
            Thread.sleep(1000);
            return "Task3 is running and completed";
        };

        List <Callable<String>> callableList = Arrays.asList(task2,task3,task1);
        //List<Future<String>> futures = exe.invokeAll(callableList);

        String result = exe.invokeAny(Arrays.asList(task1, task2, task3));

        /*for(Future<String> future: futures) {
            // The result is printed only after all the futures are complete. (i.e. after 5 seconds)
            System.out.println(future.get());
        }*/
        System.out.println(result);
        exe.shutdown();

    }
}
