package liran_nevo;

import java.util.Arrays;
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

    public void addLecturerToDep(String depName, String lectName) throws DepartmentDontExistException,LecturerDontExistException,LecturerExistException {
        if(!Util.isExist(depName,departments,numOfDepartments)){
            throw new DepartmentDontExistException();
        }
        if(!Util.isExist(lectName,lecturers,numOfLecturers)){
            throw new LecturerDontExistException();
        }
        Department dep = Util.getDepartmentFromName(depName,departments);
        if(Util.isExist(lectName,dep.getLecturers(),dep.getNumOfLecturers())){
            throw new LecturerExistException();
        }
        dep.addLecturerToDep(Util.getLecturerFromName(lectName,lecturers));
        Util.getLecturerFromName(lectName,lecturers).setDepartment(dep);
    }

    public void showAverageSalaryByDep(String dep) throws DepartmentDontExistException {
        if (!Util.isExist(dep,departments,numOfDepartments)){
            throw new DepartmentDontExistException();
        }
    }

    public void addStudyDepartment(String name, int num)throws DepartmentExistException {
        if(Util.isExist(name,departments,numOfDepartments)){
            throw new DepartmentExistException();
        }
        if (departments.length==numOfDepartments){
            departments=Arrays.copyOf(departments,numOfDepartments==0?1:numOfDepartments*2);
        }
        departments[numOfDepartments++]=new Department(name,num);
    }


    public void removeLecturerFromCommittee(String lecturerName, String committeName)throws CommitteeDontExistException,LecturerDontExistException {
        if(!Util.isExist(committeName,committees,numOfCommittees)){
            throw new CommitteeDontExistException();
        }
        Committee c = Util.getCommitteeFromName(committeName,committees);
        if(!Util.isExist(lecturerName,c.getLecturers(),c.getNumOfLecturers())){
            throw new LecturerDontExistException();
        }
        c.removeLecturerByName(lecturerName);
    }

    public void updateHeadOfCommittee(String committeeName, String newHead) throws HeadNotValidException, LecturerDontExistException, CommitteeDontExistException {
        if (!Util.isExist(committeeName,committees,numOfCommittees)){
           throw new CommitteeDontExistException();
        }
        if (!Util.isExist(newHead,lecturers,numOfLecturers)){
            throw new LecturerDontExistException();
        }
        if (!(Util.getLecturerFromName(newHead,lecturers) instanceof Doctor doctor)){
            throw new HeadNotValidException();
        }
        Util.getCommitteeFromName(committeeName,committees).setHead(Util.getLecturerFromName(newHead,lecturers));
    }

    public void addLecturerToCommittee(Lecturer lecturer, Committee committee) throws LecturerDontExistException,CommitteeDontExistException,LecturerExistException {
        if(committees.length==0){
            throw new CommitteeDontExistException();
        }
       if (!Util.isExist(committee.getName(),committees,numOfCommittees)){
           throw new CommitteeDontExistException();
       }
       if (!Util.isExist(lecturer.getName(),lecturers,numOfLecturers)){
           throw new LecturerDontExistException();
       }
       if (Util.isExist(lecturer.getName(),committee.getLecturers(),committee.getNumOfLecturers())){
           throw new LecturerExistException();
       }
        committee.addLecturerToCommittee(lecturer);
        lecturer.addCommittee(committee);
    }

    public void addCommittee(String name, Lecturer head)throws HeadNotValidException,CommitteExistException {
        if(!Util.isDocProf(head)){
            throw new HeadNotValidException();
        }
        if (Util.isExist(name,committees,numOfCommittees)){
            throw new CommitteExistException();
        }

        if (numOfCommittees == committees.length) {
            committees = (Committee[]) Util.copyArr(committees, numOfCommittees == 0 ? 1 : numOfCommittees * 2);
        }
        committees[numOfCommittees++] = new Committee(name, head);
    }

    public void addLecturer(String name,String id,Lecturer.eDegreeType degree,String degName,int salary)throws LecturerExistException {
        if(Util.isExist(name,lecturers,numOfLecturers)){
            throw new LecturerExistException();
        }
        if (numOfLecturers == lecturers.length) {
            lecturers = (Lecturer[]) Util.copyArr(lecturers, numOfLecturers == 0 ? 1 : numOfLecturers * 2);
        }
        lecturers[numOfLecturers++] = new Lecturer(name, id, degree, degName, salary);
    }

    public void addLecturer(String name,String id,Lecturer.eDegreeType degree,String degName,int salary,String[] articles)throws LecturerExistException {
        if(Util.isExist(name,lecturers,numOfLecturers)){
            throw new LecturerExistException();
        }
        if (numOfLecturers == lecturers.length) {
            lecturers = (Lecturer[]) Util.copyArr(lecturers, numOfLecturers == 0 ? 1 : numOfLecturers * 2);
        }
        lecturers[numOfLecturers++] = new Doctor(name, id, degree, degName, salary,articles);
    }

    public void addLecturer(String name,String id,Lecturer.eDegreeType degree,String degName,int salary,String[] articles, String inst)throws LecturerExistException {
        if(Util.isExist(name,lecturers,numOfLecturers)){
            throw new LecturerExistException();
        }
        if (numOfLecturers == lecturers.length) {
            lecturers = (Lecturer[]) Util.copyArr(lecturers, numOfLecturers == 0 ? 1 : numOfLecturers * 2);
        }
        lecturers[numOfLecturers++] = new Professor(name, id, degree, degName, salary,articles, inst);
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
