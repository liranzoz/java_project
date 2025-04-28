package liran_nevo;

public enum eStatus {
    SUCCESS("Added"),
    LECTURER_EXISTS("Lecturer Exists"),
    LECTURER_DONT_EXIST("Lecturer Dont Exist"),
   DEPARTMENT_EXIST("Department Exist"),
    DEPARTMENT_DONT_EXIST("Department Dont Exist"),
    COMMITTEE_EXIST("Committee Exist"),
    COMMITTEE_DONT_EXIST("Committee Dont Exist"),
    HEAD_NOT_VALID("Head Not Valid"),
    GENERAL_ERROR("Something Went Wrong...");

    private final String description;

    eStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
