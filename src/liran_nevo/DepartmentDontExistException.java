package liran_nevo;

public class DepartmentDontExistException extends CollegeExceptions{
    private static final String DEPARTMENT_DONT_EXIST_MESSAGE ="department dont exist";
    public DepartmentDontExistException(){
        super(DEPARTMENT_DONT_EXIST_MESSAGE);
    }
}
