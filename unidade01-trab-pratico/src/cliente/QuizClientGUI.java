package cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class QuizClientGUI {

    private static JTextArea area;
    private static JTextField campo;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame janela =
                    new JFrame("Quiz em Rede");

            area = new JTextArea();
            area.setEditable(false);

            campo = new JTextField();

            JButton enviar =
                    new JButton("Enviar");

            janela.setLayout(new BorderLayout());

            janela.add(
                    new JScrollPane(area),
                    BorderLayout.CENTER);

            janela.add(
                    campo,
                    BorderLayout.SOUTH);

            janela.add(
                    enviar,
                    BorderLayout.NORTH);

            janela.setSize(600, 400);

            janela.setDefaultCloseOperation(
                    JFrame.DO_NOTHING_ON_CLOSE);

            janela.setVisible(true);

            try {

                String ipServidor =
                        JOptionPane.showInputDialog(
                                "Digite o IP do servidor:");

                UDPClient.verificarServidor(ipServidor);

                Socket socket =
                        new Socket(ipServidor, 5000);

                janela.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        try { socket.close(); } catch (Exception ex) { }
                        System.exit(0);
                    }
                });

                BufferedReader entrada =
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()));

                PrintWriter saida =
                        new PrintWriter(
                                socket.getOutputStream(),
                                true);

                Thread receber =
                        new Thread(() -> {

                            try {

                                String linha;

                                while ((linha =
                                        entrada.readLine())
                                        != null) {

                                    area.append(
                                            linha + "\n");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                receber.start();

                enviar.addActionListener(e -> {

                    saida.println(campo.getText());

                    campo.setText("");
                });

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Erro: " + e.getMessage());
            }
        });
    }
}