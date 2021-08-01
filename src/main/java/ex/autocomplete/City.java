package ex.autocomplete;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
	String name;
	double latitude;
	double longitude;
	double score;
}
