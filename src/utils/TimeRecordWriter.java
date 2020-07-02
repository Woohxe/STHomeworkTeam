package utils;

import object.TimeModeRecord;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 写入排行榜文件的工具类
 * Created by lero on 2020/7/2.
 */
public class TimeRecordWriter {

    //待写入文件的记录
    private TimeModeRecord[] records;

    /**
     * 构建函数，参数为待写入的记录数组。
     * @param records
     */
    public TimeRecordWriter(TimeModeRecord[] records) {
        this.records = records;
    }

    /**
     * 将记录写入对应的xml文件
     * @throws IOException
     */
    public void write() throws IOException {

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
