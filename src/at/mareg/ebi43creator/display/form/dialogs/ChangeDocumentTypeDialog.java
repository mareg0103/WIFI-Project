package at.mareg.ebi43creator.display.form.dialogs;

import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.enums.EDocumentType;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ChangeDocumentTypeDialog extends Dialog <ButtonType>
{
  /**
   * With this dialog you can change the type of an document, invoked from
   * DocumentTypePane
   *
   * @author Martin Regitnig
   */

  /*
   * Instance of ResourceManager because this pane doesn't extend BasePane
   */
  private final ResourceManager rm;

  /*
   * Internal variable to store the current document type
   */
  private final String currentType;

  public ChangeDocumentTypeDialog (final String current, final ResourceManager resman)
  {
    super ();

    rm = resman;
    currentType = current;

    _showDialog ();
  }

  /*
   * Create the dialog
   */
  private void _showDialog ()
  {
    this.setTitle ("Dokumententyp ändern");

    final VBox v = new VBox ();
    final ToggleGroup tg = new ToggleGroup ();

    /*
     * Search all document types and create radio buttons for each one, set
     * current document type to disable
     */
    for (final EDocumentType e : EDocumentType.values ())
    {
      final RadioButton r = new RadioButton (e.getElementText ());

      r.setId (e.getElementID ());
      r.disableProperty ().set (currentType.equalsIgnoreCase (r.getId ()) ? true : false);
      r.setPrefWidth (150d);
      r.setToggleGroup (tg);

      v.getChildren ().add (r);
    }

    tg.selectedToggleProperty ()
      .addListener ((ChangeListener <Toggle>) (observable,
                                               oldValue,
                                               newValue) -> rm.getInvoiceData ()
                                                              .setDocumentType (((RadioButton) newValue).getId ()));

    this.getDialogPane ().setContent (v);

    final ButtonType ok = ButtonType.OK;
    final ButtonType cancel = ButtonType.CANCEL;
    this.getDialogPane ().getButtonTypes ().addAll (ok, cancel);
  }
}
