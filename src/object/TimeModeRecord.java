package object;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lero on 2020/7/2.
 */
public class TimeModeRecord implements Comparable<TimeModeRecord> {
    private String name;
    private int MapId;
    private int second;
    private String curDate;

    public TimeModeRecord(String name, int mapId, int second, String curDate) {
        this.name = name;
        MapId = mapId;
        this.second = second;
        this.curDate = curDate;
    }

    public TimeModeRecord(String name, int mapId, int second) {
        this.name = name;
        MapId = mapId;

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
