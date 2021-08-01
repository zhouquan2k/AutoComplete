package ex.autocomplete.impl.sql;

import java.util.List;

//import org.apache.ibatis.annotations.Param;


import component.mybatisex.BasicMapper;
import ex.autocomplete.QueryParam;

public interface CityMapper extends BasicMapper<CityData> {

	List<CityData> queryCities(QueryParam param);
}
