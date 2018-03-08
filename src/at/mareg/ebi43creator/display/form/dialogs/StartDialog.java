package at.mareg.ebi43creator.display.form.dialogs;

import java.io.File;

import at.mareg.ebi43creator.display.main.EBI43CreatorMain;
import at.mareg.ebi43creator.display.resources.Data;
import at.mareg.ebi43creator.invoicedata.enums.EDocumentType;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartDialog extends Dialog <ButtonType>
{
  private final EBI43CreatorMain main;
  private final Stage primaryStage;

  public StartDialog (final EBI43CreatorMain m, final Stage s)
  {
    main = m;
    primaryStage = s;

    _showDialog ();
  }

  /*
   * Show start dialog
   */
  private void _showDialog ()
  {
    this.setTitle ("Neues Dokument");

    final GridPane grid = new GridPane ();

    /*
     * New empty document
     */
    final VBox newDocumentBox = new VBox ();
    newDocumentBox.setPrefWidth (Data.STARTDIALOG_VBOX_WIDTH);
    final ToggleGroup tg = new ToggleGroup ();

    final Label newDocumentLabel = new Label ("Neues leeres Dokument");
    newDocumentLabel.setFont (Font.font ("Arial", FontWeight.BOLD, 12));
    newDocumentLabel.setPadding (Data.STARTDIALOG_LABEL_NEW_PADDING);
    newDocumentBox.getChildren ().add (newDocumentLabel);

    /*
     * Search all document types and create radio buttons for each one, set
     * current document type to disable
     */
    for (final EDocumentType e : EDocumentType.values ())
    {
      final RadioButton r = new RadioButton (e.getElementText ());
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
    tg.selectedToggleProperty ()
      .addListener ((ChangeListener <Toggle>) (observable,
                                               oldValue,
                                               newValue) -> main.setDocumentType (((RadioButton) newValue).getId ()));

    grid.add (newDocumentBox, 0, 0);

    /*
     * Load document
     */
    final VBox loadDocumentBox = new VBox ();
    loadDocumentBox.setPrefWidth (Data.STARTDIALOG_VBOX_WIDTH);

    final Label loadDocumentLabel = new Label ("Dokument laden");
    loadDocumentLabel.setFont (Font.font ("Arial", FontWeight.BOLD, 12));
    loadDocumentLabel.setPadding (Data.STARTDIALOG_LABEL_LOAD_PADDING);
    loadDocumentBox.getChildren ().add (loadDocumentLabel);

    final GridPane loadGrid = new GridPane ();
    loadGrid.setVgap (15d);
    loadGrid.setHgap (loadGrid.getVgap ());

    final Button loadDocumentButton = new Button ("Durchsuchen...");
    loadGrid.add (loadDocumentButton, 0, 0);

    final Label showSelectedFileDescription = new Label ("Keine Datei ausgewählt");
    loadGrid.add (showSelectedFileDescription, 1, 0);

    loadDocumentBox.getChildren ().add (loadGrid);

    /*
     * Add action listener to load button
     */
    loadDocumentButton.setOnAction (e -> {
      final FileChooser fileChooser = new FileChooser ();
      fileChooser.setTitle ("Vorlage laden...");
      fileChooser.setInitialDirectory (new File (Data.DEFAULT_SAVE_DIRECTORY));

      final File file = fileChooser.showOpenDialog (primaryStage);

      if (file == null)
      {
        showSelectedFileDescription.setText ("Keine Datei ausgewählt");
        main.setLoadPath (null);
      }
      else
      {
        showSelectedFileDescription.setText (file.getName ());

        main.setLoadPath (file.getAbsolutePath ());
      }
    });

    grid.add (loadDocumentBox, 0, 1);

    /*
     * Set this content
     */
    this.getDialogPane ().setContent (grid);

    final ButtonType ok = ButtonType.OK;
    final ButtonType cancel = ButtonType.CANCEL;
    this.getDialogPane ().getButtonTypes ().addAll (ok, cancel);
  }
}
