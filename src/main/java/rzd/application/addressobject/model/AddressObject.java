package rzd.application.addressobject.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressObject {
    private String id;
    private String objectId;
    private String name;
    private String typeName;
    private String startDate;
    private String endDate;
    private String isActual;
    private String isActive;

    @XmlAttribute(name = "ID")
    public String getId() {
        return id;
    }

    @XmlAttribute(name = "OBJECTID")
    public String getObjectId() {
        return objectId;
    }

    @XmlAttribute(name = "NAME")
    public String getName() {
        return name;
    }

    @XmlAttribute(name = "TYPENAME")
    public String getTypeName() {
        return typeName;
    }

    @XmlAttribute(name = "STARTDATE")
    public String getStartDate() {
        return startDate;
    }

    @XmlAttribute(name = "ENDDATE")
    public String getEndDate() {
        return endDate;
    }

    @XmlAttribute(name = "ISACTUAL")
    public String getIsActual() {
        return isActual;
    }

    @XmlAttribute(name = "ISACTIVE")
    public String getIsActive() {
        return isActive;
    }
}