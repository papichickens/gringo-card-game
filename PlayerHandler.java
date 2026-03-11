import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class PlayerHandler implements Runnable {

    private Socket socket;
    // reads from Player
    private BufferedReader bufferedReader;
    // writes messages to player terminal from server
    private BufferedWriter bufferedWriter;
    private String playerUsername;

    public PlayerHandler(Socket socket) throws IOException {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.playerUsername = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PlayerHandler create(Socket socket) throws IOException {
        PlayerHandler handler = new PlayerHandler(socket);
        GameServer.addPlayer(handler);   // now `this` is fully initialized
        return handler;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void sendMessage(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeConnection(socket, bufferedReader, bufferedWriter);
        }
    }


    public void closeConnection(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if(socket != null)
                socket.close();
            if (bufferedReader != null)
                bufferedReader.close();
            if (bufferedWriter != null)
                bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                GameServer.handlePlayerInput(this, input);
                // System.out.println("we wrote: " + input);
            }
            GameServer.removePlayer(this);
            closeConnection(socket, bufferedReader, bufferedWriter);
        } catch (IOException e) {
            GameServer.removePlayer(this);
            closeConnection(socket, bufferedReader, bufferedWriter);
        }
    }
}
