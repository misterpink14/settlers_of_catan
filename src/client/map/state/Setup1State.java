package client.map.state;

import java.util.ArrayList;

import client.clientFacade.ClientFacade;
import client.map.IMapView;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.mapClasses.Piece;
import shared.models.mapClasses.WaterHex;

/**
 * This is the first phase of Setup. Players take turns in order and
 * 	can only place one settlement with an adjoining road.	
 * 		Transitions to NotMyTurn
 * 
 */
public class Setup1State extends BaseState {
	

	boolean isFree = true, isSetup = true;

	public Setup1State(IMapView view) {
		super(view);
		this.color = ClientFacade.getInstance().getUserColor();
	}

	@Override
	public void initFromModel() { 
		for (int x = 0; x <= 3; ++x) {
			
			int maxY = 3 - x;			
			for (int y = -3; y <= maxY; ++y) {				

				HexLocation hexLoc = new HexLocation(x, y);
				HexType hexType;
				if (ClientFacade.getInstance().getHex(hexLoc) == null) {
					hexType = HexType.WATER;
				}
				else {
					hexType = ClientFacade.getInstance().getHex(hexLoc).getHexType();
					if (hexType.equals(HexType.WATER)) {
						WaterHex hex = (WaterHex) ClientFacade.getInstance().getHex(hexLoc);
						PortType portType = hex.getPortType();
						if (x > 0) {
							if (y <= 0) {
								getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.SouthWest), portType);
							}
							else {
								getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.NorthWest), portType);
							}
						}
						else {
							if (y <= 0) {
								getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.South), portType);
							}
							else {
								getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.North), portType);
							}
						}
					}
				}
				getView().addHex(hexLoc, hexType);
				
				ArrayList<EdgeLocation> edges = new ArrayList<EdgeLocation>();
				
				EdgeLocation nwEdge = new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
				edges.add(nwEdge);
				EdgeLocation nEdge = new EdgeLocation(hexLoc, EdgeDirection.North);
				edges.add(nEdge);
				EdgeLocation neEdge = new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
				edges.add(neEdge);
				EdgeLocation swEdge = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
				edges.add(swEdge);
				EdgeLocation sEdge = new EdgeLocation(hexLoc, EdgeDirection.South);
				edges.add(sEdge);
				EdgeLocation seEdge = new EdgeLocation(hexLoc, EdgeDirection.SouthEast);
				edges.add(seEdge);
				
				for (EdgeLocation edgeLoc : edges) {
					Piece edgePiece = ClientFacade.getInstance().getEdge(edgeLoc);
					if (edgePiece != null) {
						int owner = edgePiece.getOwner();
						getView().placeRoad(edgeLoc,
								ClientFacade.getInstance().getColorById(owner));	
					}
				}
				
				ArrayList<VertexLocation> vertices = new ArrayList<VertexLocation>();
					
				VertexLocation nwVertex = new VertexLocation(hexLoc,  VertexDirection.NorthWest);
				vertices.add(nwVertex);
				VertexLocation neVertex = new VertexLocation(hexLoc,  VertexDirection.NorthEast);
				vertices.add(neVertex);
				VertexLocation eVertex = new VertexLocation(hexLoc,  VertexDirection.East);
				vertices.add(eVertex);
				VertexLocation seVertex = new VertexLocation(hexLoc,  VertexDirection.SouthEast);
				vertices.add(seVertex);
				VertexLocation swVertex = new VertexLocation(hexLoc,  VertexDirection.SouthWest);
				vertices.add(swVertex);
				VertexLocation wVertex = new VertexLocation(hexLoc,  VertexDirection.West);
				vertices.add(wVertex);
				
				for (VertexLocation vertex: vertices) {
					Piece vertexPiece = ClientFacade.getInstance().getVertex(vertex);
					if (vertexPiece != null) {
						int owner = vertexPiece.getOwner();
						if (vertexPiece.getType().toString().equals("CITY")) {
							getView().placeCity(vertex, 
									ClientFacade.getInstance().getColorById(owner));
						}
						else {
							getView().placeSettlement(vertex, 
									ClientFacade.getInstance().getColorById(owner));
						}
						
					}
				}
				
				if (ClientFacade.getInstance().getHex(hexLoc) != null) {
					if (ClientFacade.getInstance().getHex(hexLoc).getToken() != -1) {
						getView().addNumber(hexLoc, ClientFacade.getInstance().getHex(hexLoc).getToken());
					}
				}
			}
			
			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
					HexLocation hexLoc = new HexLocation(-x, y);
					HexType hexType;
					if (ClientFacade.getInstance().getHex(hexLoc) == null) {
						hexType = HexType.WATER;
					}
					else {
						hexType = ClientFacade.getInstance().getHex(hexLoc).getHexType();
						if (hexType.equals(HexType.WATER)) {
							WaterHex hex = (WaterHex) ClientFacade.getInstance().getHex(hexLoc);
							PortType portType = hex.getPortType();
							if (-x < 0) {
								if (y <= 0) {
									getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.SouthEast), portType);
								}
								else {
									getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.NorthEast), portType);
								}
							}
						}
					}
					
					getView().addHex(hexLoc, hexType);
					
					ArrayList<EdgeLocation> edges = new ArrayList<EdgeLocation>();
					
					EdgeLocation nwEdge = new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
					edges.add(nwEdge);
					EdgeLocation nEdge = new EdgeLocation(hexLoc, EdgeDirection.North);
					edges.add(nEdge);
					EdgeLocation neEdge = new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
					edges.add(neEdge);
					EdgeLocation swEdge = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
					edges.add(swEdge);
					EdgeLocation sEdge = new EdgeLocation(hexLoc, EdgeDirection.South);
					edges.add(sEdge);
					EdgeLocation seEdge = new EdgeLocation(hexLoc, EdgeDirection.SouthEast);
					edges.add(seEdge);
					
					for (EdgeLocation edgeLoc : edges) {
						Piece edgePiece = ClientFacade.getInstance().getEdge(edgeLoc);
						if (edgePiece != null) {
							int owner = edgePiece.getOwner();
							getView().placeRoad(edgeLoc,
									ClientFacade.getInstance().getColorById(owner));	
						}
					}
					
					ArrayList<VertexLocation> vertices = new ArrayList<VertexLocation>();
						
					VertexLocation nwVertex = new VertexLocation(hexLoc,  VertexDirection.NorthWest);
					vertices.add(nwVertex);
					VertexLocation neVertex = new VertexLocation(hexLoc,  VertexDirection.NorthEast);
					vertices.add(neVertex);
					VertexLocation eVertex = new VertexLocation(hexLoc,  VertexDirection.East);
					vertices.add(eVertex);
					VertexLocation seVertex = new VertexLocation(hexLoc,  VertexDirection.SouthEast);
					vertices.add(seVertex);
					VertexLocation swVertex = new VertexLocation(hexLoc,  VertexDirection.SouthWest);
					vertices.add(swVertex);
					VertexLocation wVertex = new VertexLocation(hexLoc,  VertexDirection.West);
					vertices.add(wVertex);
					
					for (VertexLocation vertex: vertices) {
						Piece vertexPiece = ClientFacade.getInstance().getVertex(vertex);
						if (vertexPiece != null) {
							int owner = vertexPiece.getOwner();
							if (vertexPiece.getType().toString().equals("CITY")) {
								getView().placeCity(vertex, 
										ClientFacade.getInstance().getColorById(owner));
							}
							else {
								getView().placeSettlement(vertex, 
										ClientFacade.getInstance().getColorById(owner));
							}
							
						}
					}
					
					if (ClientFacade.getInstance().getHex(hexLoc) != null) {
						if (ClientFacade.getInstance().getHex(hexLoc).getToken() != -1) {
							getView().addNumber(hexLoc, ClientFacade.getInstance().getHex(hexLoc).getToken());
						}
					}
				}
			}
		}
		
		getView().placeRobber(ClientFacade.getInstance().getRobberLocation().getHexLoc());
		
		if (!this.hasRenderedOverlay) {
			this.hasRenderedOverlay = true;
			
			if (ClientFacade.getInstance().game.getPlayers().getPlayerByIndex(ClientFacade.getInstance().getUserData().getPlayerIndex()).getRoads() == 15) {
				this.startMove(PieceType.ROAD, isFree, false);
			}
			else if (ClientFacade.getInstance().game.getPlayers().getPlayerByIndex(ClientFacade.getInstance().getUserData().getPlayerIndex()).getSettlements() == 5) {
				this.startMove(PieceType.SETTLEMENT, isFree, false);
			}
		}
	}


	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		return ClientFacade.getInstance().canBuildRoad(edgeLoc, this.isFree, this.isSetup);
	}


	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		return ClientFacade.getInstance().canBuildSettlement(vertLoc);
	}

	
	@Override
	public void placeRoad(EdgeLocation edgeLoc) {
		try {
			ClientFacade.getInstance().buildRoad(edgeLoc, this.isFree, this.isSetup);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		getView().placeRoad(edgeLoc, this.color);
		
		this.startMove(PieceType.SETTLEMENT, isFree, false);
	}


	@Override
	public void placeSettlement(VertexLocation vertLoc) {
		try {
			ClientFacade.getInstance().buildSettlement(vertLoc, this.isFree, this.isSetup);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		getView().placeSettlement(vertLoc, this.color);
	}
	

	@Override
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {
		
		getView().startDrop(pieceType, this.color, false);
	}
}
