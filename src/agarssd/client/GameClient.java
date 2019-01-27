package agarssd.client;

import agarssd.model.Item;
import agarssd.model.MoveCommand;
import agarssd.model.Player;
import agarssd.model.World;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.util.Scanner;

public class GameClient {

    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 54555;
    public static final int TIMEOUT = 5000;

    private Client kryoClient;
    private Gui gui;

    public void start() {
        kryoClient = new Client();
        kryoClient.getKryo().register(World.class);
        kryoClient.getKryo().register(Player.class);
        kryoClient.getKryo().register(Item.class);
        kryoClient.getKryo().register(MoveCommand.class);
        kryoClient.getKryo().register(java.util.List.class);
        kryoClient.getKryo().register(java.util.ArrayList.class);
        kryoClient.start();
        kryoClient.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof World) {
                    World world = (World) object;
                    if(gui != null) {
                        gui.repaintWorld(world);
                    }
                }
            }
        });
        try {
            kryoClient.connect( TIMEOUT, ADDRESS, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gui = new Gui();
    }

    public static void main(String[] args) {
        GameClient gameClient = new GameClient();
        gameClient.start();

        new Scanner(System.in).nextLine();
    }

}
