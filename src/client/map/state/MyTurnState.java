package client.map.state;

import java.util.Random;

import client.clientFacade.ClientFacade;
import client.map.IMapView;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.mapClasses.Piece;

public class MyTurnState extends BaseState {

	public MyTurnState(IMapView view) {
		super(view);
		// TODO Auto-generated constructor stub
	}

	public void initFromModel() { 
		Random rand = new Random();

		for (int x = 0; x <= 3; ++x) {
			
			int maxY = 3 - x;			
			for (int y = -3; y <= maxY; ++y) {				
				//int r = rand.nextInt(HexType.values().length);
				//HexType hexType = HexType.values()[r];
				HexLocation hexLoc = new HexLocation(x, y);
				HexType hexType = ClientFacade.getInstance().getHex(hexLoc).getHexType();
				getView().addHex(hexLoc, hexType);
				
				EdgeLocation nwEdge = new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
				Piece nwEdgePiece = ClientFacade.getInstance().getEdge(nwEdge);
				if (nwEdgePiece != null) {
					int owner = nwEdgePiece.getOwner();
					getView().placeRoad(nwEdge,
							ClientFacade.getInstance().getColorById(owner));	
				}
				
				EdgeLocation nEdge = new EdgeLocation(hexLoc, EdgeDirection.North);
				Piece nEdgePiece = ClientFacade.getInstance().getEdge(nEdge);
				if (nEdgePiece != null) {
					int owner = nEdgePiece.getOwner();
					getView().placeRoad(nEdge,
							ClientFacade.getInstance().getColorById(owner));	
				}
				
				EdgeLocation neEdge = new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
				Piece neEdgePiece = ClientFacade.getInstance().getEdge(neEdge);
				if (neEdgePiece != null) {
					int owner = neEdgePiece.getOwner();
					getView().placeRoad(neEdge,
							ClientFacade.getInstance().getColorById(owner));	
				}
				
				EdgeLocation swEdge = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
				Piece swEdgePiece = ClientFacade.getInstance().getEdge(swEdge);
				if (swEdgePiece != null) {
					int owner = swEdgePiece.getOwner();
					getView().placeRoad(swEdge,
							ClientFacade.getInstance().getColorById(owner));	
				}
				
				EdgeLocation sEdge = new EdgeLocation(hexLoc, EdgeDirection.South);
				Piece sEdgePiece = ClientFacade.getInstance().getEdge(sEdge);
				if (sEdgePiece != null) {
					int owner = sEdgePiece.getOwner();
					getView().placeRoad(sEdge,
							ClientFacade.getInstance().getColorById(owner));	
				}
				
				EdgeLocation seEdge = new EdgeLocation(hexLoc, EdgeDirection.SouthEast);
				Piece seEdgePiece = ClientFacade.getInstance().getEdge(seEdge);
				if (seEdgePiece != null) {
					int owner = nwEdgePiece.getOwner();
					getView().placeRoad(seEdge,
							ClientFacade.getInstance().getColorById(owner));	
				}
				
				//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
					//	CatanColor.RED);
				//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
					//	CatanColor.BLUE);
				//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
					//	CatanColor.ORANGE);
				
				VertexLocation nwVertex = new VertexLocation(hexLoc,  VertexDirection.NorthWest);
				Piece nwVertexPiece = ClientFacade.getInstance().getVertex(nwVertex);
				if (nwVertexPiece != null) {
					int owner = nwVertexPiece.getOwner();
					getView().placeSettlement(nwVertex, 
							ClientFacade.getInstance().getColorById(owner));
				}
				
				VertexLocation neVertex = new VertexLocation(hexLoc,  VertexDirection.NorthEast);
				Piece neVertexPiece = ClientFacade.getInstance().getVertex(neVertex);
				if (neVertexPiece != null) {
					int owner = neVertexPiece.getOwner();
					getView().placeSettlement(neVertex, 
							ClientFacade.getInstance().getColorById(owner));
				}
				
				VertexLocation eVertex = new VertexLocation(hexLoc,  VertexDirection.East);
				Piece eVertexPiece = ClientFacade.getInstance().getVertex(eVertex);
				if (eVertexPiece != null) {
					int owner = eVertexPiece.getOwner();
					getView().placeSettlement(eVertex, 
							ClientFacade.getInstance().getColorById(owner));
				}
				
				VertexLocation seVertex = new VertexLocation(hexLoc,  VertexDirection.SouthEast);
				Piece seVertexPiece = ClientFacade.getInstance().getVertex(seVertex);
				if (seVertexPiece != null) {
					int owner = seVertexPiece.getOwner();
					getView().placeSettlement(seVertex, 
							ClientFacade.getInstance().getColorById(owner));
				}
				
				String sw = "SouthWest";
				VertexLocation swVertex = new VertexLocation(hexLoc,  VertexDirection.SouthWest);
				Piece swVertexPiece = ClientFacade.getInstance().getVertex(swVertex);
				if (swVertexPiece != null) {
					int owner = swVertexPiece.getOwner();
					if (swVertexPiece.getType().toString().equals("CITY")) {
						getView().placeCity(swVertex, 
								ClientFacade.getInstance().getColorById(owner));
					}
					else {
						getView().placeSettlement(swVertex, 
								ClientFacade.getInstance().getColorById(owner));
					}
					
				}
				
				VertexLocation wVertex = new VertexLocation(hexLoc,  VertexDirection.West);
				Piece wVertexPiece = ClientFacade.getInstance().getVertex(wVertex);
				if (wVertexPiece != null) {
					int owner = wVertexPiece.getOwner();
					getView().placeSettlement(wVertex, 
							ClientFacade.getInstance().getColorById(owner));
				}
				
				//getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
				//getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
			}
			
			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
					int r = rand.nextInt(HexType.values().length);
					HexType hexType = HexType.values()[r];
					HexLocation hexLoc = new HexLocation(-x, y);
					getView().addHex(hexLoc, hexType);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
							CatanColor.RED);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
							CatanColor.BLUE);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
							CatanColor.ORANGE);
					getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
					getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
				}
			}
		}
		
		PortType portType = PortType.BRICK;
		getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
		getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
		
		getView().placeRobber(new HexLocation(0, 0));
		
		getView().addNumber(new HexLocation(-2, 0), 2);
		getView().addNumber(new HexLocation(-2, 1), 3);
		getView().addNumber(new HexLocation(-2, 2), 4);
		getView().addNumber(new HexLocation(-1, 0), 5);
		getView().addNumber(new HexLocation(-1, 1), 6);
		getView().addNumber(new HexLocation(1, -1), 8);
		getView().addNumber(new HexLocation(1, 0), 9);
		getView().addNumber(new HexLocation(2, -2), 10);
		getView().addNumber(new HexLocation(2, -1), 11);
		getView().addNumber(new HexLocation(2, 0), 12);
	}
}
