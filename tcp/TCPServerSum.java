import java.io.*;
import java.net.*;

public class TCPServerSum {
    public static void main(String argv[]) throws Exception
    {
        String clientSentence;
        Integer capitalizedSentence;

        BufferedReader inPort =
                new BufferedReader(new InputStreamReader(System.in));


        /* Crea una "listening socket" sulla porta specificata */
        ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(inPort.readLine()));
        System.out.println("Connesso?");


        while(true) {
            /*
             * Viene chiamata accept (bloccante).
             * All'arrivo di una nuova connessione crea una nuova
             * "established socket"
             */
            Socket connectionSocket = welcomeSocket.accept();

            /* Inizializza lo stream di input dalla socket */
            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(connectionSocket.getInputStream()));

            /* Inizializza lo stream di output verso la socket */
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            /* Legge una linea (terminata da \n) dal client */
            clientSentence = inFromClient.readLine();

            System.out.println(clientSentence);

            capitalizedSentence = Integer.getInteger(clientSentence);

            /* Invia la risposta al client */
            outToClient.writeInt(capitalizedSentence);

        }
    }
}
