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

    public Lecturer(String name, String id, eDegreeType degree, String degName, int salary) {
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

    public void setDegree(eDegreeType deg) {
        this.degree = deg;
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
        StringBuilder sb = new StringBuilder("lecturer: "+name+" | id: "+id+" | degree: "+degree+" | degree name: "+degreeName+"\ncommittees:\n") ;
        for (Committee c : committees){
            if(c!=null){
                sb.append(c.getName());
            }
        }
        if(department!=null) {
            sb.append("department: " + department.getName());
        }
        return sb.toString();
    }

}
