package at.mareg.ebi43creator.display.main;

import at.mareg.ebi43creator.display.form.HelpArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.SaveMethodMapper;
import at.mareg.ebi43creator.invoicedata.enums.EFormFields;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

  private ResourceManager rm;
  private HelpArea helpArea;

  @Override
  public void start (final Stage primaryStage)
  {
    // Set icon for application
    final Image applicationIcon = new Image ("at/mareg/ebi43creator/display/images/m.jpg");

    // Start resource manager
    // docType is manually set to "Invoice" for the moment, replace with return
    // value of start dialogue when implemented
    rm = new ResourceManager (this, "CreditMemo");

    // Get help area instance
    helpArea = rm.getHelpArea ();

    // Set scene and primary stage settings
    primaryStage.setScene (rm.getForm ().getFormScene ());
    primaryStage.setTitle (Data.APPLICATION_NAME);
    primaryStage.getIcons ().add (applicationIcon);

    // Add focus listener to show help texts and save
    // values of elements when focus is lost
    primaryStage.getScene ().focusOwnerProperty ().addListener ( (observable, oldValue, newValue) -> {

      if (oldValue != null)
      {
        _oldValueWorker (oldValue);
      }

      if (newValue != null)
      {
        _newValueWorker (newValue);
      }

    });

    // Show application
    primaryStage.show ();

    // Temporary show required list
    // delete before deployment
    System.out.println ("Call from Mainclass.init ()");
    RequiredAndErrorHelper.showRequiredMap ();
  }

  // Handles oldValue (last focused node) from scene focus listener
  private void _oldValueWorker (final Node oldValue)
  {
    final String elementID = oldValue.getId ();
    final EFormFields field = EFormFields.getFromIDOrNull (elementID);
    String elementContent = "";
    boolean elementIsEmpty = true;
    String valueCheckMessage = "ok";

    if (oldValue.getClass () == TextField.class)
    {
      final TextField t = (TextField) oldValue;
      elementContent = t.getText ();
      elementIsEmpty = elementContent.isEmpty ();

      t.setStyle ("-fx-control-inner-background: #" +
                  (elementIsEmpty ? (field.isRequired () ? "FFFFE0" : "FFFFFF") : "FFFFFF"));
    }

    if (oldValue.getClass () == TextArea.class)
    {
      final TextArea t = (TextArea) oldValue;
      elementContent = t.getText ();
      elementIsEmpty = elementContent.isEmpty ();

      t.setStyle ("-fx-control-inner-background: #" +
                  (elementIsEmpty ? (field.isRequired () ? "FFFFE0" : "FFFFFF") : "FFFFFF"));
    }

    if (oldValue.getClass () == DatePicker.class)
    {
      final DatePicker dp = (DatePicker) oldValue;
      elementContent = (dp.getValue () == null ? null : dp.getValue ().toString ());
      elementIsEmpty = (elementContent == null) ? true : false;

      dp.setStyle ("-fx-control-inner-background: #" +
                   (elementIsEmpty ? (field.isRequired () ? "FFFFE0" : "FFFFFF") : "FFFFFF"));

    }

    if (field != null)
      valueCheckMessage = SaveMethodMapper.callMethodFor (field, (elementIsEmpty ? null : elementContent));

    System.out.println ("In Mainclass._oldValueWorker:\n   CheckMessage = " + valueCheckMessage);
  }

  // Handles newValue (current focused node) from scene focus listener
  private void _newValueWorker (final Node newValue)
  {
    Class <?> c = newValue.getClass ();

    if (c == TextField.class || c == TextArea.class || c == DatePicker.class)
    {
      if (newValue.isFocused ())
        helpArea.show (newValue.getId ());
    }
  }

  // START
  public static void main (final String [] args)
  {
    launch (args);
  }

}
