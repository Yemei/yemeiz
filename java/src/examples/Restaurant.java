package examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/9/17.
 */
class Meal{
    private final int id;
    public Meal(int id){
        this.id=id;
    }
    public String toString(){
        return "Meal:"+id;
    }
}
class WaitPerson implements Runnable{
    private Restaurant restaurant;
    public WaitPerson(Restaurant restaurant){
        this.restaurant=restaurant;
    }
    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal==null)
                        wait();
                }
                System.out.println("WaiterPerson got "+restaurant.meal);
                synchronized (restaurant.chef){
                    restaurant.meal=null;
                    restaurant.chef.notifyAll();
                }
            }
        }catch (InterruptedException e){
            System.out.println("WaiterPerson interrupted");
        }
    }
}
class Chef implements Runnable{
    private int count=0;
    private Restaurant restaurant;
    public Chef(Restaurant restaurant){
        this.restaurant=restaurant;
    }
    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while (restaurant.meal!=null)
                        wait();
                }
                if(++count==10){
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                System.out.println("Order up!");
                synchronized (restaurant.waitPerson){
                    restaurant.meal=new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }catch (InterruptedException e){
            System.out.println("Chef interrupted");
        }
    }
}
public class Restaurant {
    Meal meal;
    WaitPerson waitPerson=new WaitPerson(this);
    Chef chef=new Chef(this);
    ExecutorService exec= Executors.newCachedThreadPool();
    public Restaurant(){
        exec.execute(waitPerson);
        exec.execute(chef);
    }
    public static void main(String[] args){
        new Restaurant();
    }
}
