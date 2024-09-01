package testClass;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Setter
public class Person {

    private String name;
    private String age;

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "age")
    public String getAge() {
        return age;
    }
}
