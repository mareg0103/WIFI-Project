package at.mareg.ebi43creator.display.utilities;

import java.util.List;

import at.mareg.ebi43creator.invoicedata.details.ListLineItem;
import at.mareg.ebi43creator.invoicedata.details.SurchargeListLineItem;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;

public final class LoadInvoiceDataMapper
{
	private LoadInvoiceDataMapper ()
	{
	}

	public static String callMethodFor (final EFormElement element, final ListLineItem lli)
	{
		String value;

		switch (element)
		{
			case DETAILS_LINE_ORDERPOSITIONNUMER:
				Integer s = lli.getOrderPositionNumber ();
				value = s == null ? null : s.toString ();
				break;

			case DETAILS_LINE_QUANTITY:
				value = TextFieldHelper.getFourDecimalsStringFromDouble (lli.getQuantity ().getQuantity ());
				break;

			case DETAILS_LINE_UNIT:
				value = lli.getQuantity ().getUnit ();
				break;

			case DETAILS_LINE_UNITPRICE:
				value = TextFieldHelper.getFourDecimalsStringFromDouble (lli.getUnitPrice ());
				break;

			case DETAILS_LINE_DESCRIPTION:
				value = lli.getDescription ();
				break;

			case DETAILS_LINE_SURCHARGE:
				List<SurchargeListLineItem> l = lli.getSurcharge ();

				if (l == null)
					value = null;
				else
					value = TextFieldHelper.getTwoDecimalsStringFromDouble (l.get (0).getAmount ());

				break;

			case DETAILS_LINE_VAT:
				value = lli.getVatRate ().toString ();
				break;

			case DETAILS_LINE_TAXEXEMPTION_REASON:
				value = lli.getTaxExemption ();
				break;

			default:
				value = null;
		}

		return value;
	}
}
