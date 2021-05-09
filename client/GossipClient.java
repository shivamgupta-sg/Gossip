import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GossipClient {
    public static void main(String[] args) throws Exception {
        System.out.println("Enter host ip: ");
        Scanner scanner = new Scanner(System.in);
        String hostIP = null;
        if (args[0] != null)
            hostIP = args[0];
        else hostIP = scanner.next();

        System.out.println("Enter port: ");
        int port;
        if (args[1] != null)
            port = Integer.parseInt(args[1]);
        else port = scanner.nextInt();

        Socket sock = new Socket(hostIP, port);

        // reading from keyboard (keyRead object)
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

        // sending to client (pwrite object)
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);

        // receiving from server ( receiveRead object)
        InputStream istream = sock.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

        FileWriter fileWriter = new FileWriter("clientdata/conversation.txt", true);

        System.out.println("Start the chitchat, type and press Enter key");

        String receiveMessage, sendMessage;
        while (true) {
            sendMessage = keyRead.readLine(); // keyboard reading

            pwrite.println(sendMessage); // sending to server

            String clientMsg = "Client: " + sendMessage;
            try {
                fileWriter.write(clientMsg + "\n");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            pwrite.flush(); // flush the data

            if ((receiveMessage = receiveRead.readLine()) != null) // receive from server
            {
                String serverMsg = "Server: " + receiveMessage;
                System.out.println(serverMsg); // displaying at DOS prompt
                try {
                    fileWriter.write(serverMsg + "\n");
                    fileWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
