package hms;

import java.io.*;
import java.util.ArrayList;

public class FeedbackFileHandler {

    private static final String FILE_NAME = "data/feedback.txt";

    public static void saveFeedback(Feedback fb) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, true));
            pw.println(fb.toString());
            pw.close();
        } catch (IOException e) {
            System.out.println("Error saving feedback: " + e.getMessage());
        }
    }

    public static ArrayList<Feedback> loadAllFeedback() {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                list.add(new Feedback(p[0], p[1], p[2], 
                         Integer.parseInt(p[3]), p[4], p[5]));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("No feedback file found.");
        }
        return list;
    }

}