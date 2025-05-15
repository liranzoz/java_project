package liran_nevo;

public class LecturerException extends CollegeExceptions{
    private static final String PRE_MESSAGE = "type- Lecturer Error- ";
    public LecturerException(String message) {
        super(PRE_MESSAGE+message);
    }
}
