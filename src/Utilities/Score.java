package Utilities;

public class Score {

    private double earned;
    private double possible;

    public Score(double earned, double possible) {
        this.earned = earned;
        this.possible = possible;
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

    /**
     * deals with input like this: (10.0/20.0)
     */
    public static Score readScore(String line) {
        String[] parts = line.substring(1, line.length() - 1).split("/");
        return new Score(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
    }

    public String toString() {
        return "(" + earned + "/" + possible + ")";
    }

    public double getEarned() {
        return earned;
    }

    public double getPossible() {
        return possible;
    }

    public double percentage() {
        return earned / possible;
    }
}
