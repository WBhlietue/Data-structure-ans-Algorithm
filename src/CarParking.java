import java.io.*;

import Lab2.MyChain;
import Lab3.MyStackT;
import UITools.UI;
import UITools.UILabel;

public class CarParking extends UI {

    MyChain<Car> cars;
    MyStackT<Car> park;
    MyStackT<Car> backPark;
    MyChain<CarProgress> progress;
    int size;

    UILabel parkTxt;
    UILabel backTxt;
    UILabel parkCarTxt;
    UILabel backCarTxt;
    UILabel pro;
    UILabel p;

    @Override
    protected void MainUI() {
        parkTxt = new UILabel(panel, 100, 100, 200, 0, "Park");
        backTxt = new UILabel(panel, 100, 100, 800, 0, "BackPark");
        parkCarTxt = new UILabel(panel, 100, 600, 200, 100, "");
        backCarTxt = new UILabel(panel, 100, 600, 800, 100, "");
        new UILabel(panel, 100, 600, 150, 100, "1<br>2<br>3<br>4<br>5<br>6<br>7<br>8<br>9<br>10", 1).SetTop();
        parkCarTxt.SetTop();
        backCarTxt.SetTop();
        pro = new UILabel(panel, 1280, 100, 0, 300, "");
        p = new UILabel(panel, 1280, 100, 0, 400, "");
    }

    @Override
    protected void Start() {
        Load();
        for (int i = 0; i < progress.size(); i++) {
            p.SetText(progress.Get(i).toString());
            if (progress.Get(i).IsIn()) {
                Input(progress.Get(i).car);
                Pause(500);
            } else {
                Output(progress.Get(i).car);
            }
        }
    }

    void Load() {
        cars = new MyChain<>();
        park = new MyStackT<>();
        progress = new MyChain<>();
        backPark = new MyStackT<>();
        size = 0;
        try {
            File file = new File("cars.txt");
            FileReader reader = new FileReader(file);
            BufferedReader read = new BufferedReader(reader);
            try {
                while (true) {
                    String[] str = read.readLine().split(" ");
                    progress.Add(new CarProgress(str[0].charAt(0), AddToCars(str[1])));
                }
            } catch (Exception e) {

            }
            read.close();
        } catch (Exception e) {

        }
    }

    Car AddToCars(String code) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.Get(i).Check(code)) {
                return cars.Get(i);
            }
        }
        Car c = new Car(code);
        cars.Add(c);
        return c;
    }

    void Input(Car car) {
        if (HaveThisCar(car)) {
            pro.SetText(car.getCode() + " :: " + "This car already in this park");
            System.out.println(car.getCode() + " :: " + "This car already in this park");
            return;
        }
        if (size >= 10) {
            pro.SetText(car.getCode() + " :: " + "Garage full, this car cannot enter.");
            System.out.println(car.getCode() + " :: " + "Garage full, this car cannot enter.");
        } else {
            size++;
            park.push(car);
            pro.SetText("Arrival " + car + " -> There is room");
            System.out.println("Arrival " + car + " -> There is room");
        }
        ChangeUI();
    }

    void Output(Car car) {
        if (!HaveThisCar(car)) {
            pro.SetText(car.getCode() + " :: " + "This car no inside this park");
            System.out.println(car.getCode() + " :: " + "This car no inside this park");
            Pause(500);
            return;
        } else {
            size--;
            Process(car);
        }
    }

    void ChangeUI() {
        parkCarTxt.SetText(park.GetString());
        backCarTxt.SetText(backPark.GetString());
    }

    void Process(Car car) {
        pro.SetText("Departure " + car);
        while (!((Car) park.peek()).Check(car.getCode())) {
            backPark.push((Car) park.pop());
            pro.SetText((Car) backPark.peek() + " :: The car is Out");
            System.out.println((Car) backPark.peek() + " :: The car is Out");
            ChangeUI();
            Pause(500);
        }
        Car c = (Car) park.pop();
        pro.SetText(c + " is out");
        ChangeUI();
        System.out.println("Departure " + c);
        Pause(500);
        try {
            while (true) {
                park.push((Car) backPark.pop());
                pro.SetText((Car) park.peek() + " :: This car is back to park");
                System.out.println((Car) park.peek() + " :: This car is back to park");
                ChangeUI();
                Pause(500);
            }
        } catch (Exception e) {
        }

    }

    boolean HaveThisCar(Car car) {
        Object[] c = park.toArray();
        for (int i = 0; i < c.length; i++) {
            if (((Car) c[i]).Check(car.getCode())) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CarParking par = new CarParking();
        par.Show("title");
    }

    public static void Pause(long mill) {
        try {
            Thread.currentThread().sleep(mill);
        } catch (Exception e) {

        }
    }
}

class CarProgress {
    char progress;
    Car car;

    public CarProgress(char p, Car c) {
        progress = p;
        car = c;
    }

    public boolean IsIn() {
        return progress == 'A';
    }

    @Override
    public String toString() {
        return (IsIn() ? "Arriaval " : "Departure ") + car;
    }
}

class Car {
    private String code;

    public Car(String c) {
        code = c;
    }

    @Override
    public String toString() {
        return code;
    }

    public boolean Check(String str) {
        return code.equals(str);
    }

    public String getCode() {
        return code;
    }

}