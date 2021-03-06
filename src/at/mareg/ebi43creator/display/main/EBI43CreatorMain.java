package at.mareg.ebi43creator.display.main;

import java.util.Optional;

import at.mareg.ebi43creator.display.form.dialogs.StartDialog;
import at.mareg.ebi43creator.display.form.help.HelpArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.SaveMethodMapper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main display class Shows the form to create an ebInterface 4.3 invoice for
 * government and other entities Supported entities:
 * <ul>
 * <li>Government</li>
 * <li>Austrian Public Procurement Agency (Bundesbeschaffung GmbH)</li>
 * <li>Austrian Federal Computing Centre (Bundesrechenzentrum GmbH)</li>
 * <li>State of Carinthia</li>
 * <li>State of Upper Austria</li>
 * <li>Oesterreichische Nationalbank (OeNB)</li>
 * <li>Oesterreichische Bundesbahnen (ÖBB)</li>
 * </ul>
 *
 * @author Martin Regitnig
 */

public class EBI43CreatorMain extends Application
{
  private String loadPath;
  private String documentType;
  private ResourceManager rm;
  private HelpArea helpArea;

  @Override
  public void start (final Stage primaryStage)
  {
    /*
     * Set icon for application
     */
    final Image applicationIcon = new Image ("at/mareg/ebi43creator/display/images/m.jpg");

    /*
     * Set default document type for new document
     */
    documentType = Data.DEFAULT_DOCUMENTTYPE;

    /*
     * Show start dialog
     */
    final Optional <ButtonType> result = (new StartDialog (this, primaryStage).showAndWait ());

    if (result.isPresent ())
      if (result.get () == ButtonType.OK)
        // Start resource manager
        rm = new ResourceManager (this, documentType, loadPath);
      else
        System.exit (0);

    /*
     * Get help area instance
     */
    helpArea = rm.getHelpArea ();

    /*
     * Set scene and primary stage settings
     */
    primaryStage.setScene (rm.getForm ().getFormScene ());
    // primaryStage.setHeight (640d);
    primaryStage.setTitle (Data.APPLICATION_NAME);
    primaryStage.getIcons ().add (applicationIcon);

    /*
     * Add focus listener to show help texts and save values of elements when
     * focus is lost
     */
    primaryStage.getScene ().focusOwnerProperty ().addListener ( (observable, oldValue, newValue) ->

    {

      if (oldValue != null)
      {
        if (oldValue.getClass () == TextField.class)
          _TextFieldOldValueWorker ((TextField) oldValue);

        if (oldValue.getClass () == TextArea.class)
          _TextAreaOldValueWorker ((TextArea) oldValue);

        if (oldValue.getClass () == DatePicker.class)
          _DatePickerOldValueWorker ((DatePicker) oldValue);
      }

      if (newValue != null)
      {
        _newValueWorker (newValue);
      }

    });

    /*
     * Show application
     */
    primaryStage.show ();
  }

  /*
   * START
   */
  public static void main (final String [] args)
  {
    launch (args);
  }

  /*
   * Internal methods
   */

  /*
   * TextField handler for oldValue (last focused node) from scene focus
   * listener
   */
  private void _TextFieldOldValueWorker (final TextField t)
  {
    final String elementID = t.getId ();
    final String elementContent = t.getText ();
    final boolean elementIsEmpty = elementContent.isEmpty ();

    final EFormElement field = EFormElement.getFromIDOrNull (elementID);
    final boolean fieldIsRequired = field == null ? false : field.isRequired ();
    String valueCheckMessage = Data.CHECKMESSAGE_SUCCESS;

    valueCheckMessage = SaveMethodMapper.callMethodFor (field, (elementIsEmpty ? null : elementContent));

    if (valueCheckMessage.equals (Data.CHECKMESSAGE_SUCCESS))
    {
      t.setStyle ("-fx-control-inner-background: #" +
                  (elementIsEmpty ? (fieldIsRequired ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK)
                                  : Data.BACKGROUND_HEX_OK));
      t.setTooltip (null);
    }
    else
    {
      t.setStyle ("-fx-control-inner-background: #" + Data.BACKGROUND_HEX_ERROR);
      t.setTooltip (new Tooltip (valueCheckMessage));
    }

    if (fieldIsRequired)
    {
      if (elementIsEmpty)
      {
        RequiredAndErrorHelper.addRequiredField (field.getTiteldPaneID (), elementID);
      }
      else
      {
        RequiredAndErrorHelper.removeRequiredField (field.getTiteldPaneID (), elementID);
      }
    }

    if (t.getTooltip () == null)
    {
      RequiredAndErrorHelper.removeErrorField (field.getTiteldPaneID (), elementID);
    }
    else
    {
      RequiredAndErrorHelper.addErrorField (field.getTiteldPaneID (), elementID);
    }
  }

