# Java WebServer Project

This project demonstrates three different implementations of a simple Java-based server:
1. **SingleThreaded**  
2. **MultiThreaded**  
3. **ThreadPool**

Each implementation listens for client connections and responds with a basic greeting, but they differ in how they handle concurrency.

---

## Folder Structure

```
WEBServer/
├── MultiThreaded/
│   ├── Client.java
│   └── Server.java
├── SingleThreaded/
│   ├── Client.java
│   └── Server.java
├── ThreadPool/
│   └── Server.java
├── .gitignore
└── Thread Group.jmx
```

- **MultiThreaded**: Contains the client and server code for a server that spawns a new thread for each incoming connection.  
- **SingleThreaded**: Contains the client and server code for a server that handles one client at a time in a single thread.  
- **ThreadPool**: Demonstrates using a pool of threads (usually via an `ExecutorService`) to handle multiple client connections without creating a new thread for each connection.

The `Thread Group.jmx` is used for load testing with [Apache JMeter](https://jmeter.apache.org/).

---

## SingleThreaded

- **Server.java**  
  - Opens a `ServerSocket` on a specified port (e.g., `8010`).  
  - Accepts connections one at a time.  
  - Processes each client request in a single thread, blocking subsequent clients until the current client disconnects.  

- **Client.java**  
  - Connects to the server socket.  
  - Sends a greeting message and reads the server response.  

### How to Run (SingleThreaded)

1. Compile:
   ```bash
   cd SingleThreaded
   javac Server.java Client.java
   ```
2. Run the server (in one terminal):
   ```bash
   java Server
   ```
3. Run the client (in another terminal):
   ```bash
   java Client
   ```

---

## MultiThreaded

- **Server.java**  
  - Similar to the single-threaded version but spawns a **new thread** to handle each client connection.  
  - Allows multiple clients to connect simultaneously without blocking one another.

- **Client.java**  
  - Same basic structure as in SingleThreaded but may include changes to test concurrency.

### How to Run (MultiThreaded)

1. Compile:
   ```bash
   cd MultiThreaded
   javac Server.java Client.java
   ```
2. Run the server (in one terminal):
   ```bash
   java Server
   ```
3. Run multiple clients (in other terminals) to see concurrent handling:
   ```bash
   java Client
   ```

---

## ThreadPool

- **Server.java**  
  - Uses an `ExecutorService` (e.g., `Executors.newFixedThreadPool(...)`) to manage a pool of worker threads.  
  - Each incoming connection is handed off to the thread pool, which handles the request without spawning a new thread every time.

### How to Run (ThreadPool)

1. Compile:
   ```bash
   cd ThreadPool
   javac Server.java
   ```
2. Run the server (in one terminal):
   ```bash
   java Server
   ```
3. You can reuse the **Client.java** from the MultiThreaded. Make sure to compile and run the Client from its respective directory or adjust the package as needed.

---

## Testing with JMeter

- The file `Thread Group.jmx` is a [JMeter](https://jmeter.apache.org/) test plan.  
- You can open it in JMeter to load test your servers by sending multiple concurrent requests.  
- The `jmeter.log` file can store the logs or results of these tests.

---

## Troubleshooting

- **Port Already in Use**: If you get an error like “Address already in use,” change the port in your server and client code.  
