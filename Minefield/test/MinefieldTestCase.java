import static org.junit.Assert.*;

import java.awt.Point;

import net.scrummanager.minefield.Minefield;
import net.scrummanager.minefield.Resources;

import org.junit.Test;


public class MinefieldTestCase {

	@Test
	public void testInitMinefieldIsLostFalse() {
		int dimension = 3;
		int mines = 7;
		Minefield minefield = new Minefield(dimension, mines);
		assertFalse(minefield.isLost());
	}
	
	@Test
	public void testInitMinefieldIsWinFalse() {
		int dimension = 3;
		int mines = 7;
		Minefield minefield = new Minefield(dimension, mines);
		assertFalse(minefield.isWin());
	}
	
	@Test
	public void testFourDimensionCheckPositionWithFullMineIsLostTrue() {
		int dimension = 4;
		int mines = 16;
		int xpoint = 0;
		int ypoint = 0;
		Minefield minefield = new Minefield(dimension, mines);
		Point p = new Point(xpoint, ypoint);
		minefield.check(p);
		assertTrue(minefield.isLost());
	}

	@Test
	public void testThreeDimensionCheckPositionWithZeroMinesIsLostFalse() {
		int dimension = 3;
		int mines = 0;
		int xpoint = 2;
		int ypoint = 1;
		Minefield minefield = new Minefield(dimension, mines);
		Point p = new Point(xpoint, ypoint);
		minefield.check(p);
		assertFalse(minefield.isLost());
	}
	
	@Test
	public void testOneDimensionCheckPositionWithZeroMinesIsWinTrue() {
		int dimension = 1;
		int mines = 0;
		int xpoint = 0;
		int ypoint = 0;
		Minefield minefield = new Minefield(dimension, mines);
		Point p = new Point(xpoint, ypoint);
		minefield.check(p);
		assertTrue(minefield.isWin());
	}
	
	@Test
	public void testTwoDimensionCheckPositionWithZeroMinesIsWinFalse() {
		int dimension = 2;
		int mines = 0;
		int xpoint = 1;
		int ypoint = 0;
		Minefield minefield = new Minefield(dimension, mines);
		Point p = new Point(xpoint, ypoint);
		minefield.check(p);
		assertFalse(minefield.isWin());
	}
	
	@Test
	public void testInitMinefieldToDificultyHardReturnsHardLevel() {
		int level = 3;
		Minefield minefield = new Minefield(level);
		assertEquals(Resources.HARD_LEVEL, minefield.getLevel());
	}
	
	@Test
	public void testInitMinefieldToEasyReturnsEasyMines() {
		int easy_level = 1;
		Minefield minefield = new Minefield(easy_level);
		assertEquals(Resources.EASY_MINES, minefield.getNumberOfMines());
	}
	
	@Test
	public void testInitMinefieldToMediumReturnsMediumDimension() {
		int level = 2;
		Minefield minefield = new Minefield(level);
		assertEquals(Resources.MEDIUM_DIMENSION, minefield.getDimension());
	}
}
