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
        String[] a={"a", "a"};
        int[] num={0,0};
        boolean ch;
        String operator;
        try {
            for(int i=1; i<=2; i++) {
                do {
                    ch=true;
                    outToClient.writeBytes("Inserisci cifra " + i + "\n");
                    a[i - 1] = inFromClient.readLine();
                    try {
                        num[i - 1] = Integer.parseInt(a[i - 1]);
                        ch = false;
                        outToClient.writeBytes("ok\n");
                    } catch (NumberFormatException e) {
                        outToClient.writeBytes("Valore errato\n");
                    }

                } while (ch);
            }
            int result=0;
            boolean chOp;

            do {
                chOp=false;
                outToClient.writeBytes("Inserisci operatore valido\n");
                operator=inFromClient.readLine();
                switch (operator) {
                    case "+":
                        result = (num[0] + num[1]);
                        outToClient.writeBytes("ok\n");
                        break;
                    case "-":
                        result = (num[0] - num[1]);
                        outToClient.writeBytes("ok\n");
                        break;
                    case "*":
                        result = (num[0] * num[1]);
                        outToClient.writeBytes("ok\n");
                        break;
                    case "/":
                        result = (num[0] / num[1]);
                        outToClient.writeBytes("ok\n");
                        break;
                    default:
                        chOp = true;
                        outToClient.writeBytes("operatore non valido\n");
                }
            } while(chOp);


            outToClient.writeBytes(result+"\n");

            connectionSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
