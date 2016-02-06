package shared.models.mapClasses;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;

import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.RobberLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.serializerJSON.Deserializer;
import org.junit.Assert.*;

public class MapTest {
	Map map;

	@Before
	public void setUp() throws Exception 
	{
		HexLocation hexLoc;
		
		// Build HexMap (Terrain Hex)
		Hex hex;
		HexMap hexMap = new HexMap();

		hexLoc = new HexLocation(0, -2);
		hex = new Hex(HexType.DESERT, -1);
		hexMap.setHex(hexLoc, hex);
		
		RobberLocation robber = new RobberLocation(hexLoc);

		hexLoc = new HexLocation(1, -2);
		hex = new Hex(HexType.BRICK, 4);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(2, -2);
		hex = new Hex(HexType.WOOD, 11);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(-1, -1);
		hex = new Hex(HexType.BRICK, 8);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(0, -1);
		hex = new Hex(HexType.WOOD, 3);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(1, -1);
		hex = new Hex(HexType.ORE, 9);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(2, -1);
		hex = new Hex(HexType.SHEEP, 12);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(-2, 0);
		hex = new Hex(HexType.ORE, 5);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(-1, 0);
		hex = new Hex(HexType.SHEEP, 10);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(0, 0);
		hex = new Hex(HexType.WHEAT, 11);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(1, 0);
		hex = new Hex(HexType.BRICK, 5);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(2, 0);
		hex = new Hex(HexType.WHEAT, 6);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(-2, 1);
		hex = new Hex(HexType.WHEAT, 2);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(-1, 1);
		hex = new Hex(HexType.SHEEP, 9);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(0, 1);
		hex = new Hex(HexType.WOOD, 4);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(1, 1);
		hex = new Hex(HexType.SHEEP, 10);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(-2, 2);
		hex = new Hex(HexType.WOOD, 6);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(-1, 2);
		hex = new Hex(HexType.ORE, 3);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(0, 2);
		hex = new Hex(HexType.WHEAT, 8);
		hexMap.setHex(hexLoc, hex);
		
		
		// Build Ports (WaterHex)
		
		hexLoc = new HexLocation(1, -3);
		hex = new WaterHex(PortType.ORE, EdgeDirection.South);
		hexMap.setHex(hexLoc, hex);
		
		hexLoc = new HexLocation(3, -3);
		hex = new WaterHex(PortType.THREE, EdgeDirection.SouthWest);
		hexMap.setHex(hexLoc, hex);
		
		hexLoc = new HexLocation(2, 1);
		hex = new WaterHex(PortType.THREE, EdgeDirection.NorthWest);
		hexMap.setHex(hexLoc, hex);
		
		hexLoc = new HexLocation(-2, 3);
		hex = new WaterHex(PortType.BRICK, EdgeDirection.NorthEast);
		hexMap.setHex(hexLoc, hex);
		
		hexLoc = new HexLocation(-1, -2);
		hex = new WaterHex(PortType.WHEAT, EdgeDirection.South);
		hexMap.setHex(hexLoc, hex);
		
		hexLoc = new HexLocation(-3, 2);
		hex = new WaterHex(PortType.WOOD, EdgeDirection.NorthEast);
		hexMap.setHex(hexLoc, hex);
		
		hexLoc = new HexLocation(-3, 0);
		hex = new WaterHex(PortType.THREE, EdgeDirection.SouthEast);
		hexMap.setHex(hexLoc, hex);
		
		hexLoc = new HexLocation(3, -1);
		hex = new WaterHex(PortType.SHEEP, EdgeDirection.NorthWest);
		hexMap.setHex(hexLoc, hex);
		
		hexLoc = new HexLocation(0, 3);
		hex = new WaterHex(PortType.THREE, EdgeDirection.North);
		hexMap.setHex(hexLoc, hex);
		
		
		// Build remaining WaterHex
		
		hexLoc = new HexLocation(-3, 1);
		hex = new WaterHex(null, null);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(-3, 3);
		hex = new WaterHex(null, null);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(-2, -1);
		hex = new WaterHex(null, null);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(-1, 3);
		hex = new WaterHex(null, null);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(3, 0);
		hex = new WaterHex(null, null);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(3, -2);
		hex = new WaterHex(null, null);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(2, -3);
		hex = new WaterHex(null, null);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(1, 2);
		hex = new WaterHex(null, null);
		hexMap.setHex(hexLoc, hex);

		hexLoc = new HexLocation(0, -3);
		hex = new WaterHex(null, null);
		hexMap.setHex(hexLoc, hex);
		
		
		/*
		 -3, 1
		 -3, 3
		 -2, -1
		 -1. 3
		 3, -0
		 3, -2
		 2, -3
		 1, 2
		 0, -3
		 
		 -3, 0 -
		 -3, 2 -
		 -2, 3 -
		 -1, -2 -
		 3, -1 -
		 3, -3 -
		 2, 1 -
		 1. -3 -
		 0, 3 -
		 */
		
		// EdgeMap
		
		PlayerMap pm = new PlayerMap();
		
		EdgeLocation edgeLoc;
		Piece p;
		EdgeMap edges = new EdgeMap();
		
		hexLoc = new HexLocation(-1, -1);
		edgeLoc = new EdgeLocation(hexLoc, EdgeDirection.South);
		p = new Piece();
		p.setType(PieceType.ROAD);
		p.setOwner(3);
		edges.setEdge(edgeLoc, p);
		pm.addRoad(edgeLoc, 3);
		
		hexLoc = new HexLocation(-1, 1);
		edgeLoc = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
		p = new Piece();
		p.setType(PieceType.ROAD);
		p.setOwner(3);
		edges.setEdge(edgeLoc, p);
		pm.addRoad(edgeLoc, 3);
		
		hexLoc = new HexLocation(2, -2);
		edgeLoc = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
		p = new Piece();
		p.setType(PieceType.ROAD);
		p.setOwner(3);
		edges.setEdge(edgeLoc, p);
		pm.addRoad(edgeLoc, 3);
		
		hexLoc = new HexLocation(1, -1);
		edgeLoc = new EdgeLocation(hexLoc, EdgeDirection.South);
		p = new Piece();
		p.setType(PieceType.ROAD);
		p.setOwner(2);
		edges.setEdge(edgeLoc, p);
		pm.addRoad(edgeLoc, 2);
		
		hexLoc = new HexLocation(0, 1);
		edgeLoc = new EdgeLocation(hexLoc, EdgeDirection.South);
		p = new Piece();
		p.setType(PieceType.ROAD);
		p.setOwner(0);
		edges.setEdge(edgeLoc, p);
		pm.addRoad(edgeLoc, 0);
		
		hexLoc = new HexLocation(0, 0);
		edgeLoc = new EdgeLocation(hexLoc, EdgeDirection.South);
		p = new Piece();
		p.setType(PieceType.ROAD);
		p.setOwner(2);
		edges.setEdge(edgeLoc, p);
		pm.addRoad(edgeLoc, 2);
		
		hexLoc = new HexLocation(-2, 1);
		edgeLoc = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
		p = new Piece();
		p.setType(PieceType.ROAD);
		p.setOwner(1);
		edges.setEdge(edgeLoc, p);
		pm.addRoad(edgeLoc, 1);
		
		hexLoc = new HexLocation(2, 0);
		edgeLoc = new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
		p = new Piece();
		p.setType(PieceType.ROAD);
		p.setOwner(0);
		edges.setEdge(edgeLoc, p);
		pm.addRoad(edgeLoc, 0);
		
		
		// Set Vertexes
		
		VertexLocation vertexLoc;
		VertexMap vertexes = new VertexMap();
		
		hexLoc = new HexLocation(-1, 1);
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		p = new Piece();
		p.setType(PieceType.SETTLEMENT);
		p.setOwner(3);
		vertexes.setVertex(vertexLoc, p);
		pm.addSettlement(vertexLoc, 3);
		
		hexLoc = new HexLocation(1, -2);
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthEast);
		p = new Piece();
		p.setType(PieceType.SETTLEMENT);
		p.setOwner(3);
		vertexes.setVertex(vertexLoc, p);
		pm.addSettlement(vertexLoc, 3);
		
