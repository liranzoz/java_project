package liran_nevo;

import java.util.Arrays;

import static liran_nevo.eStatus.*;

public class Lecturer {

    public void removeCommitte(Committee c) throws CommitteeException {
        if(!Util.isExist(c.getName(),committees,numOfCommittees)){
            throw new CommitteeException(COMMITTEE_DONT_EXIST.toString());
        }
        for (int i = 0; i < numOfCommittees; i++) {
            if (committees[i].equals(c)){
                for (int j = i; j < numOfCommittees -1; j++) {
                    committees[i] = committees[i+1];
                }
            }
        }
        committees[--numOfCommittees]=null;
    }

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

    public void addCommittee(Committee committee) throws CollegeExceptions {
        if (this.committees.length == this.numOfCommittees) {
            this.committees = Arrays.copyOf(committees, committees.length == 0 ? 1 : committees.length * 2);
        }
        if (!Util.isExist(committee.getName(), committees, numOfCommittees)) {
            this.committees[numOfCommittees++] = committee;
        } else {
            throw new CommitteeException(COMMITTEE_EXIST.toString());
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
        this.id = Util.rJust(id, 9, '0');
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
        if(this.department != null){
            department.removeLecturerFromDep(this);
        }
        this.department = department;

    }

    @Override
    public String toString() {
        System.out.println();
        StringBuilder sb = new StringBuilder("lecturer: "+name+" | id: "+id+" | degree: "+degree+" | degree name: "+degreeName+" | salary:"+salary+"\ncommittees: ") ;
        if (numOfCommittees == 0){
            sb.append("no committees");
        }else {
        for (Committee c : committees) {
            if (c != null) {
                sb.append(" ").append(c.getName());
                if (c.getHead().equals(this)){
                    sb.append("(head) ");
                }
            }
        }
        }
        if(department!=null) {
            sb.append("\ndepartment: ").append(department.getName());
        }else {
            sb.append("\ndepartments: no departments");
        }
        return sb.toString();
    }

}
