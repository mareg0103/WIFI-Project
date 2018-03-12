package at.mareg.ebi43creator.display.resources;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class MyNamespaceMapper extends NamespacePrefixMapper
{
  /**
   * Mapping elements to a prefix in XML
   *
   * @author Martin Regitnig
   */

  /*
   * Preferred prefix to return
   */
  private static final String EB_PREFIX = "ebi";

  /*
   * Set default namespace
   */
  private static final String EB_URI = Data.DEFAULT_NAMESPACE;

  /*
   * Getter
   */
  @Override
  public String getPreferredPrefix (final String namespace, final String prefix, final boolean required)
  {
    if (EB_URI.equals (namespace))
    {
      return EB_PREFIX;
    }

    return prefix;

  }

}
