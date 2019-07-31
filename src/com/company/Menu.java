package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void start() throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("1.Сгенерировать n файлов по m строк \n2.Поиск среднего арифметического \n3.Поиск среднего арифметического в многопоточном режиме \n4.Поиск среднего арифметического в многопоточном режиме V2");
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
                    for (AverageSearchThread thread: averageSearchThreads) {
                        thread.join();
                        sum += thread.getAverage();
                    }
                    double average = sum / averageSearchThreads.size();
                    System.out.println("Среднее арифметическое по всем файлам:" +average);
                    long finish = System.currentTimeMillis();
                    long timeConsumedMillis = finish - start;
                    System.out.println("Время выполнения: " + timeConsumedMillis);
                    break;
                case "4":
                    countThreads = 10;
                    List<String> fileNames1 = MyFileUtils.getFilesNames();
                    for (int i = 1; i <= countThreads; i++) {
                        AverageSearchThreadV2 averageSearchThreadV2 = new AverageSearchThreadV2(fileNames1, i, countThreads);
                        averageSearchThreadV2.start();
                    }
                    //неообходим собрать результаты работы всех тредов и найти по ним среднее арифметическое
                    break;
            }
            scan.reset();
        }
    }
}
