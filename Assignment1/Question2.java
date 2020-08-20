import java.util.*;
// punctuations will be discarded
public class Question2 {

    public static String[] changeParaToWords(String para, int words) {
        String[] changed = new String[words];
        int k = 0;
        int prev = 0;
        for(int i=0;i<para.length();i++) {
            if(para.charAt(i)==' ') {
                changed[k] = para.substring(prev, i);
                prev = i+1;
                k++;
            }
        }
        changed[k] = para.substring(prev);
        return changed;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("------Enter the Paragraph---------");
        String para = sc.nextLine();
        System.out.println("Enter the Size of vector");
        int n = Integer.parseInt(sc.nextLine());
        System.out.println("Enter elements of vector");
        String[] dict = new String[n];
        for(int i=0;i<dict.length;i++) {
            dict[i] = sc.nextLine();
        }
        int words = 0;
        for(int i=0;i<para.length();i++) {
            if(para.charAt(i) == ' ') {
                words++;
            }
        }
        words++;
        String[] changed = changeParaToWords(para, words);
        // remove all punctuations
        for(int i=0;i<words;i++) {
            if(!((changed[i].charAt(changed[i].length()-1)>='a' && changed[i].charAt(changed[i].length()-1)<='z') || (changed[i].charAt(changed[i].length()-1)>='A' && changed[i].charAt(changed[i].length()-1)<='Z'))) {
                changed[i] = changed[i].substring(0, changed[i].length()-1);
            }
            System.out.println(changed[i]);
        }
        // remove all q
        for(int i=0;i<words;i++) {
            String curr = changed[i];
            if(Arrays.asList(dict).contains(curr)) {
                String newString = curr.charAt(0)+"*";
                for(int j=0;j<curr.length()-2;j++) {
                    newString+='*';
                }
                changed[i] = newString;
            }
        }
        String finalPara = "";
        for(int i=0;i<words-1;i++) {
            finalPara+=changed[i]+" ";
        }
        finalPara+=changed[words-1];
        System.out.println(finalPara);
    }
}