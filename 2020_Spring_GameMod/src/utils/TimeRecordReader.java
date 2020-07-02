package utils;

import object.TimeModeRecord;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.text.ParseException;
import java.util.List;

/**
 * Created by lero on 2020/7/2.
 */
public class TimeRecordReader {
    private String filename;
    private TimeModeRecord[] records;

    public TimeRecordReader(int mapId) throws Exception,ParseException {
        filename = "time_mode_xml/"+String.valueOf(mapId) + "_time_mode.xml";
        System.out.println(filename);
        SAXBuilder builder = new SAXBuilder();

        Document document = builder.build(filename);

        Element root = document.getRootElement();

        List list = root.getChildren("record");
        records = new TimeModeRecord[list.size()];



        for (int i = 0; i<list.size(); i++) {
            Element record = (Element) list.get(i);
            String name = record.getChildText("name");
            //System.out.println(name);
            int second = Integer.parseInt(record.getChildText("second"));
            String date = record.getChildText("date");
            // System.out.println(record.getChildText("date"));
            records[i] = new TimeModeRecord(name,mapId,second,date);
            //System.out.println(records[i]);
        }

    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public TimeModeRecord[] getRecords() {
        return records;
    }

    public void setRecords(TimeModeRecord[] records) {
        this.records = records;
    }
}
