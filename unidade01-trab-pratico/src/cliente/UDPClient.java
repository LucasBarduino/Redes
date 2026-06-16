package cliente;

import java.net.*;

public class UDPClient {

    public static void verificarServidor(String ip)
            throws Exception {

        DatagramSocket socket =
                new DatagramSocket();

        String mensagem = "PING";

        DatagramPacket pacote =
                new DatagramPacket(
                        mensagem.getBytes(),
                        mensagem.length(),
                        InetAddress.getByName(ip),
                        6000);

        socket.send(pacote);

        byte[] buffer = new byte[1024];

        DatagramPacket resposta =
                new DatagramPacket(
                        buffer,
                        buffer.length);
                        
        socket.setSoTimeout(3000);
        socket.receive(resposta);

        System.out.println(
                "Resposta UDP: " +
                        new String(
                                resposta.getData(),
                                0,
                                resposta.getLength()));

        socket.close();
    }
}