package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.form.invoicelines.InvoiceLineArea;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.display.utilities.FormElementCreator;
import at.mareg.ebi43creator.invoicedata.enums.EFormElement;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DetailsPane extends BorderPane
{
  /**
   * Pane to enter the detail lines of the document
   * 
   * @author Martin Regitnig
   */
<<<<<<< HEAD

  /*
   * Instance of ResourceManager because this pane doesn't extend BasePane
   */
  private ResourceManager rm;

  /*
   * Pane elements
   */
  private InvoiceLineArea invoiceLineArea;
=======

  /*
   * Instance of ResourceManager because this pane doesn't extend BasePane
   */
  private ResourceManager rm;

  /*
   * Pane elements
   */
  private InvoiceLineArea invoiceLines;
>>>>>>> 65866830856d628d7eeed8e879042153d55ce848
  private GridPane grid;
  private int rightAreaRow;

  public DetailsPane (final ResourceManager resman)
  {
    rm = resman;

    init ();
  }

  /*
   * Create details pane. This pane contains the invoice lines
   */
  private void init ()
  {
    rightAreaRow = 0;

    /*
     * Center content of BorderPane
     */
<<<<<<< HEAD
    invoiceLineArea = new InvoiceLineArea (rm);
=======
    invoiceLines = new InvoiceLineArea (rm);
>>>>>>> 65866830856d628d7eeed8e879042153d55ce848

    /*
     * Right content of BorderPane
     */
    grid = new GridPane ();
    grid.setPadding (Data.BASEPANE_PADDING);
    grid.setHgap (Data.BASEPANE_HVGAP);
    grid.setVgap (grid.getHgap ());

    for (final EFormElement eb : EFormElement.values ())
    {
      if (eb.getTiteldPaneID ().equals (Data.DETAILS_RIGHT_AREA))
      {
        if (eb.getType ().equals (Data.ELEMENTTYPE_BUTTON))
        {
          Button b = FormElementCreator.getStandardButton (eb.getID (), eb.getLabelText ());
          b.setPrefWidth (Data.DETAILS_RIGHT_AREA_COMPONENT_WIDTH);
          b.setOnAction (e -> {
<<<<<<< HEAD
            rm.getInvoiceData ().getDetails ().addEmptyListLineItem ();
=======
            System.out.println ("Neue Zeile hinzufügen geklickt");
>>>>>>> 65866830856d628d7eeed8e879042153d55ce848
          });
          grid.add (b, 0, rightAreaRow);

          rightAreaRow++;
        }
      }

      if (eb.getTiteldPaneID ().equals (Data.DETAILS_RIGHT_AREA))
      {
        if (eb.getType ().equals (Data.ELEMENTTYPE_TEXTFIELD))
        {
          VBox v = new VBox ();

          if (eb.getName ().equals (EFormElement.DETAILS_RIGHT_TOTALNETAMOUND.getName ()) ||
              eb.getName ().equals (EFormElement.DETAILS_RIGHT_VATID_BILLER.getName ()))
            v.getChildren ().add (FormElementCreator.getStandardLabel (eb.getLabelText (), new Insets (20, 0, 0, 0)));
          else
            v.getChildren ().add (FormElementCreator.getStandardLabel (eb.getLabelText (), null));
<<<<<<< HEAD

          TextField t = FormElementCreator.getStandardTextField (eb.getID (), eb.isRequired ());
          t.setPrefWidth (Data.DETAILS_RIGHT_AREA_COMPONENT_WIDTH);
          v.getChildren ().add (t);

          grid.add (v, 0, rightAreaRow);

          rightAreaRow++;
        }
      }
    }

    this.setCenter (invoiceLineArea);
    this.setRight (grid);
  }

  public InvoiceLineArea getInvoiceLineArea ()
  {
    return invoiceLineArea;
=======

          TextField t = FormElementCreator.getStandardTextField (eb.getID (), eb.isRequired ());
          t.setPrefWidth (Data.DETAILS_RIGHT_AREA_COMPONENT_WIDTH);
          v.getChildren ().add (t);

          grid.add (v, 0, rightAreaRow);

          rightAreaRow++;
        }
      }
    }

    this.setCenter (invoiceLines);
    this.setRight (grid);
>>>>>>> 65866830856d628d7eeed8e879042153d55ce848
  }
}
