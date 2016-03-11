package aw.GUI;


import java.awt.GridLayout;
import javax.swing.JPanel;


public class InformationComponent extends JPanel{
	private Grid grid;
	private InformationView view;
	private InformationModel model;
	
	public InformationComponent(InformationModel model) {
		super();
		grid = new Grid();
		this.model = model;
		view = new InformationView(model);
		
		setLayout(new GridLayout(0, 2));
		model.addObserver(view);
		add(grid);
		add(view);
		
	}
}
