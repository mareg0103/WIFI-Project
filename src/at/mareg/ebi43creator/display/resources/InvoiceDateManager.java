package at.mareg.ebi43creator.display.resources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

public class InvoiceDateManager
{
  /**
   * Date manager to create and calculate dates and day cell factories
   *
   * @author Martin Regitnig
   */

  /*
   * Formatter to parse a German formatted String to a LocalDate
   */
  private final String DATE_FORMATTER_PATTERN_DE = "dd.MM.yyyy";

  /*
   * Necessary dates
   */
  private final LocalDate currentDate;
  private final LocalDate minInvoiceDate;
  private final LocalDate maxInvoiceDate;

  /*
   * Day cell for limiting general date picker
   */
  private Callback <DatePicker, DateCell> dayCellFactory;

  /*
   * Day cell for limiting order reference date date picker
   */
  private Callback <DatePicker, DateCell> dayCellFactoryOrder;

  public InvoiceDateManager ()
  {

    currentDate = LocalDate.now ();
    minInvoiceDate = currentDate.minusYears (3L);
    maxInvoiceDate = currentDate.plusMonths (6L);

    initDayCellFactories ();

  }

  /*
   * Initialize day cell factories
   */
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

  /*
   * Return a new day cell factory where all dates after a given date are
   * disabled
   */
  public Callback <DatePicker, DateCell> getDayCellFectoryDisableAfter (final LocalDate date)
  {

    final Callback <DatePicker, DateCell> dcf = (final DatePicker dp) -> new DateCell ()
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

  /*
   * Return a new day cell factory where all dates before a given date are
   * disabled
   */
  public Callback <DatePicker, DateCell> getDayCellFectoryDisableBefore (final LocalDate date)
  {

    final Callback <DatePicker, DateCell> dcf = (final DatePicker dp) -> new DateCell ()
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

  /*
   * Comparing two dates and return whether the first date is after the second
   * one
   */
  public boolean isFromDateAfterToDate (final String from, final String to)
  {
    final LocalDate fromDate = LocalDate.parse (from);
    final LocalDate toDate = LocalDate.parse (to);

    return fromDate.isAfter (toDate);
  }

  /*
   * Getter / Setter
   */
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

  public Callback <DatePicker, DateCell> getDayCellFactoryForInvoiceDate ()
  {
    return dayCellFactory;
  }

  public Callback <DatePicker, DateCell> getDayCellFactoryOrder ()
  {
    return dayCellFactoryOrder;
  }

  public String getGermanFormatedDateFromLocalDateString (final String localDateAsString)
  {
    final String [] sCurDate = localDateAsString.split ("-");

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
}
