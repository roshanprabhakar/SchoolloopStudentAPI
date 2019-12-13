import com.google.common.collect.DiscreteDomain;

import java.util.Arrays;

public class Score {

    private double earned;
    private double possible;

    public Score(double earned, double possible) {
        this.earned = earned;
        this.possible = possible;
    }

    public double percentage() {
        return earned / possible;
    }

    public static Score parseScore(String line) {
//        System.out.println("line: " + line);
        int dash = line.indexOf("/");
//        System.out.println("dash: " + dash);
        int end = 0;
        for (int i = dash + 1; i < line.length(); i++) {
            if (!isDigit(line.substring(i, i + 1)) && line.charAt(i) != ' ' && line.charAt(i) != '.') {
                end = i;
                break;
            }
        }
//        System.out.println("end: " + end);
        int beginning = 0;
        for (int i = dash - 1; i >= 0; i--) {
            if (!isDigit(line.substring(i, i + 1)) && line.charAt(i) != ' ' && line.charAt(i) != '.') {
                beginning = i + 1;
                break;
            }
        }
//        System.out.println("beginning: " + beginning);
        String[] total = line.substring(beginning, end).trim().split(" ");
//        System.out.println("total: " + Arrays.toString(total));

        return new Score(Double.parseDouble(total[1]), Double.parseDouble(total[3]));
    }

    private static boolean isDigit(String ch) {
        try {
            Integer.parseInt(ch);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String toString() {
        return "(" + earned + "/" + possible + ")";
    }
}
