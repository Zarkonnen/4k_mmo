import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import javax.swing.JApplet;

public class a extends JApplet implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent me) {}

	@Override
	public void mouseMoved(MouseEvent me) {
		my = me.getY();
		mx = me.getX();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		click = true;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		key[((KeyEvent) e).getKeyCode()] = true;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		key[((KeyEvent) e).getKeyCode()] = false;
	}
	
	boolean key[] = new boolean[65535];
	boolean click = false;
	int my, mx;
	BufferStrategy strategy;
	
	@Override
	public void init() {
		setIgnoreRepaint(true);
		Canvas canvas = new Canvas();
		add(canvas);
		canvas.setBounds(0, 0, 800, 600);
		canvas.createBufferStrategy(2);
		strategy = canvas.getBufferStrategy();
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		new Thread(this).start();
	}

	@Override
	public void run() {
		int tick = 0;
		while(true) {
			tick++;
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.RED);
			g.fillOval(tick % 800, 200, 50, 50);
			strategy.show();
			try { Thread.sleep(25); } catch (Exception e) {}
		}
	}
}