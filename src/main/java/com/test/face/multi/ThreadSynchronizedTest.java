package com.test.face.multi;

import com.card.test.ThreadUtil;

/**
 * Created by cuihp on 2020/11/10.
 */
public class ThreadSynchronizedTest {


    /**
     * 1、synchronized 在非静态方法，或者synchronized (this) 是对当前对象加锁。加锁后其他线程均在对同对此对象加锁的方法或者代码块均顺序执行。
     * 2、synchronized 在静态方法，或者synchronized (AA.class) 是对类加锁，所有对类加锁的方法都同步。
     * 3、类锁与对象锁互不干扰。
     * 4、synchronized关键字是不能继承的
     * 5、synchronized锁的是对象，即使对象的属性发生变化，只要对象不变，不同线程关于该对象的锁运行都是同步的。
     * 6、synchronized(字符串对象) 要关注字符串常量池的情况，同理，对象池缓存对象的时候加锁也要注意。
     * 7、synchronized可重入，出现异常自动释放锁。
     * 8、synchronized的实现原理和应用总结
     *    synchronized同步代码块：synchronized关键字经过编译之后，会在同步代码块前后分别形成monitorenter和monitorexit字节码指令，
     *          在执行monitorenter指令的时候，首先尝试获取对象的锁，如果这个锁没有被锁定或者当前线程已经拥有了那个对象的锁，锁的计数器就加1，
     *          在执行monitorexit指令时会将锁的计数器减1，当减为0的时候就释放锁。如果获取对象锁一直失败，那当前线程就要阻塞等待，直到对象锁被
     *          另一个线程释放为止。
     *    同步方法：方法级的同步是隐式的，无须通过字节码指令来控制，JVM可以从方法常量池的方法表结构中的ACC_SYNCHRONIZED访问标志得知一个
     *          方法是否声明为同步方法。当方法调用的时，调用指令会检查方法的ACC_SYNCHRONIZED访问标志是否被设置，如果设置了，执行线程就要求
     *          先持有monitor对象，然后才能执行方法，最后当方法执行完（无论是正常完成还是非正常完成）时释放monitor对象。在方法执行期间，执
     *          行线程持有了管程，其他线程都无法再次获取同一个管程。
     *
     *    每个对象都有一个监视器锁(monitor)与之对应。当monitor被占用时就会处于锁定状态，线程执行monitorenter指令时尝试获取monitor的所有权，
     *    过程如下：
     *      1、如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者。
     *      2、如果线程已经占有该monitor，只是重新进入，则进入monitor的进入数加1.
     *      3.如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权。
     *
     *
     *   通过这两个指令我们应该能很清楚的看出Synchronized的实现原理，Synchronized的语义底层是通过一个monitor的对象来完成，
     *   其实wait/notify等方法也依赖于monitor对象，这就是为什么只有在同步的块或者方法中才能调用wait/notify等方法，
     *   否则会抛出java.lang.IllegalMonitorStateException的异常的原因。
     *
     *
     *
     */

    public static void main(String[] args) {
        AA aa = new AA();
        AA bb = new AA();
        new Thread(AA::printAA,"AA").start();
        new Thread(AA::printBB,"BB").start();
        new Thread(aa::printCC,"CC").start();

    }


}


class AA {
    synchronized public static void printAA() {
        System.out.println(Thread.currentThread().getName() + "  start");
        ThreadUtil.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "  end");
    }


    synchronized public static void printBB() {
        System.out.println(Thread.currentThread().getName() + "  start");
        ThreadUtil.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "  end");
    }


    synchronized public void printCC() {
        System.out.println(Thread.currentThread().getName() + "  start");
        ThreadUtil.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "  end");
    }

    public synchronized void print() {
        System.out.println(Thread.currentThread().getName() + "  start");
        ThreadUtil.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "  end");
    }
    public void printA() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "  start");
            ThreadUtil.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "  end");
        }

        synchronized (AA.class) {

        }

    }
}
