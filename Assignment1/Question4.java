import java.lang.reflect.Array;
import java.util.*;

public class Question4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("----Enter First String---");
        String s1 = sc.nextLine();
        System.out.println("----Enter Second String---");
        String s2 = sc.nextLine();

        char tempArray[] = s1.toCharArray();
        Arrays.sort(tempArray);
        s1 = new String(tempArray);

        tempArray = s2.toCharArray();
        Arrays.sort(tempArray);
        s2 = new String(tempArray);

        if(s1.equals(s2)) {
            System.out.println("They Are Anagarams");
        } else {
            System.out.println("They Are Not Anagrams");
        }

    }

}
