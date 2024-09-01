import org.junit.Test;
import rzd.application.addressobject.Main;
import rzd.application.addressobject.model.AddressObject;
import rzd.application.addressobject.model.AddressObjects;
import testClass.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class MainTest {

    @Test
    public void convertStringToDate_shouldReturnDateWhenValidStringsIsPassed() {
        //given
        String validDate = "2024-09-01";

        //when
        Optional<LocalDate> result = Main.convertStringToDate(validDate);

        //then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(validDate);
    }

    @Test
    public void convertStringToDate_shouldReturnEmptyOptionalWhenInvalidStringIsPassed() {
        //given
        String invalidString = "2024-09-32";

        //when
        Optional<LocalDate> result = Main.convertStringToDate(invalidString);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void convertStringToDate_shouldReturnEmptyOptionalWhenNullStringIsPassed() {
        //given
        String nullString = null;

        //when
        Optional<LocalDate> result = Main.convertStringToDate(nullString);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void unMarshallerXML_successfullyExecution() {
        //given
        String pathTestFile = "test.xml";

        //when
        Optional<Person> result = Main.unMarshallerXML(pathTestFile, Person.class);

        //then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Max");
        assertThat(result.get().getAge()).isEqualTo("10");
    }

    @Test
    public void unMarshallerXML_shouldReturnEmptyOptionalWhenFileInvalid() {
        //given
        String invalidFilePath = "invalidFile.xml";

        //when
        Optional<Person> result = Main.unMarshallerXML(invalidFilePath, Person.class);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void unMarshallerXML_shouldReturnEmptyOptionalWhenClassInvalid() {
        //given
        String filePath = "test.xml";

        //when
        Optional<InvalidClassTest> result = Main.unMarshallerXML(filePath, InvalidClassTest.class);

        //then
        assertThat(result).isEmpty();
    }

    private static class InvalidClassTest {
    }

    @Test
    public void findObjectsByTypeAndName_shouldFindObjectsWithinDateRange() {
        //given
        List<String> listObjectIds = List.of("1422396", "1450759", "1449192");
        LocalDate dateTest = LocalDate.of(2010, 1, 1);

        AddressObject obj1 = new AddressObject("1", "1422396", "name1", "typeName1", "2010-01-01", "2010-02-01", "1", "1");
        AddressObject obj2 = new AddressObject("2", "1450759", "name2", "typeName2", "2009-01-01", "2010-02-01", "1", "1");
        AddressObject obj3 = new AddressObject("3", "1449192", "name3", "typeName3", "2012-01-01", "2012-02-01", "1", "1");

        AddressObjects addressObjects = new AddressObjects();
        addressObjects.setListObject(List.of(obj1, obj2, obj3));

        //when
        List<AddressObject> listResult = Main.findObjectsByTypeAndName(listObjectIds, dateTest, addressObjects);

        //then
        assertThat(listResult).hasSize(2);
        assertThat(listResult).containsExactlyInAnyOrder(obj1, obj2);
    }

    @Test
    public void findObjectsByTypeAndName_shouldNotFindObjects() {
        //given
        List<String> listObjectIds = List.of("1422396");
        LocalDate dateTest = LocalDate.of(2011, 1, 1);

        AddressObject obj1 = new AddressObject("1", "1422396", "name1", "typeName1", "2010-01-01", "2010-02-01", "1", "1");

        AddressObjects addressObjects = new AddressObjects();
        addressObjects.setListObject(List.of(obj1));

        //when
        List<AddressObject> listResult = Main.findObjectsByTypeAndName(listObjectIds, dateTest, addressObjects);

        //then
        assertThat(listResult).isEmpty();
    }

    @Test
    public void findObjectsByTypeAndName_shouldHandleEmptyObjectList() {
        //given
        List<String> listObjectIds = List.of("1422396");
        LocalDate dateTest = LocalDate.of(2011, 1, 1);

        AddressObject obj1 = new AddressObject("1", "1422396", "name1", "typeName1", "2010-01-01", "2010-02-01", "1", "1");

        AddressObjects addressObjects = new AddressObjects();
        addressObjects.setListObject(new ArrayList<>());

        //when
        List<AddressObject> listResult = Main.findObjectsByTypeAndName(listObjectIds, dateTest, addressObjects);

        //then
        assertThat(listResult).isEmpty();
    }

}
