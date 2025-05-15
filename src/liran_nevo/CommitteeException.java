package liran_nevo;

public class CommitteeException extends CollegeExceptions {
    private static final String PRE_MESSAGE = "type: Commitee Exception- ";
    public CommitteeException(String message) {
        super(PRE_MESSAGE+message);
    }
}
