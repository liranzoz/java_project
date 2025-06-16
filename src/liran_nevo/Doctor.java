package liran_nevo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class Doctor extends Lecturer implements Comparable<Doctor> {
    private ArrayList<String> articles;

    public Doctor(String name, String id, eDegreeType degree, String degName, int salary, ArrayList<String> articles) {
        super(name, id, degree, degName, salary);
        this.articles = articles;
    }

    @Override
    public String toString() {
        return super.toString() +"\narticles:\n"+ articlesToString();
    }

    public String articlesToStringComparison(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < articles.size(); i++) {
            i++;
        }
        sb.append(i);
        return sb.toString();
    }


    public String articlesToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < articles.size(); i++) {
                sb.append(i+1).append(")").append(" ").append(articles.get(i)).append("\n");

        }
        return sb.toString();
    }

    @Override
    public int compareTo(Doctor doctor) {
        return Integer.compare(this.articles.size(),doctor.articles.size());
    }

    public ArrayList<String> getArticles() {
        return articles;
    }
}