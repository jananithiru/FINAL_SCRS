package scrs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UtilMethods { //TODO: Rename this class after checking with people 
	
	public static List<ArrayList<String>> convertObjListToStringList(List<ArrayList<Object>> objList) {
		List<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

		Iterator<ArrayList<Object>> outerIter = objList.iterator();
		while (outerIter.hasNext()) {
			ArrayList<String> metaRes = new ArrayList<String>();
			Iterator<Object> innerIter = outerIter.next().iterator();
			while (innerIter.hasNext()) {
				metaRes.add(innerIter.next().toString());
			}
			result.add(metaRes);
		}
		return result;
	}

}
