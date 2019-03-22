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
        boolean ch=true;
        String operator;
        try {
            for(int i=1; i<=2; i++) {
                do {
                    outToClient.writeBytes("Inserisci cifra " + i + "\n");
                    a[i - 1] = inFromClient.readLine();
                    try {
                        System.out.println(ch);
                        num[i - 1] = Integer.parseInt(a[i - 1]);
                        System.out.println(ch);
                        ch = false;
                        System.out.println(ch);
                        outToClient.writeBytes("ok\n");
                    } catch (NumberFormatException e) {
                        //outToClient.writeBytes("Valore errato");
                        System.out.println("valore errato");
                        continue;
                    }

                } while (ch);
            }
            int result=0;
             operator=inFromClient.readLine();
             switch(operator){
                 case "+": result=(num[0]+num[1]);
                 break;
                 case "-": result=(num[0]-num[1]);
                 break;
                 case "*": result=(num[0]*num[1]);
                 break;
                 case "/": result=(num[0]/num[1]);
                 break;
            }


            outToClient.writeBytes(result+"\n");

            connectionSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
