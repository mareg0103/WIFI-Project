package at.mareg.ebi43creator.display.resources;

import at.mareg.ebi43creator.invoicedata.enums.EDocumentType;
import at.mareg.ebi43creator.invoicedata.enums.EUnit;
import at.mareg.ebi43creator.invoicedata.enums.EVATRate;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public final class Data
{

	private Data ()
	{
	}

	// ********************
	// * General settings *
	// ********************

	/*
	 * Application name
	 */
	public static final String APPLICATION_NAME = "ebInterface 4.3 XML Creator for government";

	/*
	 * XML root element attributes
	 */
	public static final String DEFAULT_NAMESPACE = "http://www.ebinterface.at/schema/4p3/";
	public static final String GENERATING_SYSTEM = "Mareg ebi4.3 document creator";
	public static final String INVOICE_LANGUAGE = "ger";

	/*
	 * Default save directory for XML files
	 */
	public static final String DEFAULT_SAVE_DIRECTORY = "Gespeicherte_Rechnungen/";

	//
	// Temporary at this place as long as the necessary part is not created
	//
	public static final String FURTERIDENTIFICATION_TYPE_FN = "FN";
	public static final String FURTERIDENTIFICATION_TYPE_FS = "FS";
	public static final String FURTERIDENTIFICATION_TYPE_FBG = "FBG";

	/*
	 * Check data message
	 */
	public static final String CHECKMESSAGE_SUCCESS = "ok";

	/*
	 * Default document type
	 */
	public static final String DEFAULT_DOCUMENTTYPE = EDocumentType.INVOICE.getElementID ();

	// *************************
	// * Form element settings *
	// *************************

	/*
	 * Scene settings
	 */
	public static final double SCENE_WIDTH = 1100d;
	public static final double SCENE_HEIGHT = 595d;

	/*
	 * Status background colors
	 */
	public static final String BACKGROUND_HEX_OK = "FFFFFF";
	public static final String BACKROUND_HEX_REQUIRED = "FFFFE0";
	public static final String BACKGROUND_HEX_ERROR = "FF9999";

	/*
	 * Default component settings
	 */
	public static final double DEFAULT_COMPONENT_WIDTH = 250d;

	/*
	 * HelpText settings
	 */
	public static final double HELPTEXTAREA_PADDING_VALUE = 5;
	public static final double HELPTEXTAREA_WIDTH = 290d;
	public static final double HELPTEXTAREA_HEIGHT = 565d - (2 * HELPTEXTAREA_PADDING_VALUE);
	public static final Color HELPTEXTAREA_BACKGROUNDCOLOR = Color.LIGHTGRAY;
	public static final String HELPTEXTAREA_FOREGROUNDCOLORASHEX = "#ff0000";
	public static final Insets HELPTEXTAREA_PADDING = new Insets (HELPTEXTAREA_PADDING_VALUE,
			HELPTEXTAREA_PADDING_VALUE, HELPTEXTAREA_PADDING_VALUE, HELPTEXTAREA_PADDING_VALUE);

	/*
	 * ScrollPane (in DetailsPane) settings
	 */
	public static final double DETAILS_SCROLLPANE_WIDTH = 800d;
	public static final double DETAILS_SCROLLPANE_HEIGHT = HELPTEXTAREA_HEIGHT + 10;

	/*
	 * ScrollPane (in SurchargeArea) settings
	 */
	public static final double SURCHARGE_SCROLLPANE_WIDTH = 800d;
	public static final double SURCHARGE_SCROLLPANE_HEIGHT = 600d;

	/*
	 * BasePane settings
	 */
	public static final Insets BASEPANE_PADDING = new Insets (5, 5, 5, 5);
	public static final double BASEPANE_HVGAP = 10d;

	/*
	 * DocumentTypePane settings
	 */
	public static final double DOCUMENTTYPEPANE_HEIGHT = 35d;

	/*
	 * VBox settings (general)
	 */
	public static final double VBOX_COMPONENT_WIDTH = 400d;
	public static final Insets VBOX_LABEL_INSETS = new Insets (0, 0, 3, 0);

	/*
	 * Start dialog component settings
	 */
	public static final double STARTDIALOG_VBOX_WIDTH = 300d;
	public static final Insets STARTDIALOG_LABEL_NEW_PADDING = new Insets (0, 0, 10, 0);
	public static final Insets STARTDIALOG_LABEL_LOAD_PADDING = new Insets (20, 0, 10, 0);
	public static final Insets STARTDIALOG_CHECKBOX_PADDING = new Insets (0, 0, 0, 30);

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
	public static final String ELEMENTTYPE_BUTTON = "Button";

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
	public static final String TITLEDPANE_ORDER_NAME = "Auftragsdaten";
	public static final String TITLEDPANE_BILLER_NAME = "Rechnungssteller";
	public static final String TITLEDPANE_INVOICERECIPIENT_NAME = "Rechnungsempfänger";
	public static final String TITLEDPANE_DELIVERY_NAME = "Lieferdaten";
	public static final String TITLEDPANE_PAYMENT_NAME = "Kontoverbindung";
	public static final String TITLEDPANE_DETAILS_NAME = "Details";
	public static final String TITLEDPANE_SURCHARGE_NAME = "Aufschlag/Abzug";
	public static final String TITLEDPANE_DISCOUNT_NAME = "Skonto";

	/*
	 * Details - right area
	 */
	public static final String DETAILS_RIGHT_AREA = "DetailsRightArea";
	public static final double DETAILS_RIGHT_AREA_COMPONENT_WIDTH = 130;

	/*
	 * Details - invoice line
	 */
	public static final String DEFAULT_VAT_RATE = EVATRate.VAT_TWENTY.getVatRateOutput ();
	public static final String DEFAULT_UNIT = EUnit.UNIT_03.getUnitDescription ();

	public static final Insets LINE_PADDING = new Insets (5, 5, 5, 5);
	public static final double LINE_HVGAP = 5d;

	public static final int DETAILS_LINE_COLUMN_COUNT_PER_ROW = 4;
	public static final double DETAILS_LINE_GRID_WIDTH = DETAILS_SCROLLPANE_WIDTH / 4;

	/*
	 * Reduction/Surcharge/Discount - right area
	 */
	public static final String SURCHARGE_RIGHT_AREA = "SurchargeRightArea";
	public static final double SURCHARGE_RIGHT_AREA_COMPONENT_WIDTH = DETAILS_RIGHT_AREA_COMPONENT_WIDTH;

}
