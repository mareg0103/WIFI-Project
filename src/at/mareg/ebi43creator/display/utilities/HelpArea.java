package at.mareg.ebi43creator.display.utilities;

import java.util.Map;
import java.util.TreeMap;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.enums.EBiller;
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
		helpTexts.put ("billertab", "Bitte geben Sie hier Ihre Lieferantenummer und Adressdaten an.");
		helpTexts.put (EBiller.SUPPLIERID.getID (), "Hilfetext zu Lieferantennummer");
		helpTexts.put (EBiller.CONTACT.getID (),
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.\n\nAt vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.\n\nLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. \n\nStet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
		helpTexts.put (EBiller.NAME.getID (), "Bitte hier den Namen des Rechnungsleger (von Ihnen) eingeben");
	}

	public void show (final String key)
	{
		String s = helpTexts.get (key);

		if (s != null)
			this.setText (s);
		else
			this.setText ("Kein Hilfetext verfügbar");
	}

}
