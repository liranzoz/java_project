package liran_nevo;

import java.util.Arrays;

public class Doctor extends Lecturer{
    private String[] articles;
    private int numOfArticles;
    public Doctor(String name, String id, eDegreeType degree, String degName, int salary, String[] articles) {
        super(name, id, degree, degName, salary);
        this.articles = articles;
        this.numOfArticles = articles.length;
    }

    @Override
    public String toString() {
        return super.toString() +"\narticles:\n"+ articlesToString();
    }

    private String articlesToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < articles.length; i++) {
                sb.append(i+1).append(")").append(" ").append(articles[i]).append("\n");

        }
        return sb.toString();
    }
}