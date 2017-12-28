package at.mareg.ebi43creator.invoicedata.enums;

public enum EBiller
{
	SUPPLIERID ("supplierid", "Lieferantennummer*"), 
	NAME ("billername", "Name*"), 
	STREET ("billerstreet", "Strasse*"), 
	ZIP ("billerzip", "PLZ*"), 
	TOWN ("billertown", "Stadt*"), 
	COUNTRY ("billercountry", "Land*"), 
	EMAIL ("billeremail", "E-Mail*"), 
	PHONE ("billerphone", "Telefon"), 
	CONTACT ("billercontact", "Kontaktperson");

	private String idValue;
	private String labelText;

	private EBiller (final String id, final String lt)
	{
		idValue = id;
		labelText = lt;
	}

	public final String getID ()
	{
		return idValue;
	}

	public String getLabelText ()
	{
		return labelText;
	}
}
