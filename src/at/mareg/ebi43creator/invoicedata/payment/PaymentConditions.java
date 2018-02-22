package at.mareg.ebi43creator.invoicedata.payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;

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
	/*
	 * ResourceManager instance
	 */
	private ResourceManager rm;

	private String dueDate;
	private List<Discount> discounts;

	public PaymentConditions ()
	{
	}

	public PaymentConditions (final ResourceManager resman)
	{
		rm = resman;
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
	public List<Discount> getDiscounts ()
	{

		return discounts;

	}

	public void setDiscounts (final List<Discount> discounts)
	{

		this.discounts = discounts;

	}

	public void addEmptyDiscount ()
	{
		if (discounts == null)
			discounts = new ArrayList<> ();

		Discount d = new Discount ();
		discounts.add (d);
		rm.getSurchargeDiscountPane ().getDiscountArea ().addEmptyDiscountLine (d);
	}

	public void removeDiscount (final Discount discount)
	{
		if (discounts.indexOf (discount) >= 0)
			discounts.remove (discount);

		if (discounts.size () == 0)
			discounts = null;
	}

	public void setTempData ()
	{

		dueDate = LocalDate.now ().plusDays (20).toString ();
		this.addEmptyDiscount ();
		discounts.get (0).setPaymentDate (LocalDate.now ().plusDays (10).toString ());
		discounts.get (0).setPercentage (Double.valueOf (5d));

	}

}
