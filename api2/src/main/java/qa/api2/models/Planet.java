package qa.api2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Planet {
	@JsonProperty("name")
	private String planetName;
	@JsonProperty("rotation_period")
	private String rotationPeriod;
	@JsonProperty("orbital_period")
	private String orbitalPeriod;
}
