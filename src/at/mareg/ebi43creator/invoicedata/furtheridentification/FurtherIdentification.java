package at.mareg.ebi43creator.invoicedata.furtheridentification;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "identificationType", "furtherIdentification" })
public class FurtherIdentification
{

  private String identificationType;
  private String furtherIdentification;
  
  protected FurtherIdentification ()
  { }

  public FurtherIdentification (final String identification, final String type)
  {

    identificationType = type;
    furtherIdentification = identification;

  }

  @XmlAttribute (name = "IdentificationType", namespace = Data.DEFAULT_NAMESPACE)
  public String getIdentificationType ()
  {
    return identificationType;
  }

  @SuppressWarnings ("hiding")
  public void setIdentificationType (final String identificationType)
  {
    this.identificationType = identificationType;
  }

  @XmlValue
  public String getFurtherIdentification ()
  {
    return furtherIdentification;
  }

  @SuppressWarnings ("hiding")
  public void setFurtherIdentification (final String furtherIdentification)
  {
    this.furtherIdentification = furtherIdentification;
  }

}
