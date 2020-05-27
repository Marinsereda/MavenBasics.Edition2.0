import java.util.ArrayList;

public class Task_1 {
    public static void main(String[] args) {
        //writeLines3(4, line0);
        printLines3(4);
    }

    public static void writeLines(int a) {
        String line = "###";
        for (int i = 0; i < a; i++) {
            System.out.println(line);
        }
    }

    public static void writeLines2(int b) {
        String line = "###";
        if (b < 1) {
            return;
        }
        System.out.println(line);
        writeLines2(b - 1);

    }

    public static void writeLines3(int b, String line) {

        if (b < 1) {
            return;
        }
        System.out.println(line);
        writeLines3(b - 1, line+"#");

    }

    static String line0 = "##";

    static void printLines3(int height){
        writeLines3(height, "##");

    }


}

