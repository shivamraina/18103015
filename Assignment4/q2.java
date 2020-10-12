public class q2 {
    public static void main(String[] args) {
        int n = -1;
        byte b = (byte)n;
        char c = (char)b;
        int num = (int)c;
        System.out.println(n);
        System.out.println(b);
        System.out.println(c);
        System.out.println(num);
    }
}

//output:
// -1
// -1
// ? in cmd and ￿ in intellij
// 65535

// Explain:
// -1 in byte is same as -1 in integer.
// -1 changes to 65535 in integer and it represents ￿ in unicode.