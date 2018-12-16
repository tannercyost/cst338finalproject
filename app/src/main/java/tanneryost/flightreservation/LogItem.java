package tanneryost.flightreservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class LogItem {

    private UUID logID;
    private String exercize;
    private int reps;
    private double weight;
    private int sqlLogId;

    private SimpleDateFormat ft = new SimpleDateFormat("dd-M-yyyy @ HH:mm:ss");
    private Date date;

    public LogItem(){
        logID = UUID.randomUUID();

        date = new Date();
    }

    public LogItem(UUID iD){
        logID = iD;
    }

    public String getExercize() {
        return exercize;
    }

    public void setExercize(String exercize) {
        this.exercize = exercize;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public SimpleDateFormat getDateFormat() {
        return ft;
    }


    public Date getDate() {
        return date;
    }

    public String getDateString(){
        return ft.format(date);
    }

    public void setDate(Date date){
        this.date = date;
    }

    public UUID getLogID() {
        return logID;
    }

    public int getSqlLogId() {
        return sqlLogId;
    }

    public void setSqlLogId(int sqlLogId) {
        this.sqlLogId = sqlLogId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=-=-=ID(").append(getSqlLogId()).append(")=-=-=\n");
        sb.append(getDateString()).append("\n");
        sb.append("------------------\n");
        sb.append(getExercize()).append("\n");
        sb.append(getWeight()).append("\n");
        sb.append(getReps()).append("\n");
        sb.append("=-=-=-=-=-=-=-=\n");

        return sb.toString();
    }
}
