package aw.localisation;

import rp.robotics.localisation.*;
import rp.robotics.navigation.Heading;

/**
 * Example structure for an action model that should move the probabilities 1
 * cell in the requested direction. In the case where the move would take the
 * robot into an obstacle or off the map, this model assumes the robot stayed in
 * one place. This is the same as the model presented in Robot Programming
 * lecture on action models.
 * 
 * Note that this class doesn't actually do this, instead it shows you a
 * <b>possible</b> structure for your action model.
 * 
 * @author Nick Hawes
 * 
 */
public class PerfectActionModel implements ActionModel {
	public GridPositionDistribution updateAfterMove(GridPositionDistribution from, Heading heading) {

		// Create the new distribution that will result from applying the action
		// model
		GridPositionDistribution to = new GridPositionDistribution(from);
	
		// Move the probability in the correct direction for the action
		if (heading == Heading.PLUS_X) {
			movePlusX(from, to);
		} else if (heading == Heading.PLUS_Y) {
			// you could implement a movePlusY etc. or you could find a way do
			// do all moves in a single method. Hint: all changes are just + or
			// - 1 to an x or y value.
			movePlusY(from, to);

		} else if (heading == Heading.MINUS_X) {
			moveMinusX(from, to);
		} else if (heading == Heading.MINUS_Y) {
			moveMinusY(from, to);
		}
		/*
		 * for (int y = 0; y < to.getGridHeight(); y++) { for (int x = 0; x <
		 * to.getGridWidth(); x++) { System.out.println(_from.getProbability(x,
		 * y)); } }
		 */
		return to;
	}

	/**
	 * Move probabilities from from one cell in the plus x direction into to
	 * 
	 * @param _from
	 * @param _to
	 */
	private void movePlusX(GridPositionDistribution _from, GridPositionDistribution _to) {
		// iterate through points updating as appropriate
		for (int y = 0; y < _to.getGridHeight(); y++) {
			for (int x = 0; x < _to.getGridWidth(); x++) {
				// make sure to respect obstructed grid points

				if (y == 0)
					for (int k = 0; k < _to.getGridHeight(); k++)
						_to.setProbability(0, k, 0);

				if (_to.isValidGridPosition(x , y)) {

					
					int fromX = x-1;
					int fromY = y;
					if(fromX>0){
					float fromProb = _from.getProbability(fromX, fromY);
					// position after move
					int toX = x ;
					int toY = y;
					// set probability for position after move
					_to.setProbability(toX, toY, fromProb);
					}
					else{
						_to.setProbability(x,y,0);
					}
				}

				else {
					_from.setProbability(x - 1, y, 0);
					_from.normalise();
					_to.normalise();

				}
			}
		}

	}

	/**
	 * Move probabilities from from one cell in the minus x direction into to
	 * 
	 * @param _from
	 * @param _to
	 */
	private void moveMinusX(GridPositionDistribution _from, GridPositionDistribution _to) {
		// iterate through points updating as appropriate
		for (int y = 0; y < _to.getGridHeight(); y++) {
			for (int x = 0; x < _to.getGridWidth(); x++) {
				// make sure to respect obstructed grid points
				if (_to.isValidGridPosition(x + 1, y)) {
					if (x < _to.getGridWidth()) {
						int fromX = x + 1;
						int fromY = y;
						float fromProb = _from.getProbability(fromX, fromY);
						// position after move
						int toX = x;
						int toY = y;
						// set probability for position after move
						_to.setProbability(toX, toY, fromProb);

					}
				} else {
					_to.setProbability(x, y, 0);

					_to.normalise();
					_from.normalise();
				}
			}
		}
		_to.normalise();
		_from.normalise();
	}

	/**
	 * Move probabilities from from one cell in the plus y direction into to
	 * 
	 * @param _from
	 * @param _to
	 */
	private void movePlusY(GridPositionDistribution _from, GridPositionDistribution _to) {
		// iterate through points updating as appropriate
		for (int y = 0; y < _to.getGridHeight(); y++) {
			for (int x = 0; x < _to.getGridWidth(); x++) {
				// make sure to respect obstructed grid points

				if (x == 0)
					for (int k = 0; k < _to.getGridWidth(); k++)
						_to.setProbability(k, 0, 0);

				if (_to.isValidGridPosition(x, y )) {
				
					if (y > 0) {
						int fromX = x;
						int fromY = y - 1;
						float fromProb = _from.getProbability(fromX, fromY);
						// position after move
						int toX = x;
						int toY = y;
						// set probability for position after move

						_to.setProbability(toX, toY, fromProb);
				
					}
				} else {

					_from.setProbability(x, y, 0);

					_to.normalise();
					_from.normalise();
				}

			}
		}

	}

	/**
	 * Move probabilities from from one cell in the minus y direction into to
	 * 
	 * @param _from
	 * @param _to
	 */
	private void moveMinusY(GridPositionDistribution _from, GridPositionDistribution _to) {

		// iterate through points updating as appropriate
		for (int y = 0; y < _to.getGridHeight(); y++) {
			for (int x = 0; x < _to.getGridWidth(); x++) {
				System.out.println(_to.getProbability(x, y));
				// make sure to respect obstructed grid points
				if (_to.isValidGridPosition(x, y )) {
					
						int fromX = x;
						int fromY = y+1;
						if(fromY < _to.getGridHeight()){
						float fromProb = _from.getProbability(fromX, fromY);
						// position after move
						int toX = x;
						int toY = y;
						// set probability for position after move
						_to.setProbability(toX, toY, fromProb);
						}else{
							int toX = x;
							int toY = y;
							_to.setProbability(toX, toY, 0);
							
						}
						
					
				} else {
					_to.setProbability(x, y, 0);
					
				}
			}
		}
		_to.normalise();
		_from.normalise();

	}
}