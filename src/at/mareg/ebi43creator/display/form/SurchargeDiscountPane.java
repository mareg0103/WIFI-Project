package at.mareg.ebi43creator.display.form;

import java.util.List;

import at.mareg.ebi43creator.display.form.discount.DiscountArea;
import at.mareg.ebi43creator.display.form.surcharge.SurchargeArea;
import at.mareg.ebi43creator.display.form.surcharge.SurchargeLine;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.display.utilities.TextFieldHelper;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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
  private Accordion ac;
  private GridPane grid;
  private TitledPane tp_Surcharge;
  private TitledPane tp_Discount;
  private TitledPane tp_DueDate;
  private SurchargeArea surchargeArea;
  private DiscountArea discountArea;
  private DueDatePane dueDatePane;
  private Button addDiscountLine;

  private TextField totalNetField;
  private TextField totalGrossField;
  private int rightAreaRow;

  /*
   * SurchargeDiscountPane variables
   */
  private Double overallTotalNet;
  private Double overallTotalGross;

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
    /*
     * Center content of BorderPane
     */
    ac = new Accordion ();

    tp_Surcharge = new TitledPane ();
    surchargeArea = new SurchargeArea (rm);
    tp_Surcharge.setContent (surchargeArea);
    tp_Surcharge.setText (Data.TITLEDPANE_SURCHARGE_NAME);

    tp_Discount = new TitledPane ();
    discountArea = new DiscountArea (rm);
    tp_Discount.setContent (discountArea);
    tp_Discount.setText (Data.TITLEDPANE_DISCOUNT_NAME);

    tp_DueDate = new TitledPane ();
    dueDatePane = new DueDatePane (rm);
    tp_DueDate.setContent (dueDatePane);
    tp_DueDate.setText (Data.TITLEDPANE_DUEDATE_NAME);

    ac.getPanes ().addAll (tp_Surcharge, tp_Discount, tp_DueDate);

    /*
     * Right content of BorderPane
     */

    grid = new GridPane ();
    grid.setPadding (Data.BASEPANE_PADDING);
    grid.setHgap (Data.BASEPANE_HVGAP);
    grid.setVgap (grid.getHgap ());

    for (final EFormElement eb : EFormElement.values ())
    {
      if (eb.getTiteldPaneID ().equals (Data.SURCHARGE_RIGHT_AREA))
      {
        final String elementID = eb.getID ();
        final String labelText = eb.getLabelText ();

        if (eb.getType ().equals (Data.ELEMENTTYPE_BUTTON))
        {
          final Button b = FormElementCreator.getStandardButton (elementID, labelText);
          b.setPrefWidth (Data.SURCHARGE_RIGHT_AREA_COMPONENT_WIDTH);

          if (elementID.equals (EFormElement.SURCHARGE_DISCOUNT_RIGHT_ADDSURCHARGEBUTTON.getID ()))
          {
            b.setOnAction (e -> {
              rm.getInvoiceData ().addEmptySurchargeItem ();
              expandSurchargePane ();
            });
          }

          if (elementID.equals (EFormElement.SURCHARGE_DISCOUNT_RIGHT_ADDDISCOUNTBUTTON.getID ()))
          {
            addDiscountLine = b;

            b.setOnAction (e -> {
              rm.getInvoiceData ().addPaymentContitions ();

              rm.getInvoiceData ().getPaymentConditions ().addEmptyDiscount ();

              if (rm.getInvoiceData ().getPaymentConditions ().getDiscounts ().size () >= 2)
                b.disableProperty ().set (true);

              expandDiscountPane ();
            });
          }

          grid.add (b, 0, rightAreaRow);

          rightAreaRow++;
        }

        if (eb.getType ().equals (Data.ELEMENTTYPE_TEXTFIELD))
        {
          final VBox v = new VBox ();

          final TextField t = FormElementCreator.getStandardTextField (elementID, eb.isRequired ());
          t.setPrefWidth (Data.SURCHARGE_RIGHT_AREA_COMPONENT_WIDTH);

          if (elementID.equals (EFormElement.SURCHARGE_DISCOUNT_RIGHT_TOTALNETAMOUNT.getID ()))
          {
            v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, new Insets (20, 0, 0, 0)));

            totalNetField = t;
            totalNetField.setEditable(false);
          }

          if (elementID.equals (EFormElement.SURCHARGE_DISCOUNT_RIGHT_TOTALGROSSAMOUNT.getID ()))
          {
            v.getChildren ().add (FormElementCreator.getStandardLabel (labelText, null));

            totalGrossField = t;
            totalGrossField.setEditable(false);
          }

          v.getChildren ().add (t);
          grid.add (v, 0, rightAreaRow);

          rightAreaRow++;
        }
      }
    }

    this.setCenter (ac);
    this.setRight (grid);
  }

  /*
   * Expand surcharge pane
   */
  private void expandSurchargePane ()
  {
    tp_Surcharge.setExpanded (true);
  }

  /*
   * Expand discount pane ()
   */
  private void expandDiscountPane ()
  {
    tp_Discount.setExpanded (true);
  }

  public void refreshTotalNetandTotalGross ()
  {
    overallTotalNet = rm.getDetailsPane ().getInvoiceLinesTotalNet ();
    overallTotalGross = rm.getDetailsPane ().getInvoiceLinesTotalGross ();

    final List <SurchargeLine> surchargeLineList = getSurchargeArea ().getSurchargeLineList ();

    if (surchargeLineList != null)
      for (final SurchargeLine sl : getSurchargeArea ().getSurchargeLineList ())
      {
        overallTotalNet = Double.valueOf (overallTotalNet.doubleValue () +
                                          sl.getSurchargeLineTotalNet ().doubleValue ());
        overallTotalGross = Double.valueOf (overallTotalGross.doubleValue () +
                                            sl.getSurchargeLineTotalGross ().doubleValue ());
      }

    totalNetField.setText (TextFieldHelper.getTwoDecimalsStringFromDouble (overallTotalNet));
    totalGrossField.setText (TextFieldHelper.getTwoDecimalsStringFromDouble (overallTotalGross));
  }

  /*
   * Getter / Setter
   */
  public SurchargeArea getSurchargeArea ()
  {
    return surchargeArea;
  }

  public DiscountArea getDiscountArea ()
  {
    return discountArea;
  }

  public DueDatePane getDueDatePane ()
  {
    return dueDatePane;
  }

  public void setAddDiscountButtonToEnable ()
  {
    addDiscountLine.disableProperty ().set (false);
  }

  public Double getOverAllTotalNet ()
  {
    return overallTotalNet;
  }

  public Double getOverAllTotalGross ()
  {
    return overallTotalGross;
  }
}
