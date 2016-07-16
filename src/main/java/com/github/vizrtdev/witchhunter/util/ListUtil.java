package com.github.vizrtdev.witchhunter.util;

import java.util.List;
import java.util.Random;

public class ListUtil {
    static Random rand = new Random();

    public static <T> T getRandomItem(List<T> list) {
        return list.get(rand.nextInt(list.size()));
    }
}
