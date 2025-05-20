package liran_nevo;
import java.util.Arrays;


public class Util {
    public static int getNumOfDocProf(Lecturer[] lecturers) {
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

    public static boolean isExist(String name, Lecturer[] arr, int numOf) {
        for (int i = 0; i < numOf; i++) {
            if (arr[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExist(String name, Department[] arr, int numOf) {
        for (int i = 0; i < numOf; i++) {
            if (arr[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExist(String name, Committee[] arr, int numOf) {
        for (int i = 0; i < numOf; i++) {
            if (arr[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDocProf(Lecturer lecturer) {
        if (lecturer == null){
            return false;
        }
        return lecturer.getDegree().equals(Lecturer.eDegreeType.DOCTOR) || lecturer.getDegree().equals(Lecturer.eDegreeType.PROFESSOR);
    }

    public static Committee getCommitteeFromName(String name, Committee[] committees) {
        for (Committee committee : committees) {
            if (committee.getName().equals(name)) {
                return committee;
            }
        }
        return null;
    }


    public static Lecturer getLecturerFromName(String name, Lecturer[] lecturers) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getName().equals(name)) {
                return lecturer;
            }
        }
        return null;
    }

    public static Department getDepartmentFromName(String name, Department[] departments) {
        for (Department department : departments) {
            if (department.getName().equals(name)) {
                return department;
            }
        }
        return null;
    }

    public static Object[] copyArr(Object[] arr, int size) {
        return Arrays.copyOf(arr, size);
    }

    public static void printArraysByName(Lecturer[] arr) {
        System.out.println("existing lecturers (enter name):");
        int i = 1;
        for (Lecturer name : arr) {
            if (name != null) {
                System.out.println(i + ") " + name.getName());
                i++;
            }
        }
    }

    public static void printArraysByName(Department[] arr) {
        System.out.println("existing departments (enter name):");
        int i = 1;
        for (Department name : arr) {
            if (name != null) {
                System.out.println(i + ") " + name.getName());
                i++;
            }
        }
    }

    public static void printArraysByName(Committee[] arr) {
        System.out.println("existing committees (enter name):");
        int i = 1;
        for (Committee name : arr) {
            if (name != null) {
                System.out.println(i + ") " + name.getName());
                i++;
            }
        }
    }




    public static double getAverage(Lecturer[] lecturers, int numOfLectures) {
        double averageSum = 0;
        if (lecturers.length == 0) {
            return 0;
        }
        for (Lecturer lecturer : lecturers) {
            if (lecturer != null) {
                averageSum += lecturer.getSalary();
            }
        }
        return averageSum / numOfLectures;
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

// FOR FUTURE USE
//    public static String str(String[] arr) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
//        for (int i = 0; i < arr.length; i++) {
//            sb.append(arr[i]);
//            if (i < arr.length - 1) {
//                sb.append(", ");
//            }
//        }
//        sb.append("]");
//        return sb.toString();
//    }
}


