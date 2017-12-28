package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.VBoxStructurer;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public abstract class BasePane extends GridPane
{

	protected ResourceManager rm;
	protected int col, row;

	protected BasePane (final ResourceManager rm)
	{

		this.rm = rm;

	}

	protected void init ()
	{

		this.setPadding (Data.BASEPANE_PADDING);
		this.setHgap (Data.BASEPANE_HVGAP);
		this.setVgap (this.getHgap ());

		col = 0;
		row = 0;

	}

	protected void incrementCol ()
	{
		col++;

		if (col == Data.COLUMN_COUNT_PER_ROW)
		{
			col = 0;
			row++;
		}

	}

	protected void setDefaultVBoxStructure ()
	{

		for (final Node n : this.getChildren ())
		{

			if (n.getClass () == VBox.class)
				VBoxStructurer.structureVBox ((VBox) n);

		}

	}
}
