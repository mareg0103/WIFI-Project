package at.mareg.ebi43creator.display.form;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class DetailsPane extends BorderPane
{
  private ResourceManager rm;

  // Center content
  private ScrollPane scroll;
  private InvoiceLineArea invoiceLines;

  // Right area content
  private GridPane grid;

  public DetailsPane (final ResourceManager resman)
  {
    rm = resman;

    init ();
  }

  private void init ()
  {
    // Center content
    scroll = new ScrollPane ();
    scroll.setPrefHeight (Data.DETAILS_SCROLLPANE_HEIGHT);
    scroll.setMaxHeight (Data.DETAILS_SCROLLPANE_HEIGHT);

    invoiceLines = new InvoiceLineArea (rm);
    scroll.setContent (invoiceLines);

    grid = new GridPane ();

    this.setCenter (scroll);
  }
}