  /*
   * TextArea handler for oldValue (last focused node) from scene focus listener
   */
  private void _TextAreaOldValueWorker (final TextArea t)
  {
    final String elementID = t.getId ();
    final String elementContent = t.getText ();
    final boolean elementIsEmpty = elementContent.isEmpty ();

    final EFormElement field = EFormElement.getFromIDOrNull (elementID);
    final boolean fieldIsRequired = field == null ? false : field.isRequired ();
    String valueCheckMessage = Data.CHECKMESSAGE_SUCCESS;

    valueCheckMessage = SaveMethodMapper.callMethodFor (field, (elementIsEmpty ? null : elementContent));

    if (valueCheckMessage.equals (Data.CHECKMESSAGE_SUCCESS))
    {
      t.setStyle ("-fx-control-inner-background: #" +
                  (elementIsEmpty ? (fieldIsRequired ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK)
                                  : Data.BACKGROUND_HEX_OK));
      t.setTooltip (null);
    }
    else
    {
      t.setStyle ("-fx-control-inner-background: #" + Data.BACKGROUND_HEX_ERROR);
      t.setTooltip (new Tooltip (valueCheckMessage));
    }

    if (fieldIsRequired)
    {
      if (elementIsEmpty)
      {
        RequiredAndErrorHelper.addRequiredField (field.getTiteldPaneID (), elementID);
      }
      else
      {
        RequiredAndErrorHelper.removeRequiredField (field.getTiteldPaneID (), elementID);
      }
    }

    if (t.getTooltip () == null)
    {
      RequiredAndErrorHelper.removeErrorField (field.getTiteldPaneID (), elementID);
    }
    else
    {
      RequiredAndErrorHelper.addErrorField (field.getTiteldPaneID (), elementID);
    }
  }

  /*
   * DatePicker handler for oldValue (last focused node) from scene focus
   * listener
   */
  private void _DatePickerOldValueWorker (final DatePicker dp)
  {
    final String elementID = dp.getId ();
    final String elementContent = dp.getValue () == null ? null : dp.getValue ().toString ();
    final boolean elementIsEmpty = elementContent == null ? true : false;

    final EFormElement field = EFormElement.getFromIDOrNull (elementID);
    final boolean fieldIsRequired = field == null ? false : field.isRequired ();
    String valueCheckMessage = Data.CHECKMESSAGE_SUCCESS;

    valueCheckMessage = SaveMethodMapper.callMethodFor (field, (elementIsEmpty ? null : elementContent));

    if (valueCheckMessage.equals (Data.CHECKMESSAGE_SUCCESS))
    {
      dp.setStyle ("-fx-control-inner-background: #" +
                   (elementIsEmpty ? (fieldIsRequired ? Data.BACKROUND_HEX_REQUIRED : Data.BACKGROUND_HEX_OK)
                                   : Data.BACKGROUND_HEX_OK));
      dp.setTooltip (null);
    }
    else
    {
      dp.setStyle ("-fx-control-inner-background: #" + Data.BACKGROUND_HEX_ERROR);
      dp.setTooltip (new Tooltip (valueCheckMessage));
    }

    if (elementID.equals (EFormElement.DUE_DATE.getID ()))
      System.out.println ("!!! DueDate ist erforderlich: " + fieldIsRequired);
    if (fieldIsRequired)
    {
      if (elementIsEmpty)
      {
        RequiredAndErrorHelper.addRequiredField (field.getTiteldPaneID (), elementID);
      }
      else
      {
        RequiredAndErrorHelper.removeRequiredField (field.getTiteldPaneID (), elementID);
      }
    }

    if (dp.getTooltip () == null)
    {
      RequiredAndErrorHelper.removeErrorField (field.getTiteldPaneID (), elementID);
    }
    else
    {
      RequiredAndErrorHelper.addErrorField (field.getTiteldPaneID (), elementID);
    }
  }

  /*
   * Handler for newValue (current focused node) from scene focus listener
   */
  private void _newValueWorker (final Node newValue)
  {
    final Class <?> c = newValue.getClass ();

    if (c == TextField.class || c == TextArea.class || c == DatePicker.class)
    {
      if (newValue.isFocused ())
        helpArea.show (newValue.getId ());
    }
  }

  /*
   * Getter / Setter
   */
  public void setLoadPath (final String lp)
  {
    loadPath = lp;
  }

  public void setDocumentType (final String dt)
  {
    documentType = dt;
  }
}
