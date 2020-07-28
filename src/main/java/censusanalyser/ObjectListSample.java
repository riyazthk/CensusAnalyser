package com.openlibrary;

public class CSVPojoClass {
    String name;
    String email;
    String phNo;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CSVPojoClass{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phNo='" + phNo + '\'' +
                '}';
    }
}
