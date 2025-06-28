package liran_nevo;

import java.util.ArrayList;
import java.util.Arrays;

import static liran_nevo.eStatus.*;

public class Department implements Collegable{
    private String name;
    private int numOfStudents;
    private ArrayList<Lecturer> lecturers = new ArrayList<>();
    private int numOfLecturers;

    public Department(String name, int numOfStudents) {
        this.name = name;
        this.numOfStudents = numOfStudents;
    }

    public void removeLecturerFromDep(Lecturer lecturer) {
//        int pos = 0;
//        for (int i = 0; i < lecturers.size(); i++) {
//            if (lecturers.get(i).equals(lecturer)) {
//                pos = i;
//                break;
//            }
//            for (int j = pos; j < lecturers.size(); j++) {
//                if (lecturers.get(j + 1) == null || j + 1 > lecturers.size()) {
//                    break;
//                }
//                lecturers.set(j, lecturers.get(j + 1));
//            }
//        }
        lecturers.remove(lecturer);
    }

    public void addLecturerToDep(Lecturer lecturer) throws LecturerException {
        if (Util.isExist(lecturer.getName(),lecturers)){
            throw new LecturerException(LECTURER_EXIST.toString());
        }
        lecturers.add(lecturer);
        lecturer.setDepartment(this);
    }

    public int getNumOfArticles(){
        int numA = 0;
        for (int i = 0; i <numOfLecturers ; i++) {
            if (lecturers.get(i) instanceof Doctor){
                numA += ((Doctor) lecturers.get(i)).getArticles().size();
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

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
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
                    sb.append(lecturers.get(i).getName()).append(", ");
                } else {
                    sb.append(lecturers.get(i).getName());
                }
            }
        sb.append("\n");
        return sb.toString();
    }
}