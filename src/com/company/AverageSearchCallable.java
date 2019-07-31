package com.company;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class AverageSearchCallable implements Callable<Double> {

    private List<String> fileNames;
    private int numberThread;
    private int countThreads;

    AverageSearchCallable(List<String> fileNames, int numberThread, int countThreads) {
        this.fileNames = fileNames;
        this.numberThread = numberThread;
        this.countThreads = countThreads;
    }

    @Override
    public Double call() throws Exception {
//        long start = System.currentTimeMillis();
        double sum = 0;
        int iterations = 0;
        for (int fileNumber = numberThread; fileNumber < fileNames.size(); fileNumber += countThreads) {
            if (fileNumber <= fileNames.size()) {
                String name = fileNames.get(fileNumber);
                try {
                    sum = sum + MathUtils.average(MyFileUtils.fileToArray(name));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                iterations++;
            }
        }
        return sum / iterations;
//        System.out.println("Cреднее арифметическое: " + average);
//        long finish = System.currentTimeMillis();
//        long timeConsumedMillis = finish - start;
//        System.out.println("Время выполнения: " + timeConsumedMillis);
    }
}
