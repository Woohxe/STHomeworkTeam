package object;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lero on 2020/7/2.
 */
public class StepModeRecord implements Comparable<StepModeRecord> {

    private String name;
    private int MapId;
    private int step;
    private String curDate;

    public StepModeRecord(String name, int mapId, int step, String curDate) {
        this.name = name;
        MapId = mapId;
        this.step = step;
        this.curDate = curDate;
    }

    public StepModeRecord(String name, int mapId, int step) throws ParseException{
        this.name = name;
        MapId = mapId;

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        //System.out.println(dateString);

        curDate = dateString;

        //System.out.println(curDate);

        this.step = step;
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

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }


    public int compareTo(StepModeRecord record) {
        if(this.step > record.step) {
            return 1;
        }
        else if(this.step == record.step) {
            return 0;
        }
        else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "TimeModeRecord{" +
                "我叫" + name +
                ",图" + MapId +
                "中用了" + step +
                "步," + curDate +
                '}';
    }



}
