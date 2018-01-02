package at.mareg.ebi43creator.display.utilities;

import java.util.Map;
import java.util.TreeMap;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.enums.EFormFields;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class HelpArea extends TextArea
{

  private final Map <String, String> helpTexts;

  public HelpArea ()
  {
    helpTexts = new TreeMap <> ();

    initMap ();

    this.setEditable (false);
    this.setBackground (new Background (new BackgroundFill (Data.HELPTEXTAREA_BACKGROUNDCOLOR, null, null)));
    this.setStyle ("-fx-text-fill: " + Data.HELPTEXTAREA_FOREGROUNDCOLORASHEX);
    this.setPrefSize (Data.HELPTEXTAREA_WIDTH, Data.HELPTEXTAREA_HEIGHT);
    this.setPadding (Data.HELPTEXTAREA_PADDING);
    this.setWrapText (true);
    this.setFocusTraversable (false);
  }

  private void initMap ()
  {
    helpTexts.put (EFormFields.BILLER_NAME.getID (),
                   "Bitte hier den Namen/Firmennamen des Rechnungsleger (von Ihnen) eingeben");
    helpTexts.put (EFormFields.BILLER_STREET.getID (),
                   "Bitte hier die Strasse des Rechnungsleger (von Ihnen) eingeben");
    helpTexts.put (EFormFields.BILLER_ZIP.getID (),
                   "Bitte hier die Postleitzahl des Rechnungsleger (von Ihnen) eingeben");
    helpTexts.put (EFormFields.INVOICERECIPIENT_NAME.getID (),
                   "Bitte hier den Namen/Firmennamen des Rechnungsempfängers eingeben");
    helpTexts.put (EFormFields.INVOICERECIPIENT_STREET.getID (),
                   "Bitte hier die Strasse des Rechnungsempfängers eingeben");
    helpTexts.put (EFormFields.INVOICERECIPIENT_ZIP.getID (),
                   "Bitte hier die Postleitzahl des Rechnungsempfängers eingeben");
  }

  public void show (final String key)
  {
    final String s = helpTexts.get (key);

    if (s != null)
      this.setText (s);
    else
      this.setText ("Kein Hilfetext verfügbar");
  }

}
