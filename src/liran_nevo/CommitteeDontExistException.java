package liran_nevo;

public class CommitteeDontExistException extends RuntimeException {
    private static final String COMMITTEE_DONT_EXIST_MESSAGE = "committee done exist";
    public CommitteeDontExistException() {
        super(COMMITTEE_DONT_EXIST_MESSAGE);
    }
}
