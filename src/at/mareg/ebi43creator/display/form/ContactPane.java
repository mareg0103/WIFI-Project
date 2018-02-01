package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ContactPane extends BasePane
{
  /**
   * Pane to enter contact specific data for biller, invoice recipient and
   * delivery
   * 
   * @author Martin Regitnig
   */

  /*
   * Pane elements
   */
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

  /*
   * Create contact pane on base EFormElement enum. This pane contains address
   * data for biller, invoice recipient and delivery
   */
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

    for (final EFormElement eb : EFormElement.values ())
    {
      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_BILLER))
      {
        final boolean isRequired = eb.isRequired ();
        final String labelText = eb.getLabelText () + (isRequired ? "*" : "");
        final String elementID = eb.getID ();
        final String elementType = eb.getType ();

        if (elementType == Data.ELEMENTTYPE_TEXTFIELD)
        {
          final VBox v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
          v.getChildren ().add (FormElementCreator.getStandardTextField (elementID, isRequired));

          grid_Biller.add (v, billerCol, billerRow);
          VBoxHelper.structureVBox (v);

          incrementBillerCol ();
        }

        if (isRequired)
          RequiredAndErrorHelper.addRequiredField (eb.getTiteldPaneID (), elementID);
      }

      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_INVOICERECIPIENT))
      {

        final boolean isRequired = eb.isRequired ();
        final String labelText = eb.getLabelText () + (isRequired ? "*" : "");
        final String elementID = eb.getID ();
        final String elementType = eb.getType ();

        if (elementType == Data.ELEMENTTYPE_TEXTFIELD)
        {
          final VBox v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
          v.getChildren ().add (FormElementCreator.getStandardTextField (elementID, isRequired));

          grid_InvoiceRecipient.add (v, invoiceRecipientCol, invoiceRecipientRow);
          VBoxHelper.structureVBox (v);

          incrementInvoiceRecipientCol ();
        }

        if (isRequired)
          RequiredAndErrorHelper.addRequiredField (eb.getTiteldPaneID (), elementID);
      }

      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_DELIVERY))
      {
        final boolean isRequired = eb.isRequired ();
        final String labelText = eb.getLabelText () + (isRequired ? "*" : "");
        final String elementID = eb.getID ();
        final String elementType = eb.getType ();

        if (elementType == Data.ELEMENTTYPE_TEXTFIELD)
        {
          final VBox v = new VBox ();

          if (eb.getName ().equals ("DELIVERY_ID"))
          {
            v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));
            v.getChildren ().add (FormElementCreator.getStandardTextField (elementID, isRequired));
          }
          else
          {
            v.getChildren ().add (FormElementCreator.getDisabledLookingLabel (labelText, null));
            v.getChildren ().add (FormElementCreator.getDisabledTextField (elementID, isRequired));
          }

          grid_Delivery.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();
        }

        if (elementType == Data.ELEMENTTYPE_CHECKBOX)
        {
          final VBox v = new VBox ();

          v.getChildren ().add (FormElementCreator.getStandardLabel ("", null));

          CheckBox cb = FormElementCreator.getStandardCheckBox (elementID, isRequired, labelText);

          if (eb.getName ().equals ("DELIVERY_USE"))
          {
            cb.selectedProperty ().addListener ( (observable, oldValue, newValue) -> {
              rm.getInvoiceData ().getDelivery ().enableDeliveryAddress (newValue);

              if (newValue)
                _enableDeliveryAddress ();
              else
                _disableDeliveryAddress ();

            });
            cb.hoverProperty ().addListener (observable -> {
              rm.getHelpArea ().show (cb.getId ());
            });
          }

          v.getChildren ().add (cb);

          grid_Delivery.add (v, col, row);
          VBoxHelper.structureVBox (v);

          incrementCol ();
        }

        if (isRequired)
          RequiredAndErrorHelper.addRequiredField (eb.getTiteldPaneID (), elementID);
      }
    }

    tp_Biller.setContent (grid_Biller);
    tp_InvoiceRecipient.setContent (grid_InvoiceRecipient);
    tp_Delivery.setContent (grid_Delivery);

    ac.getPanes ().addAll (tp_Biller, tp_InvoiceRecipient, tp_Delivery);
    ac.setExpandedPane (tp_Biller);

    this.add (ac, 0, 0);
  }

  /*
   * If CheckBox "DELIVERY_USE" (Abweichende Lieferanschrift verwenden) is set
   * to unselected, disable and clear all fields for delivery address except
   * "DELIVERY_ID" (Lieferscheinnummer)
   */
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
            String checkLabelText = ((Label) sn).getText ();

            if (!checkLabelText.equals (EFormElement.DELIVERY_ID.getLabelText ()))
              ((Label) sn).setTextFill (Color.DARKGRAY);
          }

          if (sn.getClass () == TextField.class)
          {
            String id = sn.getId ();
            final TextField t = (TextField) sn;

            if (id != null && !id.equals (EFormElement.DELIVERY_ID.getID ()))
            {
              t.disableProperty ().set (true);
              t.setText ("");
              t.setStyle ("-fx-control-inner-background: #" +
                          (EFormElement.getFromIDOrNull (id).isRequired () ? Data.BACKROUND_HEX_REQUIRED
                                                                           : Data.BACKGROUND_HEX_OK));
            }
          }
        }
      }
    }
  }

  /*
   * If CheckBox "DELIVERY_USE" (Abweichende Lieferanschrift verwenden) is set
   * to selected, enable all fields for delivery address
   */
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
            ((Label) sn).setTextFill (Color.BLACK);
          }

          if (sn.getClass () == TextField.class)
          {
            ((TextField) sn).disableProperty ().set (false);
          }
        }
      }
    }
  }

  /*
   * Calculate new column and row values for mostly automated creation of the
   * biller part of this contact data form
   */
  public void incrementBillerCol ()
  {
    billerCol++;

    if (billerCol == Data.COLUMN_COUNT_PER_ROW)
    {
      billerCol = 0;
      billerRow++;
    }
  }

  /*
   * Calculate new column and row values for mostly automated creation of the
   * invoice recipient part of this contact data form
   */
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
