package aw.GUI.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JLabel;

import org.junit.Test;

import aw.GUI.*;
import aw.file.ItemList;
import aw.file.Job;

public class InformationViewTest {
	
	Information info = new Information();
	InformationModel model = new InformationModel(info);
	Job job1 = new Job("10001,ce,2,bi,6,aj,2,bc,3,ab,1,af,4,ai,1");
	Job job2 = new Job("10021,ab,3,ae,2,ah,1,cf,1,bi,1");
	ItemList item = new ItemList();

@Test
	public void testUpdate() {
		model.setJob(job1);
		InformationView view = new InformationView(model);
		ArrayList<JLabel> itemsArray = view.getItemsArray();
		String jobID = itemsArray.get(1).getText();
		assertEquals(jobID, "Job ID:   10001");
	}

}
