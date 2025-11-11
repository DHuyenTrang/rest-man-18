package model;

public class OrderTableDetail {
    int id;
    Table table;

    public OrderTableDetail() {}

    public OrderTableDetail(int id, Table table) {
        this.id = id;
        this.table = table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
