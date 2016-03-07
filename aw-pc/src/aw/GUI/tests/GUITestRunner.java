package aw.GUI.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GUITestRunner {
	
	public static void main(String[] args ) {
		 Result result = JUnitCore.runClasses(InformationTest.class, InformationViewTest.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());
	   }
}