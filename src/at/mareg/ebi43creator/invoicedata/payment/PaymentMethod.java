package at.mareg.ebi43creator.invoicedata.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "universalBankTransaction" })
public class PaymentMethod
{

  private UniversalBankTransaction universalBankTransaction;

  public PaymentMethod ()
  {

    universalBankTransaction = new UniversalBankTransaction ();

  }

  @XmlElement (name = "UniversalBankTransaction", namespace = Data.DEFAULT_NAMESPACE)
  public UniversalBankTransaction getUniversalBankTransaction ()
  {

    return universalBankTransaction;

  }

  public void setUniversalBankTransaction (UniversalBankTransaction universalBankTransaction)
  {

    this.universalBankTransaction = universalBankTransaction;

  }
}
