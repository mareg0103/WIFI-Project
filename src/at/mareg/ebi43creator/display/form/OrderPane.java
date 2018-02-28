package at.mareg.ebi43creator.display.form;

import java.time.LocalDate;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.ECurrency;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.scene.control.Accordion;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class OrderPane extends BasePane
{
  /**
   * Pane to enter order specific informations like order id, supplier id,
   * invoice number, etc.
   * 
   * @author Martin Regitnig
   */

  /*
   * Pane elements
   */
  private Accordion ac;
  private TitledPane tp_Order;
  private GridPane grid_Order;

  public OrderPane (final ResourceManager resman)
  {

    super (resman);

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

    tp_Order = new TitledPane ();

    grid_Order = new GridPane ();
    grid_Order.setPadding (Data.BASEPANE_PADDING);
    grid_Order.setHgap (Data.BASEPANE_HVGAP);
    grid_Order.setVgap (this.getHgap ());

    VBox v = null;

    for (final EFormElement eb : EFormElement.values ())
    {

      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_ORDER_NAME))
      {
        final boolean isRequired = eb.isRequired ();
        final String elementID = eb.getID ();
        final String labelText = eb.getLabelText () + (isRequired ? "*" : "");

        if (eb.getType () == Data.ELEMENTTYPE_TEXTFIELD)
        {
          v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
          TextField t = FormElementCreator.getStandardTextField (elementID, isRequired);
          v.getChildren ().add (t);

          if (eb.getName ().equals ("INVOICE_CURRENCY"))
          {
            t.setText (ECurrency.EURO.getInvoiceCurrency ());
            t.setEditable (false);
            rm.getInvoiceData ().setInvoiceCurrency (ECurrency.EURO.getInvoiceCurrencyShort ());
          }

          grid_Order.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();
        }

        if (eb.getType () == Data.ELEMENTTYPE_TEXTAREA)
        {
          v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
          v.getChildren ().add (FormElementCreator.getStandardTextArea (elementID, isRequired));

          while (col != 0)
            incrementCol ();

          grid_Order.add (v, col, row, 2, 3);
          VBoxHelper.structureVBox (v);

          incrementCol ();
          incrementCol ();

        }

        if (eb.getType () == Data.ELEMENTTYPE_DATEPICKER)
        {
          v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));

          final DatePicker dp = FormElementCreator.getStandardDatePicker (elementID, isRequired);
          v.getChildren ().add (dp);

          if (elementID.equals (EFormElement.INVOICE_DATE.getID ()))
          {
            dp.setDayCellFactory (rm.getInvoiceDateManager ().getDayCellFactoryForInvoiceDate ());
            dp.setValue (LocalDate.now ());
            dp.setStyle ("-fx-control-inner-background: #FFFFFF");
          }

          if (elementID.equals (EFormElement.FROM_DATE.getID ()))
            while (col != 0)
              incrementCol ();

          if (elementID.equals (EFormElement.TO_DATE.getID ()))
            dp.disableProperty ().set (true);

          grid_Order.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();

        }

        if (isRequired && !elementID.equals (EFormElement.INVOICE_DATE.getID ()))
          RequiredAndErrorHelper.addRequiredField (eb.getTiteldPaneID (), elementID);
      }

    }

    /*
     * Add change listener to DatePicker FROM_DATE (Lieferdatum /
     * Leistungszeitraum von:)
     */
    final DatePicker from = FormElementCreator.getDatePickerWithID (grid_Order, EFormElement.FROM_DATE.getID ());
    if (from != null)
    {

      from.valueProperty ().addListener ( (observable, oldValue, newValue) -> {

        final DatePicker to = FormElementCreator.getDatePickerWithID (grid_Order, EFormElement.TO_DATE.getID ());

        if (to != null)
        {

          to.setDayCellFactory (rm.getInvoiceDateManager ().getDayCellFectoryDisableBefore (from.getValue ()));
          to.disableProperty ().set (false);

          if (to.getValue () != null && !to.getValue ().toString ().isEmpty ())
          {

            if (from.getValue ().isAfter (to.getValue ()))
              to.setValue (null);

          }

        }

      });

    }

    tp_Order.setContent (grid_Order);
    tp_Order.setCollapsible (false);

    ac.getPanes ().add (tp_Order);
    ac.setExpandedPane (tp_Order);

    this.add (ac, 0, 0);
  }

  public GridPane getGridPaneOrder ()
  {
    return grid_Order;
  }
}
