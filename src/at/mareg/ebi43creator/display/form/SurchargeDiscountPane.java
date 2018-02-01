package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.ResourceManager;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SurchargeDiscountPane extends BorderPane
{
  /**
   * Class to show help texts to form elements
   * 
   * @author Martin Regitnig
   */

  /*
   * Instance of ResourceManager because this pane doesn't extend BasePane
   */
  final ResourceManager rm;

  /*
   * Pane elements
   */
  private ScrollPane scroll;

  private Accordion ac;
  private GridPane grid_Surcharge;
  private GridPane grid_Discount;
  private TitledPane tp_Surcharge;
  private TitledPane tp_Discount;

  public SurchargeDiscountPane (final ResourceManager resman)
  {
    rm = resman;

    init ();
  }

  /*
   * Initialize SurchargeDiscountPane
   */
  protected void init ()
  {

  }
}
