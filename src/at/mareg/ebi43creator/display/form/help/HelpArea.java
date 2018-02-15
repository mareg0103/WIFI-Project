package at.mareg.ebi43creator.display.form.help;

import java.util.Map;
import java.util.TreeMap;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class HelpArea extends TextArea
{
	/**
	 * Class to show help texts to form elements
	 * 
	 * @author Martin Regitnig
	 */

	/*
	 * Map to save the help texts
	 */
	private final Map<String, String> helpTexts;

	public HelpArea ()
	{
		helpTexts = new TreeMap<> ();

		initMap ();

		this.setEditable (false);
		this.setBackground (new Background (new BackgroundFill (Data.HELPTEXTAREA_BACKGROUNDCOLOR, null, null)));
		this.setStyle ("-fx-text-fill: " + Data.HELPTEXTAREA_FOREGROUNDCOLORASHEX);
		this.setPrefSize (Data.HELPTEXTAREA_WIDTH, Data.HELPTEXTAREA_HEIGHT);
		this.setMaxSize (Data.HELPTEXTAREA_WIDTH, Data.HELPTEXTAREA_HEIGHT);
		this.setPadding (Data.HELPTEXTAREA_PADDING);
		this.setWrapText (true);
		this.setFocusTraversable (false);
	}

	/*
	 * Initialize help text map
	 */
	private void initMap ()
	{
		/*
		 * Order pane
		 */
		helpTexts.put (EFormElement.ORDER_ID.getID (),
				"Bitte geben Sie hier die Auftragsreferenz ein, welche Ihnen vom Auftraggeber mitgeteilt wurde. Die Auftragsreferenzen folgender Empfänger werden unterstützt:\n\n"
						+ "Bund:\nEinkäufergruppe (dreistellig alphanumerisch);\nBestellnummer (zehnstellig, numerisch, beginnend mit 47);\n\n"
						+ "ÖBB:\nMuss immer mit OEBB/ beginnen gefolgt von...\n"
						+ "Auftragsnummer (zehnstellig, numerisch, beginnend mit 30, 43, 44, 45 oder 49); ODER\n"
						+ "Business Center Adresse (BCA, dreistellig, numerisch);\n\n" + "...");

		helpTexts.put (EFormElement.ORDER_REFERENCEDATE.getID (), "Hier können Sie das Beauftragsungsdatum eintragen.");

		helpTexts.put (EFormElement.ORDER_DESCRIPTION.getID (),
				"Hier können Sie eine kurze Beschreibung zur Beauftragung eintragen.");

		helpTexts.put (EFormElement.SUPPLIER_ID.getID (),
				"Bitte geben Sie hier die Lieferantennummer ein, welche Ihnen vom Auftraggeber mitgeteilt wurde.");

		helpTexts.put (EFormElement.INVOICE_NUMBER.getID (), "Bitte geben Sie Ihre Rechnungsnummer ein.");

		helpTexts.put (EFormElement.INVOICE_DATE.getID (), "Hier können Sie das Rechnugsdatum angeben. Voreingestellt "
				+ "ist bei Neubeginn immer das aktuelle Datum.\n\nDiese kann bis zu drei Jahre in die Vergangenheit und "
				+ "bis zu sechs Monate in die Zukunft gesetzt werden");

		helpTexts.put (EFormElement.INVOICE_CURRENCY.getID (),
				"Hier ist die Währung eingetragen in welcher die Rechnung "
						+ "erstellt wird.\n\nDerzeit wird nur EURO unterstützt.");

		helpTexts.put (EFormElement.FROM_DATE.getID (),
				"Bitte geben sie hier das Lieferdatum der Waren bzw. das Startdatum der Leistungserbringung an.\n\n"
						+ "Das Feld \"Leistungszeitraum Bis\" wird aktiv, sobald hier ein Datum eingetragen ist.");

		helpTexts.put (EFormElement.TO_DATE.getID (),
				"Bitte geben sie hier das Enddatum der Leistungserbringung an.\n\nEine Auswahl eines Datums vor "
						+ "dem Datum in \"Leistungszeitraum Von\" ist nicht möglich.\n\n"
						+ "Bei Korrektur des Startdatums des Leistungszeitraumes auf ein Datum nach "
						+ "dem Datum im \"Bis\"-Feld wird das \"Bis\"-Feld geleert.");

		helpTexts.put (EFormElement.COMMENT.getID (),
				"Hier können Sie noch eine Mitteilung eingeben, welche für den Empfänger bestimmt ist. Z.B.\n\n"
						+ "Wenn Sie eine Gutschrift erstellen und diese sich auf eine bestimmte Rechnung bezieht oder eine "
						+ "Rechnung mit der Gutschrift storniert werden soll, können Sie hier den entsprechenden Hinweis "
						+ "eingeben.\n\n(Diese Gutschrift storniert die Rechnung \"...\" vom \"...\" mit der internen "
						+ "Dokumentenummer \"...\", weil... (die interne Dokumentennummer befindet sich in der PDF welche "
						+ "nach der Einbringung verschickt wird auf der ersten Seite ganz unten und in der Bestätigungsmail))");

		/*
		 * Contact pane - biller
		 */
		helpTexts.put (EFormElement.BILLER_NAME.getID (),
				"Bitte hier den Namen/Firmennamen des Rechnungsleger (von Ihnen/Ihrer Firma) eingeben.");

		helpTexts.put (EFormElement.BILLER_STREET.getID (),
				"Bitte hier die Strasse des Rechnungsleger (von Ihnen/Ihrer Firma) eingeben.");

		helpTexts.put (EFormElement.BILLER_ZIP.getID (),
				"Bitte hier die Postleitzahl des Rechnungsleger (von Ihnen/Ihrer Firma) eingeben.");

		helpTexts.put (EFormElement.BILLER_TOWN.getID (),
				"Bitte hier die Stadt des Rechnungsleger (von Ihnen/Ihrer Firma) eingeben.");

		helpTexts.put (EFormElement.BILLER_COUNTRY.getID (),
				"Bitte hier das Land des Rechnungsleger (von Ihnen/Ihrer Firma) eingeben.");

		helpTexts.put (EFormElement.BILLER_EMAIL.getID (),
				"Bitte hier die E-Mail-Adresse des Rechnungsleger (von Ihnen/Ihrer Firma) eingeben.\n\n"
						+ "Bitte legen Sie bei dieser E-Mail-Adresse ein besonderes Augenmerk darauf "
						+ "dass diese korrekt geschrieben ist. An diese E-Mail-Adresse wird nach der"
						+ "Einbringung eine Bestätigungsmail mit einer PDF als Anhang verschickt.");

		helpTexts.put (EFormElement.BILLER_PHONE.getID (),
				"Hier können Sie die Telefonnummer des Rechnungsleger (von Ihnen/Ihrer Firma) eingeben.");

		helpTexts.put (EFormElement.BILLER_CONTACT.getID (),
				"Hier können sie eine zuständigen Ansprechperson eingeben.");

		/*
		 * Contact pane - invoice recipient
		 */
		helpTexts.put (EFormElement.INVOICERECIPIENT_NAME.getID (),
				"Bitte hier den Namen/Firmennamen des Rechnungsempfängers eingeben.");

		helpTexts.put (EFormElement.INVOICERECIPIENT_STREET.getID (),
				"Bitte hier die Strasse des Rechnungsempfängers eingeben.");

		helpTexts.put (EFormElement.INVOICERECIPIENT_ZIP.getID (),
				"Bitte hier die Postleitzahl des Rechnungsempfängers eingeben.");

		helpTexts.put (EFormElement.INVOICERECIPIENT_TOWN.getID (),
				"Bitte hier die Stadt des Rechnungsempfängers eingeben.");

		helpTexts.put (EFormElement.INVOICERECIPIENT_COUNTRY.getID (),
				"Bitte hier das Land des Rechnungsempfängers eingeben.");

		helpTexts.put (EFormElement.INVOICERECIPIENT_EMAIL.getID (),
				"Hier können Sie eine E-Mail-Adresse des Rechnungsempfängers eingeben");

		helpTexts.put (EFormElement.INVOICERECIPIENT_PHONE.getID (),
				"Hier können Sie eine Telefonnummer des Rechnungsempfängers eingeben.");

		helpTexts.put (EFormElement.INVOICERECIPIENT_CONTACT.getID (),
				"Hier können sie eine zuständige Ansprechperson beim Rechnungsempfänger eingeben.");

		/*
		 * Contact pane - delivery
		 */
		helpTexts.put (EFormElement.DELIVERY_ID.getID (),
				"Hier können Sie eine Lieferscheinnummer eingeben, auf welche sich die Rechnung bezieht.");

		helpTexts.put (EFormElement.DELIVERY_USE.getID (), "Aktivieren Sie diese Otion, falls die Liefer- bzw. "
				+ "die Leistungsanschrift von der Adresse des Rechnungsempfängers abweicht.");

		helpTexts.put (EFormElement.DELIVERY_NAME.getID (),
				"Bitte hier den Namen/Firmennamen der Lieferung- oder leistungsempfangenden Stelle eingeben.");

		helpTexts.put (EFormElement.DELIVERY_STREET.getID (),
				"Bitte hier die Strasse der Lieferung- oder leistungsempfangenden Stelle eingeben.");

		helpTexts.put (EFormElement.DELIVERY_ZIP.getID (),
				"Bitte hier die Postleitzahl der Lieferung- oder leistungsempfangenden Stelle eingeben.");

		helpTexts.put (EFormElement.DELIVERY_TOWN.getID (),
				"Bitte hier die Stadt der Lieferung- oder leistungsempfangenden Stelle eingeben.");

		helpTexts.put (EFormElement.DELIVERY_COUNTRY.getID (),
				"Bitte hier das Land der Lieferung- oder leistungsempfangenden Stelle eingeben.");

		helpTexts.put (EFormElement.DELIVERY_EMAIL.getID (),
				"Hier können Sie eine E-Mail-Adresse der Lieferung- oder leistungsempfangenden Stelle eingeben");

		helpTexts.put (EFormElement.DELIVERY_PHONE.getID (),
				"Hier können Sie eine Telefonnummer der Lieferung- oder leistungsempfangenden Stelle eingeben.");

		helpTexts.put (EFormElement.DELIVERY_CONTACT.getID (),
				"Hier können sie eine zuständige Ansprechperson bem der Lieferung- oder leistungsempfangenden Stelle eingeben.");

		/*
		 * Payment pane
		 */
		helpTexts.put (EFormElement.PAYMENT_BIC.getID (),
				"Hier können sie die BIC des Rechnungslegers (von Ihnen/Ihrer Firma) eingeben.");

		helpTexts.put (EFormElement.PAYMENT_IBAN.getID (),
				"Bitte geben Sie hier die IBAN des Rechnungslegers (von Ihnen/Ihrer Firma) ein.");

		helpTexts.put (EFormElement.PAYMENT_ACCOUNTOWNER.getID (),
				"Hier können sie die Namen des Kontoinhabers beim Rechnungsleger (von Ihnen/Ihrer Firma) eingeben.");

		/*
		 * Details pane - invoice line
		 */
		helpTexts.put (EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER.getID (), "Die \"Pos.Nr. der Bestellung\" ist "
				+ "nur aktiv, weil als Auftragsreferenz eine Bestellnummer des Bundes vorliegt.\n\nIn diesem Feld muss "
				+ "die Bestellpositionsnummer der Beauftragung angegeben werden, auf welche sich diese Zeile bezieht.");

		helpTexts.put (EFormElement.DETAILS_LINE_QUANTITY.getID (),
				"Bitte geben Sie hier die zu verechnende/gutzuschreibenden Menge an.");

		helpTexts.put (EFormElement.DETAILS_LINE_UNIT.getID (),
				"Bitte wählen Sie hier die entsprechende Mengeneinheit (Stück, Stunde, etc.) aus.");

		helpTexts.put (EFormElement.DETAILS_LINE_UNITPRICE.getID (),
				"Bitte geben Sie hier den Einzelpreis netto der zu verrechnenden/gutzuschreibenden Mengeneinheit an.");

		helpTexts.put (EFormElement.DETAILS_LINE_DESCRIPTION.getID (),
				"Bitte geben Sie hier die Beschreibung der zu verrechnenden/gutzuschreibenden Mengeneinheit an.\n\nWenn "
						+ "Sie eine umfangreiche Originalrechnung haben und hier nur pro Steuersatz eine Zeile anlegen, haben Sie "
						+ "hier die Möglichkeit auf die Detailaufstellung in der Beilage hinzuweisen. Z.B.\n\nDiverse Arbeiten "
						+ "(Details siehe beigefügte Aufstellung)\n\nBei der Einbringung auf \"https://www.erechnung.gv.at\" "
						+ "können Sie dann die entsprechnden Beilage z.B. im Format PDF mit hochladen.");

		helpTexts.put (EFormElement.DETAILS_LINE_TOTALNET.getID (),
				"Hier ersehen Sie die Gesamtnettosumme der Rechnungszeile. Diese erechnet sich aus:\n\nMenge * Einzelpreis\n\n"
						+ "Diese Zeile ist nicht editierbar.");

		helpTexts.put (EFormElement.DETAILS_LINE_SURCHARGE.getID (),
				"Hier können Sie optional einen Aufschlag oder Abzug für diese Rechnungszeile eingeben\n\nPositive Zahlen sind "
						+ "Aufschläge.\n\nNegative Zahlen sind Abzüge.");

		helpTexts.put (EFormElement.DETAILS_LINE_SURCHARGE_DESCRIPTION.getID (),
				"Hier können Sie eine kurze Beschreibung zu einen getätigten Aufschlag order Abzug machen.");

		helpTexts.put (EFormElement.DETAILS_LINE_VAT.getID (),
				"Bitte wählen Sie hier den Steuersatz aus, der für diese Zeile gilt.");

		helpTexts.put (EFormElement.DETAILS_LINE_TAXEXEMPTION_CHECK.getID (),
				"Wenn diese Zeile einen steuerbefreiten Artikel enthält, aktivieren Sie diese Check-Box.\n\nDadurch wird das "
						+ "das Steuersatz-Auswahlfeld deaktiviert und das Textfeld zur Angabe des Steuerbefreiungsgrundes aktiviert.");

		helpTexts.put (EFormElement.DETAILS_LINE_TAXEXEMPTION_REASON.getID (),
				"Bitte geben Sie hier den Steuerbefreiungsgrund für diese Zeile an.");

		helpTexts.put (EFormElement.DETAILS_LINE_TOTALGROSS.getID (),
				"Hier ersehen Sie die Gesamtbruttosumme der Rechnungszeile. Diese errechnet sich aus:\n\nGesamtnettosumme\n"
						+ "+ einen vorhanden Aufschlag bzw. - einen vorhanden Abzug\n+ Steuer (wenn nicht steuerbefreit)\n\n"
						+ "Diese Zeile ist nicht editierbar.");

		helpTexts.put (EFormElement.DETAILS_LINE_REMOVE.getID (), "Entfernt diese Zeile aus dem Dokument.");
	}

	/*
	 * Shows the help text for the current submitted element id
	 */
	public void show (final String key)
	{
		final String s = helpTexts.get (key);

		if (s != null)
			this.setText (s);
		else
			this.setText ("Kein Hilfetext verfügbar");
	}

}
