import java.util.Scanner;

public class Question2 {

    public static void sort(int[] arr) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[21];
        for (int i=0;i<21;i++) {
            count[i] = 0;
        }
        for (int i=0;i<n;i++) {
            ++count[arr[i]];
        }
        for (int i=1;i<21;i++) {
            count[i] += count[i-1];
        }

        for (int i=n-1;i>=0;i--) {
            output[count[arr[i]]-1] = arr[i];
            --count[arr[i]];
        }
        for(int i=0;i<n;i++) {
            arr[i] = output[i];
        }
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Size of Array");
        int n = Integer.parseInt(sc.nextLine());
        int[] arr = new int[n];
        System.out.println("Input Array elements/ Space separated only");
        String input = sc.nextLine();
        String[] temp = input.split(" ");
        if (temp.length > n) {
            System.out.println("Extra Elements Provided are neglected in computation");
        }
        else if (temp.length < n) {
            System.out.println("Incomplete Input Provided");
            return;
        }
        for (int i=0;i<n;i++) {
            try {
                arr[i] = Integer.parseInt(temp[i]);
            } catch (NumberFormatException e) {
                System.out.println("Input not Integer");
                return;
            }

        }
        for (int i=0;i<n;i++) {
            if (!(arr[i]>=0 && arr[i] <= 20)) {
                System.out.println("Input not in the range of 0 to 20");
                return;
            }
        }
        sort(arr);
        for (int i=0;i<n;i++) {
            System.out.println(arr[i]);
        }
    }
}