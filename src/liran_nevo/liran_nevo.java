//    Liran Zozulya & Nevo Glanz
package liran_nevo;
import java.util.Scanner;

public class liran_nevo {
    //update
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
            "show all committees data",
            "compare Doctor/ professor by article number",
            "compare department",// 2 קריטריונים
            "duplicate committee",
            "remove lecturer from committee"
    };

    public static void run(College college) {
        int userChoice;
        do {
            System.out.println("---------menu---------");
            System.out.println("enter the number of the option:");
            for (int i = 0; i < MENU.length; i++) {
                System.out.println(i + ") " + MENU[i]);
            }
            System.out.print("your choice: ");
            userChoice = s.nextInt();
            switch (userChoice) {
                case 0 -> {
                    System.out.println("Thanks, See You...");
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
                case 12 -> System.out.println("12");
                case 13 -> System.out.println("13");
                case 14 -> System.out.println("14");
                case 15 -> System.out.println("15");

                default -> System.out.println("invalid option");
            }
        } while (userChoice != 0);
    }

    private static void showAllCommittee(College college) {
        for (int i = 0; i < college.getNumOfCommittees(); i++) {
            System.out.println(college.getCommittees()[i]);
        }
    }

    private static void showAllLecturers(College college) {
        for (int i = 0; i < college.getNumOfLecturers(); i++) {
            System.out.println(college.getLecturers()[i]);
        }
    }

    private static void getDepartmentDetails(College college, String show) { //dep salaries
        s.nextLine();
        System.out.println("enter department name: ");
        Util.printArraysByName(college.getDepartments());
        String name = s.nextLine();
        try {
            college.showAverageSalaryByDep(name);
        }catch(DepartmentDontExistException e){
            System.out.println(e.getMessage());
        }
    }

    private static void showAverageSalaryAllLecturers(College college) {
       double average = Util.getAverage(college.getLecturers(), college.getNumOfLecturers());
        System.out.println(average);
    }

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
        }catch (LecturerExistException | LecturerDontExistException | DepartmentDontExistException e){
            System.out.println(e.getMessage());
        }
    }

    private static void getDepartmentDetails(College college) {
        s.nextLine();
        System.out.println("enter department name- ");
        String name = s.nextLine();
        System.out.println("enter number of students ");
        int num = s.nextInt();
        try {
            college.addStudyDepartment(name, num);
        }catch(DepartmentExistException e){
            System.out.println(e.getMessage());
        }
    }

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
        }catch (HeadNotValidException | LecturerDontExistException |
                CommitteeDontExistException e){
            System.out.println(e.getMessage());
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
        try {
            college.removeLecturerFromCommittee(lecturerName, committeeName);
        }catch(CommitteeDontExistException | LecturerDontExistException e){
            System.out.println(e.getMessage());
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
        try {
            college.addLecturerToCommittee(Util.getLecturerFromName(lecturerName, college.getLecturers()), Util.getCommitteeFromName(committeeName, college.getCommittees()));
        }catch (LecturerExistException | CommitteeDontExistException e){
            System.out.println(e.getMessage());
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
        try {
            college.addCommittee(name, Util.getLecturerFromName(headName, college.getLecturers()));
        }catch (CommitteExistException | HeadNotValidException e){
            System.out.println(e.getMessage());
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
        }catch (LecturerExistException e){
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