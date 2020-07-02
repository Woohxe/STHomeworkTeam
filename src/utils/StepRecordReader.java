package utils;

import object.StepModeRecord;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.text.ParseException;
import java.util.List;

/**
 * Created by lero on 2020/7/2.
 */
public class StepRecordReader {
    private String filename;
    private StepModeRecord[] records;

    public StepRecordReader(int mapId) throws Exception,ParseException {
        filename = String.valueOf(mapId) + "_step_mode.xml";
        System.out.println(filename);
        SAXBuilder builder = new SAXBuilder();

        Document document = builder.build(filename);

        Element root = document.getRootElement();

        List list = root.getChildren("record");
        records = new StepModeRecord[list.size()];



        for (int i = 0; i<list.size(); i++) {
            Element record = (Element) list.get(i);
            String name = record.getChildText("name");
            //System.out.println(name);
            int step = Integer.parseInt(record.getChildText("step"));
            String date = record.getChildText("date");
           // System.out.println(record.getChildText("date"));
            records[i] = new StepModeRecord(name,mapId,step,date);
            //System.out.println(records[i]);
        }


    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public StepModeRecord[] getRecords() {
        return records;
    }

    public void setRecords(StepModeRecord[] records) {
        this.records = records;
    }
}
