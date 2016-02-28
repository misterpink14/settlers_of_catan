package client.devcards;

import shared.definitions.DevCardType;
import shared.definitions.GameState;
import shared.definitions.ResourceType;
import shared.models.cardClasses.InsufficientCardNumberException;
import shared.observers.DevCardObserver;

import javax.swing.JOptionPane;

import client.base.*;
import client.clientFacade.ClientFacade;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	private GameState state;
	private DevCardObserver obs;
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction) {

		super(view);
		
		this.obs = new DevCardObserver(this);	
		ClientFacade.getInstance().game.addObserver(obs);
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
	}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		if(state == GameState.MYTURN) {
			getBuyCardView().showModal();
		}
	}

	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() {
		
		getPlayCardView().showModal();
	}

	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		try {
			ClientFacade.getInstance().playDevCard(DevCardType.MONOPOLY);
		} catch (InsufficientCardNumberException e) {
			JOptionPane.showMessageDialog((PlayDevCardView)this.getPlayCardView(), "You cannot play this card now.");
		}
	}

	@Override
	public void playMonumentCard() {
		try {
			ClientFacade.getInstance().playDevCard(DevCardType.MONUMENT);
		} catch (InsufficientCardNumberException e) {
			JOptionPane.showMessageDialog((PlayDevCardView)this.getPlayCardView(), "You cannot play this card now.");
		}
	}

	@Override
	public void playRoadBuildCard() {
		try {
			ClientFacade.getInstance().playDevCard(DevCardType.ROAD_BUILD);
		} catch (InsufficientCardNumberException e) {
			JOptionPane.showMessageDialog((PlayDevCardView)this.getPlayCardView(), "You cannot play this card now.");
		}
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		try {
			ClientFacade.getInstance().playDevCard(DevCardType.SOLDIER);
		} catch (InsufficientCardNumberException e) {
			JOptionPane.showMessageDialog((PlayDevCardView)this.getPlayCardView(), "You cannot play this card now.");
		}
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		try {
			ClientFacade.getInstance().playDevCard(DevCardType.YEAR_OF_PLENTY);
		} catch (InsufficientCardNumberException e) {
			JOptionPane.showMessageDialog((PlayDevCardView)this.getPlayCardView(), "You cannot play this card now.");
		}
	}
	
	public void update(GameState state) {
		switch(state) {
		case MYTURN:
			this.state = GameState.MYTURN;
			break;
		default:
			this.state = GameState.NOTMYTURN;
			break;
		
		}
	}

}

