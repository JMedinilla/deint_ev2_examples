package com.jmedinilla.managephonecall;

public class Call {
    private String number;
    private String type;
    private String date;
    private String duration;

    public Call(String number, String type, String date, String duration) {
        this.number = number;
        this.type = type;
        this.date = date;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Call{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
