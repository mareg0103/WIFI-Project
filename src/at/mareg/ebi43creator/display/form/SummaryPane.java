package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SummaryPane extends BasePane
{
	/**
	 * Pane to show a summary of the entered data and save them to an XML file
	 * 
	 * @author Martin Regitnig
	 */

	/*
	 * Pane elements
	 */
	private GridPane gridSummary;

	public SummaryPane (final ResourceManager resman)
	{
		super (resman);

		init ();
	}

	/*
	 * Initialize OrderPane
	 */
	@Override
	protected void init ()
	{
		super.init ();

		gridSummary = new GridPane ();

		Button testShowInvoiceData = new Button ("InvoiceData anzeigen");
		testShowInvoiceData.setOnAction (e -> {
			// showInvoiceData ();
			showRequired ();
			showRequiredForLines ();
			showError ();

			System.out.println ("Rechnung kann gespeichert werden: "
					+ (RequiredAndErrorHelper.allFieldsArrFilledAndCorrect () ? "JA" : "NEIN"));
		});

		gridSummary.getChildren ().add (testShowInvoiceData);

		this.getChildren ().add (gridSummary);
	}

	/*
	 * Test methods to show some invoice data, remove before deployment
	 */
	private void showInvoiceData ()
	{
		InvoiceData id = rm.getInvoiceData ();

		System.out.println ("DocumentType: " + id.getDocumentType ());
		System.out.println ("InvoiceNumber: " + id.getInvoiceNumber ());
		System.out.println ("BillerName: " + id.getBiller ().getAddress ().getName ());
		System.out.println ("Auftragsreferenz: " + id.getInvoiceRecipient ().getOrderReference ().getOrderId ());
		System.out.println ("Lieferdatum: " + id.getDelivery ().getDeliveryDate ());
		System.out.println ("Rechnungswährung: " + id.getInvoiceCurrency ());
		System.out.println ("Auftragsreferenz ist Bundbestellnummer: "
				+ id.getInvoiceRecipient ().getOrderReference ().isOrderIDGovernmentOrderNumber ());
	}

	private void showRequiredForLines ()
	{
		System.out.println ("Required Fields von Zeilen eingetragen:");
		RequiredAndErrorHelper.showLineReqMap ();

		System.out.println ("Größe: " + RequiredAndErrorHelper.getLineRequiredMapSize ());
		System.out.println ();
	}

	private void showError ()
	{
		System.out.println ("Fehlerhafte Felder:");
		RequiredAndErrorHelper.showErrorMap ();
		System.out.println ();

	}

	private void showRequired ()
	{
		System.out.println ("Required Fields eingetragen:");
		RequiredAndErrorHelper.showReqMap ();
		System.out.println ();
	}
}
