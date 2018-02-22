package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
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
	private DatePicker dueDatePicker;

	public DueDatePane (ResourceManager rm)
	{
		super (rm);

		init ();
	}

	@Override
	protected void init ()
	{
		super.init ();

		VBox dueDateBox = new VBox ();

		EFormElement dueDateElement = EFormElement.DUE_DATE;

		Label dueDateLabel = FormElementCreator
				.getStandardLabel (dueDateElement.getLabelText () + (dueDateElement.isRequired () ? "*" : ""), null);
		dueDatePicker = FormElementCreator.getStandardDatePicker (dueDateElement.getID (),
				dueDateElement.isRequired ());

		dueDateBox.getChildren ().addAll (dueDateLabel, dueDatePicker);

		VBoxHelper.structureVBox (dueDateBox);
		this.add (dueDateBox, 0, 0);
	}
}
