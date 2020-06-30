package commons;

import person.Person;
import person.PersonCell;

/**
 * Created by lero on 2020/6/30.
 */
public class GameStatus {
    private GameConfiguration gameConfiguration;
    private Person[] persons;
    private int step;
    private boolean win;
    private String username;

    public GameStatus(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
        persons = new Person[10];
        step = 0;
        win = false;

        int BASELENGTH = gameConfiguration.getBASELENGTH();
        PersonCell[] personCells = new PersonCell[10];
        personCells[0] = new PersonCell(BASELENGTH,0,BASELENGTH*2, BASELENGTH*2,0,1);
        personCells[1] = new PersonCell(0, 0, BASELENGTH, BASELENGTH*2, 0,0);
        personCells[2] = new PersonCell(BASELENGTH*3,0,  BASELENGTH, BASELENGTH*2, 0,3);
        personCells[3] = new PersonCell(0,BASELENGTH*2 , BASELENGTH, BASELENGTH*2, 2,0);
        personCells[4] = new PersonCell(BASELENGTH,BASELENGTH*2, BASELENGTH*2, BASELENGTH,2,1);
        personCells[5] = new PersonCell( BASELENGTH*3, BASELENGTH*2, BASELENGTH, BASELENGTH*2, 2,3);
        personCells[6] = new PersonCell(BASELENGTH, BASELENGTH*3, BASELENGTH, BASELENGTH, 3,1);
        personCells[7] = new PersonCell(BASELENGTH*2, BASELENGTH*3, BASELENGTH, BASELENGTH, 3,2);
        personCells[8] = new PersonCell(0, BASELENGTH*4, BASELENGTH, BASELENGTH, 4,0);
        personCells[9] = new PersonCell(BASELENGTH*3, BASELENGTH*4, BASELENGTH, BASELENGTH, 4,3);

        persons[0] = new Person(personCells[0],"曹操");
        persons[1] = new Person(personCells[1], "张辽");
        persons[2] = new Person(personCells[2], "曹仁");
        persons[3] = new Person(personCells[3], "张飞");
        persons[4] = new Person(personCells[4], "关羽");
        persons[5] = new Person(personCells[5], "刘备");
        persons[6] = new Person(personCells[6], "兵1");
        persons[7] = new Person(personCells[7], "兵2");
        persons[8] = new Person(personCells[8], "兵3");
        persons[9] = new Person(personCells[9], "兵4");




    }

    public GameConfiguration getGameConfiguration() {
        return gameConfiguration;
    }

    public void setGameConfiguration(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
