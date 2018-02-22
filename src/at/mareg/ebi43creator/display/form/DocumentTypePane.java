package at.mareg.ebi43creator.display.form;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import at.mareg.ebi43creator.display.form.dialogs.ChangeDocumentTypeDialog;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.display.resources.ResourceManager;
import at.mareg.ebi43creator.invoicedata.enums.EDocumentType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DocumentTypePane extends BasePane
{
  /**
   * Pane to show the current document type. Here is the possibility to change
   * the type at any time
   * 
   * @author Martin Regitnig
   */

  /*
   * Map with the supported document types based on EDocumentType enum
   */
  private Map <String, String> doctypeMap;

  /*
   * Pane elements
   */
  private Label documentTypeLabel;
  private Button changeDocumentTypeButton;

  public DocumentTypePane (final ResourceManager resman)
  {
    super (resman);

    doctypeMap = new TreeMap <> ();

    initMap ();
    init ();
  }

  private void initMap ()
  {
    for (final EDocumentType e : EDocumentType.values ())
    {
      doctypeMap.put (e.getElementID (), e.getElementText ());
    }
  }

  @Override
  protected void init ()
  {
    super.init ();
    this.setPrefHeight (Data.DOCUMENTTYPEPANE_HEIGHT);
    this.setMaxHeight (Data.DOCUMENTTYPEPANE_HEIGHT);

    documentTypeLabel = new Label ();
    documentTypeLabel.setFont (Font.font ("Arial", FontWeight.BOLD, 20));
    documentTypeLabel.setPrefWidth (350d);
    documentTypeLabel.setText ("Dokumententyp: " + doctypeMap.get (rm.getInvoiceData ().getDocumentType ()));

    this.add (documentTypeLabel, col, row);
    incrementCol ();

    changeDocumentTypeButton = new Button ("Dokumententyp ändern");
    changeDocumentTypeButton.setOnAction (e -> {
      Optional <ButtonType> result = (new ChangeDocumentTypeDialog (rm.getInvoiceData ().getDocumentType (),
                                                                    rm).showAndWait ());

      if (result.isPresent ())
        if (result.get () == ButtonType.OK)
          _refresh ();
    });

    this.add (changeDocumentTypeButton, col, row);
  }

  private void _refresh ()
  {
    documentTypeLabel.setText ("Dokumententyp: " + doctypeMap.get (rm.getInvoiceData ().getDocumentType ()));
  }
}
