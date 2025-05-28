//    Liran Zozulya & Nevo Glanz
package liran_nevo;
import java.util.InputMismatchException;
import java.util.Scanner;

import static liran_nevo.eStatus.*;

public class liran_nevo {
    static Scanner s = new Scanner(System.in);
    private static final String[] MENU = {
            "exit menu",//0
            "add lecturer to college",//1
            "add committee to college",//2
            "add lecturer to committee",//3
            "update head of committee",//4
            "remove lecturer from committee",//5
            "add department",//6
            "add lecturer to department",//7
            "show average salary - all lecturers",//8
            "show average salary by department",//9
            "show all lecturers data",//10
            "show all committees data",//11
            "compare Doctor/ professor by number of articles",//12
            "compare department",//13
            "duplicate committee",//14 - 2קריטריונים
            "remove lecturer from committee"//15
    };
    // i/o method run
    public static void run(College college) {
            int userChoice = -1;
            do {
                try {
                    System.out.println("---------menu---------");
                    System.out.println("enter the number of the option:");
                    for (int i = 0; i < MENU.length; i++) {
                        System.out.println(i + ") " + MENU[i]);
                    }
                    System.out.print("your choice: ");
                    try {
                        userChoice = s.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: enter an integer number");
                        s.nextLine();
                        continue;
                    }
                    switch (userChoice) {
                        case 0 -> {
                            System.out.println("Thanks, See You...");
                        }
                        case 1 -> getLecturerDetails(college);
                        case 2 -> getCommitteeDetails(college);
                        case 3 -> getLecturerCommitteeDetails(college);
                        case 4 -> getHeadDetails(college);
                        case 5 -> getLecturerCommitteeDetails(college, "remove");
                        case 6 -> getDepartmentDetails(college);
                        case 7 -> getLecturerDepDetails(college);
                        case 8 -> showAverageSalaryAllLecturers(college);
                        case 9 -> getDepartmentDetails(college, "show");
                        case 10 -> showAllLecturers(college);
                        case 11 -> showAllCommittee(college);
                        case 12 -> comparisonDocProfByArticles(college);
                        case 13 -> comparisonDepsByCriteria(college);// 2 קריטריונים
                        case 14 -> duplicateCommitte(college);
                        case 15 -> removeLecturerFromCommitte(college);

                        default ->
                                System.out.println("invalid option, enter a number 0 - " + ((MENU.length) - 1) + " please");
                    }
                }catch(CollegeExceptions e){
                    System.out.println(e.getMessage());
                }
            } while (userChoice != 0);

    }

    private static void removeLecturerFromCommitte(College college) throws CollegeExceptions {
        s.nextLine();
        if (college.getNumOfCommittees() == 0){
            throw new CommitteeException(NO_COMMITTES_REMOVE.toString());
        }
        System.out.println("what committee?");
        String committeName = s.nextLine();
        Committee c = Util.getCommitteeFromName(committeName,college.getCommittees());
        if (c == null){
            throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
        }
        System.out.println("what lecturer?");
        Util.printArraysByName(c.getLecturers());
        String lectName = s.nextLine();
        c.removeLecturerByName(lectName);
    }

    private static void duplicateCommitte(College college)throws CollegeExceptions {
        if (college.getNumOfCommittees() == 0){
            throw new CommitteeException(NO_COMMITTES.toString());
        }
        s.nextLine();
        System.out.println("what committee?");
        Util.printArraysByName(college.getCommittees());
        String comiteeName = s.nextLine();
        college.DuplicateComittee(comiteeName);
    }

    private static void comparisonDepsByCriteria(College college)throws CollegeExceptions {
        System.out.println("Enter which criteria you want to compare (enter number): ");
        System.out.println("1 - Compare by number of lecturers");
        System.out.println("2 - Compare by number of articles");

        while (true){
            try {
                int choice = s.nextInt();
                switch (choice){
                    case 1 -> college.compareByNumOfLec();
                    case 2 -> college.compareByNumOfArt();

                }
            }catch (InputMismatchException e){
                s.nextLine();
                throw new CollegeExceptions("wrong input. Try again..");
//                System.out.println("Error: wrong input. Try again..");
            }
        }
    }

