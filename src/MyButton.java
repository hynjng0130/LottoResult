import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class MyButton extends JButton{
	
	public int state = 10;
	
	public MyButton(String text) {
		super(text);
		decorate();
	}

	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	public void paint(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		
		if(state!=10)
			g.setColor(Color.green);
		else
			g.setColor(Color.blue);
		
		g.fillRoundRect(0, 0, width, height, 66, 66);
		g.setColor(Color.white);
		g.drawString(getText(), (int)getSize().getWidth()/2-10, (int)getSize().getWidth()/2+5);
	}
}
