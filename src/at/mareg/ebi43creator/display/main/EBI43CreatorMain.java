package at.mareg.ebi43creator.display.main;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.HelpArea;
import at.mareg.ebi43creator.display.utilities.RequiredAndErrorHelper;
import at.mareg.ebi43creator.display.utilities.SaveMethodMapper;
import at.mareg.ebi43creator.invoicedata.enums.EFormFields;
import javafx.application.Application;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * Main display class Shows the form to create an ebInterface 4.3 invoice for
 * government and other entities
 * 
 * Supported entities:
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
 * 
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
		rm = new ResourceManager (this);

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

				if (oldValue.getClass () == TextField.class)
				{
					final TextField t = (TextField) oldValue;
					final EFormFields e = EFormFields.getFromIDOrNull (t.getId ());
					final boolean tIsEmpty = t.getText ().isEmpty ();

					SaveMethodMapper.callMethodFor (e, (tIsEmpty ? null : t.getText ()));
					t.setStyle ("-fx-control-inner-background: #"
							+ (tIsEmpty ? (e.isRequired () ? "FFFFE0" : "FFFFFF") : "FFFFFF"));

				}

				if (oldValue.getClass () == TextArea.class)
				{
					final TextArea t = (TextArea) oldValue;
					final EFormFields e = EFormFields.getFromIDOrNull (t.getId ());
					final boolean tIsEmpty = t.getText ().isEmpty ();

					SaveMethodMapper.callMethodFor (e, (tIsEmpty ? null : t.getText ()));
					t.setStyle ("-fx-control-inner-background: #"
							+ (tIsEmpty ? (e.isRequired () ? "FFFFE0" : "FFFFFF") : "FFFFFF"));

				}

				if (oldValue.getClass () == DatePicker.class)
				{
					final DatePicker dp = (DatePicker) oldValue;
					final EFormFields e = EFormFields.getFromIDOrNull (dp.getId ());
					final boolean tIsEmpty = (dp.getValue () == null || dp.getValue ().toString ().isEmpty ()) ? true
							: false;

					SaveMethodMapper.callMethodFor (e, (tIsEmpty ? null : dp.getValue ().toString ()));
					dp.setStyle ("-fx-control-inner-background: #"
							+ (tIsEmpty ? (e.isRequired () ? "FFFFE0" : "FFFFFF") : "FFFFFF"));

				}

			}

			Class<?> c = newValue.getClass ();

			if (newValue != null && (c == TextField.class || c == TextArea.class || c == DatePicker.class))
			{

				if (newValue.isFocused ())
					helpArea.show (newValue.getId ());

			}

		});

		// Show application
		primaryStage.show ();

		// Temporary show required list
		// delete before deployment
		System.out.println ("Call from Mainclass.init ()");
		RequiredAndErrorHelper.showRequiredMap ();

	}

	public static void main (final String[] args)
	{
		launch (args);
	}

}