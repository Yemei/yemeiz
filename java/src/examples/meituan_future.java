package examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/9/17.
 */
public class meituan_future {
    public static void main(String[] args){
        ExecutorService exec=Executors.newCachedThreadPool();
        List<Future<Integer>> list=new ArrayList<>();
        for(int i=0;i<3;i++){
            list.add(exec.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int result=0;
                    for(int j=1;j<=100;j++)
                        result+=j;
                    System.out.println(result);
                    return result;
                }
            }));
        }
        int result=0;
        try{
            for(Future<Integer> future:list)
                result+=future.get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
