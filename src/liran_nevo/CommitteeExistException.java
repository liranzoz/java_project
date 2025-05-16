package liran_nevo;

public class CommitteeExistException extends CollegeExceptions{
    private static final String COMMITTEE_EXIST_MESSAGE = "committee already exist";
    public CommitteeExistException(){
        super(COMMITTEE_EXIST_MESSAGE);
    }
}
