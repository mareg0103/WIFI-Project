package at.mareg.ebi43creator.display.form.surcharge;

import java.util.ArrayList;
import java.util.List;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.SurchargeLineFiller;
import at.mareg.ebi43creator.invoicedata.reductionandsurcharge.Surcharge;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class SurchargeArea extends BasePane
{
  /**
   * Surcharge line area to dynamically add or remove a surcharget line to the
   * document (negative surcharge is an reduction)
   *
   * @author Martin Regitnig
   */

  /*
   * Surcharge lines
   */
  private List <SurchargeLine> surchargeLineList;

  /*
   * Pane elements
   */
  private ScrollPane scroll;
  private GridPane grid;
  private int areaRow;

  public SurchargeArea (final ResourceManager resman)
  {
    super (resman);

    init ();
  }

  /*
   * Initialize surcharge area
   */
  @Override
  protected void init ()
  {
    super.init ();

    scroll = new ScrollPane ();
    scroll.setPrefHeight (Data.SURCHARGE_SCROLLPANE_HEIGHT);
    scroll.setMaxHeight (scroll.getPrefHeight ());
    scroll.setPrefWidth (Data.SURCHARGE_SCROLLPANE_WIDTH);
    scroll.setMaxWidth (scroll.getPrefWidth ());

    grid = new GridPane ();

    scroll.setContent (grid);
    this.getChildren ().add (scroll);
  }

  /*
   * (Re) Build surcharge line area
   */
  private void _buildArea ()
  {
    areaRow = 0;

    for (final SurchargeLine sl : surchargeLineList)
    {
      grid.add (sl, 0, areaRow);
      areaRow++;
    }

    rm.getSurchargeDiscountPane ().refreshTotalNetandTotalGross ();
  }

  /*
   * Remove all nodes an rebuild grid
   */
  private void _refreshArea ()
  {
    final ObservableList <Node> nodes = grid.getChildren ();
    grid.getChildren ().removeAll (nodes);

    _buildArea ();
  }

  /*
   * Add a new surcharge item to list
   */
  public void addEmptySurchargeLine (final Surcharge surcharge)
  {
    if (surchargeLineList == null)
      surchargeLineList = new ArrayList <> ();

    surchargeLineList.add (new SurchargeLine (rm, surcharge));

    _refreshArea ();
  }

  /*
   * Remove a surcharge line
   */
  public void removeSurchargeLine (final SurchargeLine line)
  {
    if (surchargeLineList.contains (line))
    {
      rm.getInvoiceData ().removeSurchargeItem (line.getSurcharge ());
      surchargeLineList.remove (line);

      _refreshArea ();
    }
  }

  /*
   * Create surcharge line list after loading a XML
   */
  public void createSurchargeLineAfterLoading ()
  {
    if (surchargeLineList == null)
      surchargeLineList = new ArrayList <> ();

    for (final Surcharge s : rm.getInvoiceData ().getSurchargeList ())
    {
      final SurchargeLine sl = new SurchargeLine (rm, s);

      SurchargeLineFiller.fillLineWithLoadedData (sl);

      surchargeLineList.add (sl);
    }

    _refreshArea ();

    rm.getSurchargeDiscountPane ().refreshTotalNetandTotalGross ();
  }

  /*
   * Getter / Setter
   */
  public List <SurchargeLine> getSurchargeLineList ()
  {
    return surchargeLineList;
  }
}
