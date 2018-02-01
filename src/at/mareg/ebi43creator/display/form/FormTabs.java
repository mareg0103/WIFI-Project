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
  /**
   * Main class for form
   * 
   * @author Martin Regitnig
   */

  /*
   * Instance of ResourceManager because this pane doesn't extend BasePane
   */
  private ResourceManager rm;

  /*
   * Pane elements
   */
  private DocumentTypePane documentTypePane;

  private Scene scene;
  private TabPane tabs;
  private final OrderPane orderPane;
  private final ContactPane billerPane;
  private final PaymentPane paymentPane;
  private final DetailsPane detailsPane;
  private final SurchargeDiscountPane surchargeDiscountPane;
  private final SummaryPane summaryPane;

  private final HelpArea helpArea;

  public FormTabs (final OrderPane op,
                   final ContactPane bp,
                   final PaymentPane pp,
                   final DetailsPane dp,
                   final SurchargeDiscountPane sdp,
                   final SummaryPane sp,
                   final HelpArea area,
                   final ResourceManager resman)
  {
    orderPane = op;
    billerPane = bp;
    paymentPane = pp;
    detailsPane = dp;
    surchargeDiscountPane = sdp;
    summaryPane = sp;

    helpArea = area;

    rm = resman;

    init ();
  }

  /*
   * Create form
   */
  private void init ()
  {
    /*
     * Top content of BorderPane
     */
    documentTypePane = new DocumentTypePane (rm);
    this.setTop (documentTypePane);

    /*
     * Right content of BorderPane
     */
    this.setRight (helpArea);

    /*
     * Center content of BorderPane
     */
    tabs = new TabPane ();

    /*
     * Integrate OrderPane
     */
    final Tab tabOrder = new Tab (Data.TAB_ORDER_DATA);
    tabOrder.setContent (orderPane);
    tabOrder.setId ("ordertab");
    tabOrder.disableProperty ().set (false);

    /*
     * Integrate ContactPane
     */
    final Tab tabContact = new Tab (Data.TAB_CONTACT_DATA);
    tabContact.setContent (billerPane);
    tabContact.setId ("billertab");

    /*
     * Integrate PaymentPane
     */
    final Tab tabPayment = new Tab (Data.TAB_PAYMENT_DATA);
    tabPayment.setContent (paymentPane);
    tabPayment.setId ("paymenttab");

    /*
     * Integrate DetailsPane
     */
    final Tab tabDetails = new Tab (Data.TAB_DETAILS_DATA);
    tabDetails.setContent (detailsPane);
    tabDetails.setId ("detailstab");

    /*
     * Integrate SurchargeDiscountPane
     */
    final Tab tabDiscount = new Tab (Data.TAB_DISCOUNT_DATA);
    tabDiscount.setContent (surchargeDiscountPane);
    tabDiscount.setId ("discounttab");

    /*
     * Integrate SummaryPane
     */
    final Tab tabSummary = new Tab (Data.TAB_SUMMARY_DATA);
    tabSummary.setContent (summaryPane);
    tabSummary.setId ("summarytab");

    tabs.getTabs ().addAll (tabOrder, tabContact, tabPayment, tabDetails, tabDiscount, tabSummary);
    this.setCenter (tabs);

    /*
     * Create scene
     */
    scene = new Scene (this, Data.SCENE_WIDTH, Data.SCENE_HEIGHT);
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

}
