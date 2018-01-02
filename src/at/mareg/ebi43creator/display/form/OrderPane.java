package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormFields;
import javafx.scene.control.Accordion;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class OrderPane extends BasePane
{

  public OrderPane (final ResourceManager resman)
  {

    super (resman);

    init ();

  }

  @Override
  protected void init ()
  {

    super.init ();

    final Accordion ac = new Accordion ();

    final TitledPane tp_Order = new TitledPane ();

    final GridPane grid_Order = new GridPane ();
    grid_Order.setPadding (Data.BASEPANE_PADDING);
    grid_Order.setHgap (Data.BASEPANE_HVGAP);
    grid_Order.setVgap (this.getHgap ());

    for (final EFormFields eb : EFormFields.values ())
    {

      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_ORDER))
      {

        final boolean isRequired = eb.isRequired ();

        if (eb.getType () == Data.ELEMENT_TEXT_FIELD)
        {

          final VBox v = new VBox ();

          final Label l = new Label (eb.getLabelText ());
          v.getChildren ().add (l);

          final TextField t = FormElementCreator.getStandardTextField (eb.getID (), isRequired);
          v.getChildren ().add (t);

          grid_Order.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();

        }

        if (eb.getType () == Data.ELEMENT_TEXT_AREA)
        {

          final VBox v = new VBox ();

          final Label l = new Label (eb.getLabelText ());
          v.getChildren ().add (l);

          final TextArea t = FormElementCreator.getStandardTextArea (eb.getID (), isRequired);
          v.getChildren ().add (t);

          while (col != 0)
            incrementCol ();

          grid_Order.add (v, col, row, 2, 3);
          VBoxHelper.structureVBox (v);

          incrementCol ();
          incrementCol ();

        }

        if (eb.getType () == Data.ELEMENT_DATE_PICKER)
        {

          final VBox v = new VBox ();

          final Label l = new Label (eb.getLabelText ());
          v.getChildren ().add (l);

          final String id = eb.getID ();

          final DatePicker dp = FormElementCreator.getStandardDatePicker (id, isRequired);
          v.getChildren ().add (dp);

          if (id.equals (EFormFields.INVOICE_DATE.getID ()))
            dp.setDayCellFactory (rm.getInvoiceDateManager ().getDayCellFactoryForInvoiceDate ());

          if (id.equals (EFormFields.FROM_DATE.getID ()))
            while (col != 0)
              incrementCol ();

          if (id.equals (EFormFields.TO_DATE.getID ()))
            dp.disableProperty ().set (true);

          grid_Order.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();

        }

        if (isRequired)
          RequiredAndErrorHelper.incrementTabCount (this.getClass ().getSimpleName ());

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

    RequiredAndErrorHelper.showRequiredMap ();
  }

}
