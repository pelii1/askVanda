package hu.innobyte.question;

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
public class Answer {
    private String id;
    private String answer;
    private int confidence;

    @Tolerate
    public Answer() {

    }
}
