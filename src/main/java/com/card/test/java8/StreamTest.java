package com.card.test.java8;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cuihp on 2020/5/14.
 */
public class StreamTest {
    /*
    * 接下来，当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
    * Intermediate：
    *   map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
    *   peek、 limit、 skip、 parallel、 sequential、 unordered
    *
    * Terminal：
    *   forEach、 forEachOrdered、 toArray、 reduce、 collect、
    *   min、 max、 count、 anyMatch、 allMatch、 noneMatch、
    *   findFirst、 findAny、 iterator
    *
    *  Short-circuiting：
    *  anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
    * */
    public static void main(String[] args) {
        List<Dish> menu = Lists.newArrayList(new Dish(100,1),
                new Dish(300,1),
                new Dish(200,1),
                new Dish(500,2),
                new Dish(400,2)
                );
        menu.stream()
                .filter(d -> d.getCalories() > 200)
                .sorted(Comparator.comparingInt(d2 -> d2.calories))
                .map(Dish::getCalories)
                .skip(1)
                .limit(2)
                .forEach(System.out::print);



    }


    static class Dish{
        private int calories;
        private int type;

        public Dish(int calories, int type) {
            this.calories = calories;
            this.type = type;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


}
