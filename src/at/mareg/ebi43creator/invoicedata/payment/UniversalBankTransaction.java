package at.mareg.ebi43creator.invoicedata.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "beneficiaryAccount" })
public class UniversalBankTransaction
{

	private BeneficiaryAccount beneficiaryAccount;

	public UniversalBankTransaction ()
	{

		beneficiaryAccount = new BeneficiaryAccount ();

	}

	@XmlElement (name = "BeneficiaryAccount", namespace = Data.DEFAULT_NAMESPACE)
	public BeneficiaryAccount getBeneficiaryAccount ()
	{

		return beneficiaryAccount;

	}

	public void setBeneficiaryAccount (BeneficiaryAccount beneficiaryAccount)
	{

		this.beneficiaryAccount = beneficiaryAccount;

	}

}
