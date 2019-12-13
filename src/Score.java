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

        int dash = line.indexOf("/");
        int end = 0;
        for (int i = dash + 1; i < line.length(); i++) {
            if (!isDigit(line.substring(i, i + 1)) && line.charAt(i) != ' ' && line.charAt(i) != '.') {
                end = i;
                break;
            }
        }

        int beginning = 0;
        for (int i = dash - 1; i >= 0; i--) {
            if (!isDigit(line.substring(i, i + 1)) && line.charAt(i) != ' ' && line.charAt(i) != '.') {
                beginning = i + 1;
                break;
            }
        }

        String[] total = line.substring(beginning, end).split(" ");
        String[] score = Arrays.copyOfRange(total, 1, 3);

        return new Score(Double.parseDouble(score[0]), Double.parseDouble(score[1]));
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
