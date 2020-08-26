import java.util.Scanner;

public class Question6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter N");
        int n = Integer.parseInt(sc.nextLine());
        while(n!=1) {
            System.out.print(n+" ");
            if (n%2==0) {
                n/=2;
            }
            else
                n = 3*n+1;
        }
        System.out.println(n);
    }
}