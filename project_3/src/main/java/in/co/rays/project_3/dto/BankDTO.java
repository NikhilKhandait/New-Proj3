package in.co.rays.project_3.dto;

public class BankDTO extends BaseDTO {

	private String name;
	private String accountNo;
	private String amount;
	private String branch;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Override
	public String getKey() {
		
		return id + "";
	}
	@Override
	public String getValue() {
		
		return name;
	}
	
	
}