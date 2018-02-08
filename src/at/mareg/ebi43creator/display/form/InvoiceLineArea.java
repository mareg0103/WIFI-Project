package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class InvoiceLineArea extends BasePane
{
  /**
   * Class to add lines to the current document
   * 
   * @author Martin Regitnig
   */

  /*
   * Pane elements
   */
  private ScrollPane scroll;
  private GridPane grid;

  protected InvoiceLineArea (final ResourceManager resman)
  {
    super (resman);

    init ();
  }

  /*
   * Initialize pane
   */
  @Override
  protected void init ()
  {
    super.init ();

    scroll = new ScrollPane ();
    scroll.setPrefHeight (Data.DETAILS_SCROLLPANE_HEIGHT);
    scroll.setMaxHeight (scroll.getPrefHeight ());
    scroll.setPrefWidth (Data.DETAILS_SCROLLPANE_WIDTH);
    scroll.setMaxWidth (scroll.getPrefWidth ());

    grid = new GridPane ();

    scroll.setContent (grid);
    this.getChildren ().add (scroll);
  }
}
