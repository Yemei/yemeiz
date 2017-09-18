package examples;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/9/17.
 */
public class CallableAndFuture02 {
    public static void main(String[] args){
        ExecutorService threadPool= Executors.newSingleThreadExecutor();
        Future<Integer> future=threadPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
        try{
            Thread.sleep(5000);
            System.out.println(future.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}
