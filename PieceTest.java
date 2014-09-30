package model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Diego Maroto
 * @date 30/09/2014
 * Pasando todas estas pruebas aseguras el funcionamiento de:
 * - Piece.toString
 * - Piece.getAbsoluteCells
 * - Gameboard.toString
 * - Gameboard.removePiece
 * - Gameboard.putPiece
 * - Piece.rotateClockwise
 * - Gameboard.isPlaceValid
 **/
public class PieceTest {
	Piece p,p1;
	Gameboard g;
	@Before
	public void setUp() throws Exception {
		p = new Piece();
		p1 = new Piece();
		g = new Gameboard(new Coordinate(7,10));
	}
	@Test
	public final void testToString(){
		assertEquals("Piece.toString()",
				"····\n"
				+p.getBlockSymbol()+p.getBlockSymbol()+p.getBlockSymbol()+p.getBlockSymbol()+"\n"
				+"····\n"+"····\n",
				p.toString());
	}
	@Test
	public final void testGetAbsoluteCells() throws Exception{
		int coord = 2;
		Set<Coordinate> squares = p.getAbsoluteCells(new Coordinate(coord,coord));
		Set<Coordinate> squares2 = new HashSet<Coordinate>();
		squares2.add(new Coordinate(coord+1,coord));
		squares2.add(new Coordinate(coord+1,coord+1));
		squares2.add(new Coordinate(coord+1,coord+2));
		squares2.add(new Coordinate(coord+1,coord+3));
		for(Coordinate c : squares){
			//System.out.println(c);
		}
		if(4!=squares.size())
			throw new RuntimeException("Longitud erronea.");
		for(Coordinate c : squares){
			assertEquals("Coordenadas Absolutas "+c+":",true,squares2.contains(c));
		}
	}
	@Test
	public final void testGameboardToString(){
		assertEquals("Tablero vacio:",
				"··········\n"
				+ "··········\n"
				+ "··········\n"
				+ "··········\n"
				+ "··········\n"
				+ "··········\n"
				+ "··········\n",g.toString());
		Gameboard g1 = new Gameboard(new Coordinate(7,10));
		g1.putPiece(new Coordinate(1,1), p);
		assertEquals("Tablero con p:",
				"··········\n"
				+ "··········\n"
				+ "·▒▒▒▒·····\n"
				+ "··········\n"
				+ "··········\n"
				+ "··········\n"
				+ "··········\n",g1.toString());
		g1.putPiece(new Coordinate(3,1), p1);
		g1.removePiece(p);
		assertEquals("Tablero con p 2:",
				"··········\n"
				+ "··········\n"
				+ "··········\n"
				+ "··········\n"
				+ "·▒▒▒▒·····\n"
				+ "··········\n"
				+ "··········\n",g1.toString());
		Piece p2 = new Piece();
		p2.rotateClockwise();
		g1.putPiece(new Coordinate(0,0), p2);
		assertEquals("Tablero con p 3:",
				"··▒·······\n"
				+ "··▒·······\n"
				+ "··▒·······\n"
				+ "··▒·······\n"
				+ "·▒▒▒▒·····\n"
				+ "··········\n"
				+ "··········\n",g1.toString());
	}
	@Test
	public final void testValidPosition(){
		assertEquals("Posicion valida:",true,g.isPlaceValid(new Coordinate(1,1), p));
		assertEquals("Posicion invalida:",true,g.isPlaceValid(new Coordinate(5,6), p));
		assertEquals("Posicion invalida:",false,g.isPlaceValid(new Coordinate(5,7), p));
		assertEquals("Posicion invalida:",false,g.isPlaceValid(new Coordinate(6,6), p));
	}
}
