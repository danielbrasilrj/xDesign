package uk.co.xdesign.munros.dto;

import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

public class MunroDTO  implements Serializable {
    private static final long serialVersionUID = -6974068521803009285L;

    public MunroDTO() {}

    public MunroDTO(String name, String heightMeter, MunroCategory munroCategory) {
        this.name = name;
        HeightMeter = heightMeter;
        if(munroCategory != null) {
            this.post1997 = munroCategory.name();
        }
    }

    @CsvBindByPosition(position = 0)
    private String runningNo;

    @CsvBindByPosition(position = 1)
    private String doBihNumber;

    @CsvBindByPosition(position = 2)
    private String streetmap;

    @CsvBindByPosition(position = 3)
    private String geograph;

    @CsvBindByPosition(position = 4)
    private String hillBagging;

    @CsvBindByPosition(position = 5)
    private String name;

    @CsvBindByPosition(position = 6)
    private String smcSection;

    @CsvBindByPosition(position = 7)
    private String rhbSection;

    @CsvBindByPosition(position = 8)
    private String section;

    @CsvBindByPosition(position = 9)
    private String HeightMeter;

    @CsvBindByPosition(position = 10)
    private String HeightFoot;

    @CsvBindByPosition(position = 11)
    private String Map1_50;

    @CsvBindByPosition(position = 12)
    private String Map1_25;

    @CsvBindByPosition(position = 13)
    private String GridRef;

    @CsvBindByPosition(position = 14)
    private String GridRefXY;

    @CsvBindByPosition(position = 15)
    private String xcoord;

    @CsvBindByPosition(position = 16)
    private String ycoord;

    @CsvBindByPosition(position = 17)
    private String _1891;

    @CsvBindByPosition(position = 18)
    private String _1921;

    @CsvBindByPosition(position = 19)
    private String _1933;

    @CsvBindByPosition(position = 20)
    private String _1953;

    @CsvBindByPosition(position = 21)
    private String _1969;

    @CsvBindByPosition(position = 22)
    private String _1974;

    @CsvBindByPosition(position = 23)
    private String _1981;

    @CsvBindByPosition(position = 24)
    private String _1984;

    @CsvBindByPosition(position = 25)
    private String _1990;

    @CsvBindByPosition(position = 26)
    private String _1997;

    @CsvBindByPosition(position = 27)
    private String post1997;

    @CsvBindByPosition(position = 28)
    private String comments;

    public String getRunningNo() {
        return runningNo;
    }

    public void setRunningNo(String runningNo) {
        this.runningNo = runningNo;
    }

    public String getDoBihNumber() {
        return doBihNumber;
    }

    public void setDoBihNumber(String doBihNumber) {
        this.doBihNumber = doBihNumber;
    }

    public String getStreetmap() {
        return streetmap;
    }

    public void setStreetmap(String streetmap) {
        this.streetmap = streetmap;
    }

    public String getGeograph() {
        return geograph;
    }

    public void setGeograph(String geograph) {
        this.geograph = geograph;
    }

    public String getHillBagging() {
        return hillBagging;
    }

    public void setHillBagging(String hillBagging) {
        this.hillBagging = hillBagging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmcSection() {
        return smcSection;
    }

    public void setSmcSection(String smcSection) {
        this.smcSection = smcSection;
    }

    public String getRhbSection() {
        return rhbSection;
    }

    public void setRhbSection(String rhbSection) {
        this.rhbSection = rhbSection;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getHeightMeter() {
        return HeightMeter;
    }

    public void setHeightMeter(String heightMeter) {
        HeightMeter = heightMeter;
    }

    public String getHeightFoot() {
        return HeightFoot;
    }

    public void setHeightFoot(String heightFoot) {
        HeightFoot = heightFoot;
    }

    public String getMap1_50() {
        return Map1_50;
    }

    public void setMap1_50(String map1_50) {
        Map1_50 = map1_50;
    }

    public String getMap1_25() {
        return Map1_25;
    }

    public void setMap1_25(String map1_25) {
        Map1_25 = map1_25;
    }

    public String getGridRef() {
        return GridRef;
    }

    public void setGridRef(String gridRef) {
        GridRef = gridRef;
    }

    public String getGridRefXY() {
        return GridRefXY;
    }

    public void setGridRefXY(String gridRefXY) {
        GridRefXY = gridRefXY;
    }

    public String getXcoord() {
        return xcoord;
    }

    public void setXcoord(String xcoord) {
        this.xcoord = xcoord;
    }

    public String getYcoord() {
        return ycoord;
    }

    public void setYcoord(String ycoord) {
        this.ycoord = ycoord;
    }

    public String get_1891() {
        return _1891;
    }

    public void set_1891(String _1891) {
        this._1891 = _1891;
    }

    public String get_1921() {
        return _1921;
    }

    public void set_1921(String _1921) {
        this._1921 = _1921;
    }

    public String get_1933() {
        return _1933;
    }

    public void set_1933(String _1933) {
        this._1933 = _1933;
    }

    public String get_1953() {
        return _1953;
    }

    public void set_1953(String _1953) {
        this._1953 = _1953;
    }

    public String get_1969() {
        return _1969;
    }

    public void set_1969(String _1969) {
        this._1969 = _1969;
    }

    public String get_1974() {
        return _1974;
    }

    public void set_1974(String _1974) {
        this._1974 = _1974;
    }

    public String get_1981() {
        return _1981;
    }

    public void set_1981(String _1981) {
        this._1981 = _1981;
    }

    public String get_1984() {
        return _1984;
    }

    public void set_1984(String _1984) {
        this._1984 = _1984;
    }

    public String get_1990() {
        return _1990;
    }

    public void set_1990(String _1990) {
        this._1990 = _1990;
    }

    public String get_1997() {
        return _1997;
    }

    public void set_1997(String _1997) {
        this._1997 = _1997;
    }

    public String getPost1997() {
        return post1997;
    }

    public void setPost1997(String post1997) {
        this.post1997 = post1997;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
