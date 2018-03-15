package at.mareg.ebi43creator.display.utilities;

import java.util.List;

import at.mareg.ebi43creator.display.form.invoicelines.InvoiceLine;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.details.ListLineItem;
import at.mareg.ebi43creator.invoicedata.details.SurchargeListLineItem;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public final class InvoiceLineFiller
{
  /**
   * Fills the form with loaded invoice line data
   *
   * @author Martin Regitnig
   */

   /*
   * No instantiation, only static methods
   */
  private InvoiceLineFiller ()
  {}

  /*
   * Fill invoice line with loaded data
   */
  @SuppressWarnings ("unchecked")
  public static void fillLineWithLoadedData (final InvoiceLine il)
  {
    final ListLineItem lli = il.getListLineItem ();
    final List <SurchargeListLineItem> l = lli.getSurcharge ();
    final GridPane grid = il.getGridPane ();

    for (final Node n : grid.getChildren ())
    {
      if (n.getClass () == VBox.class)
      {
        for (final Node vn : ((VBox) n).getChildren ())
        {
          if (vn.getClass () != Label.class)
          {
            final String elementID = vn.getId ();
            final EFormElement element = EFormElement.getFromIDOrNull (elementID);
            String value = "";

            if (element != null)
            {
              final boolean elementIsRequired = element.isRequired ();

              switch (element)
              {
                case DETAILS_LINE_ORDERPOSITIONNUMER:
                  final Integer ilo = lli.getOrderPositionNumber ();
                  il.setOrderPositionNumber (ilo);
                  value = (ilo == null ? null : String.valueOf (ilo.intValue ()));
                  break;

                case DETAILS_LINE_QUANTITY:
                  final Double dlq = lli.getQuantity ().getQuantity ();
                  il.setQuantity (dlq);
                  value = (dlq == null ? null : TextFieldHelper.getFourDecimalsStringFromDouble (dlq.doubleValue ()));
                  break;

                case DETAILS_LINE_UNIT:
                  final String s = lli.getQuantity ().getUnit ();
                  il.setUnit (s);
                  value = s;
                  break;

                case DETAILS_LINE_UNITPRICE:
                  final Double dlu = lli.getUnitPrice ();
                  il.setUnitprice (dlu);
                  value = (dlu == null ? null : TextFieldHelper.getFourDecimalsStringFromDouble (dlu.doubleValue ()));
                  break;

                case DETAILS_LINE_DESCRIPTION:
                  value = lli.getDescription ();
                  final String sld = lli.getDescription ();
                  value = sld;
                  break;

                case DETAILS_LINE_SURCHARGE:
                  final Double dls = (l == null ? null : l.get (0).getAmount ());
                  il.setSurcharge (dls);
                  value = (dls == null ? null : TextFieldHelper.getTwoDecimalsStringFromDouble (dls.doubleValue ()));
                  break;

                case DETAILS_LINE_SURCHARGE_DESCRIPTION:
                  final String dlsc = (l == null ? null : l.get (0).getComment ());
                  il.setComment (dlsc);
                  value = dlsc;
                  break;

                case DETAILS_LINE_VAT:
                  final Integer ilv = lli.getVatRate ();
                  il.setVatRate (ilv);
                  value = lli.getVatRate ().toString ();
                  break;

                case DETAILS_LINE_TAXEXEMPTION_REASON:
                  final String tr = lli.getTaxExemption ();
                  if (tr == null)
                    value = "";
                  else
                  {
                    value = tr;

                    il.setTaxExemptionCheckBoxToSelected ();
                    il.enableTaxExemption ();

                    RequiredAndErrorHelper.removeRequiredFieldForLine (Integer.valueOf (il.getInvoiceLineNumber ()),
                                                                       elementID);
                  }
                  value = lli.getTaxExemption ();
                  break;

                default:
                  value = null;
              }

              if (elementIsRequired)
                RequiredAndErrorHelper.removeRequiredFieldForLine (Integer.valueOf (il.getInvoiceLineNumber ()),
                                                                   elementID);

              if (vn.getClass () == TextField.class)
              {
                final TextField t = (TextField) vn;
                t.setText (value);
                t.setStyle ("-fx-control-inner-background: #" +
                            (value == null ? (elementIsRequired ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK)
                                           : Data.BACKGROUND_HEX_OK));
              }

              if (vn.getClass () == TextArea.class)
              {
                final TextArea t = (TextArea) vn;
                t.setText (value);
                t.setStyle ("-fx-control-inner-background: #" +
                            (value == null ? (elementIsRequired ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK)
                                           : Data.BACKGROUND_HEX_OK));
              }

              if (vn.getClass () == ComboBox.class)
              {
                if (elementID.equals (EFormElement.DETAILS_LINE_VAT.getID ()))
                  ((ComboBox <String>) vn).getSelectionModel ().select (value + "%");

                if (elementID.equals (EFormElement.DETAILS_LINE_UNIT.getID ()))
                  ((ComboBox <String>) vn).getSelectionModel ().select (value);
              }
            }
          }
        }
      }
    }

    il.calculateLine ();
  }
}
