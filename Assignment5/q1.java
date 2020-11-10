public class Main extends Thread {
    public void run() {
        for(int i=0;i<100;i++) {
            System.out.println(i+1);
            if((i+1)%10 == 0) {
                System.out.println("10 numbers printed");
            }
            try {
                Thread.sleep(1000);
            }
            catch(Exception e){}
        }
    }
    public static void main(String[] args)
    {
        Main obj = new Main();
        obj.start();
    }
}