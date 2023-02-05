package chess;

import java.util.LinkedList;

public class Pieces {
	int xp;
	int yp;
	int x;
	int y;
	boolean isWhite;
	LinkedList<Pieces> ps;
	String name;
	public Pieces(int xp, int yp,String name ,boolean isWhite, LinkedList<Pieces> ps) {
		this.xp = xp;
		this.yp = yp;
		x = xp*64;
		y = yp*64;
		this.isWhite = isWhite;
		this.ps = ps;
		this.name = name;
		ps.add(this);
	}
	
	public void move(int x, int y) {
		if(Main.getPiece(x*64, y*64)!=null) {
			if(Main.getPiece(x*64, y*64).isWhite != isWhite) {
				Main.getPiece(x*64, y*64).kill();
			}else {
				this.x = xp*64;
				this.y = yp*64;
				return;
			}
		}
			
		this.xp = x;
		this.yp = y;
		this.x = xp*64;
		this.y = yp*64;
	}
	public void kill() {
		ps.remove(this);
	}
}
