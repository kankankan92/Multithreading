package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

public class Menu {
    public static void start() throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("1.Сгенерировать n файлов по m строк \n2.Поиск среднего арифметического \n3.Поиск среднего арифметического в многопоточном режиме \n4.Поиск среднего арифметического в многопоточном режиме V2 \n5.Поиск среднего арифметического в многопоточном режиме V3");
            switch (scan.nextLine()) {
                case "1":
                    System.out.println("Введите колличество файлов:");
                    int n = scan.nextInt();
                    System.out.println("Введите колличество строк:");
                    int m = scan.nextInt();
                    scan.nextLine();
                    new FileCreationThread(n, m).start();
                    break;
                case "2":
                    MathUtils.averageInAllFiles();
                    break;

                case "3":

                    //реализация подсчета сренего арифметического с помощью Thread и метода join().
                    long start = System.currentTimeMillis();
                    int countThreads = 10;
                    List<String> fileNames = MyFileUtils.getFilesNames();
                    List<AverageSearchThread> averageSearchThreads = new ArrayList<>();
                    for (int i = 0; i < countThreads; i++) {
                        if (i < fileNames.size()) {
                            List<String> filesForThread = new ArrayList<>();
                            for (int fileNumber = i; fileNumber < fileNames.size(); fileNumber += countThreads) {
                                if (fileNumber < fileNames.size()) {
                                    filesForThread.add(fileNames.get(fileNumber));
                                }
                            }
                            AverageSearchThread averageSearchThread = new AverageSearchThread(filesForThread);
                            averageSearchThreads.add(averageSearchThread);
                            averageSearchThread.start();
                        }
                    }
                    double sum = 0;
                    for (AverageSearchThread thread : averageSearchThreads) {
                        thread.join();
                        sum += thread.getAverage();
                    }
                    double average = sum / averageSearchThreads.size();
                    System.out.println("Среднее арифметическое по всем файлам:" + average);
                    long finish = System.currentTimeMillis();
                    long timeConsumedMillis = finish - start;
                    System.out.println("Время выполнения: " + timeConsumedMillis);
                    break;

                case "4":

                    //реализация подсчета сренего арифметического с помощью Thread, класса CountDownLatch и его методов await() и countDown().
                    long start2 = System.currentTimeMillis();
                    List<String> fileNames1 = MyFileUtils.getFilesNames();
                    if (fileNames1.size() > 10) {
                        countThreads = 10;
                    } else {
                        countThreads = fileNames1.size();
                    }

                    CountDownLatch countDownLatch = new CountDownLatch(countThreads);
                    List<AverageSearchThreadV2> averageSearchThreads2 = new ArrayList<>();
                    for (int i = 0; i < countThreads; i++) {
                        AverageSearchThreadV2 averageSearchThreadV2 = new AverageSearchThreadV2(fileNames1, i, countThreads, countDownLatch);
                        averageSearchThreads2.add(averageSearchThreadV2);
                        averageSearchThreadV2.start();
                    }
                    countDownLatch.await();
                    double sum2 = 0;
                    for (AverageSearchThreadV2 thread : averageSearchThreads2) {
                        sum2 += thread.getAverage();
                    }
                    double average2 = sum2 / averageSearchThreads2.size();
                    System.out.println("Среднее арифметическое по всем файлам:" + average2);
                    long finish2 = System.currentTimeMillis();
                    long timeConsumedMillis2 = finish2 - start2;
                    System.out.println("Время выполнения: " + timeConsumedMillis2);
                    //неообходим собрать результаты работы всех тредов и найти по ним среднее арифметическое
                    break;

                case "5":

                    List<String> fileNames2 = MyFileUtils.getFilesNames();
                    if (fileNames2.size() > 10) {
                        countThreads = 10;
                    } else {
                        countThreads = fileNames2.size();
                    }
                    for (int i = 0; i < countThreads; i++) {
                        AverageSearchCallable averageSearchCallable = new AverageSearchCallable(fileNames2, i, countThreads);
                        FutureTask futureTask = new FutureTask(averageSearchCallable);
//                        futureTask.run();
                    }
            }
            scan.reset();
        }
    }
}
