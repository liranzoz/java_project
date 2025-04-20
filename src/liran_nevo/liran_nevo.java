//    Liran Zozulya & Nevo Glanz
package liran_nevo;
import java.util.Scanner;

public class liran_nevo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter collage name: ");
        String name = s.nextLine();
        College c = new College(name);
        c.run();
    }
}
//Pini The King !