package ex.autocomplete;

import java.util.List;
import java.util.Vector;

import lombok.Data;

@Data
public class QueryResult {
	List<City> suggestions=new Vector<City>();
}
