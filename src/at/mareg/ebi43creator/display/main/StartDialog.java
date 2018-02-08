package at.mareg.ebi43creator.display.main;

import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.enums.EDocumentType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StartDialog extends Dialog <ButtonType>
{
  private EBI43CreatorMain main;

  public StartDialog (final EBI43CreatorMain m)
  {
    main = m;

    _showDialog ();
  }

  /*
   * Show start dialog
   */
  private void _showDialog ()
  {
    this.setTitle ("Neues Dokument");

    GridPane grid = new GridPane ();

    /*
     * New empty document
     */
    VBox newDocumentBox = new VBox ();
    newDocumentBox.setPrefWidth (Data.STARTDIALOG_VBOX_WIDTH);
    ToggleGroup tg = new ToggleGroup ();

    Label newDocumentLabel = new Label ("Neues leeres Dokument");
    newDocumentLabel.setFont (Font.font ("Arial", FontWeight.BOLD, 12));
    newDocumentLabel.setPadding (Data.STARTDIALOG_LABEL_NEW_PADDING);
    newDocumentBox.getChildren ().add (newDocumentLabel);

    /*
     * Search all document types and create radio buttons for each one, set
     * current document type to disable
     */
    for (final EDocumentType e : EDocumentType.values ())
    {
      RadioButton r = new RadioButton (e.getElementText ());
      final String documentType = e.getElementID ();

      r.setId (documentType);
      if (documentType.equals (Data.DEFAULT_DOCUMENTTYPE))
        r.selectedProperty ().set (true);

      r.setPrefWidth (150d);
      r.setPadding (Data.STARTDIALOG_CHECKBOX_PADDING);
      r.setToggleGroup (tg);

      newDocumentBox.getChildren ().add (r);
    }

    /*
     * Add change listener to toggle group
     */
    tg.selectedToggleProperty ().addListener (new ChangeListener <Toggle> ()
    {
      public void changed (ObservableValue <? extends Toggle> observable, Toggle oldValue, Toggle newValue)
      {
        /*
         * Set new document type
         */
        main.setDocumentType (((RadioButton) newValue).getId ());
      }
    });

    grid.add (newDocumentBox, 0, 0);

    /*
     * Load document
     */
    VBox loadDocumentBox = new VBox ();
    loadDocumentBox.setPrefWidth (Data.STARTDIALOG_VBOX_WIDTH);

    Label loadDocumentLabel = new Label ("Dokument laden");
    loadDocumentLabel.setFont (Font.font ("Arial", FontWeight.BOLD, 12));
    loadDocumentLabel.setPadding (Data.STARTDIALOG_LABEL_LOAD_PADDING);
    loadDocumentBox.getChildren ().add (loadDocumentLabel);
    grid.add (loadDocumentBox, 0, 1);

    this.getDialogPane ().setContent (grid);

    ButtonType ok = ButtonType.OK;
    ButtonType cancel = ButtonType.CANCEL;
    this.getDialogPane ().getButtonTypes ().addAll (ok, cancel);
  }
}
