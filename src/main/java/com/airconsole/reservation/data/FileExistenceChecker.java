package com.airconsole.reservation.data;

import java.io.File;

public class FileExistenceChecker {

    public static boolean fileExists(String filePath) {

        // Specify the file path
        // Create a File object with the specified file path
        File file = new File(filePath);

        // Check if the file exists
        if (file.exists()) {

            return true;
        } else {

            return false;
        }
    }
}
