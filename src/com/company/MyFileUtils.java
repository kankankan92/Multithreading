package com.company;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyFileUtils {

    private static void createFile(int numbersCount, int n) throws IOException {
        Path path = Paths.get("c:/Users/Anastasia/Projects/Multithreading/files/" + n + ".txt");
        Random random = new Random();
        List<String> numbers = new ArrayList<>();
        for (int i = numbersCount; i > 0; i--) {
            numbers.add(String.valueOf(random.nextInt()));
        }
        Files.createFile(path);
        Files.write(path, numbers, StandardCharsets.UTF_8);
    }

    public static void createFiles(int filesCount, int numbersCount) throws IOException {
        long start = System.currentTimeMillis();
        File file = new File("c:/Users/Anastasia/Projects/Multithreading/files");
        FileUtils.cleanDirectory(file);
        int n = filesCount;
        while (n > 0) {
            MyFileUtils.createFile(numbersCount, n);
            n--;
        }
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Время выполнения: " + timeConsumedMillis);
    }

    public static int[] fileToArray(String fileName) throws IOException {
        Path path = Paths.get("c:/Users/Anastasia/Projects/Multithreading/files/" + fileName);
        List<String> numbers = Files.readAllLines(path);
        List<Integer> numbersInteger = new ArrayList<>();
        for (String number:numbers) {
            numbersInteger.add(Integer.parseInt(number));
        }
        int[] nums = numbersInteger.stream().mapToInt(i -> i).toArray();
//        for (int number:nums) {
//            System.out.println(number);
//        }

        return nums;
    }

    public static List<String> getFilesNames() throws IOException {
        List<String> names = new ArrayList<String>();
        File[] files = new File("c:/Users/Anastasia/Projects/Multithreading/files").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                names.add(file.getName());
            }
        }
        return names;
    }

    public static void distributingFilesByThreads() throws IOException {
        List<String> fileNames = getFilesNames();
        if(fileNames.size()<10){
            for (int i = 0; i<fileNames.size(); i++){

            }
        }
    }
}

