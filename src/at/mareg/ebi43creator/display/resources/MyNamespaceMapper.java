package at.mareg.ebi43creator.display.resources;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class MyNamespaceMapper extends NamespacePrefixMapper
{

	private static final String EB_PREFIX = "ebi";
	private static final String EB_URI = Data.DEFAULT_NAMESPACE;

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
