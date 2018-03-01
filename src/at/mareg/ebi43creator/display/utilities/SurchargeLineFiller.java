package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.display.form.surcharge.SurchargeLine;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import at.mareg.ebi43creator.invoicedata.reductionandsurcharge.Surcharge;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public final class SurchargeLineFiller
{
  private static InvoiceData invoiceData;

  private SurchargeLineFiller ()
  {}

  /*
   * Fill invoice line with loaded data
   */
  @SuppressWarnings ("unchecked")
  public static void fillLineWithLoadedData (final SurchargeLine sl)
  {
    final Surcharge s = sl.getSurcharge ();
    final GridPane grid = sl.getGridPane ();

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
                case SURCHARGE_VALUE:
                  final Double d = s.getAmount ();
                  sl.setSurchargeValue (d);
                  value = (d == null ? null : String.valueOf (d.intValue ()));
                  break;

                case SURCHARGE_VAT:
                  final Integer vat = s.getVatRate ();
                  sl.setVatRate (vat);
                  value = vat.toString ();
                  break;

                case SURCHARGE_COMMENT:
                  final String comment = s.getComment ();
                  sl.setComment (comment);
                  value = comment;
                  break;

                default:
                  value = null;
              }

              if (vn.getClass () == TextField.class)
              {
                final TextField t = (TextField) vn;

                if (element == EFormElement.SURCHARGE_VALUE)
                  t.setText (TextFieldHelper.getTwoDecimalsStringFromString (value));
                else
                  t.setText (value);

                if (elementIsRequired)
                  RequiredAndErrorHelper.removeRequiredFieldForSurchargeLine (sl.toString (), elementID);

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

    sl.calculateLine ();
  }

  public static void setInvoiceData (final InvoiceData id)
  {
    invoiceData = id;
  }
}
