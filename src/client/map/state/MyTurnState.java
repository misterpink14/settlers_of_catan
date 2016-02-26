package client.map.state;

import java.util.ArrayList;
import java.util.Random;

import client.clientFacade.ClientFacade;
import client.map.IMapView;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.models.mapClasses.Piece;
import shared.models.mapClasses.WaterHex;

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

				
				//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
					//	CatanColor.RED);
				//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
					//	CatanColor.BLUE);
				//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
					//	CatanColor.ORANGE);
				
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
				
				getView().addNumber(hexLoc, ClientFacade.getInstance().getHex(hexLoc).getToken());
				//getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
				//getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
			}
			
			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
					HexLocation hexLoc = new HexLocation(x, y);
					HexType hexType = ClientFacade.getInstance().getHex(hexLoc).getHexType();
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

					
					//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
						//	CatanColor.RED);
					//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
						//	CatanColor.BLUE);
					//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
						//	CatanColor.ORANGE);
					
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
					
					getView().addNumber(hexLoc, ClientFacade.getInstance().getHex(hexLoc).getToken());
				}
			}
		}
		
		HexLocation hexLoc = new HexLocation(0, 3);
		WaterHex hex = (WaterHex) ClientFacade.getInstance().getHex(hexLoc);
		PortType portType = hex.getPortType();
		getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.North), portType);
		
		hexLoc = new HexLocation(0, -3);
		hex = (WaterHex) ClientFacade.getInstance().getHex(hexLoc);
		portType = hex.getPortType();
		getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.South), portType);
		
		hexLoc = new HexLocation(-3, 3);
		hex = (WaterHex) ClientFacade.getInstance().getHex(hexLoc);
		portType = hex.getPortType();
		getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.NorthEast), portType);
		
		hexLoc = new HexLocation(-3, 0);
		hex = (WaterHex) ClientFacade.getInstance().getHex(hexLoc);
		portType = hex.getPortType();
		getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.SouthEast), portType);
		
		hexLoc = new HexLocation(3, -3);
		hex = (WaterHex) ClientFacade.getInstance().getHex(hexLoc);
		portType = hex.getPortType();
		getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.SouthWest), portType);
		
		hexLoc = new HexLocation(3, 0);
		hex = (WaterHex) ClientFacade.getInstance().getHex(hexLoc);
		portType = hex.getPortType();
		getView().addPort(new EdgeLocation(hexLoc, EdgeDirection.NorthWest), portType);
		
		
		getView().placeRobber(ClientFacade.getInstance().getRobberLocation().getHexLoc());
		
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
	}
}
