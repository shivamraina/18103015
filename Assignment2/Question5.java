import java.util.*;

public class Question5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] universe = {0,1,2,3,4,5,6,7,8,9,10};

        System.out.println("Enter size of First Array");
        int n = Integer.parseInt(sc.nextLine());
        int[] a = new int[n];

        System.out.println("Enter the Elements of First Array - Space Separated");
        String[] arr = sc.nextLine().split(" ");
        for (int i=0;i<n;i++) {
            a[i] = Integer.parseInt(arr[i]);
        }

        System.out.println("Enter size of Second Array");
        int m = Integer.parseInt(sc.nextLine());
        int[] b = new int[m];

        System.out.println("Enter the Elements of Second Array - Space Separated");
        arr = sc.nextLine().split(" ");
        for (int i=0;i<m;i++) {
            b[i] = Integer.parseInt(arr[i]);
        }

        // Naive Approach
        long start = System.currentTimeMillis();
        System.out.println("Union of A and B: ");
        Vector union = new Vector();
        for (int i=0;i<n;i++) {
            union.add(a[i]);
        }
        for(int i=0;i<m;i++) {
            int flag = 0;
            for(int j=0;j<n;j++) {
                if(b[i] == a[j]) {
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                union.add(b[i]);
            }
        }
        for(int i=0;i<union.size();i++) {
            System.out.print(union.get(i)+" ");
        }
        System.out.println();
        System.out.println("Intersection of A and B: ");
        Vector inter = new Vector();
        for (int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(b[j] == a[i]) {
                    inter.add(a[i]);
                    break;
                }
            }
        }
        for(int i=0;i<inter.size();i++) {
            System.out.print(inter.get(i)+" ");
        }
        System.out.println();
        System.out.println("Complement of A:");
        Vector comp1 = new Vector();
        for(int i=0;i<11;i++) {
            int flag = 0;
            for(int j=0;j<n;j++) {
                if(a[j] == i) {
                    flag=1;
                    break;
                }
            }
            if(flag == 0){
                comp1.add(i);
            }
        }
        for(int i=0;i<comp1.size();i++) {
            System.out.print(comp1.get(i)+" ");
        }
        System.out.println();
        System.out.println("Complement of B:");
        Vector comp2 = new Vector();
        for(int i=0;i<11;i++) {
            int flag = 0;
            for(int j=0;j<m;j++) {
                if(b[j] == i) {
                    flag=1;
                    break;
                }
            }
            if(flag == 0){
                comp2.add(i);
            }
        }
        for(int i=0;i<comp2.size();i++) {
            System.out.print(comp2.get(i)+" ");
        }
        System.out.println();
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("Elapsed Time in all these operations using array is : " + elapsedTime +" milliseconds");

        // better approach
        long start2 = System.currentTimeMillis();
        HashSet<Integer> set1 = new HashSet<>();
        for(int i=0;i<n;i++){
            set1.add(a[i]);
        }
        for(int i=0;i<m;i++){
            set1.add(b[i]);
        }
        Integer[] newUnion = {};
        newUnion = set1.toArray(newUnion);
        System.out.println("Union of A and B: ");
        for(int i=0;i<newUnion.length;i++) {
            System.out.print(newUnion[i]+" ");
        }
        System.out.println();
        HashSet<Integer> set2 = new HashSet<>();
        for(int i=0;i<n;i++){
            set2.add(a[i]);
        }
        Vector  newInter = new Vector();
        for(int i=0;i<m;i++){
            if(set2.contains(b[i])) {
                newInter.add(b[i]);
            }
        }
        System.out.println("Intersection of A and B: ");
        for(int i=0;i<newInter.size();i++) {
            System.out.print(newInter.get(i)+" ");
        }
        System.out.println();
        System.out.println("Complement of A:");
        HashSet<Integer> set3 = new HashSet<>();
        Vector comp3 = new Vector();
        for(int i=0;i<n;i++){
            set3.add(a[i]);
        }
        for(int i=0;i<11;i++) {
            if(!set3.contains(i)) {
                comp3.add(i);
            }
        }
        for(int i=0;i<comp3.size();i++) {
            System.out.print(comp3.get(i)+" ");
        }
        System.out.println();
        System.out.println("Complement of B:");
        HashSet<Integer> set4 = new HashSet<>();
        Vector comp4 = new Vector();
        for(int i=0;i<m;i++){
            set4.add(b[i]);
        }
        for(int i=0;i<11;i++) {
            if(!set4.contains(i)) {
                comp4.add(i);
            }
        }
        for(int i=0;i<comp4.size();i++) {
            System.out.print(comp4.get(i)+" ");
        }
        System.out.println();
        long end2 = System.currentTimeMillis();
        long elapsedTime2 = end2 - start2;
        System.out.println("Elapsed Time in all these operations using sets is : " + elapsedTime2+" milliseconds");
    }
}