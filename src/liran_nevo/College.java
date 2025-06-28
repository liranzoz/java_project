package liran_nevo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static liran_nevo.eStatus.*;

public class College implements Serializable {
    private static Scanner s = new Scanner(System.in);
    private String collegeName;
    private ArrayList<Lecturer> lecturers = new ArrayList<>();
    private ArrayList<Department> departments = new ArrayList<>();
    private ArrayList<Committee> committees = new ArrayList<>();


    public College(String collegeName) {
        setCollegeName(collegeName);
    }

    public void addLecturerToDep(String depName, String lectName) throws CollegeExceptions {
        if(!Util.isExist(depName,departments)){
            throw new DepartmentException(DEPARTMENT_DONT_EXIST.toString());
        }
        if(!Util.isExist(lectName,lecturers)){
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        Department dep = Util.getDepartmentFromName(depName,departments);
        if(Util.isExist(lectName,dep.getLecturers())){
            throw new LecturerException(LECTURER_EXIST.toString());
        }
        dep.addLecturerToDep(Util.getLecturerFromName(lectName,lecturers));
        Util.getLecturerFromName(lectName,lecturers).setDepartment(dep);
    }

    public void showAverageSalaryByDep(String dep) throws CollegeExceptions {
        if (!Util.isExist(dep,departments)){
            throw new DepartmentException(DEPARTMENT_DONT_EXIST.toString());
        }
    }

    public void addStudyDepartment(String name, int num)throws CollegeExceptions {
        if(Util.isExist(name,departments)){
            throw new DepartmentException(DEPARTMENT_EXIST.toString());
        }
        departments.add(new Department(name,num));
    }


    public void removeLecturerFromCommittee(String lecturerName, String committeName)throws CollegeExceptions {
        if (committees.size() == 0){
            throw new CommitteeException(NO_COMMITTES_REMOVE.toString());
        }
        if(!Util.isExist(committeName,committees)){
            throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
        }
        Committee c = Util.getCommitteeFromName(committeName,committees);
        if (c.getNumOfLecturers() == 0){
            throw new LecturerException(NO_LECTURERS_REMOVE.toString());
        }
        if(!Util.isExist(lecturerName,c.getLecturers())){
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        c.removeLecturerByName(lecturerName);
    }

    public void updateHeadOfCommittee(String committeeName, String newHead) throws CollegeExceptions{
        if (!Util.isExist(committeeName,committees)){
           throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
        }
        if (!Util.isExist(newHead,lecturers)){
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        if (!(Util.getLecturerFromName(newHead,lecturers) instanceof Doctor doctor)){
            throw new LecturerException(HEAD_NOT_VALID.toString());
        }
        Committee c = Util.getCommitteeFromName(committeeName,committees);
        Lecturer head = c.getHead();
        head.removeCommitte(c);
        Util.getCommitteeFromName(committeeName,committees).setHead(Util.getLecturerFromName(newHead,lecturers));
    }

    public void addLecturerToCommittee(Lecturer lecturer, Committee committee) throws CollegeExceptions {
        if (lecturers.isEmpty()){
            throw new LecturerException(NO_LECTURERS_ADD.toString());
        }
        if(committees.isEmpty()){
            throw new CommitteeException(NO_COMMITTES_ADD.toString());
        }
       if (!Util.isExist(committee.getName(),committees)){
           throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
       }
       if (!Util.isExist(lecturer.getName(),lecturers)){
           throw new LecturerException(LECTURER_DONT_EXIST.toString());
       }
       if (Util.isExist(lecturer.getName(),committee.getLecturers())){
           throw new LecturerException(LECTURER_EXIST.toString());
       }
        committee.addLecturerToCommittee(lecturer);
        lecturer.addCommittee(committee);
    }
    private void addCommittee(Committee clone) {
        committees.add(clone);
    }
    public void addCommittee(String name, String headName,Lecturer.eDegreeType deg)throws CollegeExceptions {
        Lecturer head = Util.getLecturerFromName(headName, lecturers);
        if(head == null || !(head instanceof Doctor)){
            throw new LecturerException(HEAD_NOT_VALID.toString());
        }
        if (Util.isExist(name,committees)){
            throw new CommitteeException(COMMITTEE_EXIST.toString());
        }
        committees.add(new Committee(name, head,deg));
    }

    // add lecturer
    public void addLecturer(String name,String id,Lecturer.eDegreeType degree,String degName,int salary)throws CollegeExceptions {
        if(Util.isExist(name,lecturers)){
            throw new LecturerException(LECTURER_EXIST.toString());
        }
        lecturers.add(new Lecturer(name, id, degree, degName, salary));
    }

    // override - add lecturer for Doctor
    public void addLecturer(String name,String id,Lecturer.eDegreeType degree,String degName,int salary,ArrayList<String> articles)throws CollegeExceptions{
        if(Util.isExist(name,lecturers)){
            throw new LecturerException(LECTURER_EXIST.toString());
        }
        lecturers.add(new Doctor(name, id, degree, degName, salary,articles));
    }

    // override - add lecturer for Professor
    public void addLecturer(String name,String id,Lecturer.eDegreeType degree,String degName,int salary,ArrayList<String> articles, String inst)throws CollegeExceptions {
        if(Util.isExist(name,lecturers)){
            throw new LecturerException(LECTURER_EXIST.toString());
        }
        lecturers.add(new Professor(name, id, degree, degName, salary,articles, inst));
    }

//    public String comparisonDocProf() throws LecturerException {
//        if (countDocProf() == 0){
//            throw new LecturerException(NO_DOC_PROF.toString());
//        }
//        StringBuilder sb = new StringBuilder();
//        sb.append("\nNumber of articles for doctors:\n");
//        for (int i = 0; i < lecturers.size(); i++) {
//            if (lecturers.get(i) instanceof Doctor && !(lecturers.get(i) instanceof Professor)) {
//                sb.append(lecturers.get(i).getName()).append(": ").append(((Doctor) lecturers.get(i)).getNumOfArticles()).append("\n");
//            }
//        }
//        sb.append("\nNumber of articles for professors:\n");
//        for (int i = 0; i < lecturers.size(); i++) {
//            if (lecturers.get(i) instanceof Professor) {
//                sb.append(lecturers.get(i).getName()).append(": ").append(((Doctor) lecturers.get(i)).getNumOfArticles()).append("\n");
//            }
//        }
//        return sb.toString();
//    }

//    public String comparisonDocProf(Doctor d1,Doctor d2){
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < numOfLecturers; i++) {
//            if (lecturers[i] == d1 || lecturers[i] == d2){
//                sb.append(lecturers[i].getName()).append(": ").append(((Doctor) lecturers[i]).getNumOfArticles()).append("\n");
//            }
//        }
//        return sb.toString();
//    }
//
//    private int countDocProf() {
//        int n = 0;
//        for (int i = 0; i < numOfLecturers; i++) {
//            if(lecturers[i] instanceof Doctor){
//                n++;
//            }
//        }
//        return n;
//    }

//    public String compareByNumOfLec() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Comparison by number of lecturers in department:\n ");
//        for (int i = 0; i <numOfDepartments; i++){
//            sb.append("\n").append(i+1).append(")").append(departments[i].getName())
//                    .append(", number of lecturers: ").append(departments[i].getNumOfLecturers()).append("\n");
//        }
//        return sb.toString();
//    }
//
//    public String compareByNumOfArt(){
//        StringBuilder sb = new StringBuilder();
//        sb.append("Comparison by number of Articles in department:\n ");
//        for (int i = 0; i <numOfDepartments; i++){
//            sb.append("\n").append(i+1).append(")").append(departments[i].getName())
//                    .append(", number of Articles: ").append(departments[i].getNumOfArticles()).append("\n");
//        }
//        return sb.toString();
//    }

    public void DuplicateComittee(String comiteeName) throws CollegeExceptions, CloneNotSupportedException {
        if (committees.size() == 0){
            throw new CommitteeException(NO_COMMITTES.toString());
        }
        if (Util.getCommitteeFromName(comiteeName,committees) == null){
            throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
        }
        Committee newCom = Util.getCommitteeFromName(comiteeName,committees);
        this.addCommittee(newCom.clone());
    }


    // 1- compare by lecturers
    // 2- compare by articles
    public int CompareCom(String c1, String c2, int criteria) throws CollegeExceptions {
        Committee com1 = Util.getCommitteeFromName(c1,committees);
        Committee com2 = Util.getCommitteeFromName(c2,committees);
        if (criteria != 1 && criteria != 2){
            throw new CollegeExceptions(GENERAL_ERROR.toString());
        }
        if (com1 == null || com2 == null){
            throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
        }
        switch (criteria) {
            case 1-> {return new CompareComByNumOfLect().compare(com1, com2);}
            case 2-> {return new CompareComByNumOfArt().compare(com1,com2);}
        }
        return 0;
    }

    public int compareDocProf(String firstName, String secName) throws CollegeExceptions {
        Lecturer doc1 = Util.getLecturerFromName(firstName,lecturers);
        Lecturer doc2 = Util.getLecturerFromName(secName,lecturers);
        if (doc1 == null || doc2 == null){
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        if (!(doc1 instanceof Doctor) || !(doc2 instanceof Doctor)){
            throw new LecturerException(NOT_DOC.toString());
        }
        return ((Doctor) doc1).compareTo((Doctor) doc2);
    }


    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public ArrayList<Committee> getCommittees() {
        return committees;
    }

    public void setCommittees(ArrayList<Committee> committees) {
        this.committees = committees;
    }


    public int getNumOfLecturers() {
        return lecturers.size();
    }

    public int getNumOfCommittees() {
        return committees.size();
    }
}
