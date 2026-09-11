package non_leetcode.a004;


/*
用两个线程交替打印 1 到 100（线程 A 打印奇数，线程 B 打印偶数），要求输出严格有序。用
synchronized + wait/notify 实现。
 */

public class Solution {
    public static void main(String[] args) {

        Printer printer = new Printer();
     
        Thread a = new Thread(
            ()->{
                try{
                    printer.printOdd();
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        );

        Thread b = new Thread(
            ()->{
                try{
                    printer.printEven();
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        );

        a.start();
        b.start();

    }
}

class Printer {
    
    private int num = 1;

    public synchronized void printOdd() throws InterruptedException {

        while(
            num <= 100
        ){
            
            while ( num % 2 == 0 ) {
                wait();
            }

            System.out.println("A: " + num );
            num++;

            notifyAll();
        }

    }

    public synchronized void printEven() throws InterruptedException {

        while(
            num <= 100
        ){
            
            while ( num % 2 == 1 ) {
                wait();
            }

            System.out.println("B: " + num );
            num++;

            notifyAll();
        }

    }

}