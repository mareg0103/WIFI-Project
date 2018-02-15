package at.mareg.ebi43creator.display.resources;

import at.mareg.ebi43creator.display.form.ContactPane;
import at.mareg.ebi43creator.display.form.DetailsPane;
import at.mareg.ebi43creator.display.form.FormTabs;
import at.mareg.ebi43creator.display.form.OrderPane;
import at.mareg.ebi43creator.display.form.PaymentPane;
import at.mareg.ebi43creator.display.form.SummaryPane;
import at.mareg.ebi43creator.display.form.SurchargeDiscountPane;
import at.mareg.ebi43creator.display.form.help.HelpArea;
import at.mareg.ebi43creator.display.main.EBI43CreatorMain;
import at.mareg.ebi43creator.display.utilities.FormFiller;
import at.mareg.ebi43creator.display.utilities.SaveMethodMapper;
import at.mareg.ebi43creator.invoicedata.InputChecker;
import at.mareg.ebi43creator.invoicedata.InvoiceData;

/**
 * Starts and manages all necessary resources
 * 
 * @author Martin Regitnig
 */
public class ResourceManager
{
	/*
	 * Date manager
	 */
	private final InvoiceDateManager invoiceDateManager;

	/*
	 * Invoice data
	 */
	private InvoiceData invoiceData;

	/*
	 * Form filler (loaded XML document)
	 */
	private final FormFiller formFiller;

	/*
	 * Display management
	 */
	private final EBI43CreatorMain display;

	/*
	 * Help texts
	 */
	private final HelpArea helpArea;

	/*
	 * The form
	 */
	private final FormTabs form;
	private final OrderPane orderPane;
	private final ContactPane contactPane;
	private final PaymentPane paymentPane;
	private final DetailsPane detailsPane;
	private final SurchargeDiscountPane surchargeDiscountPane;
	private final SummaryPane summaryPane;

	public ResourceManager (final EBI43CreatorMain disp, final String docType, final String loadPath)
	{
		InputChecker.setResourceManager (this);

		this.helpArea = new HelpArea ();
		this.invoiceDateManager = new InvoiceDateManager ();

		if (loadPath == null)
		{
			this.invoiceData = new InvoiceData (invoiceDateManager, docType);
			formFiller = null;
		} else
		{
			invoiceData = new InvoiceData ().readXMLInvoice (loadPath);
			formFiller = new FormFiller (invoiceData, this);
		}
		SaveMethodMapper.setInvoiceData (invoiceData);
		invoiceData.getDetails ().setResourceManagerInternal (this);

		this.contactPane = new ContactPane (this);
		this.orderPane = new OrderPane (this);
		this.paymentPane = new PaymentPane (this);
		this.detailsPane = new DetailsPane (this);
		this.surchargeDiscountPane = new SurchargeDiscountPane (this);
		this.summaryPane = new SummaryPane (this);
		this.form = new FormTabs (orderPane, contactPane, paymentPane, detailsPane, surchargeDiscountPane, summaryPane,
				helpArea, this);

		this.display = disp;

		if (loadPath == null)
			invoiceData.getDetails ().addEmptyListLineItem ();
		else
			formFiller.fillFormWithLoadedData ();
	}

	/*
	 * Data
	 */
	public InvoiceDateManager getInvoiceDateManager ()
	{
		return invoiceDateManager;
	}

	public InvoiceData getInvoiceData ()
	{
		return invoiceData;
	}

	/*
	 * Display management
	 */
	public EBI43CreatorMain getDisplay ()
	{
		return display;
	}

	/*
	 * The form
	 */
	public FormTabs getForm ()
	{
		return form;
	}

	public OrderPane getOrderPane ()
	{
		return orderPane;
	}

	public ContactPane getContactPane ()
	{
		return contactPane;
	}

	public PaymentPane getPaymentPane ()
	{
		return paymentPane;
	}

	public DetailsPane getDetailsPane ()
	{
		return detailsPane;
	}

	public HelpArea getHelpArea ()
	{
		return helpArea;
	}
}
