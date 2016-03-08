package shared.communication.proxy;
import java.util.ArrayList;

import shared.definitions.ResourceType;
import shared.models.cardClasses.ResourceCards;

public class OfferTrade
{

	/**
	 * The index of the player offering the trade
	 */
	public int playerIndex;
	
	/**
	 * The index of the player receiving the offer
	 */
	public int receiverIndex;
	
	/**
	 * The resources the player wants to get
	 */
	public int brick;
	
	public int sheep;
	
	public int ore;
	
	public int wheat;
	
	public int wood;
	
	public OfferTrade(int playerIndex, int receiverIndex, ResourceCards resourceToSend, ResourceCards resourceToReceive) {
		this.playerIndex = playerIndex;
		this.receiverIndex = receiverIndex;
		brick = resourceToReceive.getBrickCards() - resourceToSend.getBrickCards();
		sheep = resourceToReceive.getSheepCards() - resourceToSend.getSheepCards();
		ore = resourceToReceive.getOreCards() - resourceToSend.getOreCards();
		wheat = resourceToReceive.getWheatCards() - resourceToSend.getWheatCards();
		wood = resourceToReceive.getWoodCards() - resourceToSend.getWoodCards();
	}
	
	public OfferTrade(int playerIndex, int receiverIndex, int brick, int sheep, int ore, int wheat, int wood) {
		this.playerIndex = playerIndex;
		this.receiverIndex = receiverIndex;
		this.brick = brick;
		this.sheep = sheep;
		this.ore = ore;
		this.wheat = wheat;
		this.wood = wood;
	}

	public OfferTrade() {
	}


	
}
