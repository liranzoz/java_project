package liran_nevo;

import java.util.Arrays;
import java.util.Comparator;

public class Doctor extends Lecturer implements Comparable<Doctor> {
    private String[] articles;
    private int numOfArticles;
    public Doctor(String name, String id, eDegreeType degree, String degName, int salary, String[] articles) {
        super(name, id, degree, degName, salary);
        this.articles = articles;
        this.numOfArticles = articles.length;
    }

    public int getNumOfArticles() {
        return numOfArticles;
    }

    @Override
    public String toString() {
        return super.toString() +"\narticles:\n"+ articlesToString();
    }

    public String articlesToStringComparison(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < articles.length; i++) {
            i++;
        }
        sb.append(i);
        return sb.toString();
    }


    public String articlesToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < articles.length; i++) {
                sb.append(i+1).append(")").append(" ").append(articles[i]).append("\n");

        }
        return sb.toString();
    }

    @Override
    public int compareTo(Doctor doctor) {
        return Integer.compare(this.numOfArticles,doctor.numOfArticles);
    }
}