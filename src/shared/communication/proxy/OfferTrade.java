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
		brick = resourceToSend.getBrickCards() + resourceToReceive.getBrickCards();
		sheep = resourceToSend.getSheepCards() + resourceToReceive.getSheepCards();
		ore = resourceToSend.getBrickCards() + resourceToReceive.getBrickCards();
		wheat = resourceToSend.getWheatCards() + resourceToReceive.getWheatCards();
		wood = resourceToSend.getWoodCards() + resourceToReceive.getWoodCards();
	}

	public OfferTrade() {
	}


	
}
