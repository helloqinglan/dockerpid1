import sun.misc.Signal;

public class Main2 {

    public static void main(String[] args) throws Exception {
        handleSignal("TERM");

        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("I'm doing some work ...");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private static void shutdown() {
        System.out.println("Shutdown initiated");
        System.exit(0);
    }

    private static void handleSignal(String name) {
        Signal signal = new Signal(name);
        Signal.handle(signal, Signal -> {
            System.out.println("Signal received: " + signal.getName());
            if ("TERM".equals(signal.getName())) {
                shutdown();
            }
        });
    }
}