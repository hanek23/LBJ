package transformers;

import java.util.stream.Stream;

import gui.builders.LBJComboBoxStringBuilder;
import gui.forms.addcolumn.AddColumnForm;
import utils.FileUtils;

public class DataTypesLoader {

    public static void loadDataTypesValues(LBJComboBoxStringBuilder builder) {
        String content = FileUtils.getStringFromFileResource(AddColumnForm.class, "/liquibaseDataTypes.txt");
        Stream<String> lines = Stream.of(content.split(System.lineSeparator()));
        lines.forEach(builder::addItem);
    }
}
