package liran_nevo;

public enum eStatus {
    SUCCESS("Added"),
    LECTURER_EXIST("Lecturer Exists"),
    LECTURER_DONT_EXIST("Lecturer Dont Exist"),
    DEPARTMENT_EXIST("Department Exist"),
    DEPARTMENT_DONT_EXIST("Department Dont Exist"),
    COMMITTEE_EXIST("Committee Exist"),
    COMMITTEE_DONT_EXIST("Committee Dont Exist"),
    HEAD_NOT_VALID("head of committee needs to be Doctor/Professor"),
    NO_COMMITTES_ADD("no committees to add to"),
    NO_COMMITTES_REMOVE("no committees to remove from"),
    NO_LECTURERS_ADD("no lecturers to add"),
    NO_LECTURERS_REMOVE("no lecturers to remove"),
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
