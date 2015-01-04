import java.util.Map;


public class Controller {
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
}
