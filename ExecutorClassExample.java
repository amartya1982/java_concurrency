import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * In this example the executor service is calling the callable (same as runnable)
 * But runnable it's not possible to catch the error
 * This callable is sleeping and doing its work and this will continue until the future
 * task is finished
 * @author amartya roy chowdhury
 */
public class ExecutorClassExample {
    public static void main(String args[]) throws Exception{

        ExecutorService secserv = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            System.out.println("Entering to the callable");
            for(int i=0; i<10; i++){
                System.out.println("The value is from CALLABLE "+i);
                Thread.sleep(1000);
            }
            return "Hey I am doing something";
        };
        System.out.println("callable is called");

        Future<String> future = secserv.submit(callable);

        while(!future.isDone()) {
            System.out.println("Task is still not done...");
            Thread.sleep(200);
            double elapsedTimeInSec = (System.nanoTime() - System.nanoTime()) / 1000000000.0;

            if (elapsedTimeInSec > 1) {
                // cancel future if the elapsed time is more than one second
                future.cancel(true);
            }
        }

        System.out.println("Do something else");
        for(int i=0; i<100; i=i+10){
            System.out.println("The value is from other task "+i);
            Thread.sleep(500);
        }
        System.out.println("Get the value from  callable");
        System.out.println(" "+future.get());
        secserv.shutdown();

    }
}
