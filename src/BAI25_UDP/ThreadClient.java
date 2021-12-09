/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI25_UDP;

/**
 *
 * @author azure Tran
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
public class ThreadClient  implements  Runnable{
    private Socket socket;
    private BufferedReader cin1;

    public ThreadClient(Socket socket) throws IOException {
        this.socket = socket;
        this.cin1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = cin1.readLine();
                System.out.println(message);
            }
        } catch (SocketException e) {
            System.out.println("Bạn đã rời khỏi phòng chat");
        } catch (IOException exception) {
            System.out.println(exception);
        } finally {
            try {
                cin1.close();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }
}
