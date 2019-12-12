package com.card.test.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListTest {


    public static void main(String[] args) {
        removeTest();
    }

    private static void removeTest() {

        List<Integer> tempArray = Lists.newArrayList(100);
        List<Integer> tempLink = Lists.newArrayList(100);





        initLinkList();

        initArrayList();


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
