package rzd.application.addressobject;

import rzd.application.addressobject.model.AddressObject;
import rzd.application.addressobject.model.AddressObjects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws JAXBException {

        String filePath = "AS_ADDR_OBJ.XML";
        List<String> listObjectIds = List.of("1422396", "1450759", "1449192", "1451562");
        String searchDate = "2010-01-01";

        Optional<LocalDate> optDateObj = convertStringToDate(searchDate);
        Optional<AddressObjects> optAddressObjects = unMarshallerXML(filePath, AddressObjects.class);

        if (optAddressObjects.isPresent() && optDateObj.isPresent()) {
            showConsole(findObjectsByTypeAndName(listObjectIds, optDateObj.get(), optAddressObjects.get()));
        } else {
            System.out.println("isEmpty");
        }

    }

    public static void showConsole(List<AddressObject> objectsByTypeAndName) {
        objectsByTypeAndName.forEach(obj -> {
            System.out.println(obj.getId() + " : " + obj.getTypeName() + " " + obj.getName());
        });
    }

    public static List<AddressObject> findObjectsByTypeAndName(List<String> listObjectIds, LocalDate dateObj,
                                                               AddressObjects addressObjects) {

        List<AddressObject> listAddressObject = new ArrayList<>();
        for (AddressObject obj : addressObjects.getListObject()) {
            if (listObjectIds.contains(obj.getObjectId())) {

                Optional<LocalDate> optStartDate = convertStringToDate(obj.getStartDate());
                Optional<LocalDate> optEndDate = convertStringToDate(obj.getEndDate());

                if (optStartDate.isPresent() && optEndDate.isPresent()) {
                    if (!dateObj.isBefore(optStartDate.get()) && !dateObj.isAfter(optEndDate.get())) {
                        listAddressObject.add(obj);
                    }
                }
            }
        }
        return listAddressObject;
    }

    public static <T> Optional<T> unMarshallerXML(String filePath, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return Optional.ofNullable(clazz.cast(unmarshaller.unmarshal(new File(filePath))));
        } catch (JAXBException | IllegalArgumentException exception) {
            System.out.println("Ошибка обработки JAXB " + exception.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<LocalDate> convertStringToDate(String searchDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return Optional.of(LocalDate.parse(searchDate, dateFormatter));
        } catch (DateTimeParseException | NullPointerException exception) {
            System.out.println("Строка не соответсвтует формату: " + exception.getMessage());
            return Optional.empty();
        }
    }
}