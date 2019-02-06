package agarssd.client;

import agarssd.model.Item;
import agarssd.model.MoveCommand;
import agarssd.model.Player;
import agarssd.model.World;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameClient implements Observable {
	
	//
	private List<Observer> observers;
	private boolean status;
	private final Object MUTEX= new Object();
	
	

	

    // public static final String ADDRESS = "127.0.0.1"; // For testing locally
    public static final String ADDRESS = "206.189.34.126"; // For testing on online server
    // public static final String ADDRESS = "192.168.xxx.xxx"; // For testing on lan network

    // Please do not modify these variables.
    public static final int PORT = 54555;
    public static final int TIMEOUT = 5000;
    public static final int LOGIC_DELAY = 500;

    private Client kryoClient;
    private Gui gui = new Gui();
    private World world;
    private Player myPlayer;
    private boolean running;
    
    private static GameLogic strategy;

      /** singleton instance */
      private static GameClient instance = null;
    
      private GameClient() {
    	  this.observers = new ArrayList<>();
      } 
      
      /**
       * Get a singleton instance of this class.
       * 
       * @return the game client object.
       */
      public static GameClient getInstance() {
    	  
    	  /** set strategy */ 	  
    	  strategy = new RandomMoveStrategy();
          if (instance == null) {
              instance = new GameClient();
          }
          return instance;
      }
      
      public void register(Observer obj) {
  		if(obj == null) throw new NullPointerException("Null Observer");
  		synchronized (MUTEX) {
  		if(!observers.contains(obj)) observers.add(obj);
  		}
  	}
      
      public void unregister(Observer obj) {
  		synchronized (MUTEX) {
  		observers.remove(obj);
  		}
  	}
      
      public void notifyObservers() {
  		List<Observer> observersLocal = null;
  		synchronized (MUTEX) {
  			if (!status)
  				return;
  			observersLocal = new ArrayList<>(this.observers);
  			this.status=false;
  		}
  		for (Observer obj : observersLocal) {
  			obj.updatePlayer( myPlayer);
  		}

  	}
      
      public Object getUpdate(Observer obj) {
  		return this.world;
  	}
      
      public void setStatus(Player myPlayer){
  		
  		this.myPlayer=myPlayer;
  		this.status=true;
  		notifyObservers();
  	}
      

    public void start() {
        initNetwork();
        initLogic();
        gui.setVisible(true);
    }

    private void initLogic() {
        running = true;
        Thread logicThread = new Thread() {
            @Override
            public void run() {
                super.run();
                while(running) {
                    refreshMyPlayer();
                    MoveCommand command = strategy.getNextMoveCommand(world, myPlayer);
                    if (command != null) {
                        kryoClient.sendTCP(command);
                    }
                    try {
                        Thread.sleep(LOGIC_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        logicThread.start();
    }

    private void updateGui() {
        if(gui != null) {
            gui.update(world);
        }
    }

    private void initNetwork() {
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
                if (object instanceof Player) {
                    myPlayer = (Player) object;
                    gui.registerMyPlayer(myPlayer);
                } else if (object instanceof World) {
                    world = (World) object;
                    refreshMyPlayer();
                    updateGui();
                }
            }
        });
        try {
            kryoClient.connect( TIMEOUT, ADDRESS, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshMyPlayer() {
        if(world == null) {
            return;
        }
        for(Player p : world.players) {
            if(p.id == myPlayer.id) {
                myPlayer = p;
            }
        }
    }
    
    /**
     * Set the move strategy.
     *  
     * @param strategy
     * 
     */
    public void setMoveStrategy(GameLogic strategy) {
    	this.strategy = strategy;
    }
}
