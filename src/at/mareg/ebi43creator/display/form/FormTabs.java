package at.mareg.ebi43creator.display.form;

import java.util.List;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.utilities.HelpArea;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class FormTabs extends BorderPane
{

	private Scene scene;
	private BillerPane billerPane;

	private HelpArea helpArea;

	private TabPane tabs;

	public FormTabs (final BillerPane bp, final HelpArea area)
	{
		billerPane = bp;

		helpArea = area;

		init ();
	}

	private void init ()
	{

		this.setRight (helpArea);

		// UI tabs
		tabs = new TabPane ();

		Tab biller = new Tab (Data.TAB_BILLER_NAME);
		biller.setContent (billerPane);
		biller.setId ("billertab");

		tabs.getTabs ().addAll (biller);

		this.setCenter (tabs);

		scene = new Scene (this);
	}

	public Scene getFormScene ()
	{
		return scene;
	}

	public List<Tab> getFormTabs ()
	{
		return tabs.getTabs ();
	}

	public String getTabName (final Tab tab)
	{
		return tab.getText ();
	}

	public void setTabActiveStatus (final String tabName, final boolean status)
	{
		for (Tab t : tabs.getTabs ())
			if (t.getText ().equalsIgnoreCase (tabName))
			{
				t.disableProperty ().set (!status);
				tabs.getSelectionModel ().select (t);
			}

	}
}
