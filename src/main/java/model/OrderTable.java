package model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderTable {
    int id;
    Customer customer;
    List<OrderTableDetail> orderTableDetails;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String note;

    public OrderTable() {}

    public OrderTable(int id, Customer customer, List<OrderTableDetail> orderTableDetails, LocalDateTime startDate, LocalDateTime endDate, String note) {
        this.id = id;
        this.customer = customer;
        this.orderTableDetails = orderTableDetails;
        this.startTime = startDate;
        this.endTime = endDate;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderTableDetail> getOrderTableDetails() {
        return orderTableDetails;
    }

    public void setOrderTableDetails(List<OrderTableDetail> orderTableDetails) {
        this.orderTableDetails = orderTableDetails;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
