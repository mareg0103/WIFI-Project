package at.mareg.ebi43creator.display.form;

import java.util.List;

import at.mareg.ebi43creator.display.form.invoicelines.InvoiceLine;
import at.mareg.ebi43creator.display.form.invoicelines.InvoiceLineArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.TextFieldHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DetailsPane extends BorderPane
{
  /**
   * Pane to enter the detail lines of the document
   *
   * @author Martin Regitnig
   */
  /*
   * Instance of ResourceManager because this pane doesn't extend BasePane
   */
  private final ResourceManager rm;

  /*
   * Pane elements
   */
  private InvoiceLineArea invoiceLineArea;
  private GridPane grid;
  private TextField totalNetField;
  private TextField totalGrossField;
  private Label vatidBillerLabel;
  private TextField vatidBillerField;
  private Label vatidInvoiceRecipientLabel;
  private TextField vatidInvoiceRecipientField;
  private int rightAreaRow;

  /*
   * Pane variables
   */
  private Double totalNet;
  private Double totalGross;

  public DetailsPane (final ResourceManager resman)
  {
    rm = resman;

    init ();
  }

  /*
   * Create details pane. This pane contains the invoice lines
   */
  private void init ()
  {
    rightAreaRow = 0;

    /*
     * Center content of BorderPane
     */
    invoiceLineArea = new InvoiceLineArea (rm);

    /*
     * Right content of BorderPane
     */
    grid = new GridPane ();
    grid.setPadding (Data.BASEPANE_PADDING);
    grid.setHgap (Data.BASEPANE_HVGAP);
    grid.setVgap (grid.getHgap ());

    for (final EFormElement eb : EFormElement.values ())
    {
      if (eb.getTiteldPaneID ().equals (Data.DETAILS_RIGHT_AREA))
      {
    	final boolean isRequired = eb.isRequired();
        final String elementID = eb.getID ();
        final String labelText = eb.getLabelText ();

        if (eb.getType ().equals (Data.ELEMENTTYPE_BUTTON))
        {
          final Button b = FormElementCreator.getStandardButton (elementID, labelText);
          b.setPrefWidth (Data.DETAILS_RIGHT_AREA_COMPONENT_WIDTH);
          b.setOnAction (e -> {
            rm.getInvoiceData ().getDetails ().addEmptyListLineItem ();
          });
          grid.add (b, 0, rightAreaRow);

          rightAreaRow++;
        }

        if (eb.getType ().equals (Data.ELEMENTTYPE_TEXTFIELD))
        {
          final VBox v = new VBox ();

          final TextField t = FormElementCreator.getStandardTextField (elementID, eb.isRequired ());
          t.setPrefWidth (Data.DETAILS_RIGHT_AREA_COMPONENT_WIDTH);

          if (elementID.equals (EFormElement.DETAILS_RIGHT_TOTALNETAMOUND.getID ()))
          {
            v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, new Insets (20, 0, 0, 0)));

            totalNetField = t;
            totalNetField.setEditable (false);
          }

          if (elementID.equals (EFormElement.DETAILS_RIGHT_TOTALGROSSAMOUND.getID ()))
          {
            v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));

            totalGrossField = t;
            totalGrossField.setEditable (false);
          }

          if (elementID.equals (EFormElement.DETAILS_RIGHT_VATID_BILLER.getID ()))
          {
            vatidBillerLabel = FormElementCreator.getStandardLabel (labelText + (isRequired ? "*" : ""), new Insets (20, 0, 0, 0));
            v.getChildren ().add (vatidBillerLabel);

            vatidBillerField = t;
          }

          if (elementID.equals (EFormElement.DETAILS_RIGHT_VATID_INVOICERECIPIENT.getID ()))
          {
            vatidInvoiceRecipientLabel = FormElementCreator.getStandardLabel (labelText + (isRequired ? "*" : ""), new Insets (20, 0, 0, 0));
            v.getChildren ().add (vatidInvoiceRecipientLabel);

            vatidInvoiceRecipientField = t;
          }
          
          if (eb.isRequired())
        	  RequiredAndErrorHelper.addRequiredField(eb.getTiteldPaneID(), elementID);

          v.getChildren ().add (t);
          grid.add (v, 0, rightAreaRow);

          rightAreaRow++;
        }
      }
    }

    this.setCenter (invoiceLineArea);
    this.setRight (grid);
  }

  /*
   * Show total net and total gross amount in right area
   */
  public void refreshTotalNetAndGrossAmount ()
  {
    totalNet = Double.valueOf (0);
    totalGross = Double.valueOf (0);

    final List <InvoiceLine> ill = invoiceLineArea.getInvoiceLineList ();

    if (ill != null && ill.size () > 0)
      for (final InvoiceLine il : invoiceLineArea.getInvoiceLineList ())
      {
        totalNet = Double.valueOf (totalNet.doubleValue () + il.getTotalNetAmountWithSurcharge ());
        totalGross = Double.valueOf (totalGross.doubleValue () + il.getTotalGrossAmount ());
      }

//    final EFormElement vatidBiller = EFormElement.DETAILS_RIGHT_VATID_BILLER;
//    final EFormElement vatidInvoiceRecipient = EFormElement.DETAILS_RIGHT_VATID_INVOICERECIPIENT;
//
//    vatidBiller.setIsRequired (totalGross.doubleValue () >= 400 ? true : false);
//    vatidInvoiceRecipient.setIsRequired (totalGross.doubleValue () >= 10000 ? true : false);
//
//    FormElementCreator.setVisibleLabelStatus (vatidBillerLabel, vatidBiller);
//    FormElementCreator.setVisibleTextFieldStatus (vatidBillerField, vatidBiller);
//    FormElementCreator.setVisibleLabelStatus (vatidInvoiceRecipientLabel, vatidInvoiceRecipient);
//    FormElementCreator.setVisibleTextFieldStatus (vatidInvoiceRecipientField, vatidInvoiceRecipient);

    totalNetField.setText (TextFieldHelper.getTwoDecimalsStringFromDouble (totalNet));
    totalGrossField.setText (TextFieldHelper.getTwoDecimalsStringFromDouble (totalGross));

    rm.getSurchargeDiscountPane ().refreshTotalNetandTotalGross ();
  }

  /*
   * Getter / Setter
   */
  public InvoiceLineArea getInvoiceLineArea ()
  {
    return invoiceLineArea;
  }

  public Double getInvoiceLinesTotalNet ()
  {
    return totalNet;
  }

  public Double getInvoiceLinesTotalGross ()
  {
    return totalGross;
  }
  public GridPane getRightAreaGrid ()
  {
	  return grid;
  }
  
  public void setVatIdBillerFieldText (final String text)
  {
	  if (text == null)
		  vatidBillerField.setText ("");
	  else
		  vatidBillerField.setText (text);
	  
	  if (text != null)
	  {
		  EFormElement e = EFormElement.getFromIDOrNull(vatidBillerField.getId());
		  String eid = e.getID();
		  RequiredAndErrorHelper.removeRequiredField(e.getTiteldPaneID(), eid);
	  }

	  FormElementCreator.setVisibleTextFieldStatus(vatidBillerField, EFormElement.DETAILS_RIGHT_VATID_BILLER);
  }
  
  public void setVatIdInvoiceRecipientFieldText (final String text)
  {
	  if (text == null)
		  vatidInvoiceRecipientField.setText ("");
	  else
		  vatidInvoiceRecipientField.setText (text);
	  
	  if (text != null)
	  {
		  EFormElement e = EFormElement.getFromIDOrNull(vatidInvoiceRecipientField.getId());
		  String eid = e.getID();
		  RequiredAndErrorHelper.removeRequiredField(e.getTiteldPaneID(), eid);
	  }

	  FormElementCreator.setVisibleTextFieldStatus(vatidInvoiceRecipientField, EFormElement.DETAILS_RIGHT_VATID_INVOICERECIPIENT);
  }
}
