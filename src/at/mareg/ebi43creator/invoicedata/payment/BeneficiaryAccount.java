package at.mareg.ebi43creator.invoicedata.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "bic", "iban", "bankAccountOwner" })
public class BeneficiaryAccount
{

  // <eb:BeneficiaryAccount>
  // <eb:BIC>BKAUATWW</eb:BIC>
  // <eb:IBAN>AT611904300234573201</eb:IBAN>
  // <eb:BankAccountOwner>Max Mustermann</eb:BankAccountOwner>
  // </eb:BeneficiaryAccount>

  private String bic;
  private String iban;
  private String bankAccountOwner;

  public BeneficiaryAccount ()
  {}

  @XmlElement (name = "BIC", namespace = Data.DEFAULT_NAMESPACE)
  public String getBic ()
  {

    return bic;

  }

  public void setBic (String bic)
  {

    this.bic = bic;

  }

  @XmlElement (name = "IBAN", namespace = Data.DEFAULT_NAMESPACE)
  public String getIban ()
  {

    return iban;

  }

  public void setIban (String iban)
  {
    this.iban = iban;
  }

  @XmlElement (name = "BankAccountOwner", namespace = Data.DEFAULT_NAMESPACE)
  public String getBankAccountOwner ()
  {

    return bankAccountOwner;

  }

  public void setBankAccountOwner (String bankAccountOwner)
  {
    this.bankAccountOwner = bankAccountOwner;
  }

}
