package at.mareg.ebi43creator.invoicedata.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "bic", "iban", "bankAccountOwner" })
public class BeneficiaryAccount
{
  /**
   * Class to save payment data
   *
   * @author Martin Regitnig
   */

  /*
   * Data variables
   */
  private String bic;
  private String iban;
  private String bankAccountOwner;

  public BeneficiaryAccount ()
  {}

  /*
   * Getter / Setter
   */
  @XmlElement (name = "BIC", namespace = Data.DEFAULT_NAMESPACE)
  public String getBic ()
  {

    return bic;

  }

  public void setBic (final String bic)
  {

    this.bic = bic;

  }

  @XmlElement (name = "IBAN", namespace = Data.DEFAULT_NAMESPACE)
  public String getIban ()
  {

    return iban;

  }

  public void setIban (final String iban)
  {
    this.iban = iban;
  }

  @XmlElement (name = "BankAccountOwner", namespace = Data.DEFAULT_NAMESPACE)
  public String getBankAccountOwner ()
  {

    return bankAccountOwner;

  }

  public void setBankAccountOwner (final String bankAccountOwner)
  {
    this.bankAccountOwner = bankAccountOwner;
  }

}
