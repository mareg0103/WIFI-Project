package at.mareg.ebi43creator.testclasses;

import at.mareg.ebi43creator.display.resources.InvoiceDateManager;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.InvoiceData;

public class CreateXMLStructureTestMain
{

  public static void main (final String [] args)
  {
    final String path = "H:\\test.xml";
    final InvoiceDateManager idm = new InvoiceDateManager ();
    final ResourceManager rm = new ResourceManager (null, "Invoice", null);
    final InvoiceData id = new InvoiceData (idm, "Invoice", rm);

    // id.setTempData ();
    id.serializeInvoiceAsXML (path);

    System.out.println ("Fertig");
  }

}
