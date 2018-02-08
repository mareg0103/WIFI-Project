package at.mareg.ebi43creator.display.utilities;

import java.time.LocalDate;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public final class FormFiller
{
  private InvoiceData invoiceData;
  private ResourceManager rm;

  public FormFiller (final InvoiceData id, final ResourceManager resman)
  {
    invoiceData = id;
    rm = resman;

    LoadMethodMapper.setInvoiceData (id);
  }

  /*
   * Fill form with data of the loaded XML file
   */
  public void fillFormWithLoadedData ()
  {
    /*
     * Fill order pane
     */
    gridWorker (rm.getOrderPane ().getGridPaneOrder ());

    /*
     * Fill contact pane - biller
     */
    gridWorker (rm.getContactPane ().getGridPaneBiller ());

    /*
     * Fill contact pane - invoice recipient
     */
    gridWorker (rm.getContactPane ().getGridPaneInvoiceRecipient ());

    /*
     * Fill contact pane - delivery
     */
    gridWorker (rm.getContactPane ().getGridPaneDelivery ());

    /*
     * Fill payment pane
     */
    gridWorker (rm.getPaymentPane ().getGridPanePayment ());

    /*
     * Fill details pane
     */
    rm.getDetailsPane ().getInvoiceLineArea ().createInvoiceLineAfterLoading ();
  }

  /*
   * Run through pane an fill elements
   */
  private void gridWorker (final GridPane g)
  {
    for (final Node n : g.getChildren ())
    {
      if (n.getClass () == VBox.class)
      {
        for (final Node vn : ((VBox) n).getChildren ())
        {
          String elementID = vn.getId ();
          EFormElement element = EFormElement.getFromIDOrNull (elementID);

          if (element != null)
          {
            String returnValue = LoadMethodMapper.callMethodFor (element);

            if (returnValue != null)
            {
              if (vn.getClass () == TextField.class)
              {
                TextField t = (TextField) vn;

                t.setText (returnValue);
                t.setStyle ("-fx-control-inner-background: #" + Data.BACKGROUND_HEX_OK);
              }

              if (vn.getClass () == TextArea.class)
              {
                TextArea t = (TextArea) vn;

                t.setText (returnValue);
                t.setStyle ("-fx-control-inner-background: #" + Data.BACKGROUND_HEX_OK);
              }

              if (vn.getClass () == DatePicker.class)
              {
                DatePicker dp = (DatePicker) vn;

                dp.setValue (LocalDate.parse (returnValue));
                dp.setStyle ("-fx-control-inner-background: #" + Data.BACKGROUND_HEX_OK);
              }

              if (element.isRequired ())
                RequiredAndErrorHelper.removeRequiredField (element.getTiteldPaneID (), elementID);
            }
          }
        }
      }
    }

  }
}
