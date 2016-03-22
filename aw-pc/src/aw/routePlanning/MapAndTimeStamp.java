
public class MapAndTimeStamp {
	private Map map;
	private int timeStamp;
	private int fCost;

	public MapAndTimeStamp(Map map, int timeStamp, int fCost){
		this.fCost = fCost;
	
		this.map = map;
		this.timeStamp = timeStamp;
	}
	public boolean equals(MapAndTimeStamp mapAndTime){
		
		return (mapAndTime.getMap().equals(this.getMap()) && this.timeStamp == mapAndTime.getTimeStamp());
	
	}
	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	/**
	 * @return the timeStamp
	 */
	public int getTimeStamp() {
		return timeStamp;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String toString(){
		return this.map.toString();
	}
	/**
	 * @return the fCost
	 */
	public int getfCost() {
		return fCost;
	}
	/**
	 * @param fCost the fCost to set
	 */
	public void setfCost(int fCost) {
		this.fCost = fCost;
	}
	

}
