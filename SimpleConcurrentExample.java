import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This is a classic example of running a multithreaded application in parallel
 * Where the callable is running a loop with a sleep of 1000 ms and the outer
 * task is running at the same time with proper time slicing.
 * @author amartya roy chowdhury
 */
public class SimpleConcurrentExample {
    public static void main(String args[]) throws  Exception{
        ExecutorService exeServ = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            System.out.println("Entering to the callable");
            for(int i=0; i<10; i++){
                Thread.sleep(1000);
                System.out.println("This is a task from callable with count "+i);
            }
            return "Hey I am doing something";
        };
        System.out.println("Submitted callable");

        Future<String> future = exeServ.submit(callable);
        System.out.println("Do something else while callable is getting executed");

        for(int i=10; i<100; i=i+10){
            Thread.sleep(1000);
            System.out.println("This is a normal task with count "+i);
        }

        System.out.println("Get the result from future ");
        String result = future.get();

        System.out.print("And the result is "+result);
        exeServ.shutdown();

    }
}
