package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void start() throws IOException {
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
                    FileCreationThread fileCreationThread = new FileCreationThread(n, m);
                    fileCreationThread.start();
//                    MyFileUtils.createFiles(n, m);
                    break;
                case "2":
                    MathUtils.averageInAllFiles();
//                    MyFileUtils.fileToArray("1.txt");
                    break;
                case "3":
//                    List<String> fileNames = MyFileUtils.getFilesNames();
//                    if(fileNames.size()<10){
//                        for (int i = 0; i<fileNames.size(); i++){
//                            List<String> filesForThread = new ArrayList<>();
//                            filesForThread.add(fileNames.get(i));
//                            AverageSearchThread averageSearchThread = new AverageSearchThread(filesForThread);
//                            averageSearchThread.start();
//                        }
//                    }else {
//                        int count = Math.round(fileNames.size()/10);
//                        for (int i = 0; i<fileNames.size(); i += count){
//                            List<String> filesForThread = new ArrayList<>();
//                            //небходимо лист fileNames разделить на кусочки между потоками
//                            filesForThread = fileNames.subList(i, i+count);
//                            AverageSearchThread averageSearchThread = new AverageSearchThread(filesForThread);
//                            averageSearchThread.start();
//                        }
//                    }
//                    AverageSearchThread averageSearchThread = new AverageSearchThread(MyFileUtils.getFilesNames());
//                    averageSearchThread.start();
                    int countThreads = 10;
                    List<String> fileNames = MyFileUtils.getFilesNames();
                    for (int i = 0; i < countThreads; i++) {
                        List<String> filesForThread = new ArrayList<>();
                        for (int fileNumber = i; fileNumber < fileNames.size(); fileNumber += 10) {
                            if (fileNumber <= fileNames.size()) {
                                filesForThread.add(fileNames.get(fileNumber));
                            }
                        }
                        AverageSearchThread averageSearchThread = new AverageSearchThread(filesForThread);
                        averageSearchThread.start();
                    }
                    break;
                case "4":
                    int countThreads1 = 10;
                    List<String> fileNames1 = MyFileUtils.getFilesNames();
                    for (int i = 1; i <= countThreads1; i++) {
                        AverageSearchThreadV2 averageSearchThreadV2 = new AverageSearchThreadV2(fileNames1, i, countThreads1);
                        averageSearchThreadV2.start();
                    }
                    break;
            }
            scan.reset();
        }
    }
}
