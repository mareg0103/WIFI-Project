package at.mareg.ebi43creator.display.form.invoicelines;

import java.util.ArrayList;
import java.util.List;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.InvoiceLineFiller;
import at.mareg.ebi43creator.invoicedata.details.ListLineItem;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class InvoiceLineArea extends BasePane
{
  /**
   * Class to add invoice lines to the current document
   * 
   * @author Martin Regitnig
   */

  /*
   * Pane elements
   */
  private ScrollPane scroll;
  private GridPane grid;
  private int areaRow;

  /*
   * List to save invoice lines
   */
  private List <InvoiceLine> invoiceLineList;

  public InvoiceLineArea (final ResourceManager resman)
  {
    super (resman);
    invoiceLineList = new ArrayList <> ();

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

  /*
   * (Re) Build invoice line area
   */
  private void _buildArea ()
  {
    areaRow = 0;

    for (final InvoiceLine il : invoiceLineList)
    {
      grid.add (il, 0, areaRow);
      areaRow++;
    }
  }

  /*
   * Remove all nodes an rebuild grid
   */
  private void _refreshArea ()
  {
    ObservableList <Node> nodes = grid.getChildren ();
    grid.getChildren ().removeAll (nodes);

    _buildArea ();
  }

  /*
   * Add an empty invoice line
   */
  public void addEmptyInvoiceLine (final ListLineItem li)
  {
    invoiceLineList.add (new InvoiceLine (rm, li));
    _refreshArea ();
  }

  /*
   * Remove an invoice line
   */
  public void removeInvoiceLine (final InvoiceLine line)
  {
    if (invoiceLineList.contains (line))
    {
      invoiceLineList.remove (line);
      rm.getInvoiceData ().getDetails ().removeListLineItem (line.getListLineItem ());

      _refreshArea ();
    }
  }

  /*
   * If order id is government order number set order position number in all
   * invoice line to active
   */
  public void setOrderPostionNumberInLinesStatus (final boolean status)
  {
    for (final InvoiceLine il : invoiceLineList)
    {
      il.setOrderPositionNumberStatus (status);
    }
  }

  /*
   * Create invoice line list after loading a XML
   */
  public void createInvoiceLineAfterLoading ()
  {
    for (final ListLineItem l : rm.getInvoiceData ().getDetails ().getListLineItems ())
    {
      InvoiceLine il = new InvoiceLine (rm, l);

      InvoiceLineFiller.fillLineWithLoadedData (il);

      invoiceLineList.add (il);
    }

    _refreshArea ();
  }

  /*
   * Getter / Setter
   */
  public List <InvoiceLine> getInvoiceLineList ()
  {
    return invoiceLineList;
  }
}
