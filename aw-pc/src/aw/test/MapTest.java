package aw.test;

public class MapTest {
	public static void main(String[] args){
		Map map = new Map(8, 12);
		System.out.println(map.getPath(new Node(0,0), new Node(0,1)));
	}
}
