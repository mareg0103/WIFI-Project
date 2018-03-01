package at.mareg.ebi43creator.display.form.discount;

import java.util.ArrayList;
import java.util.List;

import at.mareg.ebi43creator.display.form.BasePane;
import at.mareg.ebi43creator.display.form.DueDatePane;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import at.mareg.ebi43creator.invoicedata.payment.Discount;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class DiscountArea extends BasePane
{
  /*
   * Discount lines
   */
  private List <DiscountLine> discountLineList;

  /*
   * Pane elements
   */
  private GridPane grid;
  private int areaRow;

  public DiscountArea (final ResourceManager resman)
  {
    super (resman);

    init ();
  }

  @Override
  protected void init ()
  {
    super.init ();

    grid = new GridPane ();

    this.getChildren ().add (grid);
  }

  /*
   * (Re) Build discount line area
   */
  private void _buildArea ()
  {
    areaRow = 0;

    if (discountLineList != null)
      for (final DiscountLine dl : discountLineList)
      {
        grid.add (dl, 0, areaRow);
        areaRow++;
      }
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
   * Add a new discount item to list
   */
  public void addEmptyDiscountLine (final Discount discount)
  {
    if (discountLineList == null)
      discountLineList = new ArrayList <> ();

    final DiscountLine dl = new DiscountLine (rm, discount);
    discountLineList.add (dl);

    _refreshArea ();
  }

  /*
   * Remove a discount line
   */
  public void removeDiscountLine (final DiscountLine line)
  {
    if (discountLineList.contains (line))
    {
      rm.getInvoiceData ().getPaymentConditions ().removeDiscount (line.getDiscount ());
      discountLineList.remove (line);

      if (discountLineList.size () == 0)
      {
        discountLineList = null;

        EFormElement.DUE_DATE.setIsRequired (false);
      }

      _refreshArea ();
    }

    setDueDatePickerToRequiredOrOptional ();
  }

  /*
   * Set due date date picker
   */
  public void setDueDatePickerToRequiredOrOptional ()
  {
    final DueDatePane ddp = rm.getSurchargeDiscountPane ().getDueDatePane ();

    if (discountLineList == null)
    {
      ddp.setDueDatePickerToOptional ();
    }
    else
    {
      if (discountLineList.size () == 1)
      {
        final String value = discountLineList.get (0).getIfPaidUntil ();

        if (value == null)
          ddp.setDueDatePickerToOptional ();
        else
          rm.getSurchargeDiscountPane ()
            .getDueDatePane ()
            .setDueDatePickerAsRequired (discountLineList.get (0).getIfPaidUntil ());
      }

      if (discountLineList.size () == 2)
      {
        final String dateValueLineOne = discountLineList.get (0).getIfPaidUntil ();
        final String dateValueLineTwo = discountLineList.get (1).getIfPaidUntil ();

        if (dateValueLineOne == null && dateValueLineTwo == null)
          ddp.setDueDatePickerToOptional ();

        if (dateValueLineOne != null && dateValueLineTwo == null)
          ddp.setDueDatePickerAsRequired (dateValueLineOne);

        if ((dateValueLineOne == null && dateValueLineTwo != null) ||
            (dateValueLineOne != null && dateValueLineTwo != null))
          ddp.setDueDatePickerAsRequired (dateValueLineTwo);
      }
    }
  }

  /*
   * Get an discount line
   */
  public DiscountLine getDiscountLine (final int index)
  {
    return discountLineList.get (index);
  }

  /*
   * Getter / Setter
   */
  public List <DiscountLine> getDiscountLineList ()
  {
    return discountLineList;
  }

  public void showInvoiceLineList ()
  {
    for (final DiscountLine dl : discountLineList)
      System.out.println (dl.toString ());
  }
}
