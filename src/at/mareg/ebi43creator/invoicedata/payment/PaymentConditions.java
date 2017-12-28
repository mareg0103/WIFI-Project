package at.mareg.ebi43creator.invoicedata.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "dueDate", "discounts" })
public class PaymentConditions
{

	// <eb:DueDate>2017-09-15</eb:DueDate>
	// <eb:Discount>
	// <eb:PaymentDate>2017-09-04</eb:PaymentDate>
	// <eb:Percentage>10</eb:Percentage>
	// </eb:Discount>
	// <eb:Discount>
	// <eb:PaymentDate>2017-09-07</eb:PaymentDate>
	// <eb:Percentage>5</eb:Percentage>
	// </eb:Discount>

	private String dueDate;
	private Discount[] discounts = new Discount[2];

	public PaymentConditions ()
	{

		for (int i = 0; i < discounts.length; i++)
			discounts[i] = null;

	}

	@XmlElement (name = "DueDate", namespace = Data.DEFAULT_NAMESPACE)
	public String getDueDate ()
	{

		return dueDate;

	}

	public void setDueDate (final String dueDate)
	{

		this.dueDate = dueDate;

	}

	@XmlElement (name = "Discount", namespace = Data.DEFAULT_NAMESPACE)
	public Discount[] getDiscounts ()
	{

		return discounts;

	}

	public void setDiscounts (final Discount[] discounts)
	{

		this.discounts = discounts;

	}

	public void setTempData ()
	{

		dueDate = "2018-02-27";
		discounts[0] = new Discount ();
		discounts[0].setPaymentDate ("2018-01-31");
		discounts[0].setPercentage (Double.valueOf (5d));

	}

}
