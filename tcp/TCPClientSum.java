import java.io.*;
import java.net.*;

class TCPClientSum {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;

        /* Inizializza l'input stream (da tastiera) */
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        /* Inizializza una socket client, connessa al server */
        Socket clientSocket = new Socket("localhost", 6789);

        /* Inizializza lo stream di output verso la socket */
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());

        /* Inizializza lo stream di input dalla socket */
        BufferedReader inFromServer =
                new BufferedReader(new
                        InputStreamReader(clientSocket.getInputStream()));

        /* Legge una linea da tastiera */
        sentence = inFromUser.readLine();

        //System.out.println(sentence1 + sentence2);

        /* Invia la linea al server */
        outToServer.writeBytes(sentence + '\n');

        /* Legge la risposta inviata dal server (linea terminata da \n) */
        modifiedSentence = inFromServer.readLine();
        String[] s=sentence.split("-");
        Integer res=Integer.parseInt(s[0])+Integer.parseInt(s[1]);

        System.out.println("FROM SERVER: " + res);

        clientSocket.close();
    }
}
