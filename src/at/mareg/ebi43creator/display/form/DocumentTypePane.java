package at.mareg.ebi43creator.display.form;

import java.util.Map;
import java.util.TreeMap;

public class DocumentTypePane {

	private Map<String, String> doctypeMap;
	
	public DocumentTypePane ()
	{
		doctypeMap = new TreeMap<> ();
		initMap ();
	}
	
	private void initMap() {
		doctypeMap.put("Invoice", "Rechnung");
		doctypeMap.put("CreditMemo", "Gutschrift");
		doctypeMap.put("InvoiceForAdvancePayment", "Vorauszahlung");
		doctypeMap.put("InvoiceForPartialDelivery", "Teilrechnung");
		doctypeMap.put("FianlSettlement", "Schlussrechnung");
	}
}
