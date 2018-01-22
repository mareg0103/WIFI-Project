package at.mareg.ebi43creator.display.resources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

public class InvoiceDateManager
{

	// Formatter to parse a German formatted String to a LocalDate
	private final String DATE_FORMATTER_PATTERN_DE = "dd.MM.yyyy";

	// Necessary dates
	private final LocalDate currentDate;
	private final LocalDate minInvoiceDate;
	private final LocalDate maxInvoiceDate;

	// Day cell for limiting general date picker
	private Callback<DatePicker, DateCell> dayCellFactory;

	// Day cell for limiting order reference date date picker
	private Callback<DatePicker, DateCell> dayCellFactoryOrder;

	public InvoiceDateManager ()
	{

		currentDate = LocalDate.now ();
		minInvoiceDate = currentDate.minusYears (3L);
		maxInvoiceDate = currentDate.plusMonths (6L);

		initDayCellFactories ();

	}

	// Internal methods

	// Initialize day cell factories
	private void initDayCellFactories ()
	{
		dayCellFactory = (final DatePicker dp) -> new DateCell ()
		{

			@Override
			public void updateItem (final LocalDate item, final boolean empty)
			{
				super.updateItem (item, empty);

				if (item.isAfter (maxInvoiceDate) || item.isBefore (minInvoiceDate))
				{
					setDisable (true);
					setStyle ("-fx-background-color: #aaaaaa;");
				}
			}

		};

		dayCellFactoryOrder = (final DatePicker dp) -> new DateCell ()
		{

			@Override
			public void updateItem (final LocalDate item, final boolean empty)
			{
				super.updateItem (item, empty);

				if (item.isAfter (currentDate) || item.isBefore (minInvoiceDate))
				{
					setDisable (true);
					setStyle ("-fx-background-color: #aaaaaa;");
				}
			}

		};
	}

	// External methods

	// Disable the dates in DatePicker which are before the allowed minimum and
	// after the allowed maximum invoice date
	public Callback<DatePicker, DateCell> getDayCellFactoryForInvoiceDate ()
	{
		return dayCellFactory;
	}

	public Callback<DatePicker, DateCell> getDayCellFactoryOrder ()
	{
		return dayCellFactoryOrder;
	}

	public Callback<DatePicker, DateCell> getDayCellFectoryDisableAfter (final LocalDate date)
	{

		final Callback<DatePicker, DateCell> dcf = (final DatePicker dp) -> new DateCell ()
		{

			@Override
			public void updateItem (final LocalDate item, final boolean empty)
			{
				super.updateItem (item, empty);

				if (item.isAfter (date))
				{

					setDisable (true);
					setStyle ("-fx-background-color: #aaaaaa;");

				}
			}

		};

		return dcf;
	}

	public Callback<DatePicker, DateCell> getDayCellFectoryDisableBefore (final LocalDate date)
	{

		final Callback<DatePicker, DateCell> dcf = (final DatePicker dp) -> new DateCell ()
		{

			@Override
			public void updateItem (final LocalDate item, final boolean empty)
			{
				super.updateItem (item, empty);

				if (item.isBefore (date))
				{

					setDisable (true);
					setStyle ("-fx-background-color: #aaaaaa;");

				}
			}

		};

		return dcf;
	}

	// Just returning values as String or local date
	public String getCurrentDateAsString ()
	{
		return currentDate.toString ();
	}

	public String getMinInvoiceDateAsString ()
	{
		return minInvoiceDate.toString ();
	}

	public String getMaxInvoiceDateAsString ()
	{
		return maxInvoiceDate.toString ();
	}

	public String getGermanFormatedDateFromLocalDateString (final String localDateAsString)
	{
		final String[] sCurDate = localDateAsString.split ("-");

		return sCurDate[2] + "." + sCurDate[1] + "." + sCurDate[0];
	}

	public String getGermanFormatedDateFromLocalDate (final LocalDate localDate)
	{
		return getGermanFormatedDateFromLocalDateString (localDate.toString ());
	}

	public LocalDate getLocalDateFromGermanFormattedDateString (final String dateString)
	{
		return LocalDate.parse (dateString, DateTimeFormatter.ofPattern (DATE_FORMATTER_PATTERN_DE));
	}

	public String getLocalDateStringFromGermanFormattedDateString (final String dateString)
	{
		return LocalDate.parse (dateString, DateTimeFormatter.ofPattern (DATE_FORMATTER_PATTERN_DE)).toString ();
	}

	// Calculating new dates

}
