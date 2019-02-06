package agarssd.client;

import agarssd.model.MoveCommand;
import agarssd.model.Player;
import agarssd.model.World;

public interface GameLogic{

    public MoveCommand getNextMoveCommand(World world, Player myPlayer) ;
}
