package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.InputChecker;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import at.mareg.ebi43creator.invoicedata.enums.ECurrency;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;

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

  /*
   * Checks (if necessary) and save data
   */
  public static final String callMethodFor (final EFormElement field, final String value)
  {
    String checkInputReturnMessage = Data.CHECKMESSAGE_SUCCESS;

    switch (field)
    {
      /*
       * Order data
       */
      case ORDER_ID:
        if (value != null)
          checkInputReturnMessage = InputChecker.checkOrderReference (value);

        if (checkInputReturnMessage.equals ("ok"))
          invoiceData.getInvoiceRecipient ().getOrderReference ().setOrderId (value);

        invoiceData.getInvoiceRecipient ().getOrderReference ().checkIfOrderIDIsGovernmentOrderNumber ();

        break;

      case ORDER_REFERENCEDATE:
        invoiceData.getInvoiceRecipient ().getOrderReference ().setReferenceDate (value);
        break;

      case ORDER_DESCRIPTION:
        invoiceData.getInvoiceRecipient ().getOrderReference ().setDescription (value);
        break;

      case SUPPLIER_ID:
        if (value != null)
          checkInputReturnMessage = InputChecker.checkSupplierID (value);

        if (checkInputReturnMessage.equals ("ok"))
          invoiceData.getBiller ().setSupplierID (value);

        break;

      case INVOICE_NUMBER:
        invoiceData.setInvoiceNumber (value);
        break;

      case INVOICE_DATE:
        invoiceData.setInvoiceDate (value);
        break;

      case INVOICE_CURRENCY:
    	ECurrency e =  ECurrency.getFromInvoiceCurrencyOrNull(value);
    	 
        invoiceData.setInvoiceCurrency (e.getInvoiceCurrencyShort());
        break;

      case FROM_DATE:
        invoiceData.getDelivery ().setFromDateInternal (value);
        break;

      case TO_DATE:
        invoiceData.getDelivery ().setToDateInternal (value);
        break;

      case COMMENT:
        invoiceData.setComment (value);
        break;

      /*
       * Contact data - biller
       */
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

      /*
       * Contact data - invoice recipient
       */
      case INVOICERECIPIENT_NAME:
        invoiceData.getInvoiceRecipient ().getAddress ().setName (value);
        break;

      case INVOICERECIPIENT_STREET:
        invoiceData.getInvoiceRecipient ().getAddress ().setStreet (value);
        break;

      case INVOICERECIPIENT_ZIP:
        invoiceData.getInvoiceRecipient ().getAddress ().setZip (value);
        break;

      case INVOICERECIPIENT_TOWN:
        invoiceData.getInvoiceRecipient ().getAddress ().setTown (value);
        break;

      case INVOICERECIPIENT_COUNTRY:
        invoiceData.getInvoiceRecipient ().getAddress ().setCountry (value);
        break;

      case INVOICERECIPIENT_EMAIL:
        invoiceData.getInvoiceRecipient ().getAddress ().setEmail (value);
        break;

      case INVOICERECIPIENT_PHONE:
        invoiceData.getInvoiceRecipient ().getAddress ().setPhone (value);
        break;

      case INVOICERECIPIENT_CONTACT:
        invoiceData.getInvoiceRecipient ().getAddress ().setContact (value);
        break;

      /*
       * Contact data - delivery
       */
      case DELIVERY_ID:
        invoiceData.getDelivery ().setDeliveryID (value);
        break;

      case DELIVERY_NAME:
        invoiceData.getDelivery ().getAddress ().setName (value);
        break;

      case DELIVERY_STREET:
        invoiceData.getDelivery ().getAddress ().setStreet (value);
        break;

      case DELIVERY_ZIP:
        invoiceData.getDelivery ().getAddress ().setZip (value);
        break;

      case DELIVERY_TOWN:
        invoiceData.getDelivery ().getAddress ().setTown (value);
        break;

      case DELIVERY_COUNTRY:
        invoiceData.getDelivery ().getAddress ().setCountry (value);
        break;

      case DELIVERY_EMAIL:
        invoiceData.getDelivery ().getAddress ().setEmail (value);
        break;

      case DELIVERY_PHONE:
        invoiceData.getDelivery ().getAddress ().setPhone (value);
        break;

      case DELIVERY_CONTACT:
        invoiceData.getDelivery ().getAddress ().setContact (value);
        break;

      /*
       * Payment fields
       */
      case PAYMENT_BIC:
        invoiceData.getPaymentMethod ().getUniversalBankTransaction ().getBeneficiaryAccount ().setBic (value);
        break;

      case PAYMENT_IBAN:
        invoiceData.getPaymentMethod ().getUniversalBankTransaction ().getBeneficiaryAccount ().setIban (value);
        break;

      case PAYMENT_ACCOUNTOWNER:
        invoiceData.getPaymentMethod ()
                   .getUniversalBankTransaction ()
                   .getBeneficiaryAccount ()
                   .setBankAccountOwner (value);
        break;

      /*
       * VAT id numbers
       */
      case DETAILS_RIGHT_VATID_BILLER:
        invoiceData.getBiller ().setVatID (value);
        break;

      case DETAILS_RIGHT_VATID_INVOICERECIPIENT:
        invoiceData.getInvoiceRecipient ().setVatID (value);
        break;

      /*
       * Due date
       */
      case DUE_DATE:
        if (value != null)
        {
          invoiceData.addPaymentContitions ();
          invoiceData.getPaymentConditions ().setDueDate (value);
        }
        else
        {
          if (invoiceData.getPaymentConditions () != null)
            invoiceData.getPaymentConditions ().setDueDate (value);
        }
        break;

      /*
       * On error
       */
      default:
        checkInputReturnMessage = Data.CHECKMESSAGE_SUCCESS;
        // System.err.println (
        // "Keine Methode für Feld '" + field + "' vorhanden, möglicherweise
        // InvoiceLine-Element!");
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
