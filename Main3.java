import sun.misc.Signal;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main3 {

    public static void main(String[] args) throws Exception {
        handleSignal("TERM");
        printAllProcesses();

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

    private static void printAllProcesses() throws Exception{
        Process process = Runtime.getRuntime().exec("ps aux");
        BufferedReader r =  new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;

        while((line=r.readLine())!=null) {
            System.out.println(line);
        }
    }
}