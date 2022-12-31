package BiyDaalt;


public class Subject {
    private String subjectCode;
    private String subjectName;
    private int credit;

    public Subject(String c, String n, int cre) {
        subjectCode = c;
        subjectName = n;
        credit = cre;
    }

    public int getCredit() {
        return credit;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return subjectCode + " / " + subjectName + " / " + credit;
    }

    public static Subject FromString(String str) {
        System.out.println(str);
        String[] s = str.split("_");
        System.out.println(s.length);
        int credit = Integer.parseInt(s[2]);
        return new Subject(s[0], s[1], credit);
    }

    public String ConvertString() {
        return subjectCode + "_" + subjectName + "_" + credit;
    }
}
