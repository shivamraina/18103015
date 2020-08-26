public class Question4 {

    public static void main (String[] args) {
        int maxi = Integer.MAX_VALUE;
        int n = 1;
        while(n*((n+1)/2) > 0) {
            n++;
        }
        System.out.println(n);
    }
}