package servidor;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientHandler extends Thread {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            BufferedReader entrada =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter saida =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true);

            List<Pergunta> perguntas = new ArrayList<>();

            perguntas.add(
                    new Pergunta(
                            "Capital do Brasil?",
                            new String[]{
                                    "A) Rio de Janeiro",
                                    "B) Brasilia",
                                    "C) Belo Horizonte",
                                    "D) Sao Paulo"
                            },
                            'B'
                    )
            );

            perguntas.add(
                    new Pergunta(
                            "Quanto eh 5 x 5?",
                            new String[]{
                                    "A) 20",
                                    "B) 15",
                                    "C) 25",
                                    "D) 10"
                            },
                            'C'
                    )
            );

            perguntas.add(
                    new Pergunta(
                            "Qual linguagem estamos usando?",
                            new String[]{
                                    "A) Python",
                                    "B) Java",
                                    "C) C",
                                    "D) JavaScript"
                            },
                            'B'
                    )
            );

            Collections.shuffle(perguntas);

            int nota = 0;

            for (Pergunta p : perguntas) {

                saida.println("PERGUNTA");
                saida.println(p.getEnunciado());

                for (String alt : p.getAlternativas()) {
                    saida.println(alt);
                }

                String resposta = entrada.readLine();

                if (resposta != null &&
                        resposta.equalsIgnoreCase(
                                String.valueOf(p.getCorreta()))) {
                    nota++;
                }
            }

            saida.println("FIM");
            saida.println("Sua nota foi: "
                    + nota + "/" + perguntas.size());

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}