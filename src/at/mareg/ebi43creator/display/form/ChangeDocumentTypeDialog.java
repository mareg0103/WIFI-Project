package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.enums.EDocumentTypes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ChangeDocumentTypeDialog extends Dialog <ButtonType>
{
  private String currentType;
  private ResourceManager rm;

  public ChangeDocumentTypeDialog (final String current, final ResourceManager resman)
  {
    super ();

    rm = resman;
    currentType = current;

    _showDialog ();
  }

  private void _showDialog ()
  {
    this.setTitle ("Dokumententyp ändern");

    VBox v = new VBox ();

    ToggleGroup tg = new ToggleGroup ();

    for (final EDocumentTypes e : EDocumentTypes.values ())
    {
      RadioButton r = new RadioButton (e.getElementText ());

      r.setId (e.getElementID ());
      r.disableProperty ().set (currentType.equalsIgnoreCase (r.getId ()) ? true : false);
      r.setPrefWidth (150d);
      r.setToggleGroup (tg);

      v.getChildren ().add (r);
    }

    tg.selectedToggleProperty ().addListener (new ChangeListener <Toggle> ()
    {

      public void changed (ObservableValue <? extends Toggle> observable, Toggle oldValue, Toggle newValue)
      {
        rm.getInvoiceData ().setDocumentType (((RadioButton) newValue).getId ());
      }

    });

    this.getDialogPane ().setContent (v);

    ButtonType ok = ButtonType.OK;
    ButtonType cancel = ButtonType.CANCEL;
    this.getDialogPane ().getButtonTypes ().addAll (ok, cancel);
  }
}
