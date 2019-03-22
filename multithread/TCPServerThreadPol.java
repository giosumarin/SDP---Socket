import java.io.*;
import java.net.*;

public class TCPServerThreadPol extends Thread {
    private     Socket connectionSocket = null;
    private     BufferedReader inFromClient;
    private     DataOutputStream  outToClient;

    /* L'argomento del costruttore e' una established socket */
    public TCPServerThreadPol(Socket s) {
        connectionSocket = s;

        try{
            inFromClient =
                    new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()));

            outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("thread");

        String clientSentence;
        String capitalizedSentence;
        //int[] a={0,0};
        int a1, a2;
        try {
        //for(int i=1; i<3; i++) {
            clientSentence = inFromClient.readLine();
            outToClient.writeBytes("ok\n");
            a1=Integer.parseInt(clientSentence);
            System.out.println(a1);

            clientSentence = inFromClient.readLine();
            outToClient.writeBytes("ok\n");
            a2=Integer.parseInt(clientSentence);
            System.out.println(a2);

        //}
            Integer b=(a1+a2);
            capitalizedSentence = b.toString()+ '\n';

            outToClient.writeBytes(capitalizedSentence);

            connectionSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
