package at.mareg.ebi43creator.display.utilities;

import java.util.List;

import at.mareg.ebi43creator.display.form.invoicelines.InvoiceLine;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import at.mareg.ebi43creator.invoicedata.details.ListLineItem;
import at.mareg.ebi43creator.invoicedata.details.SurchargeListLineItem;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public final class InvoiceLineFiller
{
	private static InvoiceData invoiceData;

	private InvoiceLineFiller ()
	{
	}

	/*
	 * Fill invoice line with loaded data
	 */
	@SuppressWarnings ("unchecked")
	public static void fillLineWithLoadedData (final InvoiceLine il)
	{
		ListLineItem lli = il.getListLineItem ();
		GridPane grid = il.getGrid ();

		for (final Node n : grid.getChildren ())
		{
			if (n.getClass () == VBox.class)
			{
				for (final Node vn : ((VBox) n).getChildren ())
				{
					if (vn.getClass () != Label.class)
					{
						String elementID = vn.getId ();
						EFormElement element = EFormElement.getFromIDOrNull (elementID);
						String value = "";

						if (element != null)
						{
							boolean elementIsRequired = element.isRequired ();

							switch (element)
							{
								case DETAILS_LINE_ORDERPOSITIONNUMER:
									Integer ilo = lli.getOrderPositionNumber ();
									il.setOrderPositionNumber (ilo);
									value = (ilo == null ? null : String.valueOf (ilo.intValue ()));
									break;

								case DETAILS_LINE_QUANTITY:
									Double dlq = lli.getQuantity ().getQuantity ();
									il.setQuantity (dlq);
									value = (dlq == null ? null
											: TextFieldHelper.getFourDecimalsStringFromDouble (dlq.doubleValue ()));
									break;

								case DETAILS_LINE_UNIT:
									String s = lli.getQuantity ().getUnit ();
									il.setUnit (s);
									value = s;
									break;

								case DETAILS_LINE_UNITPRICE:
									Double dlu = lli.getUnitPrice ();
									il.setUnitprice (dlu);
									value = (dlu == null ? null
											: TextFieldHelper.getFourDecimalsStringFromDouble (dlu.doubleValue ()));
									break;

								case DETAILS_LINE_DESCRIPTION:
									value = lli.getDescription ();
									String sld = lli.getDescription ();
									value = sld;
									break;

								case DETAILS_LINE_SURCHARGE:
									List<SurchargeListLineItem> l = lli.getSurcharge ();
									Double dls = (l == null ? null : l.get (0).getAmount ());
									il.setSurcharge (dls);
									value = (dls == null ? null
											: TextFieldHelper.getTwoDecimalsStringFromDouble (dls.doubleValue ()));
									break;

								case DETAILS_LINE_VAT:
									Integer ilv = lli.getVatRate ();
									il.setVatRate (ilv);
									value = lli.getVatRate ().toString ();
									break;

								case DETAILS_LINE_TAXEXEMPTION_REASON:
									value = lli.getTaxExemption ();
									break;

								default:
									value = null;
							}

							if (vn.getClass () == TextField.class)
							{
								TextField t = (TextField) vn;
								t.setText (value);
								t.setStyle ("-fx-control-inner-background: #" + (value == null
										? (elementIsRequired ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK)
										: Data.BACKGROUND_HEX_OK));
							}

							if (vn.getClass () == TextArea.class)
							{
								TextArea t = (TextArea) vn;
								t.setText (value);
								t.setStyle ("-fx-control-inner-background: #" + (value == null
										? (elementIsRequired ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK)
										: Data.BACKGROUND_HEX_OK));
							}

							if (vn.getClass () == ComboBox.class)
							{
								if (elementID.equals (EFormElement.DETAILS_LINE_VAT.getID ()))
									((ComboBox<String>) vn).getSelectionModel ().select (value + "%");

								if (elementID.equals (EFormElement.DETAILS_LINE_UNIT.getID ()))
									((ComboBox<String>) vn).getSelectionModel ().select (value);
							}
						}
					}
				}
			}
		}

		il.calculateLine ();
	}

	public static void setInvoiceData (final InvoiceData id)
	{
		invoiceData = id;
	}
}
