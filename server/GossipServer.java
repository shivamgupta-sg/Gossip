import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GossipServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Enter port: ");
        Scanner scanner = new Scanner(System.in);
        int port;
        if (args[0] != null)
            port = Integer.parseInt(args[0]);
        else port = scanner.nextInt();

        ServerSocket sersock = new ServerSocket(port);
        System.out.println("Server ready for chatting");
        Socket sock = sersock.accept();

        // reading from keyboard (keyRead object)
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

        // sending to client (pwrite object)
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);

        // receiving from server ( receiveRead object)
        InputStream istream = sock.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

        FileWriter fileWriter = new FileWriter("serverdata/conversation.txt", true);

        String receiveMessage, sendMessage;
        while (true) {
            if ((receiveMessage = receiveRead.readLine()) != null) {
                String clientMsg = "Client: " + receiveMessage;
                System.out.println(clientMsg);
                try {
                    fileWriter.write(clientMsg + "\n");
                    fileWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            sendMessage = keyRead.readLine();
            String serverMsg = "Server: " + sendMessage;
            pwrite.println(sendMessage);
            try {
                fileWriter.write(serverMsg + "\n");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pwrite.flush();
        }
        // while (receiveRead.readLine() == "bye" || receiveRead.readLine() == "Bye");
        // fileWriter.close();
    }
}
