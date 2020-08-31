public class Question4 {

    public static void main (String[] args) {
        int i = 1;
        int ans = 0;
        while(i*((i+1)/2) > 0) {
            if(i*((i+1)/2 )== i*i) {
                ans = i;
            }
            i++;
        }
        System.out.println(ans);
    }
}