package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Controller {
	DBConnection connection = new DBConnection();
	
	public double check(User u)throws Exception{
		if(u.getCart() != null){
			double shouldPay = u.getCart().getTotal();
			if(u.getBalance() < shouldPay){
				throw new Exception("You Do Not Have Enough Money, Please Load Your Balance");
			}
			else{
				u.setBalance(u.getBalance() - shouldPay);
				u.getCart().clear();
				return shouldPay;
			}
		}
		else{
			throw new Exception("Your Cart is Empty");
		}
	}
	
	public List<Item> getAllItems() {
		return connection.getCurrentItems();
	}
	
	public User getUser(String userName, String password) {
		return connection.getUser(userName, password);
	}
	
	public static void main(String args[]) {
		Controller c = new Controller();
		System.out.println(c.getUser("gubrian", "123").getUserName());
	}
}
