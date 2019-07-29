package com.company;

import java.io.IOException;
import java.util.List;

public class AverageSearchThreadV2 extends Thread {

    private List<String> fileNames;
    private int numberThread;
    private int countThreads;
    private double average;

    AverageSearchThreadV2(List<String> fileNames, int numberThread, int countThreads) {
        this.fileNames = fileNames;
        this.numberThread = numberThread;
        this.countThreads = countThreads;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        double sum = 0;
        int iterations = 0;
        for (int fileNumber = numberThread; fileNumber < fileNames.size(); fileNumber += countThreads) {
            if (fileNumber <= fileNames.size()) {
                String name = fileNumber + ".txt";
                try {
                    sum = sum + MathUtils.average(MyFileUtils.fileToArray(name));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                iterations++;
            }
        }
        average = sum / iterations;
        System.out.println("Cреднее арифметическое: " + average);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Время выполнения: " + timeConsumedMillis);
    }

    public double getAverage() {
        return average;
    }
}
