package at.mareg.ebi43creator.display.resources;

import java.util.Map;
import java.util.TreeMap;

import at.mareg.ebi43creator.display.form.BillerPane;
import at.mareg.ebi43creator.display.form.FormTabs;
import at.mareg.ebi43creator.display.main.EBI43CreatorMain;
import at.mareg.ebi43creator.display.utilities.HelpArea;
import at.mareg.ebi43creator.display.utilities.SaveMethodMapper;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import javafx.scene.control.Tab;

public class ResourceManager
{

	// Date manager
	private InvoiceDateManager invoiceDateManager;

	// Invoice data
	private InvoiceData invoiceData;

	// Display management
	private BillerPane billerPane;

	// Help texts
	private HelpArea helpArea;

	private FormTabs form;
	private Map<String, Boolean> tabActive;

	private EBI43CreatorMain display;

	public ResourceManager (final EBI43CreatorMain display)
	{

		this.helpArea = new HelpArea ();
		this.invoiceDateManager = new InvoiceDateManager ();

		this.invoiceData = new InvoiceData ();
		SaveMethodMapper.setInvoiceData (invoiceData);

		this.billerPane = new BillerPane (this);
		this.form = new FormTabs (billerPane, helpArea);
		this.tabActive = new TreeMap<> ();
		setTabActiveStartValues ();

		this.display = display;

	}

	public HelpArea getHelpArea ()
	{
		return helpArea;
	}

	public InvoiceDateManager getInvoiceDateManager ()
	{
		return invoiceDateManager;
	}

	public InvoiceData getInvoiceData ()
	{
		return invoiceData;
	}

	// Display management
	public EBI43CreatorMain getDisplay ()
	{
		return display;
	}

	public FormTabs getForm ()
	{
		return form;
	}

	private void setTabActiveStartValues ()
	{
		for (final Tab t : form.getFormTabs ())
		{
			t.closableProperty ().set (false);
			t.disableProperty ().set (true);
			tabActive.put (t.getText (), Boolean.valueOf (false));
		}

		tabActive.replace (Data.TAB_BILLER_NAME, Boolean.valueOf (true));
		form.setTabActiveStatus (Data.TAB_BILLER_NAME, true);
	}

}
