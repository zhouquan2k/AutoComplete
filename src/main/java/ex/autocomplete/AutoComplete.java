package ex.autocomplete;

public interface AutoComplete {

	QueryResult query(QueryParam param);
	
	void initData(String originDataFile) throws Exception;
}
