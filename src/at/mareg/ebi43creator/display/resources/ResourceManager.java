package at.mareg.ebi43creator.display.resources;

import at.mareg.ebi43creator.display.form.ContactPane;
import at.mareg.ebi43creator.display.form.DetailsPane;
import at.mareg.ebi43creator.display.form.FormTabs;
import at.mareg.ebi43creator.display.form.HelpArea;
import at.mareg.ebi43creator.display.form.OrderPane;
import at.mareg.ebi43creator.display.form.PaymentPane;
import at.mareg.ebi43creator.display.form.SummaryPane;
import at.mareg.ebi43creator.display.form.SurchargeDiscountPane;
import at.mareg.ebi43creator.display.main.EBI43CreatorMain;
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

  // Date manager
  private final InvoiceDateManager invoiceDateManager;

  // Invoice data
  private final InvoiceData invoiceData;

  // Display management
  private final OrderPane orderPane;
  private final ContactPane contactPane;
  private final PaymentPane paymentPane;
  private final DetailsPane detailsPane;
  private final SurchargeDiscountPane surchargeDiscountPane;
  private final SummaryPane summaryPane;

  // Help texts
  private final HelpArea helpArea;

  private final FormTabs form;
  // private final Map <String, Boolean> tabActive;

  private final EBI43CreatorMain display;

  public ResourceManager (final EBI43CreatorMain disp, final String docType)
  {
    InputChecker.setResourceManager (this);

    this.helpArea = new HelpArea ();
    this.invoiceDateManager = new InvoiceDateManager ();

    this.invoiceData = new InvoiceData (invoiceDateManager, docType);
    SaveMethodMapper.setInvoiceData (invoiceData);

    this.contactPane = new ContactPane (this);
    this.orderPane = new OrderPane (this);
    this.paymentPane = new PaymentPane (this);
    this.detailsPane = new DetailsPane (this);
    this.surchargeDiscountPane = new SurchargeDiscountPane (this);
    this.summaryPane = new SummaryPane (this);
    this.form = new FormTabs (orderPane,
                              contactPane,
                              paymentPane,
                              detailsPane,
                              surchargeDiscountPane,
                              summaryPane,
                              helpArea,
                              this);

    this.display = disp;

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

  public ContactPane getContactPane ()
  {
    return contactPane;
  }

  // private void setTabActiveStartValues ()
  // {
  // for (final Tab t : form.getFormTabs ())
  // {
  // t.closableProperty ().set (false);
  // t.disableProperty ().set (true);
  // tabActive.put (t.getText (), Boolean.valueOf (false));
  // }
  //
  // tabActive.replace (Data.TAB_CONTACT_DATA, Boolean.valueOf (true));
  // form.setTabActiveStatus (Data.TAB_CONTACT_DATA, true);
  // }
  //
}
