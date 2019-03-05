package it.sevenbits.courses.servlet;

public class Task {

    private int ID;
    private String name = null;
    private static int counterID = 0;

    public Task(String name) {
        this.name = name;
        this.ID = counterID;
        counterID++;
    }

    private Task(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
