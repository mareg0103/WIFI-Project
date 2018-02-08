package at.mareg.ebi43creator.testclasses;

import at.mareg.ebi43creator.display.resources.InvoiceDateManager;
import at.mareg.ebi43creator.invoicedata.InvoiceData;

public class ReadXMLFileTestMain
{

	public static void main (final String[] args)
	{
		final String path = "H:\\test.xml";

		final InvoiceDateManager idm = new InvoiceDateManager ();
		System.out.println ("InvoiceDateManager erzeugt: " + idm + "\n");

		System.out.println ("Lese Rechnungsdaten aus " + path.toString () + "\n");
		final InvoiceData id = new InvoiceData ().readXMLInvoice (path);

		System.out.println ("Rechnungsdaten gelesen, setze InvoiceDateManager in Delivery\n");
		id.getDelivery ().setInvoiceDateManager (idm);

		System.out.println ("Lese Testdaten + in Delivery eingetragenen InvoiceDateManager");
		System.out.println ("Rechnungsnummer: " + id.getInvoiceNumber ());
		System.out.println ("Rechnungsdatum: " + id.getInvoiceDate ());
		System.out.println ("Lieferscheinnummer: " + id.getDelivery ().getDeliveryID ());
		System.out.println ("Gesamtnettopreis der eingetragenen Rechnugszeile: "
				+ id.getDetails ().getListLineItems ().get (0).getLineItemAmount ());
		System.out.println ("Dokumententyp: " + id.getDocumentType ());

		System.out.println ("\nFertig");
	}

}
