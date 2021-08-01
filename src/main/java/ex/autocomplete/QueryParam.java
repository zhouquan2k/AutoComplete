package ex.autocomplete;

import lombok.Data;

@Data
public class QueryParam {

	String q;
	double latitude;
	double longitude;
	
	//TODO
	String lang;
	String apiKey;
	
	public QueryParam(String q,double latitude,double longitude) {
		this.q=q;
		this.latitude=latitude;
		this.longitude=longitude;
	}
}
