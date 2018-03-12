package at.mareg.ebi43creator.display.form.dialogs;

import java.util.List;
import java.util.Map.Entry;

import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;

public class ShowRequiredFieldsDialog extends Dialog <ButtonType>
{
  /**
   * With this dialog you can show the missing required fields in the form (if
   * present)
   *
   * @author Martin Regitnig
   */

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

    /*
     * If required fields map size > 0 -> read map and create output for this
     * dialog
     */
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

    /*
     * If line required fields map size > 0 -> read map and append the output
     * for this dialog
     */
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

    /*
     * If surcharge line required fields map size > 0 -> read map and append the
     * output for this dialog
     */
    if (RequiredAndErrorHelper.getSurchargeLineRequiredMapSize () > 0)
    {
      sb.append ("\nEs fehlen folgende Pflichtfelder in den Auf-Abschlags-Einträgen:\n");
      for (final Entry <String, List <String>> e : RequiredAndErrorHelper.getSurchargeLineRequiredMap ().entrySet ())
        for (final String s : e.getValue ())
          sb.append ("   " + RequiredAndErrorHelper.getTranslationForID (s));

      sb.append (System.getProperty ("line.separator"));
    }

    /*
     * If discount line required fields map size > 0 -> read map and append the
     * output for this dialog
     */
    if (RequiredAndErrorHelper.getDiscountLineRequiredMapSize () > 0)
    {
      sb.append ("\nEs fehlen folgende Pflichtfelder in den Skontoeinträgen:\n");
      for (final Entry <String, List <String>> e : RequiredAndErrorHelper.getDiscountLineRequiredMap ().entrySet ())
        for (final String s : e.getValue ())
          sb.append ("   " + RequiredAndErrorHelper.getTranslationForID (s));

      sb.append (System.getProperty ("line.separator"));
    }

    /*
     * Write to text area and show
     */
    if (sb.toString ().isEmpty ())
      showMissingRequiredArea.setText ("Alle Pflichtfelder befüllt");
    else
      showMissingRequiredArea.setText (sb.toString ());

    this.getDialogPane ().setContent (showMissingRequiredArea);

    final ButtonType ok = ButtonType.OK;
    this.getDialogPane ().getButtonTypes ().add (ok);

  }
}
