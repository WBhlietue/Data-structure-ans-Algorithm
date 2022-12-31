
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Lab1.MyArrayLinearListT;
import Lab4.MyHashType;
import UITools.*;

//Dijkstra
public class BD3 extends UI {
    UILabel ansLength;
    UILabel andPath;
    static Graph graph;

    @Override
    protected void MainUI() {
        ImageIcon icon = new ImageIcon("graph.jpg");
        Image image = icon.getImage();
        image = image.getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        label.setLocation(50, 100);
        label.setSize(600, 300);
        panel.add(label);

        UIInputLabel start = new UIInputLabel(panel, 700, 100, 30, 100, "start");
        UIInputLabel end = new UIInputLabel(panel, 700, 175, 30, 100, "end");
        UIButton button = new UIButton(panel, 500, 30, 700, 250, "Find shortest path with Dijkstra's algorithm");
        button.AddListener(new IUIListener() {
            @Override
            public void OnClick() {
                // TODO Auto-generated method stub
                GraphPath path = graph.CalcMinLength(start.GetInputText(), end.GetInputText());
                ansLength.SetText("length: " + path.length);
                andPath.SetText("path: " + path.path);
            }
        });
        ansLength = new UILabel(panel, 500, 30, 700, 325, "", 1);
        andPath = new UILabel(panel, 500, 30, 700, 400, "", 1);
    }

    public static void main(String[] args) {
        new BD3().Show("title");
        graph = new Graph();
        graph.CreateVertice("0");
        graph.CreateVertice("1");
        graph.CreateVertice("2");
        graph.CreateVertice("3");
        graph.CreateVertice("4");
        graph.CreateVertice("5");
        graph.CreateVertice("6");
        graph.CreateVertice("7");
        graph.CreateVertice("8");
        graph.Link("0", "1", 4);
        graph.Link("0", "7", 8);
        graph.Link("1", "7", 11);
        graph.Link("1", "2", 8);
        graph.Link("2", "8", 2);
        graph.Link("2", "5", 4);
        graph.Link("2", "3", 7);
        graph.Link("3", "5", 14);
        graph.Link("3", "4", 9);
        graph.Link("4", "5", 10);
        graph.Link("5", "6", 2);
        graph.Link("6", "7", 1);
        graph.Link("6", "8", 6);
        graph.Link("7", "8", 7);
        // System.out.println(graph);

        // System.out.println(graph.CalcMinLength("0", "4"));
    }
}

class Graph {
    public MyArrayLinearListT<Vertice> verticles;
    public MyArrayLinearListT<Edge> edges;

    public Graph() {
        verticles = new MyArrayLinearListT<>();
        edges = new MyArrayLinearListT<>();
    }

    public Vertice CreateVertice(String name, int value) {
        if (GetVerticeWithName(name) != null) {
            System.out.println("Cannot create vertice, because this vertice already exits!");
            return null;
        }
        Vertice ver = new Vertice(name, value);
        verticles.Add(ver);
        return ver;
    }

    public Vertice CreateVertice(String name) {
        Vertice ver = new Vertice(name);
        verticles.Add(ver);
        return ver;
    }

    public Edge LinkWithVertice(Vertice v1, Vertice v2) {
        Edge e = new Edge(v1, v2);
        edges.Add(e);
        return e;
    }

    public Edge Link(String v1, String v2) {
        Vertice ver1 = GetVerticeWithName(v1);
        Vertice ver2 = GetVerticeWithName(v2);
        if (ver1 == null) {
            System.out.println(v1 + ": vertice is not found");
            return null;
        } else if (ver2 == null) {
            System.out.println(v2 + ": vertice is not found");
            return null;
        }
        return LinkWithVertice(ver1, ver2);
    }

    public Edge Link(String v1, String v2, int length) {
        Vertice ver1 = GetVerticeWithName(v1);
        Vertice ver2 = GetVerticeWithName(v2);
        if (ver1 == null) {
            System.out.println(v1 + ": vertice is not found");
            return null;
        } else if (ver2 == null) {
            System.out.println(v2 + ": vertice is not found");
            return null;
        }
        return LinkWithVertice(ver1, ver2, length);
    }

    public Vertice GetVerticeWithName(String name) {
        for (int i = 0; i < verticles.size(); i++) {
            if (verticles.Get(i).name.equals(name)) {
                return verticles.Get(i);
            }
        }
        return null;
    }

    public Edge LinkWithVertice(Vertice v1, Vertice v2, int length) {
        Edge e = new Edge(v1, v2, length);
        edges.Add(e);
        return e;
    }

    public int GetVerticeNum() {
        return verticles.size();
    }

    private class GraphMinLength {
        private class GraphVerticeStatus {
            public boolean isUsed;
            public Vertice previous;
            public int minLength;

