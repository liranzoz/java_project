package liran_nevo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static liran_nevo.eStatus.*;

public class College {
    private static Scanner s = new Scanner(System.in);
    private String collegeName;
    private Lecturer[] lecturers = new Lecturer[0];
    private static int numOfLecturers;
    private Department[] departments = new Department[0];
    private static int numOfDepartments;
    private Committee[] committees = new Committee[0];
    private static int numOfCommittees;


    public College(String collegeName) {
        setCollegeName(collegeName);
    }

    public void addLecturerToDep(String depName, String lectName) throws CollegeExceptions {
        if(!Util.isExist(depName,departments,numOfDepartments)){
            throw new DepartmentException(DEPARTMENT_DONT_EXIST.toString());
        }
        if(!Util.isExist(lectName,lecturers,numOfLecturers)){
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        Department dep = Util.getDepartmentFromName(depName,departments);
        if(Util.isExist(lectName,dep.getLecturers(),dep.getNumOfLecturers())){
            throw new LecturerException(LECTURER_EXIST.toString());
        }
        dep.addLecturerToDep(Util.getLecturerFromName(lectName,lecturers));
        Util.getLecturerFromName(lectName,lecturers).setDepartment(dep);
    }

    public void showAverageSalaryByDep(String dep) throws CollegeExceptions {
        if (!Util.isExist(dep,departments,numOfDepartments)){
            throw new DepartmentException(DEPARTMENT_DONT_EXIST.toString());
        }
    }

    public void addStudyDepartment(String name, int num)throws CollegeExceptions {
        if(Util.isExist(name,departments,numOfDepartments)){
            throw new DepartmentException(DEPARTMENT_EXIST.toString());
        }
        if (departments.length==numOfDepartments){
            departments=Arrays.copyOf(departments,numOfDepartments==0?1:numOfDepartments*2);
        }
        departments[numOfDepartments++]=new Department(name,num);
    }


    public void removeLecturerFromCommittee(String lecturerName, String committeName)throws CollegeExceptions {
        if (numOfCommittees == 0){
            throw new CommitteeException(NO_COMMITTES_REMOVE.toString());
        }
        if(!Util.isExist(committeName,committees,numOfCommittees)){
            throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
        }
        Committee c = Util.getCommitteeFromName(committeName,committees);
        if (c.getNumOfLecturers() == 0){
            throw new LecturerException(NO_LECTURERS_REMOVE.toString());
        }
        if(!Util.isExist(lecturerName,c.getLecturers(),c.getNumOfLecturers())){
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        c.removeLecturerByName(lecturerName);
    }

    public void updateHeadOfCommittee(String committeeName, String newHead) throws CollegeExceptions{
        if (!Util.isExist(committeeName,committees,numOfCommittees)){
           throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
        }
        if (!Util.isExist(newHead,lecturers,numOfLecturers)){
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
        if (numOfLecturers == 0){
            throw new LecturerException(NO_LECTURERS_ADD.toString());
        }
        if(committees.length==0){
            throw new CommitteeException(NO_COMMITTES_ADD.toString());
        }
       if (!Util.isExist(committee.getName(),committees,numOfCommittees)){
           throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
       }
       if (!Util.isExist(lecturer.getName(),lecturers,numOfLecturers)){
           throw new LecturerException(LECTURER_DONT_EXIST.toString());
       }
       if (Util.isExist(lecturer.getName(),committee.getLecturers(),committee.getNumOfLecturers())){
           throw new LecturerException(LECTURER_EXIST.toString());
       }
        committee.addLecturerToCommittee(lecturer);
        lecturer.addCommittee(committee);
    }
    private void addCommittee(Committee clone) {
        if (numOfCommittees == committees.length) {
            committees = (Committee[]) Util.copyArr(committees, numOfCommittees == 0 ? 1 : numOfCommittees * 2);
        }
        committees[numOfCommittees++] = clone;
    }
    public void addCommittee(String name, String headName)throws CollegeExceptions {
        Lecturer head = Util.getLecturerFromName(headName, lecturers);
        if(head == null || !(head instanceof Doctor)){
            throw new LecturerException(HEAD_NOT_VALID.toString());
        }
        if (Util.isExist(name,committees,numOfCommittees)){
            throw new CommitteeException(COMMITTEE_EXIST.toString());
        }
        if (numOfCommittees == committees.length) {
            committees = (Committee[]) Util.copyArr(committees, numOfCommittees == 0 ? 1 : numOfCommittees * 2);
        }
        committees[numOfCommittees++] = new Committee(name, head);
    }

    // add lecturer
    public void addLecturer(String name,String id,Lecturer.eDegreeType degree,String degName,int salary)throws CollegeExceptions {
        if(Util.isExist(name,lecturers,numOfLecturers)){
            throw new LecturerException(LECTURER_EXIST.toString());
        }
        if (numOfLecturers == lecturers.length) {
            lecturers = (Lecturer[]) Util.copyArr(lecturers, numOfLecturers == 0 ? 1 : numOfLecturers * 2);
        }
        lecturers[numOfLecturers++] = new Lecturer(name, id, degree, degName, salary);
    }

    // override - add lecturer for Doctor
    public void addLecturer(String name,String id,Lecturer.eDegreeType degree,String degName,int salary,String[] articles)throws CollegeExceptions{
        if(Util.isExist(name,lecturers,numOfLecturers)){
            throw new LecturerException(LECTURER_EXIST.toString());
        }
        if (numOfLecturers == lecturers.length) {
            lecturers = (Lecturer[]) Util.copyArr(lecturers, numOfLecturers == 0 ? 1 : numOfLecturers * 2);
        }
        lecturers[numOfLecturers++] = new Doctor(name, id, degree, degName, salary,articles);
    }

    // override - add lecturer for Professor
    public void addLecturer(String name,String id,Lecturer.eDegreeType degree,String degName,int salary,String[] articles, String inst)throws CollegeExceptions {
        if(Util.isExist(name,lecturers,numOfLecturers)){
            throw new LecturerException(LECTURER_EXIST.toString());
        }
        if (numOfLecturers == lecturers.length) {
            lecturers = (Lecturer[]) Util.copyArr(lecturers, numOfLecturers == 0 ? 1 : numOfLecturers * 2);
        }
        lecturers[numOfLecturers++] = new Professor(name, id, degree, degName, salary,articles, inst);
    }

//    public String comparisonDocProf() throws LecturerException {
//        if (countDocProf() == 0){
//            throw new LecturerException(NO_DOC_PROF.toString());
//        }
//        StringBuilder sb = new StringBuilder();
//        sb.append("\nNumber of articles for doctors:\n");
//        for (int i = 0; i < numOfLecturers; i++) {
//            if (lecturers[i] instanceof Doctor && !(lecturers[i] instanceof Professor)) {
//                sb.append(lecturers[i].getName()).append(": ").append(((Doctor) lecturers[i]).getNumOfArticles()).append("\n");
//            }
//        }
//        sb.append("\nNumber of articles for professors:\n");
//        for (int i = 0; i < numOfLecturers; i++) {
//            if (lecturers[i] instanceof Professor) {
//                sb.append(lecturers[i].getName()).append(": ").append(((Doctor) lecturers[i]).getNumOfArticles()).append("\n");
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

    private int countDocProf() {
        int n = 0;
        for (int i = 0; i < numOfLecturers; i++) {
            if(lecturers[i] instanceof Doctor){
                n++;
            }
        }
        return n;
    }

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
        if (numOfCommittees == 0){
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

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    public Department[] getDepartments() {
        return departments;
    }

    public void setDepartments(Department[] departments) {
        this.departments = departments;
    }

    public Committee[] getCommittees() {
        return committees;
    }

    public void setCommittees(Committee[] committees) {
        this.committees = committees;
    }

    public int getNumOfLecturers() {
        return numOfLecturers;
    }

    public int getNumOfCommittees() {
        return numOfCommittees;
    }



}
