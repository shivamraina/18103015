import java.util.Scanner;

// 0 -> if Strings are same
// positive -> if String 1 > String 2
// negative -> if String 1 < String 2

public class Question1 {

    public static int compare(String a, String b) {
        for(int i=0;i<a.length() && i<b.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                return a.charAt(i) - b.charAt(i);
            }
        }
        return a.length()-b.length();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter The Two Strings");
        String a = sc.nextLine();
        String b = sc.nextLine();
        int ans = compare(a, b);
        if (ans == 0) {
            System.out.println("Both Strings are Same");
        } else if(ans > 0) {
            System.out.println(a + " is greater than " + b);
        } else {
            System.out.println(a + " is smaller than " + b);
        }
    }
}