            public GraphVerticeStatus(Vertice pre, int len) {
                previous = pre;
                minLength = len;
            }
        }

        MyHashType<String, GraphVerticeStatus> hash;

        public String GetUncheckMinName() {
            String str = null;
            int length = -1;
            for (int i = 0; i < GetVerticeNum(); i++) {
                GraphVerticeStatus status = hash.Get(verticles.Get(i).name);
                if (status != null && !status.isUsed) {
                    if (length < 0 || status.minLength < length) {
                        length = status.minLength;
                        str = verticles.Get(i).name;
                    }
                    // return verticles.Get(i).name;
                }
            }
            return str;
        }

        public GraphMinLength(Graph graph) {
            hash = new MyHashType<>(GetVerticeNum());
            for (int i = 0; i < GetVerticeNum(); i++) {
                hash.Put(verticles.Get(i).name, null);
            }
        }

        void Update(Vertice ver) {
            for (int i = 0; i < ver.edges.size(); i++) {
                if (CanUse(ver.edges.Get(i).GetOther(ver).name)) {
                    if (hash.Get(ver.edges.Get(i).GetOther(ver).name) == null) {
                        GraphVerticeStatus st = new GraphVerticeStatus(ver,
                                ver.edges.Get(i).length
                                        + hash.Get(ver.name).minLength);
                        hash.UpdateElement(ver.edges.Get(i).GetOther(ver).name, st);
                    } else {
                        GraphVerticeStatus st = hash.Get(ver.edges.Get(i).GetOther(ver).name);
                        if (st.minLength > ver.edges.Get(i).length + hash.Get(ver.name).minLength) {
                            st.minLength = ver.edges.Get(i).length + hash.Get(ver.name).minLength;
                            st.previous = ver;
                        }
                    }
                }
            }
        }

        public GraphPath Calc(Vertice start, Vertice end) {
            GraphVerticeStatus status = new GraphVerticeStatus(null, 0);
            status.isUsed = true;
            hash.UpdateElement(start.name, status);
            Update(start);
            while (CanUse(end.name) && GetUncheckMinName() != null) {
                String n = GetUncheckMinName();
                hash.Get(n).isUsed = true;
                Update(GetVerticeWithName(n));
            }
            String str = "";
            Vertice v = end;
            while (v != null) {
                str += v.name + " >- ";
                v = hash.Get(v.name).previous;
            }
            String s = "";
            for (int i = 0; i < str.length(); i++) {
                s = str.charAt(i) + s;
            }
            s = s.substring(4, s.length());
            GraphPath path = new GraphPath(hash.Get(end.name).minLength, s);
            return path;
        }

        boolean CanUse(String name) {
            return hash.Get(name) == null || !hash.Get(name).isUsed;
        }
    }

    public GraphPath CalcMinLength(String start, String end) {
        Vertice ver1 = GetVerticeWithName(start);
        Vertice ver2 = GetVerticeWithName(end);
        if (ver1 == null) {
            System.out.println(start + ": vertice is not found");
            return null;
        } else if (ver2 == null) {
            System.out.println(end + ": vertice is not found");
            return null;
        }
        GraphMinLength min = new GraphMinLength(this);
        return min.Calc(ver1, ver2);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < edges.size(); i++) {
            str += edges.Get(i) + "\n";
        }
        if (str.length() > 2) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}

class GraphPath {
    public int length;
    public String path;

    public GraphPath(int l, String p) {
        length = l;
        path = p;
    }

    @Override
    public String toString() {
        return "Length = " + length + "\nPath = " + path;
    }
}

class Vertice {
    public String name;
    public int value;
    public MyArrayLinearListT<Edge> edges;

    public Vertice(String n, int v) {
        name = n;
        value = v;
        edges = new MyArrayLinearListT<>();
    }

    public Vertice(String n) {
        name = n;
        value = 0;
        edges = new MyArrayLinearListT<>();
    }

    public void LinkEdge(Edge edge) {
        edges.Add(edge);
    }

    @Override
    public String toString() {
        return name + ":" + value;
    }
}

class Edge {
    public int length;
    public Vertice ver1;
    public Vertice ver2;

    public Edge(Vertice v1, Vertice v2) {
        ver1 = v1;
        ver2 = v2;
        ver1.LinkEdge(this);
        ver2.LinkEdge(this);
        length = 1;
    }

    public Edge(Vertice v1, Vertice v2, int l) {
        this(v1, v2);
        length = l;
    }

    public Vertice GetOther(Vertice ver) {
        return ver1.equals(ver) ? ver2 : ver1;
    }

    @Override
    public String toString() {
        return ver1.name + "<-" + length + "->" + ver2.name;
    }
}