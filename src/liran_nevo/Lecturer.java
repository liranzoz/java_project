package liran_nevo;

public class Lecturer {
    private String name;
    private String degree;
    private String degreeName;
    private String id;
    private int salary;
    private Department department;

    public Lecturer(String name, String id, String degree, String degName, int salary) {
        setName(name);
        setId(id);
        setDegree(degree);
        setDegreeName(degName);
        setSalary(salary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        switch (degree){
            case "1st" -> this.degree = "1st";
            case "2nd" -> this.degree = "2nd";
            case "doctor" -> this.degree = "doctor";
            case "professor" -> this.degree = "professor";
            default -> this.degree = "1st"; //default for invalid is first degree
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
        this.department = department;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "name='" + name + '\'' +
                ", degree='" + degree + '\'' +
                ", degree Name='" + degreeName + '\'' +
                ", id='" + id + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}
