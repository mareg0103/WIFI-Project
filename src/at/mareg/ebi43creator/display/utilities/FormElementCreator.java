package at.mareg.ebi43creator.display.utilities;

import at.mareg.ebi43creator.display.resources.Data;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Creates standard and extended elements and provides methods for them (if
 * necessary)
 * 
 * @author Martin Regitnig
 */

public final class FormElementCreator
{

  private FormElementCreator ()
  {}

  public static Label getStandardLabel (final String text)
  {
    return new Label (text);
  }

  public static Label getDisabledLookingLabel (final String text)
  {
    Label l = new Label (text);

    l.setTextFill (Color.DARKGRAY);

    return l;
  }

  public static TextField getStandardTextField (final String sID, final boolean required)
  {
    final TextField t = new TextField ();

    t.setPrefWidth (Data.DEFAULT_COMPONENT_WIDTH);
    t.setStyle ("-fx-control-inner-background: #" + (required ? "FFFFE0" : "FFFFFF"));
    t.setId (sID);

    return t;
  }

  public static TextField getDisabledTextField (final String sID, final boolean required)
  {
    final TextField t = new TextField ();

    t.setPrefWidth (Data.DEFAULT_COMPONENT_WIDTH);
    // t.setStyle ("-fx-control-inner-background: #" + (required ? "FFFFE0" :
    // "FFFFFF"));
    t.disableProperty ().set (true);
    t.setId (sID);

    return t;
  }

  public static TextArea getStandardTextArea (final String sID, final boolean required)
  {
    final TextArea t = new TextArea ();

    t.setPrefWidth (2 * Data.DEFAULT_COMPONENT_WIDTH);
    t.setStyle ("-fx-control-inner-background: #" + (required ? "FFFFE0" : "FFFFFF"));
    t.setId (sID);

    return t;
  }

  public static DatePicker getStandardDatePicker (final String sID, final boolean required)
  {
    final DatePicker dp = new DatePicker ();

    dp.setPrefWidth (Data.DEFAULT_COMPONENT_WIDTH);
    dp.setStyle ("-fx-control-inner-background: #" + (required ? "FFFFE0" : "FFFFFF"));
    dp.setEditable (false);
    dp.setId (sID);

    return dp;
  }

  public static CheckBox getStandardCheckBox (final String sID, final boolean required, final String text)
  {
    final CheckBox cb = new CheckBox (text);

    cb.setPrefWidth (Data.DEFAULT_COMPONENT_WIDTH);
    cb.setStyle ("-fx-control-inner-background: #" + (required ? "FFFFE0" : "FFFFFF"));
    cb.setSelected (false);
    cb.setId (sID);

    return cb;
  }

  public static DatePicker getDatePickerWithID (final GridPane grid, final String id)
  {
    for (final Node n : grid.getChildren ())
    {

      if (n.getClass () == VBox.class)
      {

        for (final Node vn : ((VBox) n).getChildren ())
        {

          if (vn.getClass () == DatePicker.class)
          {

            if (((DatePicker) vn).getId ().equals (id))
            {
              return ((DatePicker) vn);
            }

          }

        }

      }

    }

    return null;

  }

}
