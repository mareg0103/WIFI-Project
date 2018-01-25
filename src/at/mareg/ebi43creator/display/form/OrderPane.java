package at.mareg.ebi43creator.display.form;

import java.time.LocalDate;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.ECurrency;
import at.mareg.ebi43creator.invoicedata.enums.EFormFields;
import javafx.scene.control.Accordion;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class OrderPane extends BasePane
{
  private Accordion ac;
  private TitledPane tp_Order;
  private GridPane grid_Order;

  public OrderPane (final ResourceManager resman)
  {

    super (resman);

    init ();

  }

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

    for (final EFormFields eb : EFormFields.values ())
    {

      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_ORDER))
      {
        final boolean isRequired = eb.isRequired ();
        final String elementID = eb.getID ();
        final String labelText = eb.getLabelText () + (isRequired ? "*" : "");

        if (eb.getType () == Data.ELEMENT_TEXT_FIELD)
        {
          v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText));
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

        if (eb.getType () == Data.ELEMENT_TEXT_AREA)
        {
          v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText));
          v.getChildren ().add (FormElementCreator.getStandardTextArea (elementID, isRequired));

          while (col != 0)
            incrementCol ();

          grid_Order.add (v, col, row, 2, 3);
          VBoxHelper.structureVBox (v);

          incrementCol ();
          incrementCol ();

        }

        if (eb.getType () == Data.ELEMENT_DATE_PICKER)
        {
          v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText));

          final DatePicker dp = FormElementCreator.getStandardDatePicker (elementID, isRequired);
          v.getChildren ().add (dp);

          if (elementID.equals (EFormFields.INVOICE_DATE.getID ()))
          {
            dp.setDayCellFactory (rm.getInvoiceDateManager ().getDayCellFactoryForInvoiceDate ());
            dp.setValue (LocalDate.now ());
            dp.setStyle ("-fx-control-inner-background: #FFFFFF");
          }

          if (elementID.equals (EFormFields.FROM_DATE.getID ()))
            while (col != 0)
              incrementCol ();

          if (elementID.equals (EFormFields.TO_DATE.getID ()))
            dp.disableProperty ().set (true);

          grid_Order.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();

        }

        if (isRequired)
          RequiredAndErrorHelper.incrementTabCount (eb.getTiteldPaneID ());
      }

    }

    // Add change listener to DatePicker FROM_DATE
    final DatePicker from = FormElementCreator.getDatePickerWithID (grid_Order, EFormFields.FROM_DATE.getID ());
    if (from != null)
    {

      from.valueProperty ().addListener ( (observable, oldValue, newValue) -> {

        final DatePicker to = FormElementCreator.getDatePickerWithID (grid_Order, EFormFields.TO_DATE.getID ());

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

}
