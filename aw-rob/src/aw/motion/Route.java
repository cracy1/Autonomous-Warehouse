package aw.motion;

import java.util.LinkedList;

public class Route extends LinkedList<Move>{

	/**
	 * Construct a new route using varargs.
	 * @param moves
	 */
    public Route(Move... moves){
        for(Move move: moves) add(move);
    }

    /**
     * Add a new move to the list.
     * @param inst The new move.
     */
    public void addMove(Move inst){
        add(inst);
    }
    
    /**
     * Get the next move.
     * @return
     */
    public Move next(){
    	if(!this.isEmpty()){
	    	Move move = get(0);
	    	remove(0);
	    	return move;
    	}
    	
    	return Move.STOP;
    }

} 
