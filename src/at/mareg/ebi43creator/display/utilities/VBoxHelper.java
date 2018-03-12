package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.display.resources.Data;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public final class VBoxHelper
{
  /**
   * Helper class to structure vertical boxes
   *
   * @author Martin Regitnig
   */

  /*
   * No instantiation, only static methods
   */
  private VBoxHelper ()
  {}

  /*
   * Add structure to a given vertical box
   */
  public static void structureVBox (final VBox vbox)
  {

    for (final Node n : vbox.getChildren ())
      if (n.getClass () == Label.class)
      {
        ((Label) n).setPadding (Data.VBOX_LABEL_INSETS);
        ((Label) n).setPrefWidth (Data.VBOX_COMPONENT_WIDTH);
      }
      else
        if (n.getClass () == TextField.class)
        {
          ((TextField) n).setPrefWidth (Data.VBOX_COMPONENT_WIDTH);
        }
        else
          if (n.getClass () == DatePicker.class)
          {
            ((DatePicker) n).setPrefWidth (Data.VBOX_COMPONENT_WIDTH);
          }

  }
}
