package liran_nevo;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

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

    public eStatus addLecturerToDep(String depName, String lectName) {
        if(!Util.isExist(depName,departments,numOfDepartments)){
            return eStatus.DEPARTMENT_DONT_EXIST;
        }
        if(!Util.isExist(lectName,lecturers,numOfLecturers)){
            return eStatus.LECTURER_DONT_EXIST;
        }
        Department dep = Util.getDepartmentFromName(depName,departments);
        if(Util.isExist(lectName,dep.getLecturers(),dep.getNumOfLecturers())){
            return eStatus.LECTURER_EXISTS;
        }
        dep.addLecturerToDep(Util.getLecturerFromName(lectName,lecturers));
        Util.getLecturerFromName(lectName,lecturers).setDepartment(dep);
        return eStatus.SUCCESS;
    }

    private void showAllCommittee() {
        for (int i = 0; i < committees.length; i++) {
            System.out.println(i + ")" + committees[i]);
        }
    }


    private void showAllLecturers() {
        for (int i = 0; i < lecturers.length; i++) {
            System.out.println(i + ")" + lecturers[i]);
        }
    }


    public eStatus showAverageSalaryByDep(String dep) {
        if (!Util.isExist(dep,departments,numOfDepartments)){
            return eStatus.DEPARTMENT_DONT_EXIST;
        }
        return eStatus.SUCCESS;
    }



    public eStatus addStudyDepartment(String name, int num) {
        if(Util.isExist(name,departments,numOfDepartments)){
            return eStatus.DEPARTMENT_EXIST;
        }
        if (departments.length==numOfDepartments){
            departments=Arrays.copyOf(departments,numOfDepartments==0?1:numOfDepartments*2);
        }
        departments[numOfDepartments++]=new Department(name,num);
        return eStatus.SUCCESS;
    }


    public eStatus removeLecturerFromCommittee(String lecturerName, String committeName) {
        if(!Util.isExist(committeName,committees,numOfCommittees)){
            return eStatus.COMMITTEE_DONT_EXIST;
        }
        Committee c = Util.getCommitteeFromName(committeName,committees);
        if(!Util.isExist(lecturerName,c.getLecturers(),c.getNumOfLecturers())){
            return eStatus.LECTURER_DONT_EXIST;
        }
        c.removeLecturerByName(lecturerName);
        return eStatus.SUCCESS;
    }

    public eStatus updateHeadOfCommittee(String committeeName, String newHead) {
        if (!Util.isExist(committeeName,committees,numOfCommittees)){
           return eStatus.COMMITTEE_DONT_EXIST;
        }
        if (!Util.isExist(newHead,lecturers,numOfLecturers)){
            return eStatus.LECTURER_DONT_EXIST;
        }
        if (!Util.isDocProf(Util.getLecturerFromName(newHead,lecturers))){
            return eStatus.HEAD_NOT_VALID;
        }
        Util.getCommitteeFromName(committeeName,committees).setHead(Util.getLecturerFromName(newHead,lecturers));
        return eStatus.SUCCESS;
    }

    public eStatus addLecturerToCommittee(Lecturer lecturer, Committee committee) {
        if(committees.length==0){
            return eStatus.COMMITTEE_DONT_EXIST;
        }
       if (!Util.isExist(committee.getName(),committees,numOfCommittees)){
           return eStatus.COMMITTEE_DONT_EXIST;
       }
       if (!Util.isExist(lecturer.getName(),lecturers,numOfLecturers)){
           return eStatus.LECTURER_DONT_EXIST;
       }
       if (Util.isExist(lecturer.getName(),committee.getLecturers(),committee.getNumOfLecturers())){
           return eStatus.GENERAL_ERROR;
       }
        committee.addLecturerToCommittee(lecturer);
        lecturer.addCommittee(committee);
        return eStatus.SUCCESS;
    }

    public eStatus addCommittee(String name, Lecturer head) {
        if(!Util.isDocProf(head)){
            return eStatus.HEAD_NOT_VALID;
        }
        if (Util.isExist(name,committees,numOfCommittees)){
            return eStatus.COMMITTEE_EXIST;
        }

        if (numOfCommittees == committees.length) {
            committees = (Committee[]) Util.copyArr(committees, numOfCommittees == 0 ? 1 : numOfCommittees * 2);
        }
        committees[numOfCommittees++] = new Committee(name, head);
            return eStatus.SUCCESS;
    }

    public eStatus addLecturer(String name,String id,Lecturer.eDegreeType degree,String degName,int salary) {
        if(Util.isExist(name,lecturers,numOfLecturers)){
            return eStatus.LECTURER_EXISTS;
        }
        if (numOfLecturers == lecturers.length) {
            lecturers = (Lecturer[]) Util.copyArr(lecturers, numOfLecturers == 0 ? 1 : numOfLecturers * 2);
        }
        lecturers[numOfLecturers++] = new Lecturer(name, id, degree, degName, salary);
        return eStatus.SUCCESS;
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

    public static int getNumOfCommittees() {
        return numOfCommittees;
    }
}
