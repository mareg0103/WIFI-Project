package at.mareg.ebi43creator.display.form.invoicelines;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.form.help.HelpArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.TextFieldHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.details.ListLineItem;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import at.mareg.ebi43creator.invoicedata.enums.EVATRate;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InvoiceLine extends BasePane
{
  /**
   * This is the invoice line; it saves and calculates all necessary values for
   * an invoice line
   *
   * @author Martin Regitnig
   */

  /*
   * Invoice line counter
   */
  private static int invoiceLineCounter = 0;

  /*
   * Save list line item this line belongs to
   */
  private final ListLineItem listLineItem;

  /*
   * InvoiceLineArea instance
   */
  private final InvoiceLineArea invoiceLineArea;

  /*
   * Help area instance
   */
  private final HelpArea helpArea;

  /*
   * Pane elements
   */
  private GridPane grid;
  private Button removeThisLine;

  private Label opnLabel;
  private TextField opnField;
  private TextField quantityField;
  private ComboBox <String> unitComboBox;
  private TextField unitPriceField;
  private TextArea descriptionArea;
  private TextField totalNetAmountField;
  private TextField surchargeField;
  private TextField surchargeCommentField;
  private Label vatRateLabel;
  private ComboBox <String> vatComboBox;
  private CheckBox taxExemptionCheckBox;
  private Label taxExemptionReasonLabel;
  private TextField taxExemptionReasonField;
  private TextField totalGrossAmountField;

  /*
   * Line variables to save the value for later use of this line (Calculation,
   * Summary, ...)
   */
  private final int lineNumber;
  private Integer orderPositionNumber;
  private Double quantity;
  private String unit;
  private Double unitprice;
  private String description;
  private Double totalNetAmount;
  private Double totalNetAmountWithSurcharge;
  private Double surcharge;
  private String comment;
  private Integer vatRate;
  private String taxexemptionreason;
  private Double totalGrossAmount;

  /*
   * Event handler
   */
  private EventHandler <KeyEvent> onlyNumbersEventHandler;
  private EventHandler <KeyEvent> onlyNumbersSemicolonMinusTwoDecimalDigits;
  private EventHandler <KeyEvent> onlyNumbersSemicolonMinusFourDecimalDigits;

  public InvoiceLine (final ResourceManager resman, final ListLineItem li)
  {
    super (resman);
    invoiceLineCounter++;
    lineNumber = invoiceLineCounter;

    listLineItem = li;
    invoiceLineArea = rm.getDetailsPane ().getInvoiceLineArea ();

    vatRate = Integer.valueOf (20);

    helpArea = resman.getHelpArea ();

    _initEventHandler ();
    init ();
  }

  /*
   * Initialize event handlers
   */
  private void _initEventHandler ()
  {
    onlyNumbersEventHandler = event -> {
      if (!(event.getCharacter ().matches ("[0-9]")))
      {
        event.consume ();
      }
    };

    onlyNumbersSemicolonMinusTwoDecimalDigits = event -> {
      final String text = ((TextField) event.getTarget ()).getText ();

      if ((text.length () > 0) && (event.getCharacter ().equals ("-")))
        event.consume ();

      if ((!(event.getCharacter ().matches ("[0-9]")) &&
           (!(event.getCharacter ().equals (","))) &&
           (!(event.getCharacter ().equals ("-")))))
        event.consume ();

      final int indexOfSemicolon = text.indexOf (",");
      if (indexOfSemicolon != -1)
        if (text.substring (indexOfSemicolon + 1).length () == 2 &&
            ((TextField) event.getTarget ()).getCaretPosition () > indexOfSemicolon)
          event.consume ();
    };

    onlyNumbersSemicolonMinusFourDecimalDigits = event -> {
      final String text = ((TextField) event.getTarget ()).getText ();

      if ((text.length () > 0) && (event.getCharacter ().equals ("-")))
        event.consume ();

      if ((!(event.getCharacter ().matches ("[0-9]")) &&
           (!(event.getCharacter ().equals (","))) &&
           (!(event.getCharacter ().equals ("-")))))
      {
        event.consume ();
      }

      final int indexOfSemicolon = text.indexOf (",");

      if (indexOfSemicolon != -1)
        if (text.substring (indexOfSemicolon + 1).length () == 4 &&
            ((TextField) event.getTarget ()).getCaretPosition () > indexOfSemicolon)
          event.consume ();
    };
  }

  @Override
  /*
   * Initialize invoice line, manual build of invoice line necessary because of
   * needed focus listeners for calculation and direct saving of values to list
   * line item
   */
  protected void init ()
  {
    grid = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.DETAILS_SCROLLPANE_WIDTH - 255) / 4);
      grid.getColumnConstraints ().add (column);
    }
    grid.setPadding (Data.LINE_PADDING);
    grid.setHgap (Data.LINE_HVGAP);
    grid.setVgap (grid.getHgap ());

    /*
     * Order Position Number
     */
    final VBox opnBox = new VBox ();

    final EFormElement opnElement = EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER;
    final String opnID = opnElement.getID ();
    final boolean opnRequired = opnElement.isRequired ();

    if (rm.getInvoiceData ().getInvoiceRecipient ().getOrderReference ().isOrderIDGovernmentOrderNumber ())
    {
      opnLabel = FormElementCreator.getStandardLabel (opnElement.getLabelText () + (opnRequired ? "*" : ""), null);
      opnField = FormElementCreator.getStandardTextField (opnID, opnRequired);
    }
    else
    {
      opnLabel = FormElementCreator.getDisabledLookingLabel (opnElement.getLabelText (), null);
      opnField = FormElementCreator.getDisabledTextField (opnID, opnRequired);
    }

    if (opnRequired)
      RequiredAndErrorHelper.addRequiredFieldForLine (Integer.valueOf (lineNumber), opnID);

    opnField.addEventHandler (KeyEvent.KEY_TYPED, onlyNumbersEventHandler);
    opnField.focusedProperty ().addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
      if (newValue)
      {
        helpArea.show (EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER.getID ());
      }
      else
      {
        if (!opnField.getText ().isEmpty ())
        {
          orderPositionNumber = TextFieldHelper.getIntegerFromString (opnField.getText ());
          listLineItem.setOrderPositionNumber (orderPositionNumber);

          RequiredAndErrorHelper.removeRequiredFieldForLine (Integer.valueOf (lineNumber), opnID);
        }
        else
        {
          orderPositionNumber = null;
          listLineItem.setOrderPositionNumber (null);

          if (opnRequired)
            RequiredAndErrorHelper.addRequiredFieldForLine (Integer.valueOf (lineNumber), opnID);
        }
      }
    });

    opnBox.getChildren ().addAll (opnLabel, opnField);

    VBoxHelper.structureVBox (opnBox);
    grid.add (opnBox, 0, 0);

    /*
     * Quantity
     */
    final EFormElement quantityElement = EFormElement.DETAILS_LINE_QUANTITY;
    final String quantityID = quantityElement.getID ();
    final boolean quantityRequired = quantityElement.isRequired ();

    final VBox quantityBox = new VBox ();

    final Label quantityLabel = FormElementCreator.getStandardLabel (quantityElement.getLabelText () +
                                                                     (quantityRequired ? "*" : ""),
                                                                     null);
    quantityField = FormElementCreator.getStandardTextField (quantityID, quantityRequired);

    if (quantityRequired)
      RequiredAndErrorHelper.addRequiredFieldForLine (Integer.valueOf (lineNumber), quantityID);

    quantityField.addEventHandler (KeyEvent.KEY_TYPED, onlyNumbersSemicolonMinusFourDecimalDigits);
    quantityField.focusedProperty ().addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
      if (newValue)
      {
        helpArea.show (EFormElement.DETAILS_LINE_QUANTITY.getID ());
      }
      else
      {
        if (!quantityField.getText ().isEmpty ())
        {
          quantity = TextFieldHelper.getDoubleFromString (quantityField.getText ());
          quantityField.setText (TextFieldHelper.getFourDecimalsStringFromDouble (quantity));
          listLineItem.getQuantity ().setQuantity (quantity);

          RequiredAndErrorHelper.removeRequiredFieldForLine (Integer.valueOf (lineNumber), quantityID);
        }
        else
        {
          quantity = null;
          listLineItem.getQuantity ().setQuantity (null);

          if (quantityRequired)
            RequiredAndErrorHelper.addRequiredFieldForLine (Integer.valueOf (lineNumber), quantityID);
        }

        calculateLine ();
      }
    });

    quantityBox.getChildren ().addAll (quantityLabel, quantityField);

    VBoxHelper.structureVBox (quantityBox);
    grid.add (quantityBox, 1, 0);

    /*
     * Unit
     */
    final VBox unitBox = new VBox ();

    final Label unitLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_UNIT.getLabelText (), null);
    unitComboBox = FormElementCreator.getInvoiceLineUnitComboBox (EFormElement.DETAILS_LINE_UNIT.getID ());
    unitComboBox.getSelectionModel ().select (Data.DEFAULT_UNIT);
    unitComboBox.focusedProperty ().addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
      if (newValue)
      {
        helpArea.show (EFormElement.DETAILS_LINE_UNIT.getID ());
      }
      else
      {
        unit = unitComboBox.getSelectionModel ().selectedItemProperty ().get ();
        listLineItem.getQuantity ().setUnit (unit);
      }
    });

    unitBox.getChildren ().addAll (unitLabel, unitComboBox);

    VBoxHelper.structureVBox (unitBox);
    grid.add (unitBox, 2, 0);

    /*
     * Unit price
     */
    final EFormElement unitPriceElement = EFormElement.DETAILS_LINE_UNITPRICE;
    final String unitPriceID = unitPriceElement.getID ();
    final boolean unitPriceRequired = unitPriceElement.isRequired ();

    final VBox unitPriceBox = new VBox ();

    final Label unitPriceLabel = FormElementCreator.getStandardLabel (unitPriceElement.getLabelText (), null);
    unitPriceField = FormElementCreator.getStandardTextField (unitPriceID, unitPriceRequired);

    if (unitPriceRequired)
      RequiredAndErrorHelper.addRequiredFieldForLine (Integer.valueOf (lineNumber), unitPriceID);

    unitPriceField.addEventHandler (KeyEvent.KEY_TYPED, onlyNumbersSemicolonMinusFourDecimalDigits);
    unitPriceField.focusedProperty ().addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
      if (newValue)
      {
        helpArea.show (EFormElement.DETAILS_LINE_UNITPRICE.getID ());
      }
      else
      {
        if (!unitPriceField.getText ().isEmpty ())
        {
          unitprice = TextFieldHelper.getDoubleFromString (unitPriceField.getText ());
          unitPriceField.setText (TextFieldHelper.getFourDecimalsStringFromDouble (unitprice));
          listLineItem.setUnitPrice (unitprice);

          RequiredAndErrorHelper.removeRequiredFieldForLine (Integer.valueOf (lineNumber), unitPriceID);
        }
        else
        {
          unitprice = null;
          listLineItem.setUnitPrice (null);
        }

        calculateLine ();
      }
    });

    unitPriceBox.getChildren ().addAll (unitPriceLabel, unitPriceField);

    VBoxHelper.structureVBox (unitPriceBox);
    grid.add (unitPriceBox, 3, 0);

    /*
     * Description
     */
    final EFormElement descriptionElement = EFormElement.DETAILS_LINE_DESCRIPTION;
    final String descriptionID = descriptionElement.getID ();
    final boolean descriptionRequired = unitPriceElement.isRequired ();

    final VBox descriptionBox = new VBox ();

    final Label descriptionLabel = FormElementCreator.getStandardLabel (descriptionElement.getLabelText (), null);
    descriptionArea = FormElementCreator.getInvoiceLineTextArea (descriptionID, descriptionRequired);

    if (descriptionRequired)
      RequiredAndErrorHelper.addRequiredFieldForLine (Integer.valueOf (lineNumber), descriptionID);

    descriptionArea.focusedProperty ().addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
      if (newValue)
      {
        helpArea.show (EFormElement.DETAILS_LINE_DESCRIPTION.getID ());
      }
      else
      {
        if (!descriptionArea.getText ().isEmpty ())
        {
          description = descriptionArea.getText ();
          listLineItem.setDescription (description);

          RequiredAndErrorHelper.removeRequiredFieldForLine (Integer.valueOf (lineNumber), descriptionID);
        }
        else
        {
          description = null;
          listLineItem.setDescription (null);

          if (descriptionRequired)
            RequiredAndErrorHelper.addRequiredFieldForLine (Integer.valueOf (lineNumber), descriptionID);
        }
      }
    });

    descriptionBox.getChildren ().addAll (descriptionLabel, descriptionArea);

    VBoxHelper.structureVBox (descriptionBox);
    grid.add (descriptionBox, 0, 1, 2, 2);

    /*
     * Total Net Amount
     */
    final VBox totalNetBox = new VBox ();

    final Label totalNetLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_TOTALNET.getLabelText (),
                                                                     null);
    totalNetAmountField = FormElementCreator.getStandardTextField (EFormElement.DETAILS_LINE_TOTALNET.getID (),
                                                                   EFormElement.DETAILS_LINE_TOTALNET.isRequired ());
    totalNetAmountField.setEditable (false);
    totalNetAmountField.focusedProperty ().addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
      if (newValue)
      {
        helpArea.show (EFormElement.DETAILS_LINE_TOTALNET.getID ());
      }
    });

    totalNetBox.getChildren ().addAll (totalNetLabel, totalNetAmountField);

    VBoxHelper.structureVBox (totalNetBox);
    grid.add (totalNetBox, 2, 1, 2, 1);

    /*
     * Surcharge
     */
    final VBox surchargeBox = new VBox ();

    final Label surchargeLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_SURCHARGE.getLabelText (),
                                                                      null);
    surchargeField = FormElementCreator.getStandardTextField (EFormElement.DETAILS_LINE_SURCHARGE.getID (),
                                                              EFormElement.DETAILS_LINE_SURCHARGE.isRequired ());
    surchargeField.addEventHandler (KeyEvent.KEY_TYPED, onlyNumbersSemicolonMinusTwoDecimalDigits);
    surchargeField.focusedProperty ().addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
      if (newValue)
      {
        helpArea.show (EFormElement.DETAILS_LINE_SURCHARGE.getID ());
      }
      else
      {
        if (!surchargeField.getText ().isEmpty ())
        {
          surcharge = TextFieldHelper.getDoubleFromString (surchargeField.getText ());
          surchargeField.setText (TextFieldHelper.getTwoDecimalsStringFromDouble (surcharge));
          listLineItem.addSurcharge (totalNetAmount, surcharge);

          FormElementCreator.enableTextField (surchargeCommentField,
                                              EFormElement.getFromIDOrNull (surchargeCommentField.getId ()));
          surchargeCommentField.requestFocus ();
        }
        else
        {
          surcharge = null;
          comment = null;
          listLineItem.removeSurchare ();

          FormElementCreator.disableTextField (surchargeCommentField,
                                               EFormElement.getFromIDOrNull (surchargeCommentField.getId ()));
        }
      }

      calculateLine ();
    });

    surchargeBox.getChildren ().addAll (surchargeLabel, surchargeField);

    VBoxHelper.structureVBox (surchargeBox);
    grid.add (surchargeBox, 2, 2);

    /*
     * Surcharge comment
     */
    final VBox surchargeCommentBox = new VBox ();

    final Label surchargeCommentLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_SURCHARGE_DESCRIPTION.getLabelText (),
                                                                             null);
    surchargeCommentField = FormElementCreator.getStandardTextField (EFormElement.DETAILS_LINE_SURCHARGE_DESCRIPTION.getID (),
                                                                     EFormElement.DETAILS_LINE_SURCHARGE_DESCRIPTION.isRequired ());
    FormElementCreator.disableTextField (surchargeCommentField,
                                         EFormElement.getFromIDOrNull (surchargeCommentField.getId ()));
    surchargeCommentField.focusedProperty ()
                         .addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
                           if (newValue)
                           {
                             helpArea.show (EFormElement.DETAILS_LINE_SURCHARGE.getID ());
                           }
                           else
                           {
                             if (!surchargeCommentField.getText ().isEmpty ())
                             {
                               comment = surchargeCommentField.getText ();
                               listLineItem.addSurchargeComment (comment);
                             }
                             else
                             {
                               comment = null;
                             }
                           }
                         });

    surchargeCommentBox.getChildren ().addAll (surchargeCommentLabel, surchargeCommentField);

    VBoxHelper.structureVBox (surchargeCommentBox);
    grid.add (surchargeCommentBox, 3, 2);

    /*
     * VAT rate
     */
    final VBox vatRateBox = new VBox ();

    vatRateLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_VAT.getLabelText () + "*", null);
    vatComboBox = FormElementCreator.getVatRateComboBox (EFormElement.DETAILS_LINE_VAT.getID ());
    listLineItem.setVatRate (EVATRate.getFromIDOrNull (Data.DEFAULT_VAT_RATE).getVatRateInteger ());
    vatComboBox.setOnAction (e -> {
      vatRate = EVATRate.getFromIDOrNull (vatComboBox.getSelectionModel ().getSelectedItem ()).getVatRateInteger ();
      listLineItem.setVatRate (vatRate);

      calculateLine ();
    });
    vatComboBox.focusedProperty ().addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
      if (newValue)
      {
        helpArea.show (EFormElement.DETAILS_LINE_VAT.getID ());
      }
    });

    vatRateBox.getChildren ().addAll (vatRateLabel, vatComboBox);

    VBoxHelper.structureVBox (vatRateBox);
    grid.add (vatRateBox, 0, 4);

    /*
     * Tax exemption
     */
    final VBox taxExemptionBox = new VBox ();

    final Label taxExemptionLabel = FormElementCreator.getStandardLabel ("", null);
    taxExemptionCheckBox = new CheckBox (EFormElement.DETAILS_LINE_TAXEXEMPTION_CHECK.getLabelText ());
    taxExemptionCheckBox.hoverProperty ().addListener ( (observable) -> {
      helpArea.show (EFormElement.DETAILS_LINE_TAXEXEMPTION_CHECK.getID ());
    });
    taxExemptionCheckBox.selectedProperty ().addListener ( (observable, oldValue, newValue) -> {
      rm.getInvoiceData ().getDelivery ().enableDeliveryAddress (newValue);

      if (newValue)
        enableTaxExemption ();
      else
        _enableVatRate ();

    });

    taxExemptionBox.getChildren ().addAll (taxExemptionLabel, taxExemptionCheckBox);

    VBoxHelper.structureVBox (taxExemptionBox);
    grid.add (taxExemptionBox, 1, 4);

    /*
     * Tax exemption description
     */
    final VBox taxExemptionReasonBox = new VBox ();

    taxExemptionReasonLabel = FormElementCreator.getDisabledLookingLabel (EFormElement.DETAILS_LINE_TAXEXEMPTION_REASON.getLabelText (),
                                                                          null);
    taxExemptionReasonField = FormElementCreator.getDisabledTextField (EFormElement.DETAILS_LINE_TAXEXEMPTION_REASON.getID (),
                                                                       EFormElement.DETAILS_LINE_TAXEXEMPTION_REASON.isRequired ());
    taxExemptionReasonField.focusedProperty ()
                           .addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
                             if (newValue)
                             {
                               helpArea.show (EFormElement.DETAILS_LINE_TAXEXEMPTION_REASON.getID ());
                             }
                             else
                             {
                               taxexemptionreason = taxExemptionReasonField.getText ();
                               listLineItem.setTaxExemptionReasonInternal (taxexemptionreason);

                               if (!taxexemptionreason.isEmpty ())
                                 RequiredAndErrorHelper.removeRequiredFieldForLine (Integer.valueOf (getInvoiceLineNumber ()),
                                                                                    taxExemptionReasonField.getId ());
                             }
                           });

    taxExemptionReasonBox.getChildren ().addAll (taxExemptionReasonLabel, taxExemptionReasonField);

    VBoxHelper.structureVBox (taxExemptionReasonBox);
    grid.add (taxExemptionReasonBox, 2, 4);

    /*
     * Total Gross Amount
     */
    final VBox totalGrosBox = new VBox ();

    final Label totalGrossAmountLabel = FormElementCreator.getStandardLabel (EFormElement.DETAILS_LINE_TOTALGROSS.getLabelText (),
                                                                             null);
    totalGrossAmountField = FormElementCreator.getStandardTextField (EFormElement.DETAILS_LINE_TOTALGROSS.getID (),
                                                                     EFormElement.DETAILS_LINE_TOTALGROSS.isRequired ());
    totalGrossAmountField.setEditable (false);
    totalGrossAmountField.focusedProperty ()
                         .addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
                           if (newValue)
                           {
                             helpArea.show (EFormElement.DETAILS_LINE_TOTALGROSS.getID ());
                           }
                         });

    totalGrosBox.getChildren ().addAll (totalGrossAmountLabel, totalGrossAmountField);

    VBoxHelper.structureVBox (totalGrosBox);
    grid.add (totalGrosBox, 3, 4);

    /*
     * Delete button
     */
    removeThisLine = new Button (EFormElement.DETAILS_LINE_REMOVE.getLabelText (),
                                 new ImageView (new Image ("at/mareg/ebi43creator/display/images/Loeschen_50x33.png")));
    removeThisLine.hoverProperty ().addListener ( (observable) -> {
      helpArea.show (EFormElement.DETAILS_LINE_REMOVE.getID ());
    });
    removeThisLine.setOnAction (e -> {
      invoiceLineArea.removeInvoiceLine (this);

      RequiredAndErrorHelper.removeLineFromLineRequiredMap (Integer.valueOf (lineNumber));
    });

    grid.add (removeThisLine, 5, 4);

    /*
     * Invoice line number
     */
    final Label invoiceLineNumber = FormElementCreator.getStandardLabel (String.valueOf (lineNumber),
                                                                         new Insets (5, 15, 5, 15));
    invoiceLineNumber.setFont (Font.font (invoiceLineNumber.getFont ().getFamily (), FontWeight.BOLD, 12));
    grid.add (invoiceLineNumber, 5, 0);

    this.setBorder (new Border (new BorderStroke (Color.BLACK,
                                                  Color.BLACK,
                                                  Color.BLACK,
                                                  Color.BLACK,
                                                  BorderStrokeStyle.SOLID,
                                                  BorderStrokeStyle.SOLID,
                                                  BorderStrokeStyle.SOLID,
                                                  BorderStrokeStyle.SOLID,
                                                  null,
                                                  BorderWidths.DEFAULT,
                                                  new Insets (3, 3, 3, 3))));
    this.add (grid, 0, 0);

    calculateLine ();
  }

  /*
   * Get invoice line number
   */
  public int getInvoiceLineNumber ()
  {
    return lineNumber;
  }

  /*
   * Sets the status of field and label OrderPositionNumber; if order id is an
   * order number of government -> set status required and enabled, otherwise
   * set status optional and disabled
   */
  public void setOrderPositionNumberStatus (final boolean status)
  {
    final EFormElement opnElement = EFormElement.DETAILS_LINE_ORDERPOSITIONNUMER;

    if (status)
    {
      FormElementCreator.showLabelAsEnabled (opnLabel, opnElement);
      FormElementCreator.enableTextField (opnField, opnElement);

      if (opnElement.isRequired ())
        RequiredAndErrorHelper.addRequiredFieldForLine (Integer.valueOf (lineNumber), opnElement.getID ());
    }
    else
    {
      FormElementCreator.showLabelAsDisabled (opnLabel, opnElement);
      FormElementCreator.disableTextField (opnField, opnElement);

      RequiredAndErrorHelper.removeRequiredFieldForLine (Integer.valueOf (lineNumber), opnElement.getID ());
    }
  }

  /*
   * Activates tax exemption
   */
  public void enableTaxExemption ()
  {
    // Enable tax exemption
    final EFormElement t = EFormElement.DETAILS_LINE_TAXEXEMPTION_REASON;
    t.setIsRequired (true);

    FormElementCreator.showLabelAsEnabled (taxExemptionReasonLabel, t);
    FormElementCreator.enableTextField (taxExemptionReasonField, t);

    RequiredAndErrorHelper.addRequiredFieldForLine (Integer.valueOf (lineNumber), t.getID ());

    // Disable vat rate
    final EFormElement v = EFormElement.DETAILS_LINE_VAT;
    v.setIsRequired (false);

    FormElementCreator.showLabelAsDisabled (vatRateLabel, v);
    FormElementCreator.disableComboBox (vatComboBox);

    this.vatRate = null;
    listLineItem.setVatRate (null);

    calculateLine ();
  }

  /*
   * Activates vat rate
   */
  private void _enableVatRate ()
  {
    // Disable tax exemption
    final EFormElement t = EFormElement.DETAILS_LINE_TAXEXEMPTION_REASON;
    t.setIsRequired (false);

    FormElementCreator.showLabelAsDisabled (taxExemptionReasonLabel, t);
    FormElementCreator.disableTextField (taxExemptionReasonField, t);

    RequiredAndErrorHelper.removeRequiredFieldForLine (Integer.valueOf (lineNumber), t.getID ());

    this.taxexemptionreason = null;
    listLineItem.setTaxExemption (taxexemptionreason);

    // Enable vat rate
    final EFormElement v = EFormElement.DETAILS_LINE_VAT;
    v.setIsRequired (true);

    FormElementCreator.showLabelAsEnabled (vatRateLabel, v);
    FormElementCreator.enableComboBox (vatComboBox);

    final String selectedVat = vatComboBox.getSelectionModel ().getSelectedItem ();
    vatRate = EVATRate.getFromIDOrNull (selectedVat).getVatRateInteger ();
    listLineItem.setVatRate (vatRate);

    calculateLine ();
  }

  /*
   * Calls all calculate methods below
   */
  public void calculateLine ()
  {
    _calculateTotalNetAmount ();
    _calculateTotalGrossAmount ();
    rm.getDetailsPane ().refreshTotalNetAndGrossAmount ();
  }

  /*
   * Calculates the total net amount of this line
   */
  public void _calculateTotalNetAmount ()
  {
    if (quantity != null && unitprice != null)
      totalNetAmount = quantity * unitprice;
    else
      totalNetAmount = Double.valueOf (0);

    if (surcharge != null)
      totalNetAmountWithSurcharge = totalNetAmount + surcharge;
    else
      totalNetAmountWithSurcharge = totalNetAmount;

    totalNetAmountField.setText (TextFieldHelper.getTwoDecimalsStringFromDouble (totalNetAmount));
  }

  /*
   * Calculates the total gross amount of this line and sets the line item
   * amount to the list line item
   */
  public void _calculateTotalGrossAmount ()
  {
    final double rate = vatRate == null ? 0 : 1 + ((double) vatRate / 100);

    final Double lineItemAmount = totalNetAmountWithSurcharge;

    totalGrossAmount = lineItemAmount.doubleValue () * (rate == 0 ? 1 : rate);

    totalGrossAmountField.setText (TextFieldHelper.getTwoDecimalsStringFromDouble (totalGrossAmount));

    _setLineItemAmountToLidtLineItem (lineItemAmount);
  }

  /*
   * Saves line item amount in list line item
   */
  private void _setLineItemAmountToLidtLineItem (final Double lineItemAmount)
  {
    listLineItem.setLineItemAmount (lineItemAmount);
  }

  /*
   * Return list line item instance save in this line
   */
  public ListLineItem getListLineItem ()
  {
    return listLineItem;
  }

  /*
   * Get grid to run through elements to fill them with data
   */
  public GridPane getGridPane ()
  {
    return grid;
  }

  /*
   * Set tax examption check box to enable if in a loaded XML a tax exemption is
   * entered in a line
   */
  public void setTaxExemptionCheckBoxToSelected ()
  {
    taxExemptionCheckBox.selectedProperty ().set (true);
  }

  /*
   * Getter / Setter for line variables
   */
  public Integer getOrderPositionNumber ()
  {
    return orderPositionNumber;
  }

  public void setOrderPositionNumber (final Integer orderPositionNumber)
  {
    this.orderPositionNumber = orderPositionNumber;
  }

  public Double getQuantity ()
  {
    return quantity;
  }

  public void setQuantity (final Double quantity)
  {
    this.quantity = quantity;
  }

  public String getUnit ()
  {
    return unit;
  }

  public void setUnit (final String unit)
  {
    this.unit = unit;
  }

  public Double getUnitprice ()
  {
    return unitprice;
  }

  public void setUnitprice (final Double unitprice)
  {
    this.unitprice = unitprice;
  }

  public String getDescription ()
  {
    return description;
  }

  public void setDescription (final String description)
  {
    this.description = description;
  }

  public Double getTotalNetAmount ()
  {
    return totalNetAmount;
  }

  public void setTotalNetAmount (final Double totalNetAmount)
  {
    this.totalNetAmount = totalNetAmount;
  }

  public Double getTotalNetAmountWithSurcharge ()
  {
    return totalNetAmountWithSurcharge;
  }

  public void setTotalNetAmountWithSurcharge (final Double totalNetAmountWithSurcharge)
  {
    this.totalNetAmountWithSurcharge = totalNetAmountWithSurcharge;
  }

  public Double getSurcharge ()
  {
    return surcharge;
  }

  public void setSurcharge (final Double surcharge)
  {
    this.surcharge = surcharge;
  }

  public Integer getVatRate ()
  {
    return vatRate;
  }

  public String getComment ()
  {
    return comment;
  }

  public void setComment (final String comment)
  {
    this.comment = comment;
  }

  public void setVatRate (final Integer vatRate)
  {
    this.vatRate = vatRate;
  }

  public String getTaxexemptionreason ()
  {
    return taxexemptionreason;
  }

  public void setTaxexemptionreason (final String taxexemptionreason)
  {
    this.taxexemptionreason = taxexemptionreason;
  }

  public Double getTotalGrossAmount ()
  {
    return totalGrossAmount;
  }

  public void setTotalGrossAmount (final Double totalGrossAmount)
  {
    this.totalGrossAmount = totalGrossAmount;
  }
}
