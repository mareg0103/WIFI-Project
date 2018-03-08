package at.mareg.ebi43creator.display.form;

import java.util.List;

import at.mareg.ebi43creator.display.form.discount.DiscountLine;
import at.mareg.ebi43creator.display.form.invoicelines.InvoiceLine;
import at.mareg.ebi43creator.display.form.surcharge.SurchargeLine;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.TextFieldHelper;
import at.mareg.ebi43creator.invoicedata.InvoiceData;
import at.mareg.ebi43creator.invoicedata.address.Address;
import at.mareg.ebi43creator.invoicedata.delivery.DeliveryPeriod;
import at.mareg.ebi43creator.invoicedata.enums.ECurrency;
import at.mareg.ebi43creator.invoicedata.enums.EDocumentType;
import at.mareg.ebi43creator.invoicedata.payment.BeneficiaryAccount;
import at.mareg.ebi43creator.invoicedata.payment.PaymentConditions;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SummaryArea extends BasePane
{
  /*
   * Pane elements
   */
  private ScrollPane scroll;
  private GridPane grid;

  /*
   * Internal variables
   */
  private final InvoiceData id;
  private final Insets standardInsets;
  private final Insets lineSummaryInsets;
  private final int minBound;
  private final int maxBound;
  private int gridCol;
  private int gridRow;
  private boolean documentIsCreditMemo;

  public SummaryArea (final ResourceManager resman)
  {
    super (resman);

    id = resman.getInvoiceData ();

    standardInsets = new Insets (3, 3, 3, 10);
    lineSummaryInsets = new Insets (3, 3, 3, 5);
    minBound = 9;
    maxBound = 10;
  }

  public void createSummary ()
  {
    super.init ();

    /*
     * Check if current document type is credit meome
     */
    documentIsCreditMemo = id.getDocumentType ().equals (EDocumentType.CREDIT_MEMO.getElementID ());

    int gridRow = 0;

    scroll = new ScrollPane ();
    scroll.setPrefHeight (Data.DETAILS_SCROLLPANE_HEIGHT);
    scroll.setMaxHeight (scroll.getPrefHeight ());
    scroll.setPrefWidth (Data.DETAILS_SCROLLPANE_WIDTH);
    scroll.setMaxWidth (scroll.getPrefWidth ());

    grid = new GridPane ();

    final VBox orderSummaryBox = _getOrderDataSummary ();
    grid.add (orderSummaryBox, 0, gridRow);
    gridRow++;

    final VBox deliverySummaryBox = _getDeliverySummary ();
    grid.add (deliverySummaryBox, 0, gridRow);
    gridRow++;

    final VBox billerSummaryBox = _getBillerSummary ();
    grid.add (billerSummaryBox, 0, gridRow);
    gridRow++;

    final VBox invoiceRecipientSummaryBox = _getInvoiceRecipientSummary ();
    grid.add (invoiceRecipientSummaryBox, 0, gridRow);
    gridRow++;

    final VBox paymentSummaryBox = _getPaymentSummary ();
    grid.add (paymentSummaryBox, 0, gridRow);
    gridRow++;

    final VBox lineSummaryBox = _getDetailsSummary ();
    grid.add (lineSummaryBox, 0, gridRow);
    gridRow++;

    final VBox surchargeSummaryBox = _getSurchargeSummary ();
    grid.add (surchargeSummaryBox, 0, gridRow);
    gridRow++;

    final VBox totalGrossSummaryBox = _getTotalGrossSummary ();
    grid.add (totalGrossSummaryBox, 0, gridRow);
    gridRow++;

    if (!documentIsCreditMemo)
    {
      final VBox discountSummaryBox = _getDiscountSummary ();
      grid.add (discountSummaryBox, 0, gridRow);
      gridRow++;

      final VBox dueDateSummaryBox = _getDueDateSummary ();
      grid.add (dueDateSummaryBox, 0, gridRow);
      gridRow++;
    }

    scroll.setContent (grid);
    this.getChildren ().add (scroll);
  }

  /*
   * Create summary box of order data
   */
  private VBox _getOrderDataSummary ()
  {
    final InvoiceData id = rm.getInvoiceData ();
    gridRow = 0;

    final VBox v = new VBox ();
    final Label l = FormElementCreator.getStandardLabel ((documentIsCreditMemo ? "Gutschrifts" : "Rechnungs") + "daten",
                                                         new Insets (5, 5, 5, 5));
    l.setFont (Font.font (l.getFont ().getName (), FontWeight.BOLD, l.getFont ().getSize ()));
    v.getChildren ().add (l);

    final GridPane gridSummary = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 2);
      gridSummary.getColumnConstraints ().add (column);
    }

    gridSummary.setPadding (Data.BASEPANE_PADDING);
    gridSummary.setHgap (Data.BASEPANE_HVGAP);
    gridSummary.setVgap (grid.getHgap ());

    final Label orderIdDescription = FormElementCreator.getStandardLabel ("Auftragsreferenz:", standardInsets);
    gridSummary.add (orderIdDescription, 0, gridRow);

    final Label orderIdValue = FormElementCreator.getStandardLabel (id.getInvoiceRecipient ()
                                                                      .getOrderReference ()
                                                                      .getOrderId (),
                                                                    standardInsets);
    gridSummary.add (orderIdValue, 1, gridRow);

    gridRow++;

    final String orderDate = id.getInvoiceRecipient ().getOrderReference ().getReferenceDate ();
    if (orderDate != null)
    {
      final Label orderDateDescription = FormElementCreator.getStandardLabel ("Auftragsdatum:", standardInsets);
      gridSummary.add (orderDateDescription, 0, gridRow);

      final Label orderDateValue = FormElementCreator.getStandardLabel (orderDate, standardInsets);
      gridSummary.add (orderDateValue, 1, gridRow);

      gridRow++;
    }

    final String orderDescription = id.getInvoiceRecipient ().getOrderReference ().getDescription ();
    if (orderDescription != null)
    {
      final Label orderDescriptionDescription = FormElementCreator.getStandardLabel ("Auftragsbeschreibung:",
                                                                                     standardInsets);
      gridSummary.add (orderDescriptionDescription, 0, gridRow);

      final Label orderDescriptionValue = FormElementCreator.getStandardLabel (orderDescription, standardInsets);
      gridSummary.add (orderDescriptionValue, 1, gridRow);

      gridRow++;
    }

    final Label supplierIDDescription = FormElementCreator.getStandardLabel ("Lieferantennummer:", standardInsets);
    gridSummary.add (supplierIDDescription, 0, gridRow);

    final Label supplierIDValue = FormElementCreator.getStandardLabel (id.getBiller ().getSupplierID (),
                                                                       standardInsets);
    gridSummary.add (supplierIDValue, 1, gridRow);

    gridRow++;

    final Label invoiceNumberDescription = FormElementCreator.getStandardLabel ("Rechnungsnummer:", standardInsets);
    gridSummary.add (invoiceNumberDescription, 0, gridRow);

    final Label invoiceNumberValue = FormElementCreator.getStandardLabel (id.getInvoiceNumber (), standardInsets);
    gridSummary.add (invoiceNumberValue, 1, gridRow);

    gridRow++;

    final Label invoiceDateDescription = FormElementCreator.getStandardLabel ("Rechnungsdatum:", standardInsets);
    gridSummary.add (invoiceDateDescription, 0, gridRow);

    final Label invoiceDateValue = FormElementCreator.getStandardLabel (rm.getInvoiceDateManager ()
                                                                          .getGermanFormatedDateFromLocalDateString (id.getInvoiceDate ()),
                                                                        standardInsets);
    gridSummary.add (invoiceDateValue, 1, gridRow);

    gridRow++;

    final Label invoiceCurrencyDescription = FormElementCreator.getStandardLabel ("Rechnungswährung:", standardInsets);
    gridSummary.add (invoiceCurrencyDescription, 0, gridRow);

    final Label invoiceCurrencyValue = FormElementCreator.getStandardLabel (ECurrency.getFromInvoiceCurrencyShortOrNull (id.getInvoiceCurrency ())
                                                                                     .getInvoiceCurrency (),
                                                                            standardInsets);
    gridSummary.add (invoiceCurrencyValue, 1, gridRow);

    gridRow++;

    final String comment = id.getComment ();
    if (comment != null)
    {
      final Label commentDescription = FormElementCreator.getStandardLabel ("Mitteilung:", standardInsets);
      gridSummary.add (commentDescription, 0, gridRow);

      final Label commentValue = FormElementCreator.getStandardLabel (comment, standardInsets);
      commentValue.setWrapText (true);
      gridSummary.add (commentValue, 1, gridRow);

      gridRow++;
    }

    v.getChildren ().add (gridSummary);

    return v;
  }

  /*
   * Create summary box of delivery data
   */
  private VBox _getDeliverySummary ()
  {
    final InvoiceData id = rm.getInvoiceData ();
    gridRow = 0;

    final VBox v = new VBox ();
    final Label l = FormElementCreator.getStandardLabel ("Lieferdaten", new Insets (5, 5, 5, 5));
    l.setFont (Font.font (l.getFont ().getName (), FontWeight.BOLD, l.getFont ().getSize ()));
    v.getChildren ().add (l);

    final GridPane gridSummary = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 2);
      gridSummary.getColumnConstraints ().add (column);
    }

    gridSummary.setPadding (Data.BASEPANE_PADDING);
    gridSummary.setHgap (Data.BASEPANE_HVGAP);
    gridSummary.setVgap (grid.getHgap ());

    final String deliveryDate = id.getDelivery ().getDeliveryDate ();
    final DeliveryPeriod deliveryPeriod = id.getDelivery ().getDeliveryPeriod ();

    if (deliveryDate != null && deliveryPeriod == null)
    {
      final Label deliveryDateDescription = FormElementCreator.getStandardLabel ("Lieferdatum:", standardInsets);
      gridSummary.add (deliveryDateDescription, 0, gridRow);

      final Label deliveryDateValue = FormElementCreator.getStandardLabel (rm.getInvoiceDateManager ()
                                                                             .getGermanFormatedDateFromLocalDateString (deliveryDate),
                                                                           standardInsets);
      gridSummary.add (deliveryDateValue, 1, gridRow);

      gridRow++;
    }

    if (deliveryDate == null && deliveryPeriod != null)
    {
      final Label deliveryPeriodDescription = FormElementCreator.getStandardLabel ("Leistungszeitraum:",
                                                                                   standardInsets);
      gridSummary.add (deliveryPeriodDescription, 0, gridRow);

      final Label deliveryPeriodValue = FormElementCreator.getStandardLabel (rm.getInvoiceDateManager ()
                                                                               .getGermanFormatedDateFromLocalDateString (deliveryPeriod.getFromDate ()) +
                                                                             " bis " +
                                                                             rm.getInvoiceDateManager ()
                                                                               .getGermanFormatedDateFromLocalDateString (deliveryPeriod.getToDate ()),
                                                                             standardInsets);
      gridSummary.add (deliveryPeriodValue, 1, gridRow);

      gridRow++;
    }

    final String deliveryID = id.getDelivery ().getDeliveryID ();
    if (deliveryID != null)
    {
      final Label deliveryIDDescription = FormElementCreator.getStandardLabel ("Lieferscheinnummer:", standardInsets);
      gridSummary.add (deliveryIDDescription, 0, gridRow);

      final Label deliveryIDValue = FormElementCreator.getStandardLabel (deliveryID, standardInsets);
      gridSummary.add (deliveryIDValue, 1, gridRow);

      gridRow++;
    }

    final Address deliveryAddress = id.getDelivery ().getAddress ();
    System.out.println ("DeliveryAdress: " + deliveryAddress);
    if (deliveryAddress != null)
    {
      final Label deliveryNameDescription = FormElementCreator.getStandardLabel ("Name:", standardInsets);
      gridSummary.add (deliveryNameDescription, 0, gridRow);

      final Label deliveryNameValue = FormElementCreator.getStandardLabel (deliveryAddress.getName (), standardInsets);
      gridSummary.add (deliveryNameValue, 1, gridRow);

      gridRow++;

      final Label deliveryAddressDescription = FormElementCreator.getStandardLabel ("Adresse:", standardInsets);
      gridSummary.add (deliveryAddressDescription, 0, gridRow);

      final StringBuilder sb = new StringBuilder ();
      sb.append (deliveryAddress.getStreet () + "\n");
      sb.append (deliveryAddress.getZip () + " " + deliveryAddress.getTown () + "\n");
      sb.append (deliveryAddress.getCountry ());
      final Label deliveryAddressValue = FormElementCreator.getStandardLabel (sb.toString (), standardInsets);
      gridSummary.add (deliveryAddressValue, 1, gridRow);

      gridRow++;

      final String deliveryEMail = deliveryAddress.getEmail ();
      if (deliveryEMail != null)
      {
        final Label deliveryEMailDescription = FormElementCreator.getStandardLabel ("E-Mail:", standardInsets);
        gridSummary.add (deliveryEMailDescription, 0, gridRow);

        final Label deliveryEMailValue = FormElementCreator.getStandardLabel (deliveryEMail, standardInsets);
        gridSummary.add (deliveryEMailValue, 1, gridRow);

        gridRow++;
      }

      final String deliveryPhone = deliveryAddress.getPhone ();
      if (deliveryPhone != null)
      {
        final Label deliveryPhoneDescription = FormElementCreator.getStandardLabel ("Telefon:", standardInsets);
        gridSummary.add (deliveryPhoneDescription, 0, gridRow);

        final Label deliveryPhoneValue = FormElementCreator.getStandardLabel (deliveryPhone, standardInsets);
        gridSummary.add (deliveryPhoneValue, 1, gridRow);

        gridRow++;
      }

      final String deliveryContact = deliveryAddress.getContact ();
      if (deliveryContact != null)
      {
        final Label deliveryContactDescription = FormElementCreator.getStandardLabel ("Kontakt:", standardInsets);
        gridSummary.add (deliveryContactDescription, 0, gridRow);

        final Label deliveryContactValue = FormElementCreator.getStandardLabel (deliveryContact, standardInsets);
        gridSummary.add (deliveryContactValue, 1, gridRow);

        gridRow++;
      }

    }

    v.getChildren ().add (gridSummary);

    return v;
  }

  /*
   * Create summary box of biller data
   */
  private VBox _getBillerSummary ()
  {
    gridRow = 0;
    final Address billerAddress = id.getBiller ().getAddress ();

    final VBox v = new VBox ();
    final Label l = FormElementCreator.getStandardLabel ((documentIsCreditMemo ? "Gutschrifts" : "Rechnungs") +
                                                         "steller",
                                                         new Insets (5, 5, 5, 5));
    l.setFont (Font.font (l.getFont ().getName (), FontWeight.BOLD, l.getFont ().getSize ()));
    v.getChildren ().add (l);

    final GridPane gridSummary = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 2);
      gridSummary.getColumnConstraints ().add (column);
    }

    gridSummary.setPadding (Data.BASEPANE_PADDING);
    gridSummary.setHgap (Data.BASEPANE_HVGAP);
    gridSummary.setVgap (grid.getHgap ());

    final Label billerVATIDDescription = FormElementCreator.getStandardLabel ("UID-Nummer:", standardInsets);
    gridSummary.add (billerVATIDDescription, 0, gridRow);

    final Label billerVATIDValue = FormElementCreator.getStandardLabel (id.getBiller ().getVatID (), standardInsets);
    gridSummary.add (billerVATIDValue, 1, gridRow);

    gridRow++;

    final Label billerNameDescription = FormElementCreator.getStandardLabel ("Name:", standardInsets);
    gridSummary.add (billerNameDescription, 0, gridRow);

    final Label billerNameValue = FormElementCreator.getStandardLabel (billerAddress.getName (), standardInsets);
    gridSummary.add (billerNameValue, 1, gridRow);

    gridRow++;

    final Label billerAddressDescription = FormElementCreator.getStandardLabel ("Adresse:", standardInsets);
    gridSummary.add (billerAddressDescription, 0, gridRow);

    final StringBuilder sb = new StringBuilder ();
    sb.append (billerAddress.getStreet () + "\n");
    sb.append (billerAddress.getZip () + " " + billerAddress.getTown () + "\n");
    sb.append (billerAddress.getCountry ());
    final Label billerAddressValue = FormElementCreator.getStandardLabel (sb.toString (), standardInsets);
    gridSummary.add (billerAddressValue, 1, gridRow);

    gridRow++;

    final Label billerEMailDescription = FormElementCreator.getStandardLabel ("E-Mail:", standardInsets);
    gridSummary.add (billerEMailDescription, 0, gridRow);

    final Label billerEMailValue = FormElementCreator.getStandardLabel (billerAddress.getEmail (), standardInsets);
    gridSummary.add (billerEMailValue, 1, gridRow);

    gridRow++;

    final String billerPhone = billerAddress.getPhone ();
    if (billerPhone != null)
    {
      final Label billerPhoneDescription = FormElementCreator.getStandardLabel ("Telefon:", standardInsets);
      gridSummary.add (billerPhoneDescription, 0, gridRow);

      final Label billerPhoneValue = FormElementCreator.getStandardLabel (billerPhone, standardInsets);
      gridSummary.add (billerPhoneValue, 1, gridRow);

      gridRow++;
    }

    final String billerContact = billerAddress.getContact ();
    if (billerContact != null)
    {
      final Label billerContactDescription = FormElementCreator.getStandardLabel ("Kontakt:", standardInsets);
      gridSummary.add (billerContactDescription, 0, gridRow);

      final Label billerContactValue = FormElementCreator.getStandardLabel (billerContact, standardInsets);
      gridSummary.add (billerContactValue, 1, gridRow);

      gridRow++;
    }

    v.getChildren ().add (gridSummary);

    return v;
  }

  /*
   * Create summary box of invoice recipient data
   */
  private VBox _getInvoiceRecipientSummary ()
  {
    gridRow = 0;
    final Address invoiceRecipientAddress = id.getInvoiceRecipient ().getAddress ();

    final VBox v = new VBox ();
    final Label l = FormElementCreator.getStandardLabel ((documentIsCreditMemo ? "Gutschrifts" : "Rechnungs") +
                                                         "empfänger",
                                                         new Insets (5, 5, 5, 5));
    l.setFont (Font.font (l.getFont ().getName (), FontWeight.BOLD, l.getFont ().getSize ()));
    v.getChildren ().add (l);

    final GridPane gridSummary = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 2);
      gridSummary.getColumnConstraints ().add (column);
    }

    gridSummary.setPadding (Data.BASEPANE_PADDING);
    gridSummary.setHgap (Data.BASEPANE_HVGAP);
    gridSummary.setVgap (grid.getHgap ());

    final Label invocieRecipientVATIDDescription = FormElementCreator.getStandardLabel ("UID-Nummer:", standardInsets);
    gridSummary.add (invocieRecipientVATIDDescription, 0, gridRow);

    final Label invoiceRecipientVATIDValue = FormElementCreator.getStandardLabel (id.getInvoiceRecipient ().getVatID (),
                                                                                  standardInsets);
    gridSummary.add (invoiceRecipientVATIDValue, 1, gridRow);

    gridRow++;

    final Label invoiceRecipientNameDescription = FormElementCreator.getStandardLabel ("Name:", standardInsets);
    gridSummary.add (invoiceRecipientNameDescription, 0, gridRow);

    final Label invoiceRecipientNameValue = FormElementCreator.getStandardLabel (invoiceRecipientAddress.getName (),
                                                                                 standardInsets);
    gridSummary.add (invoiceRecipientNameValue, 1, gridRow);

    gridRow++;

    final Label invoiceRecipientAddressDescription = FormElementCreator.getStandardLabel ("Adresse:", standardInsets);
    gridSummary.add (invoiceRecipientAddressDescription, 0, gridRow);

    final StringBuilder sb = new StringBuilder ();
    sb.append (invoiceRecipientAddress.getStreet () + "\n");
    sb.append (invoiceRecipientAddress.getZip () + " " + invoiceRecipientAddress.getTown () + "\n");
    sb.append (invoiceRecipientAddress.getCountry ());
    final Label invoiceRecipientAddressValue = FormElementCreator.getStandardLabel (sb.toString (), standardInsets);
    gridSummary.add (invoiceRecipientAddressValue, 1, gridRow);

    gridRow++;

    final String invoiceRecipientEMail = invoiceRecipientAddress.getEmail ();
    if (invoiceRecipientEMail != null)
    {
      final Label invoiceRecipientEMailDescription = FormElementCreator.getStandardLabel ("E-Mail:", standardInsets);
      gridSummary.add (invoiceRecipientEMailDescription, 0, gridRow);

      final Label invoiceRecipientEMailValue = FormElementCreator.getStandardLabel (invoiceRecipientEMail,
                                                                                    standardInsets);
      gridSummary.add (invoiceRecipientEMailValue, 1, gridRow);

      gridRow++;
    }

    final String invoiceRecipientPhone = invoiceRecipientAddress.getPhone ();
    if (invoiceRecipientPhone != null)
    {
      final Label invoiceRecipientPhoneDescription = FormElementCreator.getStandardLabel ("Telefon:", standardInsets);
      gridSummary.add (invoiceRecipientPhoneDescription, 0, gridRow);

      final Label invoiceRecipientPhoneValue = FormElementCreator.getStandardLabel (invoiceRecipientPhone,
                                                                                    standardInsets);
      gridSummary.add (invoiceRecipientPhoneValue, 1, gridRow);

      gridRow++;
    }

    final String invoiceRecipientContact = invoiceRecipientAddress.getContact ();
    if (invoiceRecipientContact != null)
    {
      final Label invoiceRecipientContactDescription = FormElementCreator.getStandardLabel ("Kontakt:", standardInsets);
      gridSummary.add (invoiceRecipientContactDescription, 0, gridRow);

      final Label invoiceRecipientContactValue = FormElementCreator.getStandardLabel (invoiceRecipientContact,
                                                                                      standardInsets);
      gridSummary.add (invoiceRecipientContactValue, 1, gridRow);

      gridRow++;
    }

    v.getChildren ().add (gridSummary);

    return v;
  }

  /*
   * Create summary box of payment data
   */
  private VBox _getPaymentSummary ()
  {
    gridRow = 0;

    final VBox v = new VBox ();
    final Label l = FormElementCreator.getStandardLabel ("Zahlungsdaten", new Insets (5, 5, 5, 5));
    l.setFont (Font.font (l.getFont ().getName (), FontWeight.BOLD, l.getFont ().getSize ()));
    v.getChildren ().add (l);

    final GridPane gridSummary = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 2);
      gridSummary.getColumnConstraints ().add (column);
    }

    gridSummary.setPadding (Data.BASEPANE_PADDING);
    gridSummary.setHgap (Data.BASEPANE_HVGAP);
    gridSummary.setVgap (grid.getHgap ());

    final BeneficiaryAccount ba = id.getPaymentMethod ().getUniversalBankTransaction ().getBeneficiaryAccount ();
    final String bic = ba.getBic ();
    if (bic != null)
    {
      final Label bicDescription = FormElementCreator.getStandardLabel ("BIC:", standardInsets);
      gridSummary.add (bicDescription, 0, gridRow);

      final Label bicValue = FormElementCreator.getStandardLabel (bic, standardInsets);
      gridSummary.add (bicValue, 1, gridRow);

      gridRow++;
    }

    final Label ibanDescription = FormElementCreator.getStandardLabel ("IBAN:", standardInsets);
    gridSummary.add (ibanDescription, 0, gridRow);

    final Label ibanValue = FormElementCreator.getStandardLabel (ba.getIban (), standardInsets);
    gridSummary.add (ibanValue, 1, gridRow);

    gridRow++;

    final String accountOwner = ba.getBankAccountOwner ();
    if (accountOwner != null)
    {
      final Label accountOwnerDescription = FormElementCreator.getStandardLabel ("Kontoinhaber:", standardInsets);
      gridSummary.add (accountOwnerDescription, 0, gridRow);

      final Label accountOwnerValue = FormElementCreator.getStandardLabel (accountOwner, standardInsets);
      gridSummary.add (accountOwnerValue, 1, gridRow);

      gridRow++;
    }

    v.getChildren ().add (gridSummary);

    return v;
  }

  /*
   * Create summary box of invoice line data
   */
  private VBox _getDetailsSummary ()
  {
    gridRow = 0;
    gridCol = 0;
    final int divisor = id.getInvoiceRecipient ().getOrderReference ().isOrderIDGovernmentOrderNumber () ? maxBound
                                                                                                         : minBound;

    final VBox v = new VBox ();
    final Label l = FormElementCreator.getStandardLabel ((documentIsCreditMemo ? "Gutschrifts" : "Rechnungs") +
                                                         "zeilen",
                                                         new Insets (5, 5, 5, 5));
    l.setFont (Font.font (l.getFont ().getName (), FontWeight.BOLD, l.getFont ().getSize ()));
    v.getChildren ().add (l);

    final GridPane gridSummary = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / divisor);
      gridSummary.getColumnConstraints ().add (column);
    }

    gridSummary.setPadding (Data.BASEPANE_PADDING);
    gridSummary.setHgap (Data.BASEPANE_HVGAP);
    gridSummary.setVgap (grid.getHgap ());

    _createTableHeader (gridSummary, divisor);

    for (final InvoiceLine line : rm.getDetailsPane ().getInvoiceLineArea ().getInvoiceLineList ())
      _createInvoiceLineInTable (gridSummary, divisor, line);

    v.getChildren ().add (gridSummary);

    return v;
  }

  /*
   * Create summary box of surcharge data
   */
  private VBox _getSurchargeSummary ()
  {
    gridRow = 0;

    final VBox v = new VBox ();
    final Label l = FormElementCreator.getStandardLabel ("Aufschläge / Abzüge", new Insets (5, 5, 5, 5));
    _setFontBold (l);
    v.getChildren ().add (l);

    final GridPane gridSummary = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 2);
      gridSummary.getColumnConstraints ().add (column);
    }

    gridSummary.setPadding (Data.BASEPANE_PADDING);
    gridSummary.setHgap (Data.BASEPANE_HVGAP);
    gridSummary.setVgap (gridSummary.getHgap ());

    final List <SurchargeLine> surchargeLineList = rm.getSurchargeDiscountPane ()
                                                     .getSurchargeArea ()
                                                     .getSurchargeLineList ();
    if (surchargeLineList == null || surchargeLineList.size () == 0)
    {
      final Label noSurcharge = FormElementCreator.getStandardLabel ("Keine angegeben", standardInsets);
      gridSummary.add (noSurcharge, 0, 0);
    }
    else
    {
      final String currentCurrency = ECurrency.getFromInvoiceCurrencyShortOrNull (id.getInvoiceCurrency ())
                                              .getInvoiceCurrency () +
                                     " ";

      for (final SurchargeLine sl : surchargeLineList)
      {
        final StringBuilder sb = new StringBuilder ();

        sb.append (currentCurrency +
                   TextFieldHelper.getTwoDecimalsStringFromString (sl.getSurchargeValue ().toString ()) +
                   " mit " +
                   sl.getVatRate ().toString () +
                   "% USt");

        final String comment = sl.getComment ();
        if (comment != null && !comment.isEmpty ())
          sb.append (" (" + comment + ")");

        gridSummary.add (FormElementCreator.getStandardLabel (sb.toString (), standardInsets), 0, gridRow, 2, 1);

        gridRow++;
      }
    }

    v.getChildren ().add (gridSummary);

    return v;
  }

  /*
   * Create summary box of total gross amount
   */
  private VBox _getTotalGrossSummary ()
  {
    gridRow = 0;

    final VBox v = new VBox ();
    final Label l = FormElementCreator.getStandardLabel ("Gesamtsumme der " +
                                                         (documentIsCreditMemo ? "Gutschrift" : "Rechnung") +
                                                         " brutto",
                                                         new Insets (5, 5, 5, 5));
    _setFontBold (l);
    v.getChildren ().add (l);

    final GridPane gridSummary = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 2);
      gridSummary.getColumnConstraints ().add (column);
    }

    gridSummary.setPadding (Data.BASEPANE_PADDING);
    gridSummary.setHgap (Data.BASEPANE_HVGAP);
    gridSummary.setVgap (grid.getHgap ());

    final Label totalGrossAmountDescription = FormElementCreator.getStandardLabel ("Betrag:", standardInsets);
    gridSummary.add (totalGrossAmountDescription, 0, gridRow);

    final Label totalGrossAmountValue = FormElementCreator.getStandardLabel (TextFieldHelper.getTwoDecimalsStringFromString (rm.getSurchargeDiscountPane ()
                                                                                                                               .getOverAllTotalGross ()
                                                                                                                               .toString ()) +
                                                                             " " +
                                                                             ECurrency.getFromInvoiceCurrencyShortOrNull (id.getInvoiceCurrency ())
                                                                                      .getInvoiceCurrency (),
                                                                             standardInsets);
    gridSummary.add (totalGrossAmountValue, 1, gridRow);

    gridRow++;

    v.getChildren ().add (gridSummary);

    return v;
  }

  /*
   * Create summary box of surcharge data
   */
  private VBox _getDiscountSummary ()
  {
    gridRow = 0;

    final VBox v = new VBox ();
    final Label l = FormElementCreator.getStandardLabel ("Gewährte Skonti", new Insets (5, 5, 5, 5));
    _setFontBold (l);
    v.getChildren ().add (l);

    final GridPane gridSummary = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 2);
      gridSummary.getColumnConstraints ().add (column);
    }

    gridSummary.setPadding (Data.BASEPANE_PADDING);
    gridSummary.setHgap (Data.BASEPANE_HVGAP);
    gridSummary.setVgap (gridSummary.getHgap ());

    final List <DiscountLine> discountLineList = rm.getSurchargeDiscountPane ()
                                                   .getDiscountArea ()
                                                   .getDiscountLineList ();
    if (discountLineList == null || discountLineList.size () == 0)
    {
      final Label noSurcharge = FormElementCreator.getStandardLabel ("Keine angegeben", standardInsets);
      gridSummary.add (noSurcharge, 0, 0);
    }
    else
    {
      for (final DiscountLine dl : discountLineList)
      {
        final StringBuilder sb = new StringBuilder ();

        sb.append (TextFieldHelper.getTwoDecimalsStringFromString (dl.getDiscountPercent ().toString ()) +
                   "% bei Bezahlung bis ");

        final String ifPaidUntil = dl.getIfPaidUntil ();
        if (ifPaidUntil == null)
          sb.append ("(Noch kein Datum angegeben)");
        else
          sb.append (rm.getInvoiceDateManager ().getGermanFormatedDateFromLocalDateString (ifPaidUntil));

        gridSummary.add (FormElementCreator.getStandardLabel (sb.toString (), standardInsets), 0, gridRow, 2, 1);

        gridRow++;
      }
    }

    v.getChildren ().add (gridSummary);

    return v;
  }

  /*
   * Create summary box of due date
   */
  private VBox _getDueDateSummary ()
  {
    gridRow = 0;

    final VBox v = new VBox ();
    final Label l = FormElementCreator.getStandardLabel ("Fälligkeit der Rechnung netto", new Insets (5, 5, 5, 5));
    _setFontBold (l);
    v.getChildren ().add (l);

    final GridPane gridSummary = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 2);
      gridSummary.getColumnConstraints ().add (column);
    }

    gridSummary.setPadding (Data.BASEPANE_PADDING);
    gridSummary.setHgap (Data.BASEPANE_HVGAP);
    gridSummary.setVgap (grid.getHgap ());

    final Label dueDateDescription = FormElementCreator.getStandardLabel ("Datum:", standardInsets);
    gridSummary.add (dueDateDescription, 0, gridRow);

    final PaymentConditions paymentConditions = id.getPaymentConditions ();
    String dueDate;
    if (paymentConditions == null)
    {
      dueDate = "Keines angegeben";
    }
    else
    {
      dueDate = paymentConditions.getDueDate ();

      if (dueDate == null)
        dueDate = "Keines angegeben";
    }

    final Label totalGrossAmountValue = FormElementCreator.getStandardLabel (dueDate, standardInsets);
    gridSummary.add (totalGrossAmountValue, 1, gridRow);

    gridRow++;

    v.getChildren ().add (gridSummary);

    return v;
  }

  /*
   * Set new gridCol and gridRow
   */
  private void _incrementGridCol (final int bound)
  {
    gridCol++;

    if (gridCol >= bound)
    {
      gridCol = 0;
      gridRow++;
    }
  }

  /*
   * Set font for label to font weight bold
   */
  private void _setFontBold (final Label label)
  {
    label.setFont (Font.font (label.getFont ().getName (), FontWeight.BOLD, label.getFont ().getSize ()));
  }

  /*
   * Create table header for invoice lines
   */
  private void _createTableHeader (final GridPane grid, final int bound)
  {
    if (bound == maxBound)
    {
      final Label opnLabel = FormElementCreator.getStandardLabel ("Pos.Nr.", lineSummaryInsets);
      _setFontBold (opnLabel);
      grid.add (opnLabel, gridCol, gridRow);
      _incrementGridCol (bound);
    }

    final Label quantityLabel = FormElementCreator.getStandardLabel ("Menge", lineSummaryInsets);
    _setFontBold (quantityLabel);
    grid.add (quantityLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final Label unitLabel = FormElementCreator.getStandardLabel ("Einheit", lineSummaryInsets);
    _setFontBold (unitLabel);
    grid.add (unitLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final Label descriptionLabel = FormElementCreator.getStandardLabel ("Beschreibung", lineSummaryInsets);
    _setFontBold (descriptionLabel);
    grid.add (descriptionLabel, gridCol, gridRow, 2, 1);
    _incrementGridCol (bound);
    _incrementGridCol (bound);

    final Label netPriceLabel = FormElementCreator.getStandardLabel ("Einzel netto", lineSummaryInsets);
    _setFontBold (netPriceLabel);
    grid.add (netPriceLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final Label netPriceAmountLabel = FormElementCreator.getStandardLabel ("Einzel gesamt", lineSummaryInsets);
    _setFontBold (netPriceAmountLabel);
    grid.add (netPriceAmountLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final Label surchargeLabel = FormElementCreator.getStandardLabel ("Auf-/Abschlag", lineSummaryInsets);
    _setFontBold (surchargeLabel);
    grid.add (surchargeLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final Label grossAmountLabel = FormElementCreator.getStandardLabel ("Brutto gesamt", lineSummaryInsets);
    _setFontBold (grossAmountLabel);
    grid.add (grossAmountLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final Label vatLabel = FormElementCreator.getStandardLabel ("Steuersatz", lineSummaryInsets);
    _setFontBold (vatLabel);
    grid.add (vatLabel, gridCol, gridRow);
    _incrementGridCol (bound);
  }

  /*
   * Create invoice line in table
   */
  private void _createInvoiceLineInTable (final GridPane grid, final int bound, final InvoiceLine line)
  {
    if (bound == maxBound)
    {
      final Label opnLabel = FormElementCreator.getStandardLabel (line.getOrderPositionNumber ().toString (),
                                                                  lineSummaryInsets);
      grid.add (opnLabel, gridCol, gridRow);
      _incrementGridCol (bound);
    }

    final Label quantityLabel = FormElementCreator.getStandardLabel (TextFieldHelper.getFourDecimalsStringFromDouble (line.getQuantity ()),
                                                                     lineSummaryInsets);
    grid.add (quantityLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final Label unitLabel = FormElementCreator.getStandardLabel (line.getUnit (), lineSummaryInsets);
    grid.add (unitLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    String lineDescription = line.getDescription ();
    lineDescription = (lineDescription == null ? ""
                                               : lineDescription.length () > 23 ? lineDescription.substring (0, 20) +
                                                                                  "..."
                                                                                : lineDescription);
    final Label descriptionLabel = FormElementCreator.getStandardLabel (lineDescription, lineSummaryInsets);
    grid.add (descriptionLabel, gridCol, gridRow, 2, 1);
    _incrementGridCol (bound);
    _incrementGridCol (bound);

    final String unitPrice = (line.getUnitprice () == null ? ""
                                                           : TextFieldHelper.getFourDecimalsStringFromDouble (line.getUnitprice ()));
    final Label netPriceLabel = FormElementCreator.getStandardLabel (unitPrice, lineSummaryInsets);
    grid.add (netPriceLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final Label netPriceAmountLabel = FormElementCreator.getStandardLabel (TextFieldHelper.getTwoDecimalsStringFromDouble (line.getTotalNetAmount ()),
                                                                           lineSummaryInsets);
    grid.add (netPriceAmountLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final String lineSurcharge = (line.getSurcharge () == null ? "" : line.getSurcharge ().toString ());
    final Label surchargeLabel = FormElementCreator.getStandardLabel (lineSurcharge, lineSummaryInsets);
    grid.add (surchargeLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final Label grossAmountLabel = FormElementCreator.getStandardLabel (TextFieldHelper.getTwoDecimalsStringFromDouble (line.getTotalGrossAmount ()),
                                                                        lineSummaryInsets);
    grid.add (grossAmountLabel, gridCol, gridRow);
    _incrementGridCol (bound);

    final Integer vatRate = line.getVatRate ();
    final String vat = (vatRate == null ? "Steuerbefreit" : vatRate.toString () + "%");
    final Label vatLabel = FormElementCreator.getStandardLabel (vat, lineSummaryInsets);
    grid.add (vatLabel, gridCol, gridRow);
    _incrementGridCol (bound);
  }
}
