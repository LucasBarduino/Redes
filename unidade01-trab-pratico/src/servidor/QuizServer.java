package servidor;

import java.net.*;

public class QuizServer {

    public static final int PORTA_TCP = 5000;

    public static void main(String[] args) {

        try {

            ServerSocket servidor =
                    new ServerSocket(PORTA_TCP);

            System.out.println("Servidor iniciado!");

            System.out.println(
                    "IP: " +
                    InetAddress.getLocalHost()
                            .getHostAddress());

            System.out.println(
                    "Porta TCP: " +
                    PORTA_TCP);

            UDPServer udpServer =
                    new UDPServer();

            udpServer.start();

            while (true) {

                Socket cliente =
                        servidor.accept();

                System.out.println(
                        "Cliente conectado!");

                ClientHandler handler =
                        new ClientHandler(cliente);

                handler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}