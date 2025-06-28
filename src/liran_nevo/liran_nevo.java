//    Liran Zozulya & Nevo Glanz
package liran_nevo;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static liran_nevo.Lecturer.eDegreeType.*;
import static liran_nevo.eStatus.*;

public class liran_nevo {
    static Scanner s = new Scanner(System.in);
    private static final String[] MENU = {
            "save and exit",//0
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
            "compare committees",//13
            "duplicate committee"//14 - 2קריטריונים
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
                        case 5 -> removeLecturerFromCommitte(college);
                        case 6 -> getDepartmentDetails(college);
                        case 7 -> getLecturerDepDetails(college);
                        case 8 -> showAverageSalaryAllLecturers(college);
                        case 9 -> getDepartmentDetails(college, "show");
                        case 10 -> showAllLecturers(college);
                        case 11 -> showAllCommittee(college);
                        case 12 -> comparisonDocProfByArticles(college);
                        case 13 -> comparisonComsByCriteria(college);// 2 קריטריונים
                        case 14 -> duplicateCommitte(college);

                        default ->
                                System.out.println("invalid option, enter a number 0 - " + ((MENU.length) - 1) + " please");
                    }
                }catch(CollegeExceptions | CloneNotSupportedException e){
                    System.out.println(e.getMessage());
                }
            } while (userChoice != 0);

    }

    private static void removeLecturerFromCommitte(College college) throws CollegeExceptions {
        s.nextLine();
        System.out.println("what committee?");
        String committeName = s.nextLine();
        System.out.println("what lecturer?");
        String lectName = s.nextLine();
        college.removeLecturerFromCommittee(lectName,committeName);
//        s.nextLine();
//        if (college.getNumOfCommittees() == 0){
//            throw new CommitteeException(NO_COMMITTES_REMOVE.toString());
//        }
//        System.out.println("what committee?");
//        String committeName = s.nextLine();
//        Committee c = Util.getCommitteeFromName(committeName,college.getCommittees());
//        if (c == null){
//            throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
//        }
//        System.out.println("what lecturer?");
//        Util.printArraysByName(c.getLecturers());
//        String lectName = s.nextLine();
//        c.removeLecturerByName(lectName);
    }

    private static void duplicateCommitte(College college) throws CollegeExceptions, CloneNotSupportedException {
        s.nextLine();
        System.out.println("what committee?");
        Util.printArraysByName(college.getCommittees());
        String comiteeName = s.nextLine();
        college.DuplicateComittee(comiteeName);

    }

    private static void comparisonComsByCriteria(College college) throws CollegeExceptions {
        s.nextLine();
        System.out.println("Enter name of the first Committee");
        String c1 = s.nextLine();
        System.out.println("Enter name of the second Committee");
        String c2 = s.nextLine();

        System.out.println("Enter which criteria you want to compare (enter number): ");
        System.out.println("1 - Compare by number of lecturers");
        System.out.println("2 - Compare by number of articles");
        int choice = s.nextInt();
        int res = college.CompareCom(c1,c2,choice);
        switch (res){
            case -1 -> System.out.println(c2 + " has more " +( choice == 1? "lecturers":"articles"));
            case 0 -> System.out.println(c2 + " and "+c1 +" has equal number of "+( choice == 1? "lecturers":"articles"));
            case 1 -> System.out.println(c1 + " has more"+ ( choice == 1? "lecturers":"articles"));
        }
//        while (true){
//            try {
//                int choice = s.nextInt();
//                switch (choice){
//                    case 1 -> {
//                        int res = college.CompareCom(c1,c2);
//                        switch (res){
//                            case -1 -> System.out.println(c2 + " has more lecturers");
//                            case 0 -> System.out.println(c2 + " and "+c1 +" has equal number of lecturers");
//                            case 1 -> System.out.println(c1 + " has more lecturers");
//                        }
//                        return;
//                    }
//                    case 2 -> {
//                        int res = college.CompareComByNumOfArt(c1,c2);
//                        switch (res){
//                            case -1 -> System.out.println(c2 + " has more articles");
//                            case 0 -> System.out.println(c2 + " and "+c1 +" has equal number of articles");
//                            case 1 -> System.out.println(c1 + " has more articles");
//                        }
//                        return;
//                    }
//
//                }
//            }catch (InputMismatchException e){
//                s.nextLine();
//                throw new CollegeExceptions("wrong input. Try again..");
//            }
//        }
    }

    private static void comparisonDocProfByArticles(College college)throws CollegeExceptions {
        s.nextLine();
        Util.printDocProf(college.getLecturers());
        System.out.println("enter name of first doc/prof: ");
        String firstName = s.nextLine();
        System.out.println("enter name of second doc/prof: ");
        String secName = s.nextLine();
        int res = college.compareDocProf(firstName,secName);
//        if (one == null){
//            throw new LecturerException(LECTURER_DONT_EXIST.toString());
//        }
//        if (!(one instanceof Doctor)){
//            throw new LecturerException(NOT_DOC.toString());
//        }
//
//        Lecturer two = Util.getLecturerFromName(secName,college.getLecturers());
//        if (two == null){
//            throw new LecturerException(LECTURER_DONT_EXIST.toString());
//        }
//        if (!(two instanceof Doctor)){
//            throw new LecturerException(NOT_DOC.toString());
//        }
        switch (res){
            case -1 -> System.out.println(secName + " has more articles");
            case 0 -> System.out.println(firstName + " and " + secName + " has equal number of articles");
            case 1 -> System.out.println(firstName + " has more articles");
        }
    }

    //output method
    private static void showAllCommittee(College college) {
        for (int i = 0; i < college.getNumOfCommittees(); i++) {
            System.out.println(college.getCommittees().get(i));
        }
    }
    //output method
    private static void showAllLecturers(College college) {
        for (int i = 0; i < college.getNumOfLecturers(); i++) {
            System.out.println(college.getLecturers().get(i));
        }
    }
    // i/o method to get details for action
    private static void getDepartmentDetails(College college, String show) throws CollegeExceptions { //dep salaries
        s.nextLine();
        System.out.println("enter department name: ");
        Util.printArraysByName(college.getDepartments());
        String name = s.nextLine();
        college.showAverageSalaryByDep(name);
    }
    // output method
    private static void showAverageSalaryAllLecturers(College college) {
       double average = Util.getAverage(college.getLecturers());
       System.out.println(average);
    }
    // i/o method to get details for action
    private static void getLecturerDepDetails(College college) throws CollegeExceptions {
        s.nextLine();
        System.out.println("What department? ");
        Util.printArraysByName(college.getDepartments());
        String depName = s.nextLine();
        System.out.println("What lecturer? (enter name) ");
        Util.printArraysByName(college.getLecturers());
        String lecturerName = s.nextLine();
        college.addLecturerToDep(depName, lecturerName);
    }
    // i/o method to get details for action
    private static void getDepartmentDetails(College college) throws CollegeExceptions {
        s.nextLine();
        System.out.println("enter department name- ");
        String name = s.nextLine();
        System.out.println("enter number of students ");
        int num = s.nextInt();
        college.addStudyDepartment(name, num);
    }
    // i/o method to get details for action
    private static void getHeadDetails(College college) throws CollegeExceptions {
        s.nextLine();
        System.out.println("What Committee: ");
        Util.printArraysByName(college.getCommittees());
        String committeeName = s.nextLine();
        System.out.println("enter new head of committee: ");
        Util.printArraysByName(college.getLecturers());
        String newHead = s.nextLine();
        college.updateHeadOfCommittee(committeeName, newHead);
    }
    // i/o method to get details for action
    private static void getLecturerCommitteeDetails(College college) throws CollegeExceptions {
        s.nextLine();
        String lecturerName, committeeName;
        System.out.println("enter lecturer name: ");
        Util.printArraysByName(college.getLecturers());
        lecturerName = s.nextLine();
        System.out.println("enter committee name: ");
        Util.printArraysByName(college.getCommittees());
        committeeName = s.nextLine();
        college.addLecturerToCommittee(Util.getLecturerFromName(lecturerName, college.getLecturers()), Util.getCommitteeFromName(committeeName, college.getCommittees()));
    }
    // i/o method to get details for action
    private static void getCommitteeDetails(College college) throws CollegeExceptions {
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
        System.out.println("enter degree for committee: \n1- BSc\n2- MSc\n3- doctor\n4- professor");
        int degType = s.nextInt();
        Lecturer.eDegreeType deg = BSc;
        switch (degType){
            case 1 -> deg = BSc;
            case 2 -> deg = MSc;
            case 3 -> deg = DOCTOR;
            case 4 -> deg = PROFESSOR;
        }
        college.addCommittee(name, headName,deg);
    }
    // i/o method to get details for action
    private static void getLecturerDetails(College college) throws InputMismatchException, CollegeExceptions {
        s.nextLine();
        System.out.println("enter name: ");
        String name = s.nextLine();
        System.out.println("enter id");
        String id = s.nextLine();
        System.out.println("enter degree: \n1- BSc\n2- MSc\n3- doctor\n4- professor");
        int degree = s.nextInt();
        s.nextLine();
        System.out.println("enter degree name: ");
        String degName = s.nextLine();
        System.out.println("enter salary: ");
        int salary = s.nextInt();
        switch (degree){
            case 1 -> college.addLecturer(name, id, BSc, degName, salary);
            case 2 -> college.addLecturer(name, id, MSc, degName, salary);
            case 3 -> {
                ArrayList<String> articles = getArticles();
                college.addLecturer(name, id, DOCTOR, degName, salary, articles);
            }
            case 4->{
                ArrayList<String> articles = getArticles();
                System.out.println("enter name of institution that awarded the professorship");
                String inst = s.nextLine();
                college.addLecturer(name, id, PROFESSOR, degName, salary, articles, inst);
            }
        }
    }

    private static ArrayList<String> getArticles() {
        s.nextLine();
        System.out.println("enter articles written by the Doctor, '0' to end'");
        ArrayList<String> articles = new ArrayList<>();
        String input = s.nextLine();
        while (!input.equals("0")){
            articles.add(input);
            input = s.nextLine();
        }
        return articles;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        College c;
        // file path
        String packagePath = liran_nevo.class.getPackage().getName()
                        .replace('.', File.separatorChar);
        String filePath = System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + packagePath
                + File.separator;

        //try to read file

        try{
            ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(filePath+"college.dat"));
            c = (College) inFile.readObject();
            inFile.close();
        }catch (FileNotFoundException e){
            System.out.println("Enter collage name: ");
            String name = s.nextLine();
            c = new College(name);
        }
        run(c);
        ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(filePath+ "college.dat"));
        outFile.writeObject(c);
        outFile.close();
        s.close();
    }
}
//Pini The King !