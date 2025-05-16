package liran_nevo;

public class HeadNotValidException extends CollegeExceptions{
    private static final String HEAD_NOT_VALID_MESSAGE = "only Doctor/Professor allowed to be head of committee";
    public HeadNotValidException(){
        super(HEAD_NOT_VALID_MESSAGE);
    }
}
