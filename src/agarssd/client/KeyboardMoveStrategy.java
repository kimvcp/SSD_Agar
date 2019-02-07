package agarssd.client;

import agarssd.model.MoveCommand;
import agarssd.model.Player;
import agarssd.model.World;

public class KeyboardMoveStrategy implements GameLogic {
	
	public int facing = 0;

	public MoveCommand getNextMoveCommand(World world, Player myPlayer) {
		if (world == null) {
			return null;
		}

		MoveCommand command = new MoveCommand();

		switch (facing) {
		case 0: // Left
			command.toX = 0;
			command.toY = myPlayer.positionY;
			break;
		case 1: // Up
			command.toX = myPlayer.positionX;
			command.toY = 0;
			break;
		case 2: // Right
			command.toX = world.size;
			command.toY = myPlayer.positionY;
			break;
		case 3: // Down
			command.toX = myPlayer.positionX;
			command.toY = world.size;
			break;
		}
		return command;
	}
}
