package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PaymentPane extends BasePane
{
  /**
   * Pane to enter payment informations (IBAN, etc. of biller)
   * 
   * @author Martin Regitnig
   */

  /*
   * Pane elements
   */
  private Accordion ac;
  private TitledPane tp_Payment;
  private GridPane grid_Payment;

  public PaymentPane (final ResourceManager rm)
  {
    super (rm);

    init ();
  }

  /*
   * Initialize OrderPane
   */
  @Override
  protected void init ()
  {
    super.init ();

    ac = new Accordion ();

    tp_Payment = new TitledPane ();

    grid_Payment = new GridPane ();
    grid_Payment.setPadding (Data.BASEPANE_PADDING);
    grid_Payment.setHgap (Data.BASEPANE_HVGAP);
    grid_Payment.setVgap (this.getHgap ());

    VBox v = null;

    for (final EFormElement eb : EFormElement.values ())
    {
      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_PAYMENT_NAME))
      {
        final boolean isRequired = eb.isRequired ();
        final String labelText = eb.getLabelText () + (isRequired ? "*" : "");
        final String elementID = eb.getID ();
        final String elementType = eb.getType ();

        if (elementType == Data.ELEMENTTYPE_TEXTFIELD)
        {
          v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
          v.getChildren ().add (FormElementCreator.getStandardTextField (elementID, isRequired));

          grid_Payment.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();
        }

        if (elementType == Data.ELEMENTTYPE_TEXTAREA)
        {
          v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
          v.getChildren ().add (FormElementCreator.getStandardTextArea (elementID, isRequired));

          while (col != 0)
            incrementCol ();

          grid_Payment.add (v, col, row, 2, 3);
          VBoxHelper.structureVBox (v);

          incrementCol ();
          incrementCol ();
        }

        if (elementType == Data.ELEMENTTYPE_DATEPICKER)
        {
          v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
          v.getChildren ().add (FormElementCreator.getStandardDatePicker (elementID, isRequired));

          grid_Payment.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();
        }

        if (isRequired)
          RequiredAndErrorHelper.addRequiredField (eb.getTiteldPaneID (), elementID);
      }
    }

    tp_Payment.setContent (grid_Payment);
    tp_Payment.setCollapsible (false);

    ac.getPanes ().add (tp_Payment);
    ac.setExpandedPane (tp_Payment);

    this.add (ac, 0, 0);
  }

  public GridPane getGridPanePayment ()
  {
    return grid_Payment;
  }
}
