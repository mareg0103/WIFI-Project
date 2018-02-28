package at.mareg.ebi43creator.display.form.discount;

import java.time.LocalDate;
import java.util.List;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.form.help.HelpArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.InvoiceDateManager;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.TextFieldHelper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import at.mareg.ebi43creator.invoicedata.payment.Discount;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

public class DiscountLine extends BasePane
{
  /*
   * Save discount item this line belongs to
   */
  private final Discount discount;

  /*
   * DiscountArea instance
   */
  private final DiscountArea discountArea;

  /*
   * Help area instance
   */
  private final HelpArea helpArea;

  /*
   * DateManager instance
   */
  private final InvoiceDateManager invoiceDateManager;

  /*
   * Pane elements
   */
  private GridPane grid;
  private TextField discountPercentField;
  private DatePicker ifPaidUntilDatePicker;
  private Button deleteThisLine;

  /*
   * Line variables
   */
  private Double discountPercent;
  private String ifPaidUntil;

  /*
   * EventHandler
   */
  private EventHandler <KeyEvent> onlyNumbersSemicolonTwoDecimalDigits;

  public DiscountLine (final ResourceManager rm, final Discount d)
  {
    super (rm);

    discount = d;
    discountArea = rm.getSurchargeDiscountPane ().getDiscountArea ();
    helpArea = rm.getHelpArea ();
    invoiceDateManager = rm.getInvoiceDateManager ();

    _initEventHandler ();
    init ();
  }

  /*
   * Initialize event handler
   */
  private void _initEventHandler ()
  {
    onlyNumbersSemicolonTwoDecimalDigits = event -> {
      final String text = ((TextField) event.getTarget ()).getText ();

      if ((!(event.getCharacter ().matches ("[0-9]")) && (!(event.getCharacter ().equals (",")))))
        event.consume ();

      final int indexOfSemicolon = text.indexOf (",");
      if (indexOfSemicolon != -1)
        if (text.substring (indexOfSemicolon + 1).length () == 2)
          event.consume ();

      System.out.println ("CursorPosition: " + ((TextField) event.getTarget ()).getCaretPosition ());
    };
  }

