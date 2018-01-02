package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.invoicedata.InvoiceData;
import at.mareg.ebi43creator.invoicedata.enums.EFormFields;

public final class SaveMethodMapper
{

  private static InvoiceData invoiceData = null;

  private SaveMethodMapper ()
  {}

  public static final void callMethodFor (final EFormFields field, final String value)
  {
    switch (field)
    {

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
