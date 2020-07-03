package utils;

import object.TimeModeRecord;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileOutputStream;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by 77357 on 2020/7/4.
 */
public class TimeRecordWriterTest {
    @Test
    public void write() throws Exception {
        TimeModeRecord[] records = new TimeModeRecord[4];
        records[0] = new TimeModeRecord("lero",11,44);
        records[1] = new TimeModeRecord("lypl",11,55);
        records[2] = new TimeModeRecord("goose",11,66);
        records[3] = new TimeModeRecord("fish",11,77);
        Arrays.sort(records);
        //如果记录数组为空，则无需再去写入
        if(records.length == 0)
            return;
        //根据记录中的关卡编号，得到待重写的xml文件名
        String filename = String.valueOf(records[0].getMapId());
        filename = "time_mode_xml/"+filename + "_time_mode.xml";

        //将待写入的数据存入document文件中
        Document document = new Document();
        //xml文件的根节点为list
        Element list = new Element("list");
        //list元素加入到document下
        document.addContent(list);

        //遍历待写入文件的记录数组，根据每条记录的值，加入到record节点下
        //作为list的子结点加入
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

        //设置写入的格式，编码和缩进
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");
        format.setIndent("    ");

        //写入xml文件
        XMLOutputter out = new XMLOutputter(format);
        out.output(document, new FileOutputStream(filename));
    }
}
