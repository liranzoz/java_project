package liran_nevo;

public class DepartmentExistException extends RuntimeException {
    private static final String DEPARTMENT_EXIST_MESSAGE = "department exist";
    public DepartmentExistException() {
        super(DEPARTMENT_EXIST_MESSAGE);
    }
}
