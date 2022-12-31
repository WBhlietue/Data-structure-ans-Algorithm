package BiyDaalt;

public class Major {
    private String majorCode;
    private String majorName;

    public Major(String c, String n) {
        majorCode = c;
        majorName = n;
    }

    public String getMajorName() {
        return majorName;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return majorCode + " / " + majorName;
    }

    public String ConvertString() {
        return majorCode + "`" + majorName;
    }

    public static Major FromString(String str) {
        String[] s = str.split("`");
        return new Major(s[0], s[1]);
    }
}