    private static void comparisonDocProfByArticles(College college)throws CollegeExceptions {
        s.nextLine();
        System.out.println("enter name of first doc/prof: ");
        Util.printDocProf(college.getLecturers(),college.getNumOfLecturers());
        String firstName = s.nextLine();
        Lecturer one = Util.getLecturerFromName(firstName,college.getLecturers());
        if (one == null){
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        if (!(one instanceof Doctor)){
            throw new LecturerException(NOT_DOC.toString());
        }
        System.out.println("enter name of second doc/prof: ");
        Util.printDocProf(college.getLecturers(),college.getNumOfLecturers());
        String secName = s.nextLine();
        Lecturer two = Util.getLecturerFromName(firstName,college.getLecturers());
        if (two == null){
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        if (!(two instanceof Doctor)){
            throw new LecturerException(NOT_DOC.toString());
        }
        System.out.println(((Doctor) one).compareTo((Doctor)two));

    }

    //output method
    private static void showAllCommittee(College college) {
        for (int i = 0; i < college.getNumOfCommittees(); i++) {
            System.out.println(college.getCommittees()[i]);
        }
    }
    //output method
    private static void showAllLecturers(College college) {
        for (int i = 0; i < college.getNumOfLecturers(); i++) {
            System.out.println(college.getLecturers()[i]);
        }
    }
    // i/o method to get details for action
    private static void getDepartmentDetails(College college, String show) { //dep salaries
        s.nextLine();
        System.out.println("enter department name: ");
        Util.printArraysByName(college.getDepartments());
        String name = s.nextLine();
        try {
            college.showAverageSalaryByDep(name);
        }catch(CollegeExceptions e){
            System.out.println(e.getMessage());
        }
    }
    // output method
    private static void showAverageSalaryAllLecturers(College college) {
       double average = Util.getAverage(college.getLecturers(), college.getNumOfLecturers());
       System.out.println(average);
    }
    // i/o method to get details for action
    private static void getLecturerDepDetails(College college) {
        s.nextLine();
        System.out.println("What department? ");
        Util.printArraysByName(college.getDepartments());
        String depName = s.nextLine();
        System.out.println("What lecturer? (enter name) ");
        Util.printArraysByName(college.getLecturers());
        String lecturerName = s.nextLine();
        try {
            college.addLecturerToDep(depName, lecturerName);
        }catch (CollegeExceptions e){
            System.out.println(e.getMessage());
        }
    }
    // i/o method to get details for action
    private static void getDepartmentDetails(College college) {
        s.nextLine();
        System.out.println("enter department name- ");
        String name = s.nextLine();
        System.out.println("enter number of students ");
        int num = s.nextInt();
        try {
            college.addStudyDepartment(name, num);
        }catch(CollegeExceptions e){
            System.out.println(e.getMessage());
        }
    }
    // i/o method to get details for action
    private static void getHeadDetails(College college) {
        s.nextLine();
        System.out.println("What Committee: ");
        Util.printArraysByName(college.getCommittees());
        String committeeName = s.nextLine();
        System.out.println("enter new head of committee: ");
        Util.printArraysByName(college.getLecturers());
        String newHead = s.nextLine();
        try {
            college.updateHeadOfCommittee(committeeName, newHead);
        }catch (CollegeExceptions e){
            System.out.println(e.getMessage());
        }

    }
    // i/o method to get details for action
    private static void getLecturerCommitteeDetails(College college, String remove) {
        if (college.getCommittees().length == 0){
            System.out.println(new CommitteeException(NO_COMMITTES_REMOVE.toString()).getMessage());
            return;
        }
        if (college.getLecturers().length==0){
            System.out.println(new LecturerException(NO_LECTURERS_REMOVE.toString()).getMessage());
        }
        s.nextLine();
        String lecturerName, committeeName;
        System.out.println("enter lecturer name: ");
        Util.printArraysByName(college.getLecturers());
        lecturerName = s.nextLine();
        System.out.println("enter committee name: ");
        Util.printArraysByName(college.getCommittees());
        committeeName = s.nextLine();
        try {
            college.removeLecturerFromCommittee(lecturerName, committeeName);
        }catch(CollegeExceptions e){
            System.out.println(e.getMessage());
        }
    }
    // i/o method to get details for action
    private static void getLecturerCommitteeDetails(College college) {
        if (college.getLecturers().length==0){
            System.out.println(new LecturerException(NO_LECTURERS_ADD.toString()).getMessage());
            return;
        }
        if (college.getCommittees().length==0){
            System.out.println(new CommitteeException(NO_COMMITTES_ADD.toString()).getMessage());
            return;
        }
        s.nextLine();
        String lecturerName, committeeName;
        System.out.println("enter lecturer name: ");
        Util.printArraysByName(college.getLecturers());
        lecturerName = s.nextLine();
        System.out.println("enter committee name: ");
        Util.printArraysByName(college.getCommittees());
        committeeName = s.nextLine();
        try {
            college.addLecturerToCommittee(Util.getLecturerFromName(lecturerName, college.getLecturers()), Util.getCommitteeFromName(committeeName, college.getCommittees()));
        }catch (CollegeExceptions e){
            System.out.println(e.getMessage());
        }
    }
    // i/o method to get details for action
    private static void getCommitteeDetails(College college) throws LecturerException {
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
        if (!Util.isExist(headName,college.getLecturers(),college.getNumOfLecturers())){
            throw new LecturerException(LECTURER_DONT_EXIST.toString());
        }
        try {
            college.addCommittee(name, Util.getLecturerFromName(headName, college.getLecturers()));
        }catch (CollegeExceptions e){
            System.out.println(e.getMessage());
        }
    }
    // i/o method to get details for action
    private static void getLecturerDetails(College college) throws InputMismatchException {
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
        int salary = 0;
        boolean go = false;
        while (!go) {
            try {
                System.out.println("enter salary: ");
                salary = s.nextInt();
                if (salary != 0) {
                    go = true;
                }
            }catch (InputMismatchException e){
                s.nextLine();
                System.out.println("enter an integer");
            }
        }
        try {
            switch (degree) {
                case 1, 2 -> college.addLecturer(name, id, deg, degName, salary);
                case 3 -> {
                    String[] articles = getArticles();
                    college.addLecturer(name, id, deg, degName, salary, articles);
                }
                case 4 -> {
                    String[] articles = getArticles();
                    System.out.println("enter name of institution that awarded the professorship");
                    String inst = s.nextLine();
                    college.addLecturer(name, id, deg, degName, salary, articles, inst);
                }
            }
        }catch (CollegeExceptions e){
            System.out.println(e.getMessage());
        }
//        if (degree == 4){ // if professor - get the name of body
//            System.out.println("enter name of institution that awarded the professorship");
//            String inst = s.nextLine();
//            eStatus stat = college.addLecturer(name,id,deg,degName,salary,inst);
//        }else {
//            eStatus stat = college.addLecturer(name, id, deg, degName, salary);
//        }
//        switch (stat){
//            case SUCCESS -> System.out.println(eStatus.SUCCESS);
//            case LECTURER_EXISTS -> System.out.println(eStatus.LECTURER_EXISTS + ", Try again ");
//        }
    }

    private static String[] getArticles() {
        s.nextLine();
        System.out.println("enter articles written by the Doctor, seperated by spaces");
        return s.nextLine().split(" ");
    }

    public static void main(String[] args) {
        System.out.println("Enter collage name: ");
        String name = s.nextLine();
        College c = new College(name);
        run(c);
        s.close();
    }
}
//Pini The King !