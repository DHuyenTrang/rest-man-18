package model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderTable {
    int id;
    Customer customer;
    List<OrderTableDetail> orderTableDetails;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String note;
}
