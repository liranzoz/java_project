package liran_nevo;

import java.util.Arrays;

public class Department {
    private String name;
    private int numOfStudents;
    private Lecturer[] lecturers = new Lecturer[0];
    private int numOfLecturers;

    public Department(String name, int numOfStudents) {
        this.name = name;
        this.numOfStudents = numOfStudents;
    }

    public void addLecturerToDep(Lecturer lecturer){
        if(numOfLecturers==lecturers.length) {
            this.lecturers = Arrays.copyOf(lecturers, lecturers.length==0?1:lecturers.length*2);
        }
        lecturers[numOfLecturers] = lecturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    public int getNumOfLecturers() {
        return numOfLecturers;
    }

    public void setNumOfLecturers(int numOfLecturers) {
        this.numOfLecturers = numOfLecturers;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", numOfStudents=" + numOfStudents +
                ", lecturers=" + Arrays.toString(lecturers) +
                ", numOfLecturers=" + numOfLecturers +
                '}';
    }
}