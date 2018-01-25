package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.invoicedata.InputChecker;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import at.mareg.ebi43creator.invoicedata.enums.EFormFields;

/**
 * This class checks (if necessary) and saves data depending on the field which
 * triggers this class
 * 
 * @author Martin Regitnig
 */

public final class SaveMethodMapper
{

  private static InvoiceData invoiceData = null;

  private SaveMethodMapper ()
  {}

  // Check and save Data
  public static final String callMethodFor (final EFormFields field, final String value)
  {
    String checkInputReturnMessage = "ok";

    switch (field)
    {
      case ORDER_ID:
        if (value != null)
          checkInputReturnMessage = InputChecker.checkOrderReference (value);

        if (checkInputReturnMessage.equals ("ok"))
          invoiceData.getInvoiceRecipient ().getOrderReference ().setOrderid (value);

        break;

      case SUPPLIER_ID:
        if (value != null)
          checkInputReturnMessage = InputChecker.checkSupplierID (value);

        if (checkInputReturnMessage.equals ("ok"))
          invoiceData.getBiller ().setSupplierID (value);

        break;

      case BILLER_NAME:
        invoiceData.getBiller ().getAddress ().setName (value);
        break;

      case BILLER_STREET:
        invoiceData.getBiller ().getAddress ().setStreet (value);
        break;

      case BILLER_ZIP:
        invoiceData.getBiller ().getAddress ().setZip (value);
        break;

      case BILLER_TOWN:
        invoiceData.getBiller ().getAddress ().setTown (value);
        break;

      case BILLER_COUNTRY:
        invoiceData.getBiller ().getAddress ().setCountry (value);
        break;

      case BILLER_EMAIL:
        invoiceData.getBiller ().getAddress ().setEmail (value);
        break;

      case BILLER_PHONE:
        invoiceData.getBiller ().getAddress ().setPhone (value);
        break;

      case BILLER_CONTACT:
        invoiceData.getBiller ().getAddress ().setContact (value);
        break;

      default:
        System.out.println ("Keine Methode für Feld '" + field + "' vorhanden!");
    }

    return checkInputReturnMessage;
  }

  public static void setInvoiceData (final InvoiceData data)
  {
    invoiceData = data;
  }

  public static InvoiceData getInvoiceData ()
  {
    return invoiceData;
  }

}
