package liran_nevo;

public class LecturerDontExistException extends CollegeExceptions {
    private static final String LECTURER_DONT_EXIST_MESSAGE = "lecturer dont exist";
    public LecturerDontExistException() {
        super(LECTURER_DONT_EXIST_MESSAGE);
    }
}
