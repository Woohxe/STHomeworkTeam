package utils;

import object.TimeModeRecord;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.text.ParseException;
import java.util.List;

/**
 * 读取关卡排行榜的工具类
 * Created by lero on 2020/7/2.
 */
public class TimeRecordReader {

    //记录关卡排行榜的文件名
    private String filename;
    //对应文件下的所有记录
    private TimeModeRecord[] records;

    /**
     * 根据关卡编号获取文件，并将所有记录存入records数组
     * @param mapId
     * @throws Exception
     * @throws ParseException
     */
    public TimeRecordReader(int mapId) throws Exception,ParseException {

        //记录的文件名
        filename = "time_mode_xml/"+String.valueOf(mapId) + "_time_mode.xml";
        System.out.println(filename);
        //SAX解析器用于解析xml文件
        SAXBuilder builder = new SAXBuilder();

        //读入对应的文件，存在document中
        Document document = builder.build(filename);

        //获取xml中的根节点
        Element root = document.getRootElement();

        //以列表的形式获取记录
        List list = root.getChildren("record");

        //构建相应长度的数组
        records = new TimeModeRecord[list.size()];


        //根据xml文件中每条记录的属性值构建TimeModeRecord对象
        //写入records数组
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
