package com.ahlypalo.express_kassa.constants;

public enum OrderColumn {
    EMPLOYEE_NAME("employee_name"), TOTAL("total"), DATE("date");

    private String name;

    OrderColumn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
