
public class User {
	private String userName;
	private String userPassword;
	private double balance;
	private Cart myCart;
	
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getBalance(){
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Cart getCart() {
		return myCart;
	}

}
