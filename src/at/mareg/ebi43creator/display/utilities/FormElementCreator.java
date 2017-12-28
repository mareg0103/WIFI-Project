package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.display.resources.Data;
import javafx.scene.control.TextField;

public final class FormElementCreator
{

	private FormElementCreator ()
	{
	}

	public static TextField getStandardTextField (final String sID)
	{
		TextField t = new TextField ();
		t.setPrefWidth (Data.DEFAULT_COMPONENT_WIDTH);
		t.setStyle ("-fx-control-inner-background: #FFFFE0");
		t.setId (sID);

		return t;
	}

}
