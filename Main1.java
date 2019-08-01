public class Main1 {

    public static void main(String[] args) {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("I'm doing some work ...");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}