package rzd.application.addressobject.model;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@XmlRootElement(name = "ADDRESSOBJECTS")
public class AddressObjects {
    private List<AddressObject> listObject;

    @XmlElement(name = "OBJECT")
    public List<AddressObject> getListObject() {
        return listObject;
    }
}
