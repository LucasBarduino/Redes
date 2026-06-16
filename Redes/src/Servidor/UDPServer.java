package servidor;

import java.net.*;
import java.util.Arrays;

public class UDPServer extends Thread {

    @Override
    public void run() {

        try {

            DatagramSocket socket =
                    new DatagramSocket(6000);

            System.out.println("UDP iniciado na porta 6000");

            byte[] buffer = new byte[1024];

            while (true) {

                Arrays.fill(buffer, (byte) 0);

                DatagramPacket pacote =
                        new DatagramPacket(
                                buffer,
                                buffer.length);

                socket.receive(pacote);

                String mensagem =
                        new String(
                                pacote.getData(),
                                0,
                                pacote.getLength());

                System.out.println(
                        "Recebido UDP: " + mensagem);

                String resposta = "SERVIDOR ONLINE";

                DatagramPacket retorno =
                        new DatagramPacket(
                                resposta.getBytes(),
                                resposta.length(),
                                pacote.getAddress(),
                                pacote.getPort());

                socket.send(retorno);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}