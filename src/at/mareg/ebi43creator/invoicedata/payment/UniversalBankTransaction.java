package at.mareg.ebi43creator.invoicedata.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "beneficiaryAccount" })
public class UniversalBankTransaction
{
  /**
   * Class to save the beneficiary account
   *
   * @author Martin Regitnig
   */

  /*
   * Data variables
   */
  private BeneficiaryAccount beneficiaryAccount;

  public UniversalBankTransaction ()
  {
    beneficiaryAccount = new BeneficiaryAccount ();
  }

  /*
   * Getter / Setter
   */
  @XmlElement (name = "BeneficiaryAccount", namespace = Data.DEFAULT_NAMESPACE)
  public BeneficiaryAccount getBeneficiaryAccount ()
  {
    return beneficiaryAccount;
  }

  public void setBeneficiaryAccount (final BeneficiaryAccount beneficiaryAccount)
  {
    this.beneficiaryAccount = beneficiaryAccount;
  }

}
