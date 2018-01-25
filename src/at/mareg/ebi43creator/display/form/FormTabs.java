package at.mareg.ebi43creator.display.form;

import java.util.List;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class FormTabs extends BorderPane
{
  private ResourceManager rm;

  // Top content
  private DocumentTypePane documentTypePane;

  // Center content
  private Scene scene;
  private TabPane tabs;
  private final OrderPane orderPane;
  private final ContactPane billerPane;
  private final PaymentPane paymentPane;

  // Right area content
  private final HelpArea helpArea;

  public FormTabs (final OrderPane op,
                   final ContactPane bp,
                   final PaymentPane pp,
                   final HelpArea area,
                   final ResourceManager resman)
  {
    orderPane = op;
    billerPane = bp;
    paymentPane = pp;

    helpArea = area;

    rm = resman;

    init ();
  }

  private void init ()
  {
    // Top content
    documentTypePane = new DocumentTypePane (rm);
    this.setTop (documentTypePane);

    // Right area content
    this.setRight (helpArea);

    // Center content
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

    // Payment data
    final Tab tabPayment = new Tab (Data.TAB_PAYMENT_DATA);
    tabPayment.setContent (paymentPane);
    tabPayment.setId ("paymenttab");

    // Details data
    final Tab tabDetails = new Tab (Data.TAB_DETAILS_DATA);
    tabDetails.setContent (null);
    tabDetails.setId ("detailstab");

    // Surcharge/Reduction/Discount data
    final Tab tabDiscount = new Tab (Data.TAB_DISCOUNT_DATA);
    tabDiscount.setContent (null);
    tabDiscount.setId ("discounttab");

    // Summary
    final Tab tabSummary = new Tab (Data.TAB_SUMMARY_DATA);
    tabSummary.setContent (null);
    tabSummary.setId ("summarytab");

    // Add all tabs to tab pane
    tabs.getTabs ().addAll (tabOrder, tabContact, tabPayment, tabDetails, tabDiscount, tabSummary);

    // Add tabs to center content area
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
