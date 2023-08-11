# Command-Line Chat

The command-line chat is a simple Java program that allows two users to exchange messages over a network connection using command-line interface. One user acts as the server, while the other user acts as the client.

## Features

- Chat between a server and a client using command-line interface
- Basic networking using sockets

## Requirements

- Java Development Kit (JDK) installed on your system

## How to Use

1. Navigate to the directory where the Java source file is located 

2. Compile the Java source files
```console
javac CommandLineChat.java
```

3. Start the server
```console
java CommandLineChat server
```

4. Start the client
```console
java CommandLineChat client <hostname or IP address>
```

5. Type your messages in the respective command-line interfaces of the server and client

## Notes

- The server and client need be run on different terminals or command prompt windows
- The server listens on port 8080 by default. You can modify the port number in the code if needed
- The server and client should be on the same local network if you're using local IP addresses