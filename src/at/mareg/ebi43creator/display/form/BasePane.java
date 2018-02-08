package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import javafx.scene.layout.GridPane;

public abstract class BasePane extends GridPane
{
  /**
   * Basic pane, sets the very basic settings for all Panes, which extends this.
   * The following classes extends BasePane:
   * <ul>
   * <li>DocumentTypePane</li>
   * <li>OrderPane</li>
   * <li>ContactPane</li>
   * <li>InvoiceLineArea</li>
   * <li>PaymentPane</li>
   * <li>SummaryPane</li>
   * </ul>
   * 
   * @author Martin Regitnig
   */

  /*
   * Pane elements
   */
  protected ResourceManager rm;
  protected int col, row;

  protected BasePane (final ResourceManager rm)
  {

    this.rm = rm;

  }

  /*
   * Initialize basic settings
   */
  protected void init ()
  {

    this.setPadding (Data.BASEPANE_PADDING);
    this.setHgap (Data.BASEPANE_HVGAP);
    this.setVgap (this.getHgap ());

    col = 0;
    row = 0;

  }

  /*
   * Calculate new column and row values for mostly automated creation of the
   * form
   */
  protected void incrementCol ()
  {
    col++;

    if (col == Data.COLUMN_COUNT_PER_ROW)
    {
      col = 0;
      row++;
    }

  }
}
