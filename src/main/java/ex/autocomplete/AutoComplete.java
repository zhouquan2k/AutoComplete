package ex.autocomplete;

import org.springframework.stereotype.Service;

@Service
public interface AutoComplete {

	QueryResult query(QueryParam param);
	
	void initData(String originDataFile) throws Exception;
}
