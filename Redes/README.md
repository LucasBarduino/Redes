# Quiz em Rede - Java

## Descrição

Este projeto consiste em uma aplicação cliente-servidor desenvolvida em Java para a disciplina de Redes de Computadores.

A aplicação implementa um sistema de quiz de múltipla escolha em rede, permitindo que um cliente responda perguntas enviadas por um servidor remoto e receba sua nota ao final da execução.

O projeto utiliza:

- Comunicação TCP
- Comunicação UDP
- Interface gráfica Java Swing
- Multithreading
- Encaminhamento de pacotes entre roteadores

---

# Objetivos

Desenvolver uma aplicação em rede que atenda aos seguintes requisitos:

- Linguagem Java
- Interface gráfica Swing no cliente
- Comunicação TCP
- Comunicação UDP
- Uso de Multithreading

---

# Topologia da Rede

```text
PC1 (Cliente)
      |
    WiFi
      |
     R1
      |
    Cabo
      |
     R2
      |
    WiFi
      |
PC2 (Servidor)
```

---

# Endereçamento

## Rede do Cliente

```text
192.168.0.0/16
```

## Rede entre R1 e R2

```text
172.16.0.0/12
```

## Rede do Servidor

```text
10.0.0.0/8
```

---

# Estrutura do Projeto

```text
src/
│
├── cliente/
│   ├── QuizClientGUI.java
│   └── UDPClient.java
│
└── servidor/
    ├── Pergunta.java
    ├── ClientHandler.java
    ├── QuizServer.java
    └── UDPServer.java
```

---

# Funcionamento

## Comunicação TCP

A comunicação TCP é utilizada para a execução do quiz.

Porta utilizada:

```text
5000
```

Fluxo:

```text
Cliente -> Conecta ao servidor

Servidor -> Envia perguntas

Cliente -> Envia respostas

Servidor -> Calcula a nota

Servidor -> Envia resultado final
```

---

## Comunicação UDP

A comunicação UDP é utilizada para verificar se o servidor está ativo.

Porta utilizada:

```text
6000
```

Fluxo:

```text
Cliente -> PING

Servidor -> SERVIDOR ONLINE
```

---

# Multithreading

O projeto utiliza múltiplas threads para permitir o funcionamento simultâneo dos serviços.

## UDPServer

Responsável por escutar requisições UDP continuamente.

## ClientHandler

Responsável por atender clientes TCP simultaneamente.

Cada cliente conectado recebe uma thread exclusiva.

---

# Interface Gráfica

A interface foi desenvolvida utilizando Java Swing.

Componentes utilizados:

- JFrame
- JTextArea
- JTextField
- JButton

Funcionalidades:

- Informar IP do servidor
- Exibir perguntas
- Enviar respostas
- Exibir resultado final

---

# Classes do Projeto

## Pergunta.java

Representa uma pergunta do quiz.

Atributos:

```java
String enunciado;
String[] alternativas;
char correta;
```

---

## QuizServer.java

Classe principal do servidor.

Responsável por:

- Criar o ServerSocket
- Iniciar o servidor UDP
- Aceitar conexões TCP

---

## ClientHandler.java

Responsável por:

- Enviar perguntas
- Receber respostas
- Corrigir respostas
- Calcular nota

---

## UDPServer.java

Responsável por:

- Receber mensagens UDP
- Responder status do servidor

---

## UDPClient.java

Responsável por:

- Verificar disponibilidade do servidor

---

## QuizClientGUI.java

Interface gráfica do cliente.

Responsável por:

- Conectar ao servidor
- Exibir perguntas
- Enviar respostas
- Exibir resultados

---

# Encaminhamento de Portas

## R1

TCP

```bash
iptables -t nat -A PREROUTING \
-p tcp --dport 5000 \
-j DNAT --to-destination 172.16.0.2:5000
```

UDP

```bash
iptables -t nat -A PREROUTING \
-p udp --dport 6000 \
-j DNAT --to-destination 172.16.0.2:6000
```

---

## R2

TCP

```bash
iptables -t nat -A PREROUTING \
-p tcp --dport 5000 \
-j DNAT --to-destination 10.0.0.10:5000
```

UDP

```bash
iptables -t nat -A PREROUTING \
-p udp --dport 6000 \
-j DNAT --to-destination 10.0.0.10:6000
```

---

# Compilação

Na raiz do projeto:

```bash
javac -d bin src/servidor/*.java src/cliente/*.java
```

---

# Execução

## Executar Servidor

```bash
java -cp bin servidor.QuizServer
```

Saída esperada:

```text
Servidor iniciado!
IP: 10.0.0.10
Porta TCP: 5000
UDP iniciado na porta 6000
```

---

## Executar Cliente

```bash
java -cp bin cliente.QuizClientGUI
```

Ao iniciar o cliente será solicitado o IP do servidor.

---

# Exemplo de Execução

```text
Capital do Brasil?

A) Rio de Janeiro
B) Brasília
C) Belo Horizonte
D) São Paulo

Resposta: B
```

Resultado:

```text
Sua nota foi: 3/3
```

---

# Requisitos Atendidos

| Requisito                     | Status |
| ----------------------------- | ------ |
| Linguagem Java                | ✅     |
| Interface Swing               | ✅     |
| Comunicação TCP               | ✅     |
| Comunicação UDP               | ✅     |
| Multithreading                | ✅     |
| Aplicação Cliente-Servidor    | ✅     |
| Redes distintas               | ✅     |
| Encaminhamento por roteadores | ✅     |

---

# Conclusão

O projeto demonstra a implementação de uma aplicação cliente-servidor utilizando Java, explorando conceitos fundamentais de Redes de Computadores, como comunicação TCP e UDP, sockets, multithreading, roteamento entre redes distintas e desenvolvimento de interfaces gráficas com Swing.
