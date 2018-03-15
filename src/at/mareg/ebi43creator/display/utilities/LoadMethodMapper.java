package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;

public final class LoadMethodMapper
{
  /**
   * Class to write values of an loaded XML file to the form. Some values will
   * not be processed, because they have to change in each invoice. They will be
   * set to null. Values:
   * <ul>
   * <li>Invoice number</li>
   * <li>Delivery date / period</li>
   * <li>Delivery id</li>
   * <li>Discounts</li>
   * <li>Due date</li>
   * </ul>
   *
   * @author Martin Regitnig
   */

  /*
   * Invoice data instance
   */
  private static InvoiceData invoiceData;

  /*
   * Resource manager instance
   */
  private static ResourceManager resourceManager;

  /*
   * No instantiation, only static methods
   */
  private LoadMethodMapper ()
  {}

  /*
   * Map getter based on calling element
   */
  public static final String callMethodFor (final EFormElement element)
  {
    String value = null;

    switch (element)
    {
      /*
       * Order pane
       */
      case ORDER_ID:
        value = invoiceData.getInvoiceRecipient ().getOrderReference ().getOrderId ();
        break;

      /*
       * Special case order reference date, set to null
       */
      case ORDER_REFERENCEDATE:
        invoiceData.getInvoiceRecipient ().getOrderReference ().setReferenceDate (null);
        break;

      /*
       * Special case order reference description, set to null
       */
      case ORDER_DESCRIPTION:
        invoiceData.getInvoiceRecipient ().getOrderReference ().setDescription (null);
        break;

      case SUPPLIER_ID:
        value = invoiceData.getBiller ().getSupplierID ();
        break;

      /*
       * Special case invoice number, set to null
       */
      case INVOICE_NUMBER:
        invoiceData.setInvoiceNumber (null);
        break;

      /*
       * Special case invoice date, set to current date
       */
      case INVOICE_DATE:
        invoiceData.setInvoiceDate (resourceManager.getInvoiceDateManager ().getCurrentDateAsString ());
        break;

      /*
       * Special case delivery date / delivery period, set them to null
       */
      case FROM_DATE:
        invoiceData.getDelivery ().setDeliveryDate (null);
        invoiceData.getDelivery ().setDeliveryPeriod (null);
        break;

      case COMMENT:
        value = invoiceData.getComment ();
        break;

      /*
       * Contact pane - biller
       */
      case BILLER_NAME:
        value = invoiceData.getBiller ().getAddress ().getName ();
        break;

      case BILLER_STREET:
        value = invoiceData.getBiller ().getAddress ().getStreet ();
        break;

      case BILLER_ZIP:
        value = invoiceData.getBiller ().getAddress ().getZip ();
        break;

      case BILLER_TOWN:
        value = invoiceData.getBiller ().getAddress ().getTown ();
        break;

      case BILLER_COUNTRY:
        value = invoiceData.getBiller ().getAddress ().getCountry ();
        break;

      case BILLER_EMAIL:
        value = invoiceData.getBiller ().getAddress ().getEmail ();
        break;

      case BILLER_PHONE:
        value = invoiceData.getBiller ().getAddress ().getPhone ();
        break;

      case BILLER_CONTACT:
        value = invoiceData.getBiller ().getAddress ().getContact ();
        break;

      /*
       * Contact pane - invoice recipient
       */
      case INVOICERECIPIENT_NAME:
        value = invoiceData.getInvoiceRecipient ().getAddress ().getName ();
        break;

      case INVOICERECIPIENT_STREET:
        value = invoiceData.getInvoiceRecipient ().getAddress ().getStreet ();
        break;

      case INVOICERECIPIENT_ZIP:
        value = invoiceData.getInvoiceRecipient ().getAddress ().getZip ();
        break;

      case INVOICERECIPIENT_TOWN:
        value = invoiceData.getInvoiceRecipient ().getAddress ().getTown ();
        break;

      case INVOICERECIPIENT_COUNTRY:
        value = invoiceData.getInvoiceRecipient ().getAddress ().getCountry ();
        break;

      case INVOICERECIPIENT_EMAIL:
        value = invoiceData.getInvoiceRecipient ().getAddress ().getEmail ();
        break;

      case INVOICERECIPIENT_PHONE:
        value = invoiceData.getInvoiceRecipient ().getAddress ().getPhone ();
        break;

      case INVOICERECIPIENT_CONTACT:
        value = invoiceData.getInvoiceRecipient ().getAddress ().getContact ();
        break;

      /*
       * Contact pane - delivery
       */
      /*
       * Special case delivery id, set to null
       */
      case DELIVERY_ID:
        invoiceData.getDelivery ().setDeliveryID (null);
        break;

      case DELIVERY_NAME:
        value = invoiceData.getDelivery ().getAddress ().getName ();
        break;

      case DELIVERY_STREET:
        value = invoiceData.getDelivery ().getAddress ().getStreet ();
        break;

      case DELIVERY_ZIP:
        value = invoiceData.getDelivery ().getAddress ().getZip ();
        break;

      case DELIVERY_TOWN:
        value = invoiceData.getDelivery ().getAddress ().getTown ();
        break;

      case DELIVERY_COUNTRY:
        value = invoiceData.getDelivery ().getAddress ().getCountry ();
        break;

      case DELIVERY_EMAIL:
        value = invoiceData.getDelivery ().getAddress ().getEmail ();
        break;

      case DELIVERY_PHONE:
        value = invoiceData.getDelivery ().getAddress ().getPhone ();
        break;

      case DELIVERY_CONTACT:
        value = invoiceData.getDelivery ().getAddress ().getContact ();
        break;

      /*
       * Contact pane - delivery
       */
      case PAYMENT_BIC:
        value = invoiceData.getPaymentMethod ().getUniversalBankTransaction ().getBeneficiaryAccount ().getBic ();
        break;

      case PAYMENT_IBAN:
        value = invoiceData.getPaymentMethod ().getUniversalBankTransaction ().getBeneficiaryAccount ().getIban ();
        break;

      case PAYMENT_ACCOUNTOWNER:
        value = invoiceData.getPaymentMethod ()
                           .getUniversalBankTransaction ()
                           .getBeneficiaryAccount ()
                           .getBankAccountOwner ();
        break;

      /*
       * Default return value
       */
      default:
        value = null;
    }

    return value;
  }

  /*
   * Set current invoice data instance
   */
  public static void setInvoiceData (final InvoiceData id)
  {
    invoiceData = id;
  }

  /*
   * Set current resource manager instance
   */
  public static void setResourceManager (final ResourceManager rm)
  {
    resourceManager = rm;
  }
}