		hexLoc = new HexLocation(0, 0);
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		p = new Piece();
		p.setType(PieceType.SETTLEMENT);
		p.setOwner(2);
		vertexes.setVertex(vertexLoc, p);
		pm.addSettlement(vertexLoc, 2);
		
		hexLoc = new HexLocation(1, -1);
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		p = new Piece();
		p.setType(PieceType.SETTLEMENT);
		p.setOwner(2);
		vertexes.setVertex(vertexLoc, p);
		pm.addSettlement(vertexLoc, 2);
		
		hexLoc = new HexLocation(-2, 1);
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		p = new Piece();
		p.setType(PieceType.SETTLEMENT);
		p.setOwner(1);
		vertexes.setVertex(vertexLoc, p);
		pm.addSettlement(vertexLoc, 1);
		
		hexLoc = new HexLocation(0, 1);
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthEast);
		p = new Piece();
		p.setType(PieceType.SETTLEMENT);
		p.setOwner(0);
		vertexes.setVertex(vertexLoc, p);
		pm.addSettlement(vertexLoc, 0);
		
		hexLoc = new HexLocation(-1, -1);
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		p = new Piece();
		p.setType(PieceType.SETTLEMENT);
		p.setOwner(1);
		vertexes.setVertex(vertexLoc, p);
		pm.addSettlement(vertexLoc, 1);
		
		hexLoc = new HexLocation(2, 0);
		vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthWest);
		p = new Piece();
		p.setType(PieceType.SETTLEMENT);
		p.setOwner(0);
		vertexes.setVertex(vertexLoc, p);
		pm.addSettlement(vertexLoc, 0);
		

		this.map = new Map(hexMap, vertexes, edges, robber, pm);
		
	}

	@After
	public void tearDown() throws Exception 
	{
		this.map = null;
	}


	@Test
	public void testCanMaritimeTrade()
	{
		assertFalse("Error: should be false", this.map.canMaritimeTrade(0, ResourceType.BRICK));
		assertFalse("Error: should be false", this.map.canMaritimeTrade(0, ResourceType.ORE));
		assertFalse("Error: should be false", this.map.canMaritimeTrade(0, ResourceType.SHEEP));
		assertFalse("Error: should be false", this.map.canMaritimeTrade(0, ResourceType.WHEAT));
		assertFalse("Error: should be false", this.map.canMaritimeTrade(0, ResourceType.WOOD));
		assertTrue("Error: should be false", this.map.canMaritimeTrade(0, null));
		
		// Add a Settlement to a harbor
		HexLocation hexLoc = new HexLocation(-2, 3);
		VertexLocation vertexLoc = new VertexLocation(hexLoc, VertexDirection.NorthEast);
		Piece p = new Piece();
		p.setType(PieceType.SETTLEMENT);
		p.setOwner(0);
		try {
			this.map.setVertex(vertexLoc, p);
		} catch (InvalidTypeException e) {
			fail("InvalidTypeException");
		}
		this.map.addSettlementToPlayerMap(vertexLoc, 0);
		assertTrue("Error: should be true", this.map.canMaritimeTrade(0, ResourceType.BRICK));	
	}
	
	
//
//	@Test
//	public void testCanPlaceCity()
//	{
//		fail("unimplemented");
//	}
//
//	@Test
//	public void testCanPlaceSettlement()
//	{
//		fail("unimplemented");// Add ore and check
//	HexLocation hexLoc = new HexLocation(1, -2);
//	VertexLocation vertexLoc = new VertexLocation(hexLoc, VertexDirection.SouthWest);
//	Piece p = new Piece();
//	p.setType(PieceType.SETTLEMENT);
//	p.setOwner(0);
//	try {
//		this.map.setVertex(vertexLoc, p);
//	} catch (InvalidTypeException e) {
//		fail("InvalidTypeException");
//	}
//	this.map.addSettlementToPlayerMap(vertexLoc, 0);
//	assertTrue("Error: should be true", this.map.canMaritimeTrade(0, ResourceType.ORE));
//	}
//
//	@Test
//	public void testCanPlaceRoad()
//	{
//		fail("unimplemented");
//	}
//
//	@Test
//	public void testCanPlaceRobber()
//	{
//		fail("unimplemented");
//	}

}