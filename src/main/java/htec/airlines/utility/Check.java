package htec.airlines.utility;

import java.util.List;
import java.util.Map;

import org.springframework.data.util.Pair;

import htec.airlines.bom.Airport;

public class Check {

	public static boolean isValueNotEmpty(String val) {
		return !val.equals("") 
				&& !val.equalsIgnoreCase("\\N") 
				&& !val.equals(null) 
				&& !val.equalsIgnoreCase("\"\"")
				&& !val.equals("");
	}
}
