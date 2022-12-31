package BiyDaalt;

import Lab2.MyChain;

public class Student {
    private String studentCode;
    private float gpa;
    private MyChain<Lessen> lessons;
    private Major major;

    public Student(String code, MyChain<Lessen> l, Major ma) {
        studentCode = code;
        lessons = l;
        major = ma;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public void AddLesson(Lessen l) {
        lessons.Add(l);
    }

    public MyChain<Lessen> getLesson() {
        return lessons;
    }

    public void RemoveLesson(Lessen l) {
        try {
            lessons.remove(lessons.indexOf(l));
        } catch (Exception e) {

        }
    }

    float GetGPA(float score) {
        if (96 <= score && score <= 100) {
            return 4;
        } else if (91 <= score && score <= 95) {
            return 3.7f;
        } else if (88 <= score && score <= 90) {
            return 3.4f;
        } else if (84 <= score && score <= 87) {
            return 3;
        } else if (81 <= score && score <= 83) {
            return 2.7f;
        } else if (78 <= score && score <= 80) {
            return 2.4f;
        } else if (74 <= score && score <= 77) {
            return 2;
        } else if (71 <= score && score <= 73) {
            return 1.7f;
        } else if (68 <= score && score <= 70) {
            return 1.3f;
        } else if (64 <= score && score <= 67) {
            return 1;
        } else if (61 <= score && score <= 63) {
            return 0.7f;
        } else {
            return 0;
        }
    }

    public float CalculateGpa() {
        float g = 0;
        for (int i = 0; i < lessons.size(); i++) {
            g += GetGPA(lessons.Get(i).getScore());
        }
        g /= lessons.size();
        System.out.println(g);
        gpa = g;
        return gpa;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public boolean CheckIsF() {
        CalculateGpa();
        int count = 0;
        for (int i = 0; i < lessons.size(); i++) {
            if (GetGPA(lessons.Get(i).getScore()) == 0) {
                count++;
            }
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        CalculateGpa();
        return studentCode + " / " + gpa;
    }

    public String GetAllStatus() {
        CalculateGpa();
        return studentCode + " / " + major.getMajorCode() + " / " + gpa;
    }

    public static Student FromString(String str) {
        System.out.println(str);
        String[] s = str.split("/");
        MyChain<Lessen> chain = new MyChain<>();
        for (int i = 2; i < s.length; i++) {
            chain.Add(Lessen.FromString(s[i]));
        }
        return new Student(s[0], chain, Major.FromString(s[1]));
    }

    public String ConvertString() {
        String str = studentCode + "/" + major.ConvertString();
        for (int i = 0; i < lessons.size(); i++) {
            str += ("/" + lessons.Get(i).ConvertString());
        }
        return str;
    }

    public String SubjectData(Subject sub) {
        Lessen les = null;
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.Get(i).getLearned().getSubjectCode().equals(sub.getSubjectCode())) {
                les = lessons.Get(i);
                break;
            }
        }
        return studentCode + " / " + les.getScore() + " / " + GetGPA(les.getScore());
    }
}
