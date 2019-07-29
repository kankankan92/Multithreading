package com.company;

import java.io.IOException;

public class FileCreationThread extends Thread{
    private int filesCount;
    private int numbersCount;

    FileCreationThread(int filesCount, int numbersCount){
        this.filesCount = filesCount;
        this.numbersCount = numbersCount;
    }

    @Override
    public void run() {
        try {
            MyFileUtils.createFiles(filesCount, numbersCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
