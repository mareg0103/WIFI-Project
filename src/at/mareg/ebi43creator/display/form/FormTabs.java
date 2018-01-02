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
  private final OrderPane orderPane;
  private final ContactPane billerPane;

  private final HelpArea helpArea;

  private TabPane tabs;

  public FormTabs (final OrderPane op, final ContactPane bp, final HelpArea area)
  {

    orderPane = op;
    billerPane = bp;

    helpArea = area;

    init ();

  }

  private void init ()
  {

    this.setRight (helpArea);

    // UI tabs
    tabs = new TabPane ();

    // Order data
    final Tab tabOrder = new Tab (Data.TAB_ORDER_DATA);
    tabOrder.setContent (orderPane);
    tabOrder.setId ("ordertab");
    tabOrder.disableProperty ().set (false);

    // Contact data
    final Tab tabContact = new Tab (Data.TAB_CONTACT_DATA);
    tabContact.setContent (billerPane);
    tabContact.setId ("billertab");

    tabs.getTabs ().addAll (tabOrder, tabContact);

    this.setCenter (tabs);

    scene = new Scene (this);
  }

  public Scene getFormScene ()
  {
    return scene;
  }

  public List <Tab> getFormTabs ()
  {
    return tabs.getTabs ();
  }

  public String getTabName (final Tab tab)
  {
    return tab.getText ();
  }

  public void setTabActiveStatus (final String tabName, final boolean status)
  {
    for (final Tab t : tabs.getTabs ())
      if (t.getText ().equalsIgnoreCase (tabName))
      {
        t.disableProperty ().set (!status);
        tabs.getSelectionModel ().select (t);
      }

  }
}
