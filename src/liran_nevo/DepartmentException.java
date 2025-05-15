package liran_nevo;

public class DepartmentException extends CollegeExceptions {
    private static final String PRE_MESSAGE = "type: Department Error";
    public DepartmentException(String message) {
        super(PRE_MESSAGE+message);
    }
}
