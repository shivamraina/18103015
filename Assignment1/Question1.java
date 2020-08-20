import java.util.*;

public class Question1 {

    public static String subString;

    public static boolean nextpermutation() {

        int n = subString.length();
        int index = -1;
        for(int i=n-2;i>=0;i--) {
            if(subString.charAt(i) < subString.charAt(i+1)) {
                index = i;
                break;
            }
        }
        if(index == -1) return false;
        for(int i=n-1;i>=0;i--) {
            if(subString.charAt(i) > subString.charAt(index)) {
                // swap string chars at index ---> i and index
                String s1 = subString.substring(0, index);
                String s2 = subString.substring(index+1, i);
                String s3 = subString.substring(i+1);
                subString = s1+subString.charAt(i)+s2+subString.charAt(index)+s3;

                // reverse string from index+1 till end
                for(int j=index+1, k=n-1;j<k;j++,k--)
                {
                    s1 = subString.substring(0, j);
                    s2 = subString.substring(j+1, k);
                    s3 = subString.substring(k+1);
                    subString = s1+subString.charAt(k)+s2+subString.charAt(j)+s3;
                }
                return true;
            }
        }
        return false;
    }

    public static int substringSearch(String mainString) {

        // Z -  Algorithm
        String newString = subString+'$'+mainString;
        int n = newString.length();
        int[] Z = new int[n];
        int l = 0;
        int r = 0;
        for(int i=1;i<n;i++) {

            if(i>r) {
                l=i; r=i;
                while(r<n && newString.charAt(r-l) == newString.charAt(r)) {
                    r++;
                }
                r--;
                Z[i] = r+1-l;
            }
            else {
                int k = i-l;
                if(i+Z[k]<=r) {
                    Z[i] = Z[r];
                }
                else {
                    l = i;
                    while(r<n && newString.charAt(r-l) == newString.charAt(r)) {
                        r++;
                    }
                    r--;
                    Z[i] = r+1-l;
                }
            }
        }
        int ans = 0;
        for(int i=subString.length()+1;i<n;i++) {
            if(Z[i] == subString.length()) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // Create Scanner Object
        Scanner sc = new Scanner(System.in);
        // Prompt user for main string
        System.out.println("------Enter Main String-------");
        // Take main string as input
        String mainString = sc.nextLine();
        // Prompt user for second string
        System.out.println("-------Enter Substring--------");
        // take substring as input
        subString = sc.nextLine();
        // initialise answer
        int answer = 0;
        // for sorting the string, change string to array, then sort and then change back to string
        char tempArray[] = subString.toCharArray();
        Arrays.sort(tempArray);
        subString = new String(tempArray);
        // check occurrences of all permutations of substring in mainstring
        do
        {
            answer+=substringSearch(mainString);
        } while(nextpermutation());
        // print the answer
        System.out.println("The Frequency is "+answer);
    }
}