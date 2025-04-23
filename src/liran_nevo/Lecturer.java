package liran_nevo;

import java.util.Arrays;

public class Lecturer {
    public enum eDegreeType {BSc, MSc, DOCTOR, PROFESSOR}

    private String name;
    private eDegreeType degree;
    private String degreeName;
    private String id;
    private int salary;
    private Department department;
    private Committee[] committees;
    private int numOfCommittees;

    public Lecturer(String name, String id, int degree, String degName, int salary) {
        setName(name);
        setId(id);
        setDegree(degree);
        setDegreeName(degName);
        setSalary(salary);
        this.committees = new Committee[0];
    }

    public void addCommittee(Committee committee) {
        if (this.committees.length == this.numOfCommittees) {
            this.committees = Arrays.copyOf(committees, committees.length == 0 ? 1 : committees.length * 2);
        }
        if (!Util.isExist(committee.getName(), committees, numOfCommittees)) {
            this.committees[numOfCommittees++] = committee;
        } else {
            System.out.println("lecturer already in the committee, not added");
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public eDegreeType getDegree() {
        return degree;
    }

    public void setDegree(int deg) {
        // 1- B.S.c 2-MSc 3-doctor 4- professor
        switch (deg) {
            case 1 -> this.degree = eDegreeType.BSc;
            case 2 -> this.degree = eDegreeType.MSc;
            case 3 -> this.degree = eDegreeType.DOCTOR;
            case 4 -> this.degree = eDegreeType.PROFESSOR;
            default -> this.degree = eDegreeType.BSc; //default for invalid is first degree
        }
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Util.rjust(id, 9, '0');
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        Department old = this.department;
        if(old==null) {
            this.department = department;
        }else{
            old.removeLecturerFromDep(this);
            this.department=department;
        }

    }

    @Override
    public String toString() {
        return String.format(
                "Lecturer{name='%s', degree='%s', degreeName='%s', id='%s', salary=%d, department=%s}",
                name, degree, degreeName, id, salary, department
        );
    }

}
