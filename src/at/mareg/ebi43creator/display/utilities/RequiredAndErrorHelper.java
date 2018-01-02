package at.mareg.ebi43creator.display.utilities;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class RequiredAndErrorHelper
{

  private static final Map <String, Integer> requiredMap = new TreeMap <> ();

  private RequiredAndErrorHelper ()
  {}

  public static void incrementTabCount (final String tabID)
  {

    if (requiredMap.get (tabID) == null)
      requiredMap.put (tabID, Integer.valueOf (0));

    requiredMap.replace (tabID, Integer.valueOf (requiredMap.get (tabID).intValue () + 1));

  }

  public static void decrementTabCount (final String tabID)
  {
    if (requiredMap.get (tabID) != null)
      requiredMap.replace (tabID, Integer.valueOf (requiredMap.get (tabID).intValue () - 1));

    if (requiredMap.get (tabID).intValue () == 0)
      requiredMap.remove (tabID);
  }

  public static void showRequiredMap ()
  {

    for (final Entry <String, Integer> e : requiredMap.entrySet ())
    {

      System.out.println (e.getKey () + ": " + e.getValue ().intValue () + " Einträge");

    }

  }

}
