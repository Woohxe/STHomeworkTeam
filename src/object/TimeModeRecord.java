package object;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计时模式下的记录对象，实现Comparable接口以互相比较
 * Created by lero on 2020/7/2.
 */
public class TimeModeRecord implements Comparable<TimeModeRecord> {
    //用户名
    private String name;
    //关卡编号
    private int MapId;
    //所用时长
    private int second;
    //提交时间
    private String curDate;

    /**
     * 构造方法
     * @param name
     * @param mapId
     * @param second
     * @param curDate
     */
    public TimeModeRecord(String name, int mapId, int second, String curDate) {
        this.name = name;
        MapId = mapId;
        this.second = second;
        this.curDate = curDate;
    }

    /**
     * 提交时生成的记录，提交时间自动记为当前时间
     * @param name
     * @param mapId
     * @param second
     */
    public TimeModeRecord(String name, int mapId, int second) {
        this.name = name;
        MapId = mapId;

        //获取当前时间，并转为形如yyyy-MM-dd HH:mm:ss的字符串
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        //System.out.println(dateString);

        curDate = dateString;

        //System.out.println(curDate);
        this.second = second;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMapId() {
        return MapId;
    }

    public void setMapId(int mapId) {
        MapId = mapId;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    /**
     * 比较原则为用时短的在前
     * @param record
     * @return
     */
    public int compareTo(TimeModeRecord record) {
        if(this.second > record.second) {
            return 1;
        }
        else if(this.second == record.second) {
            return 0;
        }
        else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return
                "我叫" + name +
                ",图" + MapId +
                "中用了" + second +
                "秒," + curDate
                ;
    }
}
