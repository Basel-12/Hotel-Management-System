package org.example.entites;

import javax.xml.crypto.Data;
import java.util.Date;

public class Reservation {
    private int id;
    private int custId;
    private int rooomId;
    private Date startDate;
    private Date endDate;

    public Reservation() {
    }

    public Reservation(int id, int custId, int rooomId, Date startDate, Date endDate) {
        this.id = id;
        this.custId = custId;
        this.rooomId = rooomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getRooomId() {
        return rooomId;
    }

    public void setRooomId(int rooomId) {
        this.rooomId = rooomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
