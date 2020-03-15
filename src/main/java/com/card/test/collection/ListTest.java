package com.card.test.collection;

import com.card.test.ThreadUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.*;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 100;
    }

}


public class ListTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> myThreadFutureTask = new FutureTask<>(new MyThread());
        Thread thread = new Thread(myThreadFutureTask);
        thread.start();
        Integer integer = myThreadFutureTask.get();
        System.out.println(integer);
    }



    private static void SetNotSafe() {
        //        Set<String> objects = Sets.newHashSet();
//        Set<String> objects = Collections.synchronizedSet(new HashSet<>());
        CopyOnWriteArraySet objects = new CopyOnWriteArraySet();
        for (int i = 0; i < 400; i++) {
            new Thread(() -> {
                objects.add(UUID.randomUUID().toString().substring(0,3));
                System.out.println(Thread.currentThread().getName() + " object: " +objects);
                System.out.println("count:"+objects.size());
            },String.valueOf(i)).start();
        }

        ThreadUtil.sleep(1000);
        System.out.println("count:"+objects.size());


        Set<String> hashSet = new HashSet<>();
        hashSet.add("111");
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("111","111");

        /*
        * 1、
        *   Set多线程边读边写导致并发修改异常 java.util.ConcurrentModificationException
        *   HashSet并发写导致数据丢失
        *
        * 2、解决
        *
        *
        *
        *
        *
        * */
    }


    private static void ListNotSafe() {
        ArrayList<Object> objects = Lists.newArrayList();
//        objects1.add(1);
//        List<String> objects = Collections.synchronizedList(new ArrayList<>());
//        CopyOnWriteArrayList<String> objects = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 40; i++) {
            new Thread(() -> {
                objects.add(UUID.randomUUID().toString().substring(0,3));
            },String.valueOf(i)).start();
        }

        ThreadUtil.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " object: " +objects);
        System.out.println("count:"+objects.size());




        /*
        1、java.util.ConcurrentModificationException 在对list进行迭代读取操作时进行并发修改导致的异常。边写边读导致的。
            迭代操作中的Itr对象中的modCount != expectedModCount导致该异常。
            单线程对ArrayList进行读写时就会有，可以Vector
            多线程时使用Vector也会有异常。两个线程公用了ArrayList，但是遍历使用的Itr是线程私有的，并发操作也会有问题。

        2、问题原因
           ArrayList是线程不安全的 边写边读
           CopyOnWriteArrayList 读写数据分离，不会有异常。

        3、解决方案
            1、new Vector<>()
            2、Collections.synchronizedList(new ArrayList<>());
            3、CopyOnWriteArrayList() add可重入锁机制。写时复制机制
        4、优化

         */
    }


    class A {
        int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }


    class B extends A{
        int b;

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }



    private static void removeTest() {
//        List<Integer> tempArray = Lists.newArrayList(100);
//        List<Integer> tempLink = Lists.newArrayList(100);
//
//
//
//
//
//        initLinkList();
//
//        initArrayList();


//        for (Integer integer : tempArray) {
////            if (integer < 0) {
////                integer = -integer;
////            }
////            if (integer < arrayList.size()) {
////                arrayList.add(integer,integer);
////            } else {
////                int i = integer % arrayList.size();
////                arrayList.add(i,integer);
////            }
////            arrayList.remove(integer);
//            arrayList.indexOf(integer);
//        }
////        arrayList.removeAll(tempArray);
//        long four = System.currentTimeMillis();
//        System.out.println("array remove "+ tempArray.size() +", time use :"+(four - three)+"ms");
//
//
//        for (Integer integer : tempLink) {
//////            linkList.contains(integer);
////            if (integer < 0) {
////                integer = -integer;
////            }
////            if (integer < linkList.size()) {
////                linkList.add(integer,integer);
////            } else {
////                int i = integer % linkList.size();
////                linkList.add(i,integer);
////            }
//            linkList.indexOf(integer);
////            linkList.remove(integer);
//        }
////        linkList.removeAll(tempLink);
//        long five = System.currentTimeMillis();
//        System.out.println("link remove "+ tempLink.size() +", time use :"+(five - four)+"ms");


    }

    private static void resize(int oldCapacity) {
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println("oldCapacity:"+oldCapacity+"  newCapacity:"+newCapacity);
    }

    private static void initArrayList() {
        Random random = new Random();
        ArrayList<Integer> arrayList = Lists.newArrayList();
        long one2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(random.nextInt());
        }
        long three = System.currentTimeMillis();
        System.out.println("ArrayList add :"+ arrayList.size() +", time use :" + (three - one2) + "ms");
    }

    private static void initLinkList() {
        Random random2 = new Random();
        long one = System.currentTimeMillis();
        LinkedList<Integer> linkList = Lists.newLinkedList();
        for (int j = 0; j < 1000000; j++) {
            linkList.add(random2.nextInt());
        }
        long two = System.currentTimeMillis();
        System.out.println("LinkedList add :"+ linkList.size() +", time use :" + (two - one) + "ms");
    }


}
