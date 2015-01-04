package test;
import java.util.HashMap;
import java.util.Map;


public class Cart {
	private Map<Item, Integer> map = new HashMap<Item, Integer>();
	private double total;
	Map<Item, Integer> getMap(){
		return map;
	}
	void addItem(Item i, int num){
		if(map.containsKey(i)){
			map.put(i, map.get(i) + num);
		}
		else{
			map.put(i, num);
		}
		total = total + i.getPrice() * num;
	}
	
	void deletItem(Item i, int num) throws Exception{
		if(map.containsKey(i)){
			int afterDel = map.get(i) - num;
			if(afterDel < 0){
				throw new Exception("No Such Many Items in Your Cart");
			}
			else if(afterDel == 0){
				map.remove(i);
				total = total - i.getPrice() * num;
			}
			else{
				map.put(i,  map.get(i) - num);
				total = total - i.getPrice() * num;
			}
		}
		else{
			throw new Exception("No Such Item in Your Cart");
		}
	}
	
	public double getTotal(){
		return this.total;
	}
	
	public void clear(){
		map.clear();
		total = 0;
	}
	
}
