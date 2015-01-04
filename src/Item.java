
public class Item {
	private String name;
	private double price;
	private String id;
	
	public String getName() {
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public double getPrice(){
		return this.price;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public String getId() {
		return this.id;
	}
	
	@Override
	public int hashCode(){
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Item){
			Item i = (Item) o;
			if(i.name == this.name && i.price == this.price && i.id == this.id){
				return true;
			}
		}
		return false;
	}	
}
