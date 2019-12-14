package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

    public static ArrayList<String> readLines(String filepath) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            System.err.println("Could not read data");
            return null;
        }
    }

    // checks whether an assignment belongs to a specified weight category
    public static boolean belongsTo(String category, String assignmentName) {
        return assignmentName.contains(category);
    }
}
