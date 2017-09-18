package examples;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/9/16.
 */
public class CallableAndFuture01 {
    public static void main(String[] args){
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        //Runnable Future
        FutureTask<Integer> future=new FutureTask<Integer>(callable);
        new Thread(future).start();
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
