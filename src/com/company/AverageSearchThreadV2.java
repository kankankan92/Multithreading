package com.company;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class AverageSearchThreadV2 extends Thread {

    private List<String> fileNames;
    private int numberThread;
    private int countThreads;
    private double average;
    private CountDownLatch countDownLatch;

    AverageSearchThreadV2(List<String> fileNames, int numberThread, int countThreads, CountDownLatch countDownLatch) {
        this.fileNames = fileNames;
        this.numberThread = numberThread;
        this.countThreads = countThreads;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
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
        average = sum / iterations;
        countDownLatch.countDown();
    }

    public double getAverage() {
        return average;
    }
}


