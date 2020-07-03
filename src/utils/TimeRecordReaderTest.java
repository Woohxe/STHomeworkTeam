package utils;

import object.TimeModeRecord;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 77357 on 2020/7/4.
 */
public class TimeRecordReaderTest {
    int mapId = 11;
    String filename = "time_mode_xml/"+mapId + "_time_mode.xml";
    //SAX解析器用于解析xml文件
    SAXBuilder builder = new SAXBuilder();
    //读入对应的文件，存在document中
    Document document = builder.build(filename);
    //获取xml中的根节点
    Element root = document.getRootElement();
    //以列表的形式获取记录
    List list = root.getChildren("record");
    TimeModeRecord[] records = new TimeModeRecord[list.size()];

    public TimeRecordReaderTest() throws JDOMException, IOException {
    }

    @Test
    public void getFilename() throws Exception {
        System.out.println(filename);
    }

    @Test
    public void getRecords() throws Exception {
        for (int i = 0; i<list.size(); i++) {
            Element record = (Element) list.get(i);
            String name = record.getChildText("name");
            //System.out.println(name);
            int second = Integer.parseInt(record.getChildText("second"));
            String date = record.getChildText("date");
            // System.out.println(record.getChildText("date"));
            System.out.println(records[i] = new TimeModeRecord(name,mapId,second,date));
            //System.out.println(records[i]);
        }
    }

}
