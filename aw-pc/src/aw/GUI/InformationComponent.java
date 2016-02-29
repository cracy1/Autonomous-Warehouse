package aw.GUI;


import java.awt.BorderLayout;
import javax.swing.JPanel;


public class InformationComponent extends JPanel{
	private Grid grid;
	private InformationView view;
	
	public InformationComponent(Information info) {
		super();
		grid = new Grid();
		InformationModel model = new InformationModel(info);
		view = new InformationView(model);
		model.addObserver(view);
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		grid.draw(g2);
		view.draw(g2);
	}

}
