import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
    private ServerSocket serverSocket;
    public Game game;
    public static ArrayList<PlayerHandler> playerHandlers = new ArrayList<>();
    private static int currentPlayerIndex = 0;
    private static volatile boolean gameStarted = false;


    public GameServer(ServerSocket serverSocket, Game game) {
        this.serverSocket = serverSocket;
        this.game = game;
    }

    public void addNewPlayerHandler(Socket socket) {
        try {
            System.out.println("Player connected!");
            PlayerHandler playerHandler = new PlayerHandler(socket);

            Thread thread = new Thread(playerHandler);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   

    public void startServer() {
        try {
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Player connected!");
                addNewPlayerHandler(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void addPlayer(PlayerHandler handler) {
        playerHandlers.add(handler);
        System.out.println("User " + handler.getPlayerUsername() + " connected");
        System.out.println("Current Players: " + playerHandlers.size());
    }

    public static synchronized void removePlayer(PlayerHandler handler) {
        playerHandlers.remove(handler);
        System.out.println("User " + handler.getPlayerUsername() + " disconnected");
        System.out.println("Current Players: " + playerHandlers.size());
    }

    public void startGame() {
        System.out.println("Starting the game!");
        gameStarted = true;
    }

    public void waitForStartGame() {
        if(!gameStarted && playerHandlers.size() >= 2){
            startGame();
        }
    }

    public void broadcastmesage (String Message) {
        for (PlayerHandler ph : playerHandlers) {
            ph.sendMessage(Message);
        }
    }

    public static void handlePlayerInput(PlayerHandler sender, String input) {
        // if (!gameStarted) {
        //     sender.sendMessage("Game hasn't started yet.");
        // }
        System.out.println("client wrote: " + input);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Game game = new Game();
        GameServer gameServer = new GameServer(serverSocket, game);

        Thread threadAcceptPlayers = new Thread(gameServer::startServer);
        threadAcceptPlayers.start();

        Thread threadWaitForStart = new Thread(() -> {
            while (!gameStarted) {
                gameServer.waitForStartGame();
                try {
                    Thread.sleep(1000); // poll every 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadWaitForStart.start();
    }
}
