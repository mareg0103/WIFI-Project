package at.mareg.ebi43creator.display.form.dialogs;

import java.util.List;
import java.util.Map.Entry;

import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;

public class ShowRequiredFieldsDialog extends Dialog <ButtonType>
{
  public ShowRequiredFieldsDialog ()
  {
    init ();
  }

  /*
   * Initialize required fields dialog
   */
  public void init ()
  {
    this.setTitle ("Fehlende Pflichtfelder");

    final TextArea showMissingRequiredArea = new TextArea ();
    final StringBuilder sb = new StringBuilder ();

    if (RequiredAndErrorHelper.getRequiredMapSize () > 0)
    {
      sb.append ("Folgende Pflichtfelder sind noch nicht befüllt:\n");

      for (final Entry <String, List <String>> e : RequiredAndErrorHelper.getRequiredMap ().entrySet ())
      {
        for (final String s : e.getValue ())
          sb.append (RequiredAndErrorHelper.getTranslationForID (s) + "\n");

        sb.append (System.getProperty ("line.separator"));
      }
    }

    if (RequiredAndErrorHelper.getLineRequiredMapSize () > 0)
    {
      sb.append ("\nEs fehlen folgende Pflichtfelder in Rechnungszeilen:\n");

      for (final Entry <Integer, List <String>> e : RequiredAndErrorHelper.getLineRequiredMap ().entrySet ())
      {
        sb.append ("In Rechnungszeile " + e.getKey ().intValue () + " fehlt/fehlen:\n");
        for (final String s : e.getValue ())
          sb.append ("   " + RequiredAndErrorHelper.getTranslationForID (s) + "\n");

        sb.append (System.getProperty ("line.separator"));
      }
    }

    if (RequiredAndErrorHelper.getSurchargeLineRequiredMapSize () > 0)
    {
      sb.append ("\nEs fehlen folgende Pflichtfelder in den Auf-Abschlags-Einträgen:\n");
      for (final Entry <String, List <String>> e : RequiredAndErrorHelper.getSurchargeLineRequiredMap ().entrySet ())
        for (final String s : e.getValue ())
          sb.append ("   " + RequiredAndErrorHelper.getTranslationForID (s));

      sb.append (System.getProperty ("line.separator"));
    }

    if (RequiredAndErrorHelper.getDiscountLineRequiredMapSize () > 0)
    {
      sb.append ("\nEs fehlen folgende Pflichtfelder in den Skontoeinträgen:\n");
      for (final Entry <String, List <String>> e : RequiredAndErrorHelper.getDiscountLineRequiredMap ().entrySet ())
        for (final String s : e.getValue ())
          sb.append ("   " + RequiredAndErrorHelper.getTranslationForID (s));

      sb.append (System.getProperty ("line.separator"));
    }

    if (sb.toString ().isEmpty ())
      showMissingRequiredArea.setText ("Alle Pflichtfelder befüllt");
    else
      showMissingRequiredArea.setText (sb.toString ());

    this.getDialogPane ().setContent (showMissingRequiredArea);

    final ButtonType ok = ButtonType.OK;
    this.getDialogPane ().getButtonTypes ().add (ok);

  }
}
