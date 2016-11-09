package hu.innobyte.database;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Getter
@Setter
@Builder
public class Sentence {
	private String id;
	private double confidence;
	private String sentence;
	
	@Tolerate
	public Sentence() {
		
	}
}
