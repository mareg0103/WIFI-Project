package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormFields;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ContactPane extends BasePane
{

  private int billerCol = 0;
  private int billerRow = 0;
  private int invoiceRecipientCol = 0;
  private int invoiceRecipientRow = 0;

  private Accordion ac;
  private TitledPane tp_Biller;
  private TitledPane tp_InvoiceRecipient;
  private TitledPane tp_Delivery;
  private GridPane grid_Biller;
  private GridPane grid_InvoiceRecipient;
  private GridPane grid_Delivery;

  public ContactPane (final ResourceManager resman)
  {
    super (resman);

    init ();
  }

  @Override
  protected void init ()
  {
    super.init ();

    ac = new Accordion ();

    tp_Biller = new TitledPane ();
    tp_Biller.setText (Data.TITLEDPANE_BILLER);

    tp_InvoiceRecipient = new TitledPane ();
    tp_InvoiceRecipient.setText (Data.TITLEDPANE_INVOICERECIPIENT);

    tp_Delivery = new TitledPane ();
    tp_Delivery.setText (Data.TITLEDPANE_DELIVERY);

    grid_Biller = new GridPane ();
    grid_Biller.setPadding (Data.BASEPANE_PADDING);
    grid_Biller.setHgap (Data.BASEPANE_HVGAP);
    grid_Biller.setVgap (this.getHgap ());

    grid_InvoiceRecipient = new GridPane ();
    grid_InvoiceRecipient.setPadding (Data.BASEPANE_PADDING);
    grid_InvoiceRecipient.setHgap (Data.BASEPANE_HVGAP);
    grid_InvoiceRecipient.setVgap (this.getHgap ());

    grid_Delivery = new GridPane ();
    grid_Delivery.setPadding (Data.BASEPANE_PADDING);
    grid_Delivery.setHgap (Data.BASEPANE_HVGAP);
    grid_Delivery.setVgap (this.getHgap ());

    for (final EFormFields eb : EFormFields.values ())
    {
      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_BILLER))
      {
        final boolean isRequired = eb.isRequired ();
        final String labelText = eb.getLabelText () + (isRequired ? "*" : "");
        final String elementID = eb.getID ();
        final String elementType = eb.getType ();

        if (elementType == Data.ELEMENT_TEXT_FIELD)
        {
          final VBox v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText));
          v.getChildren ().add (FormElementCreator.getStandardTextField (elementID, isRequired));

          grid_Biller.add (v, billerCol, billerRow);
          VBoxHelper.structureVBox (v);

          incrementBillerCol ();
        }

        if (isRequired)
          RequiredAndErrorHelper.incrementTabCount (eb.getTiteldPaneID ());
      }

      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_INVOICERECIPIENT))
      {

        final boolean isRequired = eb.isRequired ();
        final String labelText = eb.getLabelText () + (isRequired ? "*" : "");
        final String elementID = eb.getID ();
        final String elementType = eb.getType ();

        if (elementType == Data.ELEMENT_TEXT_FIELD)
        {
          final VBox v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText));
          v.getChildren ().add (FormElementCreator.getStandardTextField (elementID, isRequired));

          grid_InvoiceRecipient.add (v, invoiceRecipientCol, invoiceRecipientRow);
          VBoxHelper.structureVBox (v);

          incrementInvoiceRecipientCol ();
        }

        if (isRequired)
          RequiredAndErrorHelper.incrementTabCount (eb.getTiteldPaneID ());
      }

      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_DELIVERY))
      {
        final boolean isRequired = eb.isRequired ();
        final String labelText = eb.getLabelText () + (isRequired ? "*" : "");
        final String elementID = eb.getID ();
        final String elementType = eb.getType ();

        if (elementType == Data.ELEMENT_TEXT_FIELD)
        {
          final VBox v = new VBox ();

          if (eb.getName ().equals ("DELIVERY_ID"))
          {
            v.getChildren ().add (FormElementCreator.getStandardLabel (labelText));
            v.getChildren ().add (FormElementCreator.getStandardTextField (elementID, isRequired));
          }
          else
          {
            v.getChildren ().add (FormElementCreator.getDisabledLookingLabel (labelText));
            v.getChildren ().add (FormElementCreator.getDisabledTextField (elementID, isRequired));
          }

          grid_Delivery.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();
        }

        if (elementType == Data.ELEMENT_CHECK_BOX)
        {
          final VBox v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (""));

          CheckBox cb = FormElementCreator.getStandardCheckBox (elementID, isRequired, labelText);

          if (eb.getName ().equals ("DELIVERY_USE"))
            cb.selectedProperty ().addListener ( (observable, oldValue, newValue) -> {
              if (newValue)
                _enableDeliveryAddress ();
              else
                _disableDeliveryAddress ();

            });

          v.getChildren ().add (cb);

          grid_Delivery.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();
        }

        if (isRequired)
          RequiredAndErrorHelper.incrementTabCount (eb.getTiteldPaneID ());
      }
    }

    tp_Biller.setContent (grid_Biller);
    tp_InvoiceRecipient.setContent (grid_InvoiceRecipient);
    tp_Delivery.setContent (grid_Delivery);

    ac.getPanes ().addAll (tp_Biller, tp_InvoiceRecipient, tp_Delivery);
    ac.setExpandedPane (tp_Biller);

    this.add (ac, 0, 0);
  }

  private void _disableDeliveryAddress ()
  {
    for (final Node n : grid_Delivery.getChildren ())
    {
      if (n.getClass () == VBox.class)
      {
        for (final Node sn : ((VBox) n).getChildren ())
        {
          if (sn.getClass () == Label.class)
          {

          }

          if (sn.getClass () == TextField.class)
          {

          }
        }
      }
    }
  }

  private void _enableDeliveryAddress ()
  {
    for (final Node n : grid_Delivery.getChildren ())
    {
      if (n.getClass () == VBox.class)
      {
        for (final Node sn : ((VBox) n).getChildren ())
        {
          if (sn.getClass () == Label.class)
          {

          }

          if (sn.getClass () == TextField.class)
          {

          }
        }
      }
    }
  }

  public void incrementBillerCol ()
  {
    billerCol++;

    if (billerCol == Data.COLUMN_COUNT_PER_ROW)
    {
      billerCol = 0;
      billerRow++;
    }
  }

  public void incrementInvoiceRecipientCol ()
  {
    invoiceRecipientCol++;

    if (invoiceRecipientCol == Data.COLUMN_COUNT_PER_ROW)
    {
      invoiceRecipientCol = 0;
      invoiceRecipientRow++;
    }
  }

}
