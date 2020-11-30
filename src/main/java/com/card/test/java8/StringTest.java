package com.card.test.java8;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by cuihp on 2020/11/10.
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = "2;32;128;256;1024;2048;524288;4194304;8388608;1073741830;1073741838;1073741826;1073741905;512;8192;32768;268435456;134217728;1073741832;1073741866;1073741874;1073741894;1073741896;1073741898;1073741899;1073741900;1073741903;1073741891;1073741901;1073741907;1073741895;1073741906;1073741850;1073741935;1073741933;1073741940;1073741846;1073741876;1073741944;1073741910;1073741985;1073741972;1073741968;1073741932;1073741966;1073741919;1073741987;1073741923;1073741989;1073741904;1073741969;1073741970;1073741996;1073741921;1073741922;1073741951;1073741995;1073741992;1073741912;1073741979;1073741999;1073741927;1073741988;1073741997;1073741994;1073741993;1073742000;1073741918;1073741959;1073741956;1073741955;1073741965;1073741953;1073741952;1073741950;1073741981;1073741984;1073741975;1073742005;1073742000;1073742003;1073742004;1073742001";
        String s2 = "67108864;1073741973;1073741979;1073741981;1073741984;1073741986;1073741987;1073741988;1073741989;1073741992;1073741993;1073741994;1073741995;1073741996;1073741997;1073741999;1073742000;1073742003;1073742006;1073742007;1073742008;1073742009;1073742010;1073742011;1073742013;1073742014;1073742015;1073742017";

        List<String> list1 = Splitter.on(";").splitToList(s1);
        HashSet<String> string1 = Sets.newHashSet(list1);
        System.out.println(string1.size() + "  " + list1.size());

        ArrayList<String> strings11 = Lists.newArrayList(list1);
        ArrayList<String> strings22 = Lists.newArrayList(string1);
        strings11.sort(
                Comparator.comparingLong(Long::valueOf)
        );
        strings22.sort(
                Comparator.comparingLong(Long::valueOf)
        );
        System.out.println(JSON.toJSONString(strings11));
        System.out.println(JSON.toJSONString(strings22));



        List<String> list2 = Splitter.on(";").splitToList(s2);
        HashSet<String> string2 = Sets.newHashSet(list2);
        System.out.println(string2.size() + "  " + list2.size());


        string1.addAll(string2);
        System.out.println(string1.size() + "  " + (list1.size() + list2.size()));

        StringBuilder stringBuilder = new StringBuilder();
        string1.forEach(s -> {stringBuilder.append(s).append(";");});
        System.out.println(stringBuilder.toString());


    }



}
