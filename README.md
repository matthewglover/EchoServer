# EchoServer

## What

Echo Server and Client applications.

## Why

To learn about Java Sockets.

## How

There are two executable java classes:

- MyEchoServer: this is the server application
- MyEchoClient: this is the client application

The classes can be run via gradle using the following commands:

1. Start the Server: `./gradlew -PmainClass=MyEchoServer -PappArgs="['\<port number\>']" execute`
2. Start the Client: `./gradlew -PmainClass=MyEchoClient -PappArgs="['\<server ip\>', '\<port number\>']" execute`   

The Server needs to be running first, so the Client has something to connect to. The Client must connect to the Server's ip 
address and port number.
