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

    public void startServer() {
        try {
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Player connected!");
                PlayerHandler playerHandler = new PlayerHandler(socket);

                Thread thread = new Thread(playerHandler);
                thread.start();
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
        System.out.println("Current Players: " + playerHandlers.size());
    }

    public static synchronized void removePlayer(PlayerHandler handler) {
        playerHandlers.remove(handler);
        System.out.println("Current Players: " + playerHandlers.size());
    }

    public void waitForStartGame() {

    }

    public void startGame() {
        System.out.println("Starting the game!");
        gameStarted = true;
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

        gameServer.startServer();
    }
}
