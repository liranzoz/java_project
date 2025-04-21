package liran_nevo;
import java.util.Arrays;

public class Util {
    public static String rjust(String st, int minWidth, char fillChar) {
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
        String deg = lecturer.getDegree();
        return deg.equals("doctor") || deg.equals("professor");
    }

    public static Committee getCommitteeFromName(String name,Committee[]committees){
        for (Committee committee : committees){
            if (committee.getName().equals(name)){
                return committee;
            }
        }
        return null;
    }


    public static Lecturer getLecturerFromName(String name, Lecturer[] lecturers){
        for (Lecturer lecturer : lecturers){
            if (lecturer.getName().equals(name)){
                return lecturer;
            }
        }
        return null;
    }

    public static Department getDepartmentFromName(String name, Department[] departments){
        for (Department department : departments){
            if (department.getName().equals(name)){
                return department;
            }
        }
        return null;
    }

    public static Object[] copyArr(Object[] arr, int size) {
        return Arrays.copyOf(arr,size);
    }

    public static void printArraysByName(Lecturer[] arr){
        for (Lecturer name : arr){
            System.out.println(name.getName());
        }
    }
    public static void printArraysByName(Department[] arr){
        for (Department name : arr){
            System.out.println(name.getName());
        }
    }
    public static void printArraysByName(Committee[] arr){
        for (Committee name : arr){
            System.out.println(name.getName());
        }
    }


    public static String str(String[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
