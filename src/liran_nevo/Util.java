package liran_nevo;
import java.util.ArrayList;
import java.util.Arrays;


public class Util {
    public static int getNumOfDocProf(ArrayList<Lecturer> lecturers) {
        int counter = 0;
        for (Lecturer l : lecturers) {
            if (l != null && l instanceof Doctor){
                counter++;
            }
        }
        return counter;
    }


    public static String rJust(String st, int minWidth, char fillChar) {
        if (st.length() >= minWidth) {
            return st;
        }
        return (fillChar + "").repeat(minWidth - st.length()) + st;
    }

    public static boolean isExist(String name, ArrayList<? extends Collegable> arr) {
        if (arr.isEmpty()){
            return false;
        }
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

//    public static boolean isExist(String name, ArrayList<Department> arr) {
//        for (int i = 0; i < arr.size(); i++) {
//            if (arr.get(i).getName().equals(name)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static boolean isExist(String name, ArrayList<Committee> arr, int numOf) {
//        for (int i = 0; i < numOf; i++) {
//            if (arr.get(i).getName().equals(name)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static boolean isDocProf(Lecturer lecturer) {
        if (lecturer == null){
            return false;
        }
        return lecturer.getDegree().equals(Lecturer.eDegreeType.DOCTOR) || lecturer.getDegree().equals(Lecturer.eDegreeType.PROFESSOR);
    }

    public static Committee getCommitteeFromName(String name, ArrayList<Committee> arr) {
        for (Committee c : arr) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }


    public static Lecturer getLecturerFromName(String name, ArrayList<Lecturer> arr) {
        for (Lecturer lecturer :arr) {
            if (lecturer.getName().equals(name)) {
                return lecturer;
            }
        }
        return null;
    }

    public static Department getDepartmentFromName(String name, ArrayList<Department> arr) {
        for (Department department : arr) {
            if (department.getName().equals(name)) {
                return department;
            }
        }
        return null;
    }

//    public static Object[] copyArr(ArrayList<Object> arr, int size) {
//        return Arrays.copyOf(arr, size);
//    }

    public static void printArraysByName(ArrayList<? extends Collegable> arr) {
        System.out.println("existing (enter name):");
        int i = 1;
        for (Collegable name : arr) {
            if (name != null) {
                System.out.println(i + ") " + name.getName());
                i++;
            }
        }
    }
//
//    public static void printArraysByName(ArrayList<Department> arr) {
//        System.out.println("existing departments (enter name):");
//        int i = 1;
//        for (Department name : arr) {
//            if (name != null) {
//                System.out.println(i + ") " + name.getName());
//                i++;
//            }
//        }
//
//    }

    public static void printDocProf(ArrayList<Lecturer> arr){
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) instanceof Doctor){
                System.out.println(i + ") " + arr.get(i).getName());
            }
        }
    }

    public static double getAverage(ArrayList<Lecturer> arr) {
        double averageSum = 0;
        if (arr.size() == 0) {
            return 0;
        }
        for (Lecturer lecturer : arr) {
            if (lecturer != null) {
                averageSum += lecturer.getSalary();
            }
        }
        return averageSum / arr.size();
    }


    public static double getAverage(Department dep) {
        double averageSum = 0;
        if (dep.getNumOfLecturers() == 0) {
            return 0;
        }
        for (Lecturer lecturer : dep.getLecturers()) {
            if (lecturer != null) {
                averageSum += lecturer.getSalary();
            }
        }
        return averageSum / dep.getNumOfLecturers();
    }

}