  /*
   * Initialize discount line
   */
  @Override
  protected void init ()
  {
    // super.init ();

    grid = new GridPane ();
    for (int i = 0; i < 4; i++)
    {
      final ColumnConstraints column = new ColumnConstraints ((Data.SURCHARGE_SCROLLPANE_WIDTH - 255) / 4);
      grid.getColumnConstraints ().add (column);
    }
    grid.setPadding (Data.LINE_PADDING);
    grid.setHgap (Data.LINE_HVGAP);
    grid.setVgap (grid.getHgap ());

    /*
     * Discount percent
     */
    final EFormElement percentElement = EFormElement.DISCOUNT_PERCENT;

    final VBox percentBox = new VBox ();

    final Label percentLabel = FormElementCreator.getStandardLabel (percentElement.getLabelText () +
                                                                    (percentElement.isRequired () ? "*" : ""),
                                                                    null);

    discountPercentField = FormElementCreator.getStandardTextField (percentElement.getID (),
                                                                    percentElement.isRequired ());
    discountPercentField.addEventHandler (KeyEvent.KEY_TYPED, onlyNumbersSemicolonTwoDecimalDigits);
    discountPercentField.focusedProperty ()
                        .addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
                          if (newValue)
                          {
                            helpArea.show (percentElement.getID ());
                          }
                          else
                          {
                            final List <DiscountLine> dll = discountArea.getDiscountLineList ();
                            final DiscountLine firstLine = dll.get (0);
                            String text = discountPercentField.getText ();

                            if (text != null && !text.isEmpty ())
                              if (!firstLine.toString ().equals (DiscountLine.this.toString ()))
                              {
                                final Double firstLineValue = firstLine.getDiscountPercent ();
                                System.out.println ("Wert der ersten Zeile: " + firstLineValue);
                                System.out.println ("Aktuell eingetragener Wert dieser Zeile: " +
                                                    TextFieldHelper.getDoubleFromString (text));

                                if (firstLineValue != null)
                                  if (TextFieldHelper.getDoubleFromString (text) >= firstLineValue)
                                    text = null;
                              }

                            if (text != null && !text.isEmpty ())
                              discountPercent = _checkPercentValues (text);
                            else
                              discountPercent = null;

                            if (discountPercent == null)
                              RequiredAndErrorHelper.addRequiredFieldForDiscountLine (DiscountLine.this.toString (),
                                                                                      discountPercentField.getId ());
                            else
                              RequiredAndErrorHelper.removeRequiredFieldForDiscountLine (DiscountLine.this.toString (),
                                                                                         discountPercentField.getId ());

                            if (discountPercent == null)
                              discountPercentField.setText ("");

                            discount.setPercentage (discountPercent);
                          }

                        });

    RequiredAndErrorHelper.addRequiredFieldForDiscountLine (this.toString (), discountPercentField.getId ());

    percentBox.getChildren ().addAll (percentLabel, discountPercentField);

    VBoxHelper.structureVBox (percentBox);
    grid.add (percentBox, 0, 0, 2, 1);

    /*
     * Discount if paid until date
     */
    final EFormElement ifPaidUntilDateElement = EFormElement.DISCOUNT_UNTIL_DATE;

    final VBox untilDateBox = new VBox ();

    final Label ifPaidUntilDateLabel = FormElementCreator.getStandardLabel (ifPaidUntilDateElement.getLabelText () +
                                                                            (ifPaidUntilDateElement.isRequired () ? "*"
                                                                                                                  : ""),
                                                                            null);
    ifPaidUntilDatePicker = FormElementCreator.getStandardDatePicker (ifPaidUntilDateElement.getID (),
                                                                      ifPaidUntilDateElement.isRequired ());

    ifPaidUntilDatePicker.focusedProperty ()
                         .addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
                           if (newValue)
                           {
                             helpArea.show (ifPaidUntilDateElement.getID ());
                           }
                         });

    ifPaidUntilDatePicker.valueProperty ().addListener ( (observable, oldValue, newValue) -> {

      ifPaidUntil = (ifPaidUntilDatePicker.getValue () == null ? null : ifPaidUntilDatePicker.getValue ().toString ());
      discount.setPaymentDate (ifPaidUntil);

      if (ifPaidUntil == null)
        RequiredAndErrorHelper.addRequiredFieldForDiscountLine (DiscountLine.this.toString (),
                                                                ifPaidUntilDatePicker.getId ());
      else
        RequiredAndErrorHelper.removeRequiredFieldForDiscountLine (DiscountLine.this.toString (),
                                                                   ifPaidUntilDatePicker.getId ());

      final String dueDateValue = rm.getSurchargeDiscountPane ().getDueDatePane ().getDueDate ();
      if (dueDateValue != null)
        if (LocalDate.parse (ifPaidUntil).isAfter (LocalDate.parse (dueDateValue)))
          rm.getSurchargeDiscountPane ().getDueDatePane ().setDueDatePickerToNull ();

      final int listSize = (discountArea.getDiscountLineList () == null ? 0
                                                                        : discountArea.getDiscountLineList ().size ());
      if (listSize == 2)
        _checkDateValues ();

      discountArea.setDueDatePickerToRequiredOrOptional ();
    });

    _addDayCellFactorysToDatePickers ();

    RequiredAndErrorHelper.addRequiredFieldForDiscountLine (this.toString (), ifPaidUntilDatePicker.getId ());

    untilDateBox.getChildren ().addAll (ifPaidUntilDateLabel, ifPaidUntilDatePicker);

    VBoxHelper.structureVBox (untilDateBox);
    grid.add (untilDateBox, 2, 0, 2, 1);

    /*
     * Delete this line button
     */
    final EFormElement deleteThisLineElement = EFormElement.DISCOUNT_REMOVE;

    deleteThisLine = new Button (deleteThisLineElement.getLabelText (),
                                 new ImageView (new Image ("at/mareg/ebi43creator/display/images/Loeschen_50x33.png")));
    deleteThisLine.hoverProperty ().addListener ( (observable) -> {
      helpArea.show (deleteThisLineElement.getID ());
    });
    deleteThisLine.setOnAction (e -> {
      discountArea.removeDiscountLine (this);

      RequiredAndErrorHelper.removeLineFromDiscountLineRequiredMap (this.toString ());

      rm.getSurchargeDiscountPane ().setAddDiscountButtonToEnable ();

      ifPaidUntilDatePicker.setDayCellFactory (invoiceDateManager.getDayCellFectoryDisableBefore (LocalDate.parse (rm.getInvoiceDateManager ()
                                                                                                                     .getCurrentDateAsString ())
                                                                                                           .plusDays (1l)));
    });

    grid.add (deleteThisLine, 4, 0);

    /*
     * Add grid to this
     */
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
  }

  /*
   * Add a day cell factory to newly created line
   */
  private void _addDayCellFactorysToDatePickers ()
  {
    final int discountListSize = discountArea.getDiscountLineList ().size ();

    if (discountListSize == 0)
    {
      ifPaidUntilDatePicker.setDayCellFactory (invoiceDateManager.getDayCellFectoryDisableBefore (LocalDate.parse (rm.getInvoiceDateManager ()
                                                                                                                     .getCurrentDateAsString ())
                                                                                                           .plusDays (1l)));
    }
    else
      if (discountListSize == 1)
      {
        final DatePicker firstDatePicker = discountArea.getDiscountLineList ().get (0).getifPaidUntilDatePicker ();

        if (firstDatePicker.getValue () == null)
        {
          ifPaidUntilDatePicker.setDayCellFactory (invoiceDateManager.getDayCellFectoryDisableBefore (LocalDate.parse (rm.getInvoiceDateManager ()
                                                                                                                         .getCurrentDateAsString ())
                                                                                                               .plusDays (1l)));
        }
        else
        {
          ifPaidUntilDatePicker.setDayCellFactory (invoiceDateManager.getDayCellFectoryDisableBefore (firstDatePicker.getValue ()
                                                                                                                     .plusDays (1l)));
        }
      }
  }

  /*
   * Check percent values
   */
  private Double _checkPercentValues (final String text)
  {
    Double value = TextFieldHelper.getDoubleFromString (text);

    if (value <= 0 || value >= 100)
    {
      value = null;
    }

    if (value != null)
    {
      final List <DiscountLine> dll = discountArea.getDiscountLineList ();
      if (dll.size () == 2)
      {
        if (dll.get (0).toString ().equals (this.toString ()))
        {
          final DiscountLine secondLine = dll.get (1);
          final Double secondLineValue = secondLine.getDiscountPercent ();

          if (secondLineValue != null)
          {
            if (secondLine.getDiscountPercent () > value)
            {
              secondLine.setPercentValueToNull ();
            }
          }
        }
      }
    }

    return value;
  }

  /*
   * Check discount lines, if two discount lines are set check dates of the
   * first and second line, if first date is after second date, set second date
   * to null
   */
  private void _checkDateValues ()
  {
    if (discountArea.getDiscountLine (0).toString ().equals (DiscountLine.this.toString ()))
    {
      final DiscountLine secondLine = discountArea.getDiscountLine (1);
      final String secondLineIfPaidUntil = secondLine.getIfPaidUntil ();

      if (secondLineIfPaidUntil != null)
      {
        if (LocalDate.parse (ifPaidUntil).isAfter (LocalDate.parse (secondLineIfPaidUntil)))
        {
          secondLine.setIfPaidUntilToNull ();

          FormElementCreator.setVisibleDatePickerStatus (secondLine.getifPaidUntilDatePicker (),
                                                         EFormElement.DISCOUNT_UNTIL_DATE);
        }
      }
    }
  }

  /*
   * To string method
   */
  @Override
  public String toString ()
  {
    return "DiscountLine@" + Integer.toHexString (this.hashCode ());
  }

  /*
   * Getter / Setter
   */
  public Discount getDiscount ()
  {
    return discount;
  }

  public DatePicker getifPaidUntilDatePicker ()
  {
    return ifPaidUntilDatePicker;
  }

  public Double getDiscountPercent ()
  {
    return discountPercent;
  }

  public String getIfPaidUntil ()
  {
    return ifPaidUntil;
  }

  public void setIfPaidUntilToNull ()
  {
    ifPaidUntil = null;
    discount.setPaymentDate (ifPaidUntil);

    ifPaidUntilDatePicker.setValue (null);
    ifPaidUntilDatePicker.setDayCellFactory (invoiceDateManager.getDayCellFectoryDisableBefore (LocalDate.parse (discountArea.getDiscountLine (0)
                                                                                                                             .getIfPaidUntil ())
                                                                                                         .plusDays (1l)));
    FormElementCreator.setVisibleDatePickerStatus (ifPaidUntilDatePicker, EFormElement.DISCOUNT_UNTIL_DATE);
  }

  public void setPercentValueToNull ()
  {
    discountPercent = null;
    discount.setPercentage (discountPercent);

    discountPercentField.setText ("");
    FormElementCreator.setVisibleTextFieldStatus (discountPercentField, EFormElement.DISCOUNT_PERCENT);
  }
}
