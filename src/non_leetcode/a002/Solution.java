package non_leetcode.a002;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// [IMPORTANT]
/*
实现一个简单的生产者-消费者模型：一个生产者线程每次生产一个整数放入容量为 5
的缓冲区，一个消费者线程从缓冲区取出并打印；缓冲区满时生产者等待，空时消费者等待。可以使用 JDK提供的阻塞队列，也可以用 wait/notify 手写。
*/

public class Solution {
    public static void main(String[] args) {

        method2();

    }

    public static void method2() {

        List<Integer> buffer = new ArrayList<>(5);

        Thread producer = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 10; i++) {

                            synchronized (buffer) {

                                // 生产太多了就睡觉
                                while (buffer.size() >= 5) {
                                    buffer.wait();
                                }

                                buffer.add(i);
                                System.out.println("producer produced " + i + " current buffer " + buffer);

                                buffer.notifyAll();

                            }

                        }
                    } catch (Exception e) {
                    }
                });

        Thread consumer = new Thread(
                () -> {

                    for (int j = 0; j < 10; j++) {

                        synchronized (buffer) {

                            try {

                                // 不够了就等消费者生产
                                while (buffer.isEmpty()) {
                                    buffer.wait();
                                }

                                // 有就消费
                                Integer i = buffer.removeLast();
                                System.out.println("consumer consumed " + i + " current buffer " + buffer);
                                buffer.notifyAll();
                                // 消费完了通知生产者生产

                            } catch (Exception e) {
                            }

                        }

                    }

                });

        producer.start();
        consumer.start();

    }

    public static void method1() {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            queue.put(i);
                            System.out.println(
                                    "生产" + i + ", 缓冲区：" + queue);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });

        Thread consumer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Integer value = queue.take();
                            System.out.println(
                                    "消费：" + value + ", 缓冲区：" + queue);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });

        producer.start();
        consumer.start();

    }

}
