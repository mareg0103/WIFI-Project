package at.mareg.ebi43creator.display.form.dialogs;

import java.util.List;
import java.util.Map.Entry;

import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;

public class ShowErrorFieldsDialog extends Dialog <ButtonType>
{
  public ShowErrorFieldsDialog ()
  {
    init ();
  }

  /*
   * Initialize error dialog
   */
  public void init ()
  {
    this.setTitle ("Fehlerhafte Felder");

    final TextArea showErroneousArea = new TextArea ();
    final StringBuilder sb = new StringBuilder ();

    if (RequiredAndErrorHelper.getErrorMapSize () > 0)
    {
      sb.append ("Folgende Felder sind fehlerhaft:\n");

      for (final Entry <String, List <String>> e : RequiredAndErrorHelper.getErrorMap ().entrySet ())
      {
        for (final String s : e.getValue ())
          sb.append (RequiredAndErrorHelper.getTranslationForID (s) + "\n");

        sb.append (System.getProperty ("line.separator"));
      }
    }

    if (sb.toString ().isEmpty ())
      showErroneousArea.setText ("Keine fehlerhaft befüllten Felder vorhanden");
    else
      showErroneousArea.setText (sb.toString ());

    this.getDialogPane ().setContent (showErroneousArea);

    final ButtonType ok = ButtonType.OK;
    this.getDialogPane ().getButtonTypes ().add (ok);
  }
}
