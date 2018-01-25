package at.mareg.ebi43creator.display.resources;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public final class Data
{

  private Data ()
  {}

  // Application name
  public static final String APPLICATION_NAME = "ebInterface 4.3 XML Creator for government";

  /*
   * XML root element attributes
   */
  public static final String DEFAULT_NAMESPACE = "http://www.ebinterface.at/schema/4p3/";
  public static final String GENERATING_SYSTEM = "Mareg ebi4.3 document creator";
  public static final String INVOICE_LANGUAGE = "ger";

  //
  // Temporary at this place as long as the necessary part is not created
  //
  public static final String FURTERIDENTIFICATION_TYPE_FN = "FN";
  public static final String FURTERIDENTIFICATION_TYPE_FS = "FS";
  public static final String FURTERIDENTIFICATION_TYPE_FBG = "FBG";

  /*
   * Default component settings
   */
  public static final double DEFAULT_COMPONENT_WIDTH = 250d;

  /*
   * Element type strings
   */
  public static final String ELEMENT_TEXT_FIELD = "TextField";
  public static final String ELEMENT_TEXT_AREA = "TextArea";
  public static final String ELEMENT_DATE_PICKER = "DatePicker";
  public static final String ELEMENT_CHECK_BOX = "CheckBox";

  /*
   * HelpText settings
   */
  public static final double HELPTEXTAREA_WIDTH = 300d;
  public static final double HELPTEXTAREA_HEIGHT = 400d;
  public static final Color HELPTEXTAREA_BACKGROUNDCOLOR = Color.LIGHTGRAY;
  public static final String HELPTEXTAREA_FOREGROUNDCOLORASHEX = "#ff0000";
  public static final Insets HELPTEXTAREA_PADDING = new Insets (5, 5, 5, 5);

  /*
   * ScrollPane (in DetailsPane) settings
   */
  public static final double DETAILS_SCROLLPANE_HEIGHT = HELPTEXTAREA_HEIGHT;

  /*
   * BasePane settings
   */
  public static final Insets BASEPANE_PADDING = new Insets (5, 5, 5, 5);
  public static final double BASEPANE_HVGAP = 10d;

  /*
   * VBox settings
   */
  public static final double VBOX_COMPONENT_WIDTH = 400d;
  public static final Insets VBOX_LABEL_INSETS = new Insets (0, 0, 3, 0);

  /*
   * Form settings
   */
  public static final int COLUMN_COUNT_PER_ROW = 2;

  /*
   * Element types for input
   */
  public static final String ELEMENTTYPE_TEXTFIELD = "TextField";
  public static final String ELEMENTTYPE_TEXTAREA = "TextArea";
  public static final String ELEMENTTYPE_DATEPICKER = "DatePicker";
  public static final String ELEMENTTYPE_CHECKBOX = "CheckBox";
  public static final String ELEMENTTYPE_COMBOBOX = "ComboBox";

  /*
   * Tab names for form
   */
  public static final String TAB_ORDER_DATA = "Auftragsdaten";
  public static final String TAB_CONTACT_DATA = "Kontaktdaten";
  public static final String TAB_PAYMENT_DATA = "Zahlungsdaten";
  public static final String TAB_DETAILS_DATA = "Details";
  public static final String TAB_DISCOUNT_DATA = "Aufschläge/Abzüge/Skonti";
  public static final String TAB_SUMMARY_DATA = "Zusammenfassung/Speichern";

  /*
   * TitledPane names for form
   */
  public static final String TITLEDPANE_ORDER = "Auftragsdaten";
  public static final String TITLEDPANE_BILLER = "Rechnungssteller";
  public static final String TITLEDPANE_INVOICERECIPIENT = "Rechnungsempfänger";
  public static final String TITLEDPANE_DELIVERY = "Lieferdaten";
  public static final String TITLEDPANE_PAYMENT = "Kontoverbindung";

}
