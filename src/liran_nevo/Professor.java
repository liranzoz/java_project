package liran_nevo;

import java.util.ArrayList;

public class Professor extends Doctor{
    private String inst;
    public Professor(String name, String id, eDegreeType degree, String degName, int salary, ArrayList<String> articles, String inst) {
        super(name, id, degree, degName, salary,articles);
        this.inst = inst;
    }

    @Override
    public String toString() {
        return super.toString() + "professorship institution: "+inst;
    }
}
