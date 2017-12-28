package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.VBoxHelper;
import at.mareg.ebi43creator.invoicedata.enums.EBiller;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BillerPane extends BasePane
{

	public BillerPane (final ResourceManager rm)
	{
		super (rm);

		init ();

	}

	protected void init ()
	{
		super.init ();

		Accordion ac = new Accordion ();
		TitledPane tp = new TitledPane ();

		GridPane grid = new GridPane ();
		grid.setPadding (Data.BASEPANE_PADDING);
		grid.setHgap (Data.BASEPANE_HVGAP);
		grid.setVgap (this.getHgap ());

		for (EBiller eb : EBiller.values ())
		{

			VBox v = new VBox ();

			Label l = new Label (eb.getLabelText ());
			v.getChildren ().add (l);

			TextField t = FormElementCreator.getStandardTextField (eb.getID ());
			v.getChildren ().add (t);

			grid.add (v, col, row);
			VBoxHelper.structureVBox (v);

			incrementCol ();

		}

		tp.setContent (grid);
		tp.setCollapsible (false);

		ac.getPanes ().add (tp);
		ac.setExpandedPane (tp);

		this.add (ac, 0, 0);

		Button cont = new Button ("Weiter");
		this.add (cont, 0, 1);

	}

}
