package com.card.test.java8;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Created by cuihp on 2020/5/11.
 */
public class Test2 {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(153, "green"),
                new Apple(120, "red"));

//         [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filterApple(inventory, Test2::isGreenApple);
        System.out.println(greenApples);
        System.out.println(inventory);
        List<Apple> collect = inventory.stream().filter((Apple a) -> a.getWeight() > 88).collect(toList());
        System.out.println(collect);;
        Map<String, List<Apple>> collect1 = collect.stream().collect(groupingBy(Apple::getColor));
        System.out.println(collect1);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples = filterApple(inventory, Test2::isHeavyApple);
        System.out.println(heavyApples);

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filterApple(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples2);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples2 = filterApple(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(heavyApples2);

        // []
        List<Apple> weirdApples = filterApple(inventory, (Apple a) -> a.getWeight() < 80 ||
                "brown".equals(a.getColor()));
        System.out.println(weirdApples);

    }


    public static List<Apple> filterApple(List<Apple> appleList, Predicate<Apple> predict) {
        List<Apple> re = Lists.newArrayList();
        for (Apple apple : appleList) {
            if (predict.test(apple)) {
                re.add(apple);
            }
        }
        return re;
    }


    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;


    }
}

class Apple {
    String color;
    int weight;

    public Apple(int weight,String color) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
