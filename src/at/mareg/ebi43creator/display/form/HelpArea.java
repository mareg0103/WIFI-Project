package at.mareg.ebi43creator.display.form;

import java.util.Map;
import java.util.TreeMap;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.enums.EFormFields;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class HelpArea extends TextArea
{

	private final Map<String, String> helpTexts;

	public HelpArea ()
	{
		helpTexts = new TreeMap<> ();

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

		helpTexts.put (EFormFields.ORDER_ID.getID (),
				"Bitte geben Sie hier die Auftragsreferenz ein, welche Ihnen vom Auftraggeber mitgeteilt wurde. Die Auftragsreferenzen folgender Empfänger werden unterstützt:\n\n"
						+ "Bund:\nEinkäufergruppe (dreistellig alphanumerisch);\nBestellnummer (zehnstellig, numerisch, beginnend mit 47);\n\n"
						+ "ÖBB:\nMuss immer mit OEBB/ beginnen gefolgt von...\n"
						+ "Auftragsnummer (zehnstellig, numerisch, beginnend mit 30, 43, 44, 45 oder 49);\n"
						+ "Business Center Adresse (BCA, dreistellig, numerisch);\n\n" + "...");

		helpTexts.put (EFormFields.SUPPLIER_ID.getID (),
				"Bitte geben Sie hier die Lieferantennummer ein, welche Ihnen vom Auftraggeber mitgeteilt wurde.");

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

		helpTexts.put (EFormFields.FROM_DATE.getID (),
				"Bitte geben sie hier das Lieferdatum der Waren bzw. das Startdatum der Leistungserbringung an.\n\n"
						+ "Das Feld \"Leistungszeitraum Bis\" wird aktiv, sobald hier ein Datum eingetragen ist.");

		helpTexts.put (EFormFields.TO_DATE.getID (),
				"Bitte geben sie hier das Enddatum der Leistungserbringung an.\n\nEine Auswahl eines Datums vor "
						+ "dem Datum in \"Leistungszeitraum Von\" ist nicht möglich.\n\n"
						+ "Bei Korrektur des Startdatums des Leistungszeitraumes auf ein Datum nach "
						+ "dem Datum im \"Bis\"-Feld wird das \"Bis\"-Feld geleert.");

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