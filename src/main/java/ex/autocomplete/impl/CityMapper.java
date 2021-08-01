package ex.autocomplete.impl;

import java.util.List;

import component.mybatisex.BasicMapper;
import ex.autocomplete.QueryParam;

public interface CityMapper extends BasicMapper<CityData> {

	List<CityData> queryCities(QueryParam param);
}
