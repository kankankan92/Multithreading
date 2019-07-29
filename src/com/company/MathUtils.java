package com.company;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MathUtils {

    public static double average(int[] numbers) {
        //        int average = 0;
//        if (numbers.length > 0) {
//            long sum = 0;
//            for (int j = 0; j < numbers.length; j++) {
//                sum += numbers[j];
//            }
//            average = Math.round(sum / numbers.length);
//        }
//        System.out.println(average);

        return Arrays.stream(numbers).average().getAsDouble();
    }

    public static void averageInAllFiles() throws IOException {
        long start = System.currentTimeMillis();
        List<String> fileNames = MyFileUtils.getFilesNames();
        double sum = 0;
        for (String name : fileNames) {
            sum = sum + average(MyFileUtils.fileToArray(name));
        }
        double average = sum / fileNames.size();
        System.out.println("Cреднее арифметическое: " + average);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Время выполнения: " + timeConsumedMillis);
    }


}
