package at.mareg.ebi43creator.display.resources;

public class TestInvoiceData {
	
	private String orderID;
	private String supplierID;
	private String billerName;
	private String billerAddress;
	
	public TestInvoiceData ()
	{
		orderID = null;
		supplierID = null;
		billerName = null;
		billerAddress = null;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getBillerName() {
		return billerName;
	}

	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}

	public String getBillerAddress() {
		return billerAddress;
	}

	public void setBillerAddress(String billerAddress) {
		this.billerAddress = billerAddress;
	}
	
	public void testFunction (final String str)
	{
		System.out.println("InvoiceData.testFunction gets param " + str);
	}
}
