//    Liran Zozulya & Nevo Glanz
package liran_nevo;
import java.util.Scanner;

public class liran_nevo {
    static Scanner s = new Scanner(System.in);
    private static final String[] MENU = {
            "exit menu",
            "add lecturer to college",
            "add committee to college",
            "add lecturer to committee",
            "update head of committee",
            "remove lecturer from committee",
            "add department",
            "add lecturer to department",
            "show average salary - all lecturers",
            "show average salary by department",
            "show all lecturers data",
            "show all committees data"
    };

    public static void run(College college) {
        int userChoice;
        do {
            System.out.println("---------menu---------");
            for (int i = 0; i < MENU.length; i++) {
                System.out.println(i + ") " + MENU[i]);
            }
            System.out.print("your choice: ");
            userChoice = s.nextInt();
            switch (userChoice) {
                case 0 -> {
                    System.out.println("Thanks, See You...");
                    return;
                }
                case 1 -> getLecturerDetails(college);
                case 2 -> getCommitteeDetails(college);
                case 3 -> getLecturerCommitteeDetails(college);
                case 4 -> getHeadDetails(college);
                case 5 -> getLecturerCommitteeDetails(college,"remove");
                case 6 -> getDepartmentDetails(college);
                case 7 -> getLecturerDepDetails(college);
                case 8 -> showAverageSalaryAllLecturers(college);
                case 9 -> getDepartmentDetails(college, "show");
                case 10 -> showAllLecturers(college);
                case 11 -> showAllCommittee(college);
            }
        } while (userChoice != 0);
    }

    private static void showAllCommittee(College college) {
        for (int i = 0; i < college.getNumOfCommittees(); i++) {
            System.out.println(college.getCommittees()[i]);
        }
    }

    private static void showAllLecturers(College college) {
        for (int i = 0; i < college.getnumOfLecturers(); i++) {
            System.out.println(college.getLecturers()[i]);
        }
    }

    private static void getDepartmentDetails(College college, String show) { //dep salaries
        s.nextLine();
        System.out.println("enter department name- ");
        String name = s.nextLine();
        eStatus status = college.showAverageSalaryByDep(name);
        switch (status){
            case DEPARTMENT_DONT_EXIST -> System.out.println(eStatus.LECTURER_DONT_EXIST);
            case SUCCESS -> System.out.println(Util.getAverage(Util.getDepartmentFromName(name,college.getDepartments())));
        }

    }

    private static void showAverageSalaryAllLecturers(College college) {
       double average = Util.getAverage(college.getLecturers(), college.getnumOfLecturers());
        System.out.println(average);
    }

    private static void getLecturerDepDetails(College college) {
        s.nextLine();
        System.out.println("What department? ");
        Util.printArraysByName(college.getDepartments());
        String depName = s.nextLine();
        System.out.println("What lecturer? ");
        System.out.println("enter the name");
        Util.printArraysByName(college.getLecturers());
        String lecturerName = s.nextLine();
        eStatus stat = college.addLecturerToDep(depName,lecturerName);
        switch (stat){
            case LECTURER_DONT_EXIST -> System.out.println(eStatus.LECTURER_DONT_EXIST);
            case LECTURER_EXISTS -> System.out.println(eStatus.LECTURER_EXISTS);
            case DEPARTMENT_DONT_EXIST -> System.out.println(eStatus.DEPARTMENT_DONT_EXIST);
            case SUCCESS -> System.out.println("added");
        }
    }

    private static void getDepartmentDetails(College college) {
        s.nextLine();
        System.out.println("enter department name- ");
        String name = s.nextLine();
        System.out.println("enter num of students ");
        int num = s.nextInt();
        eStatus stat = college.addStudyDepartment(name,num);
        switch (stat){
            case SUCCESS -> System.out.println("added");
            case DEPARTMENT_EXIST -> System.out.println(eStatus.DEPARTMENT_EXIST);
        }
    }

