package utils;

import object.TimeModeRecord;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lero on 2020/7/2.
 */
public class TimeRecordWriter {
    private TimeModeRecord[] records;

    public TimeRecordWriter(TimeModeRecord[] records) {
        this.records = records;
    }

    public void write() throws IOException {
        if(records.length == 0)
            return;
        String filename = String.valueOf(records[0].getMapId());
        filename = "time_mode_xml/"+filename + "_time_mode.xml";

        Document document = new Document();
        Element list = new Element("list");
        document.addContent(list);

        for(int i = 0; i<records.length; i++) {
            //System.out.println(records[i]);

            Element record = new Element("record");
            list.addContent(record);

            Element name = new Element("name");
            //System.out.println(records);
            //System.out.println(records[i].getName());
            name.setText(records[i].getName());
            record.addContent(name);

            Element second = new Element("second");
            second.setText(String.valueOf(records[i].getSecond()));
            record.addContent(second);

            Element date = new Element("date");
            date.setText(records[i].getCurDate());
            record.addContent(date);
        }

        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");
        format.setIndent("    ");

        XMLOutputter out = new XMLOutputter(format);
        out.output(document, new FileOutputStream(filename));
    }
}
