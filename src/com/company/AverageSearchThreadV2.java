package com.company;

import java.io.IOException;
import java.util.List;

public class AverageSearchThreadV2 extends Thread {

    List<String> fileNames;
    int numberThread;
    int countThreads;

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
        double average = sum / iterations;
        System.out.println("Cреднее арифметическое: " + average);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Время выполнения: " + timeConsumedMillis);
    }
}
