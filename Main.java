package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	
	public static LinkedList<Pieces> ps = new LinkedList<>();
	public static Pieces selectedPiece = null;
	
	public static void main(String[] args) throws IOException {
		
		BufferedImage all=ImageIO.read(new File("E:\\chess.png"));
        Image imgs[]=new Image[12];
        int ind=0;
        for(int y=0;y<400;y+=200){
        	for(int x=0;x<1200;x+=200){
        		imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
        		ind++;
        	}
        }
        Pieces wking = new Pieces(4, 7, "king", true, ps); 
        Pieces wqueen = new Pieces(3, 7, "queen", true, ps);
        Pieces wrock = new Pieces(0, 7, "rock", true, ps);
        Pieces wrock2 = new Pieces(7, 7, "rock", true, ps);
        Pieces wbishop = new Pieces(2, 7, "bishop", true, ps);
        Pieces wbishop2 = new Pieces(5, 7, "bishop", true, ps);
        Pieces wknight = new Pieces(1, 7, "knight", true, ps);
        Pieces wknight2 = new Pieces(6, 7, "knight", true, ps);
        Pieces wpawn = new Pieces(0, 6, "pawn", true, ps);
        Pieces wpawn1 = new Pieces(1, 6, "pawn", true, ps);
        Pieces wpawn2 = new Pieces(2, 6, "pawn", true, ps);
        Pieces wpawn3 = new Pieces(3, 6, "pawn", true, ps);
        Pieces wpawn4 = new Pieces(4, 6, "pawn", true, ps);
        Pieces wpawn5 = new Pieces(5, 6, "pawn", true, ps);
        Pieces wpawn6 = new Pieces(6, 6, "pawn", true, ps);
        Pieces wpawn7 = new Pieces(7, 6, "pawn", true, ps);
        
        
        Pieces bking = new Pieces(4, 0, "king", false, ps);
        Pieces bqueen = new Pieces(3, 0, "queen", false, ps);
        Pieces brock = new Pieces(0, 0, "rock", false, ps);
        Pieces brock2 = new Pieces(7, 0, "rock", false, ps);
        Pieces bbishop = new Pieces(2, 0, "bishop", false, ps);
        Pieces bbishop2 = new Pieces(5, 0, "bishop", false, ps);
        Pieces bknight = new Pieces(1, 0, "knight", false, ps);
        Pieces bknight2 = new Pieces(6, 0, "knight", false, ps);
        Pieces bpawn = new Pieces(0, 1, "pawn", false, ps);
        Pieces bpawn2 = new Pieces(1, 1, "pawn", false, ps);
        Pieces bpawn3 = new Pieces(2, 1, "pawn", false, ps);
        Pieces bpawn4 = new Pieces(3, 1, "pawn", false, ps);
        Pieces bpawn5 = new Pieces(4, 1, "pawn", false, ps);
        Pieces bpawn6 = new Pieces(5, 1, "pawn", false, ps);
        Pieces bpawn7 = new Pieces(6, 1, "pawn", false, ps);
        Pieces bpawn8 = new Pieces(7, 1, "pawn", false, ps);
        
        
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 512, 512);
		frame.setUndecorated(true);
		JPanel pn = new JPanel() {
			public void paint(Graphics g) {
				boolean white = true;
				for(int i=0;i<8;i++) {
					for(int j=0;j<8;j++) {
						if(white) {
							g.setColor(Color.white);
						}else {
							g.setColor(Color.gray);
						}
						g.fillRect(i*64, j*64, 64, 64);
						white=!white;
					}
					white=!white;
				}
				for(Pieces p: ps) {
					int ind = 0;
					if(p.name.equalsIgnoreCase("king")) {
						ind = 0;
					}
					if(p.name.equalsIgnoreCase("queen")) {
						ind = 1;
					}
					if(p.name.equalsIgnoreCase("bishop")) {
						ind = 2;
					}
					if(p.name.equalsIgnoreCase("knight")) {
						ind = 3;
					}
					if(p.name.equalsIgnoreCase("rock")) {
						ind = 4;
					}
					if(p.name.equalsIgnoreCase("pawn")) {
						ind = 5;
					}
					if(!p.isWhite) {
						ind+=6;
					}
					g.drawImage(imgs[ind], p.x, p.y, this);
				}
			}
		};
		frame.add(pn);
		
		frame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if(selectedPiece!=null) {
					selectedPiece.x = e.getX()-32;
					selectedPiece.y = e.getY()-32;
					frame.repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				//System.out.println((getPiece(e.getX(), e.getY()).isWhite?"white ":"black ") + getPiece(e.getX(), e.getY()).name);
				selectedPiece = getPiece(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				selectedPiece.move(e.getX()/64, e.getY()/64);
				frame.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
		
	}
	public static Pieces getPiece(int x, int y) {
		int xp = x/64;
		int yp = y/64;
		for(Pieces p : ps) {
			if(p.xp == xp && p.yp == yp) {
				return p;
			}
		}
		return null;
	}

}
