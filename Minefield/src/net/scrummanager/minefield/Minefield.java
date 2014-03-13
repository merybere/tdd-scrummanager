package net.scrummanager.minefield;

import java.awt.Point;

public class Minefield {
	
	private int _dimension;
	private int _numberOfMines;
	private int _level;
	

	private boolean _lost;
	private boolean _win;

	public boolean isLost() {
		return _lost;
	}
	
	public boolean isWin() {
		return _win;
	}
	
	private int[][] _minefieldMatrix;
	private int[][] _checkedMatrix;
	
	public int getDimension() {
		return _dimension;
	}

	public void setDimension(int dimension) {
		_dimension = dimension;
	}

	public int getNumberOfMines() {
		return _numberOfMines;
	}

	public int getLevel() {
		return _level;
	}
	
	public void setLevel(int _level) {
		this._level = _level;
	}

	public void setNumberOfMines(int numberOfMines) {
		_numberOfMines = numberOfMines;
	}

	public Minefield(int dimension, int numberOfMines) {
		
		initMinefield(dimension, numberOfMines);
		
		start();
	}
	
	public Minefield(int level) {
		
		setLevel(level);
		
		switch (level) {
		case Resources.EASY_LEVEL:
			initMinefield(Resources.EASY_DIMENSION, Resources.EASY_MINES);
			break;
		case Resources.MEDIUM_LEVEL:
			initMinefield(Resources.MEDIUM_DIMENSION, Resources.MEDIUM_MINES);
			break;
		case Resources.HARD_LEVEL:
			initMinefield(Resources.HARD_DIMENSION, Resources.HARD_MINES);
			break;
		default:
			break;
		}
		
		start();
	}

	/**
	 * @param dimension number of vertical and horizontal cells in Minefield
	 * @param mines number of initial mines in Minefield
	 * 
	 */
	private void initMinefield(int dimension, int mines) {
		setDimension(dimension);
		setNumberOfMines(mines);
	}

	public void start() {
		_minefieldMatrix = new int[_dimension][_dimension];
		_checkedMatrix = new int[_dimension][_dimension];
		putMinesInMinefield();
	}
	
	private void putMinesInMinefield() {
		int x;
		int y;
		
		for (int i=0;i<_numberOfMines;i++) {
			do {
				x = (int)(Math.random()*_dimension);
				y = (int)(Math.random()*_dimension);
			} while(_minefieldMatrix[x][y]!=0);
			_minefieldMatrix[x][y] = 1;
		}
	}
	
	public boolean isMine(Point minefieldPosition) {
		if (minefieldPosition.x>-1 && minefieldPosition.y>-1 && minefieldPosition.x<_dimension && minefieldPosition.y<_dimension) {
			return (_minefieldMatrix[minefieldPosition.x][minefieldPosition.y]==1);
		} else {
			return false;
		}
	}
	
	public int getNumberOfMinesNextTo(Point minefieldPosition) {
		int _return = -1;
		
		if (!isMine(minefieldPosition)) {
			_return = 0;
			for (int i=-1;i<=1;i++) {
				for (int j=-1;j<=1;j++) {
					if (isMine(new Point(minefieldPosition.x+i, minefieldPosition.y+j))) {
						_return++;
					}
				}
			}
		}
		
		return _return;
	}
	
	public String getBoardAsHtml(String contextPath) {
		StringBuffer _return = new StringBuffer("");
		_return.append("<table>");
		int numberOfMines;
	    for (int i=0;i<getDimension();i++) {
			_return.append("<tr>");
			for (int j=0;j<getDimension();j++) {
				_return.append("<td>");
				if (_checkedMatrix[i][j]==0) {
					_return.append("<input type=\"image\" width=\"20\" id=\"cell\" name=\"cell_" + i+"_"+j + "\" alt=\"cell_" + i + "_" + j + "\" src=\"" + contextPath + "/images/unknow.jpg\">");
				} else {
					if (isMine(new Point(i,j))) {
						_return.append("<img width=\"20\" src=\"" + contextPath + "/images/mine.jpg\" alt=\"mine\"/>");
					} else if (true) {
						numberOfMines = getNumberOfMinesNextTo(new Point(i,j));
						_return.append("<img width=\"20\" alt=\"" + numberOfMines + "\" src=\"" + contextPath + "/images/" + numberOfMines + ".jpg\" />");
					}
				}
				_return.append("</td>");
			}
			_return.append("</tr>");
	    }
	    _return.append("</table>");
		return _return.toString();
	}
	
	public void check(Point checkPosition) {
		if (isMine(checkPosition)) {
			_lost = true;
			for (int i=0;i<_dimension;i++) {
				for (int j=0;j<_dimension;j++) {
					_checkedMatrix[i][j] = 1;
				}
			}
		} else {
			_checkedMatrix[checkPosition.x][checkPosition.y] = 1;
			int noChecked = 0;
			for (int i=0;i<_dimension;i++) {
				for (int j=0;j<_dimension;j++) {
					if (_checkedMatrix[i][j] == 0) {
						noChecked++;
					}
				}
			}
			if (noChecked == _numberOfMines) {
				_win = true;
			}
		}
	}
	
}
