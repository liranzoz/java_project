package liran_nevo;

public class LecturerExistException extends Exception{
    private static final String LECTURER_EXIST_MESSAGE = "lecturer exist";
    public LecturerExistException() {
        super(LECTURER_EXIST_MESSAGE);
    }
}
