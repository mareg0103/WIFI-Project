package at.mareg.ebi43creator.display.form;

import java.time.LocalDate;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.SaveMethodMapper;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DueDatePane extends BasePane
{
  /*
   * Pane elements
   */
  private Label dueDateLabel;
  private DatePicker dueDatePicker;

  public DueDatePane (final ResourceManager rm)
  {
    super (rm);

    init ();
  }

  @Override
  protected void init ()
  {
    super.init ();

    final VBox dueDateBox = new VBox ();

    final EFormElement dueDateElement = EFormElement.DUE_DATE;

    dueDateLabel = FormElementCreator.getStandardLabel (dueDateElement.getLabelText () +
                                                        (dueDateElement.isRequired () ? "*" : ""),
                                                        null);
    dueDatePicker = FormElementCreator.getStandardDatePicker (dueDateElement.getID (), dueDateElement.isRequired ());
    dueDatePicker.setDayCellFactory (rm.getInvoiceDateManager ()
                                       .getDayCellFectoryDisableBefore (LocalDate.parse (rm.getInvoiceDateManager ()
                                                                                           .getCurrentDateAsString ())));

    dueDateBox.getChildren ().addAll (dueDateLabel, dueDatePicker);

    VBoxHelper.structureVBox (dueDateBox);
    this.add (dueDateBox, 0, 0);
  }

  /*
   * Sets the due date field to required if a discount entry is set
   */
  public void setDueDatePickerAsRequired (final String discountDate)
  {
    final EFormElement dueDateElement = EFormElement.DUE_DATE;

    dueDateElement.setIsRequired (true);

    if (discountDate == null)
      dueDatePicker.setDayCellFactory (null);
    else
      dueDatePicker.setDayCellFactory (rm.getInvoiceDateManager ()
                                         .getDayCellFectoryDisableBefore (LocalDate.parse (discountDate)
                                                                                   .plusDays (1l)));

    RequiredAndErrorHelper.addRequiredField (Data.TAB_DISCOUNT_DATA, dueDatePicker.getId ());

    FormElementCreator.setVisibleLabelStatus (dueDateLabel, dueDateElement);
    FormElementCreator.setVisibleDatePickerStatus (dueDatePicker, dueDateElement);
  }

  /*
   * Sets the due date field to optional if no discount is set
   */
  public void setDueDatePickerToOptional ()
  {
    final EFormElement dueDateElement = EFormElement.DUE_DATE;

    dueDateElement.setIsRequired (false);

    dueDatePicker.setDayCellFactory (null);

    RequiredAndErrorHelper.removeRequiredField (Data.TAB_DISCOUNT_DATA, dueDatePicker.getId ());

    FormElementCreator.setVisibleLabelStatus (dueDateLabel, dueDateElement);
    FormElementCreator.setVisibleDatePickerStatus (dueDatePicker, dueDateElement);

    dueDatePicker.setDayCellFactory (rm.getInvoiceDateManager ()
                                       .getDayCellFectoryDisableBefore (LocalDate.parse (rm.getInvoiceDateManager ()
                                                                                           .getCurrentDateAsString ())));
  }

  /*
   * Getter / Setter
   */
  public String getDueDate ()
  {
    final LocalDate dueDateValue = dueDatePicker.getValue ();

    return dueDateValue == null ? null : dueDateValue.toString ();
  }

  public void setDueDatePickerToNull ()
  {
    dueDatePicker.setValue (null);
    SaveMethodMapper.callMethodFor (EFormElement.DUE_DATE, null);
  }
}
