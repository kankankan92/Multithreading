package com.company;

import java.io.IOException;
import java.util.List;

public class AverageSearchThread extends Thread {

    private List<String> fileNames;
    private double average;

    AverageSearchThread(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    @Override
    public void run() {
//        long start = System.currentTimeMillis();
        double sum = 0;
        for (String name : fileNames) {
            try {
                sum = sum + MathUtils.average(MyFileUtils.fileToArray(name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        average = sum / fileNames.size();
//        System.out.println("Cреднее арифметическое: " + average);
//        long finish = System.currentTimeMillis();
//        long timeConsumedMillis = finish - start;
//        System.out.println("Время выполнения: " + timeConsumedMillis);
    }

    public double getAverage() {
        return average;
    }
}
