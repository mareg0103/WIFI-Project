package at.mareg.ebi43creator.display.form;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import at.mareg.ebi43creator.display.form.dialogs.ShowErrorFieldsDialog;
import at.mareg.ebi43creator.display.form.dialogs.ShowRequiredFieldsDialog;
import at.mareg.ebi43creator.display.form.help.HelpArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class SummaryPane extends BorderPane
{
  /**
   * Pane to show a summary of the entered data and to save them to an XML file
   *
   * @author Martin Regitnig
   */

  /*
   * Instance of ResourceManager because this pane doesn't extend BasePane
   */
  private final ResourceManager rm;

  /*
   * Help area instance
   */
  private final HelpArea helpArea;

  /*
   * Pane elements
   */
  private SummaryArea summaryArea;
  private GridPane grid;
  private Button showRequiredFieldsButton;
  private Button showErroneousFieldsButton;
  private TextField invoiceNameField;
  private Button saveInvoiceAndCloseButton;

  /*
   * Pane variables
   */
  private final String savePath;
  private String fileName;
  private int rightAreaRow;

  public SummaryPane (final ResourceManager resman)
  {
    rm = resman;
    helpArea = rm.getHelpArea ();
    savePath = Data.DEFAULT_SAVE_DIRECTORY;

    init ();
  }

  /*
   * Initialize OrderPane
   */
  protected void init ()
  {
    rightAreaRow = 0;

    /*
     * Center content of BorderPane
     */
    summaryArea = new SummaryArea (rm);

    /*
     * Right Area of BorderPane
     */
    grid = new GridPane ();
    grid.setPadding (Data.BASEPANE_PADDING);
    grid.setHgap (Data.BASEPANE_HVGAP);
    grid.setVgap (grid.getHgap ());

    for (final EFormElement eb : EFormElement.values ())
    {
      if (eb.getTiteldPaneID ().equals (Data.TITLEDPANE_SUMMARY_NAME))
      {
        final boolean isRequired = eb.isRequired ();
        final String elementID = eb.getID ();
        final String labelText = eb.getLabelText ();

        if (eb.getType ().equals (Data.ELEMENTTYPE_BUTTON))
        {
          final Button b = FormElementCreator.getStandardButton (elementID, labelText);
          b.setPrefWidth (Data.SUMMARY_RIGHT_AREA_COMPONENT_WIDTH);

          if (elementID.equals (EFormElement.SUMMARY_SHOW_REQUIRED_FIELDSBUTTON.getID ()))
          {
            showRequiredFieldsButton = b;
            b.setOnAction (e -> {
              final ShowRequiredFieldsDialog srfd = new ShowRequiredFieldsDialog ();
              srfd.initModality (Modality.NONE);

              srfd.show ();
            });
          }

          if (elementID.equals (EFormElement.SUMMARY_SHOW_ERRONEOUS_FIELDSBUTTON.getID ()))
          {
            showErroneousFieldsButton = b;
            b.setOnAction (e -> {
              final ShowErrorFieldsDialog sefd = new ShowErrorFieldsDialog ();
              sefd.initModality (Modality.NONE);

              sefd.show ();
            });
          }

          if (elementID.equals (EFormElement.SUMMARY_SAVE_INVOICE_AND_CLOSEBUTTON.getID ()))
          {
            saveInvoiceAndCloseButton = b;
            b.setOnAction (e -> {
              final String value = invoiceNameField.getText ();

              if (value != null && !value.isEmpty ())
              {
                fileName = value;

                if (!fileName.endsWith (".xml"))
                  fileName += ".xml";
              }
              else
              {
                fileName = LocalDateTime.now ().format (DateTimeFormatter.ofPattern ("yyyy-MM-dd_HHmmss")) + ".xml";
              }

              if (Files.notExists (Paths.get (savePath)))
                new File (savePath).mkdirs ();

              rm.getInvoiceData ().getTax ().calculateVATItems ();

              rm.getInvoiceData ().serializeInvoiceAsXML (savePath + fileName);

              System.exit (0);
            });
          }

          grid.add (b, 0, rightAreaRow);

          rightAreaRow++;
        }

        if (eb.getType () == Data.ELEMENTTYPE_TEXTFIELD)
        {
          final VBox v = new VBox ();
          Label l = null;
          TextField t = null;

          if (elementID.equals (EFormElement.SUMMARY_SAVENAME_TEXTFIELD.getID ()))
          {
            l = FormElementCreator.getStandardLabel (labelText + (isRequired ? "*" : ""), new Insets (20, 0, 0, 0));

            t = FormElementCreator.getStandardTextField (elementID, isRequired);
            invoiceNameField = t;

            invoiceNameField.focusedProperty ()
                            .addListener ((ChangeListener <Boolean>) (observable, oldValue, newValue) -> {
                              if (newValue)
                              {
                                helpArea.show (invoiceNameField.getId ());
                              }
                            });
            invoiceNameField.setPrefWidth (Data.SUMMARY_RIGHT_AREA_COMPONENT_WIDTH);
          }

          v.getChildren ().addAll (l, t);
          grid.add (v, 0, rightAreaRow);

          rightAreaRow++;
        }

        if (isRequired)
          RequiredAndErrorHelper.addRequiredField (eb.getTiteldPaneID (), elementID);

        this.setCenter (summaryArea);
        this.setRight (grid);
      }
    }
  }

  /*
   * Getter / Setter
   */
  public SummaryArea getSummaryArea ()
  {
    return summaryArea;
  }

  /*
   * Set show button disable status for missing required fields
   */
  private void _setButtonShowMissingRequiredFieldStatus ()
  {
    if (RequiredAndErrorHelper.allRequiredFieldsAreFilled ())
      showRequiredFieldsButton.disableProperty ().set (true);
    else
      showRequiredFieldsButton.disableProperty ().set (false);
  }

  /*
   * Set show button disable status for error fields
   */
  private void _setButtonShowErroneousFieldStatus ()
  {
    if (RequiredAndErrorHelper.getErrorMapSize () > 0)
      showErroneousFieldsButton.disableProperty ().set (false);
    else
      showErroneousFieldsButton.disableProperty ().set (true);
  }

  /*
   * Set button disable status for saving the document
   */
  public void setButtonSaveFileStatus ()
  {
    if (RequiredAndErrorHelper.allFieldsAreFilledAndCorrect ())
    {
      saveInvoiceAndCloseButton.disableProperty ().set (false);
      showRequiredFieldsButton.disableProperty ().set (true);
      showErroneousFieldsButton.disableProperty ().set (true);
    }
    else
    {
      saveInvoiceAndCloseButton.disableProperty ().set (true);
      _setButtonShowMissingRequiredFieldStatus ();
      _setButtonShowErroneousFieldStatus ();
    }
  }
}
