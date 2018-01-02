package at.mareg.ebi43creator.invoicedata.address;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import at.mareg.ebi43creator.display.resources.Data;

@XmlType (propOrder = { "salutation", "name", "street", "town", "zip", "country", "phone", "email", "contact" })
public class Address
{

  private String salutation;
  private String name;
  private String street;
  private String town;
  private String zip;
  private String country;
  private String phone;
  private String email;
  private String contact;

  public Address ()
  {}

  public Address (final String salut,
                  final String n,
                  final String s,
                  final String t,
                  final String z,
                  final String c,
                  final String p,
                  final String e,
                  final String co)
  {

    salutation = salut;
    name = n;
    street = s;
    town = t;
    zip = z;
    country = c;
    phone = p;
    email = e;
    contact = co;

  }

  @XmlElement (name = "Salutation", namespace = Data.DEFAULT_NAMESPACE)
  public String getSalutation ()
  {
    return salutation;
  }

  @SuppressWarnings ("hiding")
  public void setSalutation (final String salutation)
  {
    this.salutation = salutation;
  }

  @XmlElement (name = "Name", namespace = Data.DEFAULT_NAMESPACE)
  public String getName ()
  {
    return name;
  }

  @SuppressWarnings ("hiding")
  public void setName (final String name)
  {
    this.name = name;
  }

  @XmlElement (name = "Street", namespace = Data.DEFAULT_NAMESPACE)
  public String getStreet ()
  {
    return street;
  }

  @SuppressWarnings ("hiding")
  public void setStreet (final String street)
  {
    this.street = street;
  }

  @XmlElement (name = "Town", namespace = Data.DEFAULT_NAMESPACE)
  public String getTown ()
  {
    return town;
  }

  @SuppressWarnings ("hiding")
  public void setTown (final String town)
  {
    this.town = town;
  }

  @XmlElement (name = "ZIP", namespace = Data.DEFAULT_NAMESPACE)
  public String getZip ()
  {
    return zip;
  }

  @SuppressWarnings ("hiding")
  public void setZip (final String zip)
  {
    this.zip = zip;
  }

  @XmlElement (name = "Country", namespace = Data.DEFAULT_NAMESPACE)
  public String getCountry ()
  {
    return country;
  }

  @SuppressWarnings ("hiding")
  public void setCountry (final String country)
  {
    this.country = country;
  }

  @XmlElement (name = "Phone", namespace = Data.DEFAULT_NAMESPACE)
  public String getPhone ()
  {
    return phone;
  }

  @SuppressWarnings ("hiding")
  public void setPhone (final String phone)
  {
    this.phone = phone;
  }

  @XmlElement (name = "Email", namespace = Data.DEFAULT_NAMESPACE)
  public String getEmail ()
  {
    return email;
  }

  @SuppressWarnings ("hiding")
  public void setEmail (final String email)
  {
    this.email = email;
  }

  @XmlElement (name = "Contact", namespace = Data.DEFAULT_NAMESPACE)
  public String getContact ()
  {
    return contact;
  }

  @SuppressWarnings ("hiding")
  public void setContact (final String contact)
  {
    this.contact = contact;
  }

}
