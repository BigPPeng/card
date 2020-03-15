package com.card.test.jvm;

import com.card.test.ThreadUtil;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by cuihp on 2020/3/14.
 */
public class StrongReferenceDemo {

    /**
     * 强引用，软引用，弱引用，虚引用
     *
     * Reference 强引用,OOM不收
     * SoftReference 软引用在系统内存不足时，会被回收。内存充足，可以回收。
     * WeakReference 弱引用在垃圾回收时一定会回收，不管内存是否充足
     *
     * 软引用，若引用使用场景，eg：图片缓存，减少OOM的情况
     * WeakHashMap
     *
     * PhantomReference
     */
    public static void main(String[] args) {


        /*
        * Java提供了四种引种类型，在垃圾回收的时候，各自有自己的特点。
        * ReferenceQueue是用来配合引用工作的，没有ReferenceQueue一样可以工作
        * 创建引用的时候可以
        *
        *
        * */

//        strongReference();
//        softRef_Mem_Enough();
//        softRef_Mem_NotEnough();

//        weakRef();
//        myHashMap();
//        System.out.println("--------------------");
//        myWeakHashMap();
//        referenceQueue();

        phantomReference();


    }

    private static void phantomReference() {
        Object o = new Object();
        ReferenceQueue<Object> l = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o,l);

        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(l.poll());


        o = null;
        System.gc();
        System.out.println("=================================");
        ThreadUtil.sleep(500);
        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(l.poll());
    }

    private static void referenceQueue() {
        // 虚引用PhantomReference主要是用于跟踪垃圾回收状态
        Object o = new Object();
        ReferenceQueue<Object> l = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<Object>(o,l);
        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(l.poll());

        o = null;
        System.gc();
        System.out.println("------------------------------------");
        ThreadUtil.sleep(500);
        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(l.poll());
        System.out.println(l.poll().get());
    }

    /**
     * 如果放入WeakHashMap的key被置为无效，此map中的数据也将被清除。
     * 放入这个Map的数据导致OOM的概率很低。所以缓存很合适。
     */
    private static void myWeakHashMap() {
        WeakHashMap<Integer,String> stringHashMap = new WeakHashMap<>();
        Integer key = new Integer(2);// 运行后stringHashMap会被回收，这样是一个新的对象
//        Integer key = 2;// 运行后不会被回收，原因是IntegerCache的存在，这样声明，用的是cache。
        String hashMap = "WeakHashMap";
        stringHashMap.put(key, hashMap);
        System.out.println(stringHashMap);
        key = null;
        System.out.println(stringHashMap);
        System.gc();
        System.out.println(stringHashMap);
        System.out.println(stringHashMap.size());
    }

    private static void myHashMap() {
        HashMap<Integer,String> stringHashMap = new HashMap<>();
        Integer key = 1;
        String hashMap = "hashMap";
        stringHashMap.put(key, hashMap);
        System.out.println(stringHashMap);
        key = null;
        System.out.println(stringHashMap);
        System.gc();
        System.out.println(stringHashMap);
    }

    /**
     * 弱引用在垃圾回收时一定会回收，不管内存是否充足
     */
    private static void weakRef() {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }


    private static void softRef_Mem_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        System.out.println(o1 == softReference.get());
        o1 = null;
        System.gc();
        System.out.println(softReference.get());
    }

    /**
     * -Xms10m -Xmx10m
     * 设置小内存，申请大空间。出发fullGC
     * 验证内存不够用软引用被回收。
     */
    private static void softRef_Mem_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        System.out.println(o1 == softReference.get());
        o1 = null;
        try {
            byte[] s = new byte[30*1024*1024];
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }





    /*
    * Reference 强引用,OOM不收
    * */
    private static void strongReference() {
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;
        System.gc();
        System.out.println(o2);
    }

}
