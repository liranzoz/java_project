package liran_nevo;

import java.util.Comparator;

public class CompareComByNumOfLect implements Comparator<Committee> {

    @Override
    public int compare(Committee c1, Committee c2) {
        return Integer.compare(c1.getNumOfLecturers(), c2.getNumOfArticles());
    }
}
