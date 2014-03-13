package net.scrummanager.minefield.controller;

import java.awt.Point;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.scrummanager.minefield.Minefield;
import net.scrummanager.minefield.Resources;

/**
 * Servlet implementation class MinefieldController
 */
public class MinefieldController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(
	 *          HttpServletRequest request, 
	 *          HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		doControl(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doControl(request, response);
	}

	protected void doControl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> parameters = request.getParameterNames();
		Point minefieldPosition = (Point) getMinefieldParameter(parameters, Resources.CELL);
		int minefieldLevel = (Integer) getMinefieldParameter(parameters, Resources.LEVEL);
		
		if (minefieldLevel == -1 && minefieldPosition == null) {
			request.getSession().setAttribute(Resources.GAME_NAME, 
					new Minefield(8, 10));
		} else if (minefieldLevel != -1 && minefieldPosition == null) {
			request.getSession().setAttribute(Resources.GAME_NAME, 
					new Minefield(minefieldLevel));
        } else {
			Minefield logic = (Minefield)request.getSession(
					).getAttribute(Resources.GAME_NAME);
			logic.check(minefieldPosition);
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}


	/**
	 * 
	 * @param parameters
	 * @param parameterType
	 * @return content of parameterType
	 */
	public final Object getMinefieldParameter(
			final Enumeration<String> parameters, 
			final String parameterType) {
		Object parameter = new Object();
		String parameterName = "";
		String[] minefieldParameter = null;
		while (parameters.hasMoreElements()) {
			parameterName = parameters.nextElement();
			if (parameterName.startsWith(parameterType)) {
				minefieldParameter = parameterName.substring(parameterType.length(),
						parameterName.indexOf(".")).split("_");
				if (minefieldParameter != null && minefieldParameter.length == 2) {
					parameter = new Point(Integer.parseInt(minefieldParameter[0]),
							Integer.parseInt(minefieldParameter[1]));
					break;
				} else if (minefieldParameter != null && minefieldParameter.length==1) {
					parameter = Integer.parseInt(minefieldParameter[0]);
				}
			}
		}

		return parameter;
	}

}