package hms;

public class Feedback {

    private String feedbackId;
    private String patientId;
    private String doctorId;
    private int rating;
    private String comment;
    private String date;

    public Feedback(String feedbackId, String patientId, 
                    String doctorId, int rating, 
                    String comment, String date) {
        this.feedbackId = feedbackId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public String getFeedbackId() { return feedbackId; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public String getDate() { return date; }

    public void setRating(int rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }

    public String toString() {
        return feedbackId + "," + patientId + "," + doctorId + "," 
               + rating + "," + comment + "," + date;
    }

}