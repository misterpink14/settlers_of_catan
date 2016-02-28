package client.map;

import client.base.Controller;
import client.clientFacade.ClientFacade;
import client.data.RobPlayerInfo;
import client.map.state.BaseState;
import client.map.state.EndOfGameState;
import client.map.state.LoginState;
import client.map.state.MyTurnState;
import client.map.state.NotMyTurnState;
import client.map.state.RobberState;
import client.map.state.Setup1State;
import client.map.state.Setup2State;
import client.map.state.TradeAcceptState;
import client.map.state.TradeOfferState;
import client.map.state.WaitingState;
import shared.definitions.CatanColor;
import shared.definitions.GameState;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.observers.MapObserver;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController {
	
	private IRobView robView;
	private BaseState state;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		MapObserver obs = new MapObserver(this);
		ClientFacade.getInstance().game.addObserver(obs);
		
		setRobView(robView);
		
		this.setState(GameState.LOGIN);
		
		initFromModel();
		
	}
	
	
	
// SETTERS
	public void setState (BaseState state)
	{
		this.state = state;
	}
	
	
	public void setState (GameState gameState)
	{
		switch(gameState)
		{
			case SETUP1:
				if (this.state.getClass() != Setup1State.class)
				{
					this.state = new Setup1State(this.getView());
				}
				break;
			case SETUP2:
				this.state = new Setup2State(this.getView());
				break;
			case MYTURN:
				this.state = new MyTurnState(this.getView());
				break;
			case NOTMYTURN:
				this.state = new NotMyTurnState(this.getView());
				break;
			case ROBBER:
				this.state = new RobberState(this.getView(), this.getRobView());
				break;
			case TRADEOFFER:
				this.state = new TradeOfferState(this.getView());
				break;
			case TRADEACCEPT:
				this.state = new TradeAcceptState(this.getView());
				break;
			case OUTDATED:
			case DISCARD:
				this.state = new WaitingState(this.getView());
				break;
			case ENDOFGAME:
				this.state = new EndOfGameState(this.getView());
				break;
			case LOGIN:
			case ROLLING:
			default:
				this.state = new LoginState(this.getView());
		}
		System.out.println(this.state.toString());
	}
	
	
	private void setRobView(IRobView robView) 
	{
		this.robView = robView;
	}
	
	
	
// GETTERS
	public BaseState getState ()
	{
		return this.state;
	}
	
	
	public IMapView getView()
	{
		return (IMapView)super.getView();
	}
	
	
	private IRobView getRobView() {
		return robView;
	}
	
	
	
// Protected METHODS
	public void update(GameState gameState)
	{
		this.setState(gameState);
		this.initFromModel();
	}
	
	protected void initFromModel() {
		
		this.state.initFromModel();
		//<temp>
		
//		Random rand = new Random();
//
//		for (int x = 0; x <= 3; ++x) {
//			
//			int maxY = 3 - x;			
//			for (int y = -3; y <= maxY; ++y) {				
//				int r = rand.nextInt(HexType.values().length);
//				HexType hexType = HexType.values()[r];
//				HexLocation hexLoc = new HexLocation(x, y);
//				getView().addHex(hexLoc, hexType);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//						CatanColor.RED);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//						CatanColor.BLUE);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//						CatanColor.ORANGE);
//				getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
//				getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
//			}
//			
//			if (x != 0) {
//				int minY = x - 3;
//				for (int y = minY; y <= 3; ++y) {
//					int r = rand.nextInt(HexType.values().length);
//					HexType hexType = HexType.values()[r];
//					HexLocation hexLoc = new HexLocation(-x, y);
//					getView().addHex(hexLoc, hexType);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//							CatanColor.RED);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//							CatanColor.BLUE);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//							CatanColor.ORANGE);
//					getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
//					getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
//				}
//			}
//		}
//		
//		PortType portType = PortType.BRICK;
//		getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
//		
//		getView().placeRobber(new HexLocation(0, 0));
//		
//		getView().addNumber(new HexLocation(-2, 0), 2);
//		getView().addNumber(new HexLocation(-2, 1), 3);
//		getView().addNumber(new HexLocation(-2, 2), 4);
//		getView().addNumber(new HexLocation(-1, 0), 5);
//		getView().addNumber(new HexLocation(-1, 1), 6);
//		getView().addNumber(new HexLocation(1, -1), 8);
//		getView().addNumber(new HexLocation(1, 0), 9);
//		getView().addNumber(new HexLocation(2, -2), 10);
//		getView().addNumber(new HexLocation(2, -1), 11);
//		getView().addNumber(new HexLocation(2, 0), 12);
		
		//</temp>
	}

	
	
// Public METHODS
	public boolean canPlaceRoad(EdgeLocation edgeLoc) 
	{
		System.out.println("canPlaceRoad");
		return this.state.canPlaceCity(edgeLoc);
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) 
	{
		System.out.println("canPlaceSettlement");
		return this.state.canPlaceSettlement(vertLoc);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {

		System.out.println("canPlaceCity");
		return this.state.canPlaceCity(vertLoc);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {

		System.out.println("canPlaceRobber");
		return this.state.canPlaceRobber(hexLoc);
	}

	public void placeRoad(EdgeLocation edgeLoc) 
	{
		System.out.println("placeRoad");
		this.state.placeRoad(edgeLoc);
	}

	public void placeSettlement(VertexLocation vertLoc) {

		System.out.println("placeSettlement");
		this.state.placeSettlement(edgeLoc);
	}

	public void placeCity(VertexLocation vertLoc) {

		System.out.println("placeCity");
		this.state.placeCity(vertLoc);
	}

	public void placeRobber(HexLocation hexLoc) {

		System.out.println("placeRobber");
		this.state.placeRobber(hexLoc);
	}
	
	// allowConnected == true for setup1 and setup2 ======== isFree == true for setup1 and setup2
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		
		this.state.startMove(pieceType, isFree, allowDisconnected);
	}
	
	public void cancelMove() {
		
		this.state.cancelMove();
	}
	
	public void playSoldierCard() {	
		
		this.state.playSoldierCard();
	}
	
	public void playRoadBuildingCard() {	
		
		this.state.playRoadBuildingCard();
	}
	
	public void robPlayer(RobPlayerInfo victim) {	
		
		this.state.robPlayer(victim);
	}
	
}

