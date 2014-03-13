import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.Vector;

import net.scrummanager.minefield.Resources;
import net.scrummanager.minefield.controller.MinefieldController;

import org.junit.Test;

/**
 * 
 */

/**
 * @author scrummanager
 *
 */
public class MinefieldControllerTestCase {

	
	@Test
	public void testGetMinefieldLevelReturnsEasyLevelFromParameters() {
		MinefieldController mineController = new MinefieldController();
		Enumeration<String> param;
		Vector<String> parametros = new Vector<String>();
		parametros.add("level_1.");
		param=parametros.elements();
		assertEquals( Resources.EASY_LEVEL, 
				mineController.getMinefieldParameter(param, Resources.LEVEL));
	}

}
