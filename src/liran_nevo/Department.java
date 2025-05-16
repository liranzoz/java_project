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

    public void removeLecturerFromDep(Lecturer lecturer) {
        int pos = 0;
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i].equals(lecturer)) {
                pos = i;
                break;
            }
            for (int j = pos; j < lecturers.length; j++) {
                if (lecturers[j + 1] == null || j + 1 > lecturers.length) {
                    break;
                }
                lecturers[j] = lecturers[j + 1];
            }
        }
    }

    public void addLecturerToDep(Lecturer lecturer) {
        if (numOfLecturers == lecturers.length) {
            this.lecturers = Arrays.copyOf(lecturers, lecturers.length == 0 ? 1 : lecturers.length * 2);
        }
        lecturers[numOfLecturers++] = lecturer;
        lecturer.setDepartment(this);
    }

    public int getNumOfArticles(){
        int numA = 0;
        for (int i = 0; i <numOfLecturers ; i++) {
            if (lecturers[i] instanceof Doctor){
                numA += ((Doctor) lecturers[i]).getNumOfArticles();
            }
        }
        return numA;
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
        System.out.println();
        StringBuilder sb = new StringBuilder("department: " + name + " | number of students: " + numOfStudents + "\nlecturers:\n");
        if (numOfLecturers == 0) {
            sb.append("no lecturers\n");
            return sb.toString();
        }
            for (int i = 0; i < numOfLecturers; i++) {
                if (i + 1 == numOfLecturers) {
                    sb.append(lecturers[i].getName()).append(", ");
                } else {
                    sb.append(lecturers[i].getName());
                }
            }
        sb.append("\n");
        return sb.toString();
    }
}