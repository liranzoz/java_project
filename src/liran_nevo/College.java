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
    public College(String collegeName) {
        setCollegeName(collegeName);
    }

    public void run() {
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
                case 1 -> addLecturer();
                case 2 -> addCommittee();
                case 3 -> addLecturerToCommittee();
                case 4 -> updateHeadOfCommittee();
                case 5 -> removeLecturerFromCommittee();
                case 6 -> addStudyDepartment();
                case 7 -> addLecturerToDep();
                case 8 -> showAverageSalaryAllLecturers();
                case 9 -> showAverageSalaryByDep();
                case 10 -> showAllLecturers();
                case 11 -> showAllCommittee();
            }
        } while (userChoice != 0);
    }

    private void addLecturerToDep() {
        if(departments.length==0){
            System.out.println("no departments yet, cannot add lecturer");
            return;
        }
        s.nextLine();
        String choice, lecturerChoice;
            do {
                System.out.println("What department? ");
                Util.printArraysByName(departments);
                choice = s.nextLine();
                System.out.println("What lecturer? ");
                System.out.println("enter the name");
                Util.printArraysByName(lecturers);
                lecturerChoice = s.nextLine();

            } while(!Util.isExist(choice,departments,numOfDepartments));
            Util.getLecturerFromName(lecturerChoice,lecturers).setDepartment(Util.getDepartmentFromName(choice,departments));
            Util.getDepartmentFromName(choice,departments).addLecturerToDep(lecturers[numOfLecturers-1]);

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


    private void showAverageSalaryByDep() {
        s.nextLine();
        String depName;
        int averageSum = 0;
        do {
            System.out.println("enter department name: ");
            depName = s.nextLine();
        } while (!Util.isExist(depName, departments, numOfDepartments));
        for (Lecturer lecturer : lecturers) {
            if(lecturer.getDepartment()!=null) {
                if (lecturer.getDepartment().getName().equals(depName)) {
                    averageSum += lecturer.getSalary();
                }
            }
        }
        System.out.println(averageSum / numOfLecturers);
    }

    private void showAverageSalaryAllLecturers() {
        int averageSum = 0;
        for (Lecturer lecturer : lecturers) {
            averageSum += lecturer.getSalary();
        }
        System.out.println(averageSum / numOfLecturers);
    }

    private void addStudyDepartment() {
        String userChoice;
        int numOfStudents;
        do {
            s.nextLine();
            System.out.println("enter department name- ");
            for (Department dep: departments){
                System.out.println(dep.getName());
            }
            userChoice = s.nextLine();

            if (Util.isExist(userChoice, departments, numOfDepartments)) {
                System.out.println("department exists, try again.. ");
            } else {
                if (this.departments.length == numOfDepartments) {
                    this.departments = (Department[]) Util.copyArr(departments, numOfDepartments == 0 ? 1 : departments.length * 2);
                }
                System.out.println("enter num of students- ");
                numOfStudents = s.nextInt();
                this.departments[numOfDepartments++] = new Department(userChoice, numOfStudents);
                return;
            }

        } while (true);
    }


    private void removeLecturerFromCommittee() {
        boolean flag = false;
        s.nextLine();
        System.out.println("what committee? ");

        String committee = s.nextLine();
        while (true) {
            if (!Util.isExist(committee, committees, numOfCommittees)) {
                System.out.println("committee doesnt exist, try again: ");
                committee = s.nextLine();
            } else {
                System.out.println("what lecturer to remove? ");
                String lecturerName = s.nextLine();
                for (Lecturer lecturer : Util.getCommitteeFromName(committee, committees).getLecturers()) {
                    if (lecturer.getName().equals(lecturerName)) {
                        flag = Util.getCommitteeFromName(committee, committees).removeLecturerByName(lecturerName);
                    }
                }
                System.out.println(flag ? "removed" : "not in this committee");
            }
        }
    }

    private void updateHeadOfCommittee() {
        s.nextLine();
        System.out.println("What Committee: ");
        String committeeName = s.nextLine();
        while (true) {
            if (!Util.isExist(committeeName, committees, numOfCommittees)) {
                System.out.println("committee doesnt exist, try again: ");
                committeeName = s.nextLine();
            } else {
                break;
            }
        }
        System.out.println("enter new head of committee: ");
        String newHead = s.nextLine();
        while (true) {
            if (!Util.isExist(newHead, lecturers, numOfLecturers)) {
                System.out.println("lecturer doesn't exist, enter again: ");
                newHead = s.nextLine();
            } else {
                if (Util.isDocProf(Util.getLecturerFromName(newHead, lecturers))) {
                    Util.getCommitteeFromName(committeeName, committees).setHead(Util.getLecturerFromName(newHead, lecturers));
                }else {
                    System.out.println("not valid head, head didnt changed..");
                }
                return;
            }
        }
    }

    private void addLecturerToCommittee() {
        if(committees.length==0){
            System.out.println("no committees yet, cannot add lecturer");
            return;
        }
        s.nextLine();
        String lecturerName, committeeName;
        System.out.println("enter lecturer name: ");
        Util.printArraysByName(lecturers);
        lecturerName = s.nextLine();
        while (true) {
            if (!(Util.isExist(lecturerName, lecturers, numOfLecturers))) {
                System.out.println("lecturer doesn't exist, enter again: ");
                Util.printArraysByName(lecturers);
                lecturerName = s.nextLine();
            } else {
                break;
            }
        }
        do {
            System.out.println("enter committee name: ");
            Util.printArraysByName(committees);
            committeeName = s.nextLine();
            if (!(Util.isExist(committeeName, committees, numOfCommittees))) {
                System.out.println("committee doesn't exist");
            } else {
                Util.getCommitteeFromName(committeeName,committees).addLecturerTocommittee(Util.getLecturerFromName(lecturerName,lecturers));
                Util.getLecturerFromName(lecturerName,lecturers).addCommittee(Util.getCommitteeFromName(committeeName,committees));
            }
        }while(!Util.isExist(committeeName,committees,numOfCommittees));
    }

    private void addCommittee() {
        s.nextLine();
        System.out.println("enter committee name: ");
        String name = s.nextLine();
        while (true) {
            if (Util.isExist(name, committees, numOfCommittees)) {
                System.out.println(name + " Already Exist..");
                System.out.println("Try Again");
                name = s.nextLine();
            } else {
                break;
            }
        }
        System.out.println("enter name of head of committee: ");
        for (Lecturer name1 : lecturers){
            if(name1!=null) {
                System.out.println(name1.getName() + " - " + name1.getDegree());
            }
        }
        String headName = s.nextLine();
        while (true) {
            if (!Util.isExist(headName, lecturers, numOfLecturers)) {
                System.out.println("lecturer doesnt exist, try again ");
                headName = s.nextLine();
            } else {
                if (Util.isDocProf(Util.getLecturerFromName(headName, lecturers))) {
                    if (numOfCommittees == committees.length) {
                        System.out.println("Adding committee number: " + numOfCommittees + 1);
                        committees = (Committee[]) Util.copyArr(committees, numOfCommittees == 0 ? 1 : numOfCommittees * 2);
                    }
                    committees[numOfCommittees++] = new Committee(name, Util.getLecturerFromName(headName, lecturers));
                    return;
                }else{
                    System.out.println("not valid head, committee not created");
                    return;
                }
            }
        }
//        System.out.println("head of department- \n 1- existing lecturer \n 2- create new");
//        int choice = s.nextInt();
//        switch (choice){
//            case 1 -> {
//                while (true){
//                    System.out.println("enter name- ");
//                    String name = s.nextLine();
//                    if (!(Util.isExist(name,lecturers,numOfLecturers)&&Util.isDocProf(Util.getLecturerFromName(name,lecturers)))){
//                        System.out.println("not valid head, try again..");
//                    }else{
//                        this.departments[numOfDepartments++] = new Department(userChoice,Util.getLecturerFromName(name,lecturers));
//                    }
//                }
//            }
//            case 2 ->{
//                addLecturer();
//                if (Util.isDocProf(this.lecturers[numOfLecturers-1])){
//                    this.committees[numOfCommittees++] = new Committee(userChoice,Util.getLecturerFromName(name,lecturers));
//                }
//            }
    }


    private void addLecturer() {
        s.nextLine();
        System.out.println("enter name: ");
        String name = s.nextLine();
        while (true) {
            if (Util.isExist(name, lecturers, numOfLecturers)) {
                System.out.println(name + " Already Exist..");
                System.out.println("Try Again");
                name = s.nextLine();
            } else {
                break;
            }
        }
        System.out.println("enter id");
        String id = s.nextLine();
        System.out.println("enter degree: \n1- BSc\n2- MSc\n3- doctor\n4- professor");
        int degree = s.nextInt();
        s.nextLine();
        System.out.println("enter degree name: ");
        String degName = s.nextLine();
        System.out.println("enter salary: ");
        int salary = s.nextInt();
        //בעתיד יהיה פה מחלקה
        if (numOfLecturers == lecturers.length) {
            lecturers = (Lecturer[]) Util.copyArr(lecturers, numOfLecturers == 0 ? 1 : numOfLecturers * 2);
        }
        lecturers[numOfLecturers++] = new Lecturer(name, id, degree, degName, salary);
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
}
