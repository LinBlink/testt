package learning_points.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description: Java Semaphore 学习
 * @author: LOCRIAN_V
 * @date: 8/31/2026 11:23 AM
 *
 * Semaphore（信号量）核心概念：
 * - 本质：一个计数器，控制同时访问共享资源的线程数量
 * - 类比：停车场入口的显示屏，显示剩余车位数
 *   - permits = 3：最多3辆车同时停在停车场
 *   - acquire()：车要进入，车位数-1，满了就阻塞等待
 *   - release()：车离开，车位数+1，等待的车可以进入
 *
 * 核心方法：
 * - Semaphore(int permits)：permits 是最大并发数
 * - acquire()：获取一个许可，没有则阻塞等待
 * - release()：释放一个许可
 * - tryAcquire(timeout, unit)：限时获取，超时返回 false
 * - availablePermits()：当前剩余许可数
 */
public class Course01 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== 示例1：基础用法 =====");
        basicDemo();

        System.out.println("\n===== 示例2：限流场景（3个线程抢5个许可）=====");
        throttleDemo();

        System.out.println("\n===== 示例3：数据库连接池模拟 =====");
        connectionPoolDemo();
    }

    /**
     * 示例1：基础 acquire/release
     */
    static void basicDemo() throws InterruptedException {
        Semaphore semaphore = new Semaphore(3); // 最多允许3个线程同时访问
        System.out.println("初始许可数: " + semaphore.availablePermits());

        semaphore.acquire(); // 获取1个许可
        System.out.println("acquire后许可数: " + semaphore.availablePermits());

        semaphore.release(); // 释放1个许可
        System.out.println("release后许可数: " + semaphore.availablePermits());
    }

    /**
     * 示例2：多线程并发，Semaphore 限流
     */
    static void throttleDemo() throws InterruptedException {

        Semaphore semaphore = new Semaphore(3); // 最多3个并发

        Runnable task = () -> {
            try {
                semaphore.acquire(); // 获取许可，满了就等
                System.out.println(Thread.currentThread().getName() + " 获取许可，开始工作");
                Thread.sleep(1000); // 模拟业务处理
                System.out.println(Thread.currentThread().getName() + " 工作完成，释放许可");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release(); // 必须在 finally 中释放，防止死锁
            }
        };

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(task, "Thread-" + i);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join(); // 等待所有线程完成
        }
    }

    /**
     * 示例3：模拟数据库连接池
     * 问题：服务器有 100 个请求并发，但数据库只允许 5 个连接
     */
    static void connectionPoolDemo() throws InterruptedException {
        int maxConnections = 5;  // 数据库最大连接数
        int totalRequests = 10;  // 总请求数

        Semaphore pool = new Semaphore(maxConnections);

        Runnable request = () -> {
            try {
                pool.acquire(); // 从连接池获取连接
                System.out.println(
                        Thread.currentThread().getName()
                        + " 已获取连接 (剩余: " + pool.availablePermits() + ")"
                );
                Thread.sleep(500); // 模拟数据库操作
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                pool.release(); // 归还连接
                System.out.println(Thread.currentThread().getName()
                        + " 已归还连接 (剩余: " + pool.availablePermits() + ")");
            }
        };

        Thread[] threads = new Thread[totalRequests];
        for (int i = 0; i < totalRequests; i++) {
            threads[i] = new Thread(request, "请求-" + i);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("所有请求处理完毕，剩余许可: " + pool.availablePermits());
    }
}