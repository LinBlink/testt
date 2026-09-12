package non_leetcode.a006;

public class Solution {
    public static void main(String[] args) {

        Printer printer = new Printer();

        Thread t1 = new Thread(
            ()->{
                printer.print(true);
            }
        );

        Thread t2 = new Thread(
            ()->{
                printer.print(false);
            }
        );

        t1.start();
        t2.start();
        
    }
}

//

class Printer {
    private int num = 1;
    private final Object lock = new Object();

    void print(boolean odd) {

        try {

            while(true){

                synchronized(lock){
                    
                    // 不属于自己的就不打印
                    while (
                        num <= 100 && ( (num%2)==1 ) == odd
                    ) {
                        lock.wait();
                    }

                    // 发现num大于100就取消执行
                    if (num>100) {
                        lock.notifyAll();
                        return;
                    }

                    // 执行完了就让其他线程执行
                    System.out.println( Thread.currentThread().getName() + ":" + num++ );
                    lock.notifyAll();

                }

            }
            
        } catch (Exception e) {
            System.out.println("发生异常" + e);
        }

        
    }
}