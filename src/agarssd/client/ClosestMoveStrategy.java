package agarssd.client;

import agarssd.model.MoveCommand;
import agarssd.model.Player;
import agarssd.model.World;
import agarssd.model.Item;

public class ClosestMoveStrategy implements GameLogic {

	private long lastCommand;

	public MoveCommand getNextMoveCommand(World world, Player myPlayer) {
        if(world == null) {
            return null;
        }
        
        MoveCommand command = new MoveCommand();
        
        Item minItem = this.getClosestItem(world, myPlayer);
        
        command.toX = minItem.positionX;
        command.toY = minItem.positionY;
//        System.out.printf("%f, %f\n", minItem.positionX, minItem.positionY);
        return command;
    }
	
	/**
	 * Find the closest item to the player.
	 * 
	 * @param world
	 * @param myPlayer
	 * @return closest item to player
	 */
	public Item getClosestItem(World world, Player myPlayer) {
		Item minItem = world.items.get(0);
		double minDistance = Math.sqrt(Math.pow(minItem.positionX - myPlayer.positionX, 2)
				+ Math.pow(minItem.positionY - myPlayer.positionY, 2));

		for (Item item : world.items) {
			double distance = Math.sqrt(Math.pow(item.positionX - myPlayer.positionX, 2)
					+ Math.pow(item.positionY - myPlayer.positionY, 2));
			if (distance < minDistance) {
				minItem = item;
				minDistance = distance;
			}
		}
		return minItem;
	}
}
