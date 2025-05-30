package liran_nevo;

import java.util.Comparator;

public class CompareDepByNumOfArt implements Comparator<Committee> {

    @Override
    public int compare(Committee c1, Committee c2) {
        return Integer.compare(c1.getNumOfArticles(), c2.getNumOfArticles());
    }
}