    private static void getHeadDetails(College college) {
        s.nextLine();
        System.out.println("What Committee: ");
        String committeeName = s.nextLine();
        System.out.println("enter new head of committee: ");
        String newHead = s.nextLine();
        eStatus stat = college.updateHeadOfCommittee(committeeName,newHead);
        switch (stat){
            case HEAD_NOT_VALID -> System.out.println(eStatus.HEAD_NOT_VALID);
            case COMMITTEE_DONT_EXIST -> System.out.println(eStatus.COMMITTEE_DONT_EXIST);
            case LECTURER_DONT_EXIST -> System.out.println(eStatus.LECTURER_DONT_EXIST);
            case SUCCESS -> System.out.println("added");
        }
    }

    private static void getLecturerCommitteeDetails(College college, String remove) {
        s.nextLine();
        String lecturerName, committeeName;
        System.out.println("enter lecturer name: ");
        Util.printArraysByName(college.getLecturers());
        lecturerName = s.nextLine();
        System.out.println("enter committee name: ");
        Util.printArraysByName(college.getCommittees());
        committeeName = s.nextLine();
        eStatus stat = college.removeLecturerFromCommittee(lecturerName,committeeName);
        switch (stat){
            case COMMITTEE_DONT_EXIST -> System.out.println(eStatus.COMMITTEE_DONT_EXIST);
            case LECTURER_DONT_EXIST -> System.out.println(eStatus.LECTURER_DONT_EXIST);
            case SUCCESS -> System.out.println("removed");
        }
    }

    private static void getLecturerCommitteeDetails(College college) {
        s.nextLine();
        String lecturerName, committeeName;
        System.out.println("enter lecturer name: ");
        Util.printArraysByName(college.getLecturers());
        lecturerName = s.nextLine();
        System.out.println("enter committee name: ");
        Util.printArraysByName(college.getCommittees());
        committeeName = s.nextLine();
        eStatus stat = college.addLecturerToCommittee(Util.getLecturerFromName(lecturerName, college.getLecturers()), Util.getCommitteeFromName(committeeName, college.getCommittees()));
        switch (stat){
            case COMMITTEE_DONT_EXIST -> System.out.println(eStatus.COMMITTEE_DONT_EXIST);
            case SUCCESS -> System.out.println("added");
        }
    }

    private static void getCommitteeDetails(College college) {
        s.nextLine();
        System.out.println("enter committee name: ");
        String name = s.nextLine();
        System.out.println("enter name of head of committee: ");
        for (Lecturer name1 : college.getLecturers()){
            if(name1!=null) {
                System.out.println(name1.getName() + " - " + name1.getDegree());
            }
        }
        String headName = s.nextLine();
        eStatus stat = college.addCommittee(name,Util.getLecturerFromName(headName,college.getLecturers()));
        switch (stat){
            case COMMITTEE_EXIST -> System.out.println(eStatus.COMMITTEE_EXIST);
            case HEAD_NOT_VALID -> System.out.println(eStatus.HEAD_NOT_VALID);
            case SUCCESS -> System.out.println("added");
        }
    }

    private static void getLecturerDetails(College college) {
        s.nextLine();
        System.out.println("enter name: ");
        String name = s.nextLine();
        System.out.println("enter id");
        String id = s.nextLine();
        System.out.println("enter degree: \n1- BSc\n2- MSc\n3- doctor\n4- professor");
        int degree = s.nextInt();
        Lecturer.eDegreeType deg;
        switch (degree) {
            case 1 -> deg = Lecturer.eDegreeType.BSc;
            case 2 -> deg = Lecturer.eDegreeType.MSc;
            case 3 -> deg = Lecturer.eDegreeType.DOCTOR;
            case 4 -> deg = Lecturer.eDegreeType.PROFESSOR;
            default -> deg = Lecturer.eDegreeType.BSc; //default for invalid is first degree
        }
        s.nextLine();
        System.out.println("enter degree name: ");
        String degName = s.nextLine();
        System.out.println("enter salary: ");
        int salary = s.nextInt();
        eStatus stat = college.addLecturer(name,id,deg,degName,salary);
        switch (stat){
            case SUCCESS -> System.out.println("added");
            case LECTURER_EXISTS -> System.out.println(eStatus.LECTURER_EXISTS);
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter collage name: ");
        String name = s.nextLine();
        College c = new College(name);
        run(c);
    }
}
//Pini The King !