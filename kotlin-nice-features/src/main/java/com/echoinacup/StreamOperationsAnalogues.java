package com.echoinacup;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class StreamOperationsAnalogues {

    public static Map<String, Integer> mergeTwoListsIntoMapJava(List<String> stringList, List<Integer> integerList) {
        return IntStream.range(0, Math.min(stringList.size(), integerList.size()))
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(stringList.get(i), integerList.get(i)))
                .collect(toMap(
                        AbstractMap.SimpleEntry::getKey,
                        AbstractMap.SimpleEntry::getValue
                ));
    }

    public static int sumWithReduceJava(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }
}