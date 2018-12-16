package tanneryost.flightreservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class AccountItem {

    private UUID IDnum;
    private String name;
    private String passwd;
    private int sqlLogId;

    private SimpleDateFormat ft = new SimpleDateFormat("dd-M-yyyy @ HH:mm:ss");
    private Date date;

    public AccountItem() {
        IDnum = UUID.randomUUID();
        date = new Date();
    }

    public AccountItem(UUID iD) {
        this();
        IDnum = iD;
    }

    public SimpleDateFormat getFt() {
        return ft;
    }

    public String getDateString() {
        return ft.format(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getIDnum() {
        return IDnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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
        sb.append("\n");
        sb.append("Name: ").append(this.name).append("\n");
        sb.append("Password: ").append(this.passwd).append("\n");
        sb.append("Date: ").append(this.getDateString()).append("\n");
        sb.append("ID: ").append(this.getIDnum());

        return sb.toString();
    }
}
