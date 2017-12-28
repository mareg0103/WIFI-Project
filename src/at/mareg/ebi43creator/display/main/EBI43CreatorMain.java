package at.mareg.ebi43creator.display.main;

import java.nio.file.Paths;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.HelpArea;
import at.mareg.ebi43creator.display.utilities.MethodMapper;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EBI43CreatorMain extends Application
{

	private ResourceManager rm;
	private HelpArea helpArea;

	@Override
	public void start (Stage primaryStage)
	{

		// Set icon for application
		Image applicationIcon = new Image (Paths.get ("res\\m.jpg").toUri ().toString ());

		// Start resource manager
		rm = new ResourceManager (this);

		// Get help area instance
		helpArea = rm.getHelpArea ();

		// Set scene and primary stage settings
		primaryStage.setScene (rm.getForm ().getFormScene ());
		primaryStage.setTitle (Data.APPLICATION_NAME);
		primaryStage.getIcons ().add (applicationIcon);

		// Add focus listener to show help texts and save
		// values of text fields whether focus is lost
		primaryStage.getScene ().focusOwnerProperty ().addListener ( (observable, oldValue, newValue) -> {

			if (oldValue != null && oldValue.getId () != null)
			{

				if (oldValue.getClass () == TextField.class && !((TextField) oldValue).getText ().isEmpty ())
				{

					MethodMapper.callMethodFor (oldValue.getId (), ((TextField) oldValue).getText ());

				}

			}

			if (newValue != null && (newValue.getClass () == TextField.class))
			{

				if (newValue.isFocused ())
					helpArea.show (newValue.getId ());

			}

		});

		// Show apllication
		primaryStage.show ();
	}

	public static void main (String[] args)
	{
		launch (args);
	}

}
