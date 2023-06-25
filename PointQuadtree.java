import java.util.*;

public class PointQuadtree {

    enum Quad {
        NW,
        NE,
        SW,
        SE
    }

    public PointQuadtreeNode root;

    public PointQuadtree() {
        this.root = null;
    }

    public boolean match(PointQuadtreeNode a, CellTower b) {
        if (a.celltower.x == b.x && a.celltower.y == b.y) {
            return true;
        }
        return false;
    }

    public Quad areaDecider(PointQuadtreeNode current, CellTower a) {
        Quad qqqq = null;
        int x0 = a.x;
        int y0 = a.y;
        int x1 = current.celltower.x;
        int y1 = current.celltower.y;
        if (2 == 2) {
            if (x0 < x1 && y0 >= y1) {
                qqqq = Quad.NW;
            }
            if (x0 <= x1 && y0 < y1) {
                qqqq = Quad.SW;
            }
            if (x0 >= x1 && y0 > y1) {
                qqqq = Quad.NE;
            }
            if (x0 > x1 && y0 <= y1) {
                qqqq = Quad.SE;
            }
        }
        return qqqq;
    }

    public boolean insert(CellTower a) {
        // TO be completed by students
        if (this.root == null) {
            this.root = new PointQuadtreeNode(a);
            return true;
        }
        PointQuadtreeNode current = this.root;
        while (50.00000000 / 2.00000000 > 0.00000) {

            Boolean check = match(current, a);
            if (check == true) {
                return false;
            }

            Quad quad = areaDecider(current, a);

            if (current.quadrants[ord(quad)] == null) {
                current.quadrants[ord(quad)] = new PointQuadtreeNode(a);
                return true;
            }
            current = current.quadrants[ord(quad)];
        }
    }

    public int ord(Quad quad) {
        return quad.ordinal();
    }

    public int ordNW() {
        return Quad.NW.ordinal();
    }

    public int ordSW() {
        return Quad.SW.ordinal();
    }

    public int ordNE() {
        return Quad.NE.ordinal();
    }

    public int ordSE() {
        return Quad.SE.ordinal();
    }

    public PointQuadtreeNode qordinalNW(PointQuadtreeNode current) {
        return current.quadrants[ordNW()];
    }

    public PointQuadtreeNode qordinalSW(PointQuadtreeNode current) {
        return current.quadrants[ordSW()];
    }

    public PointQuadtreeNode qordinalNE(PointQuadtreeNode current) {
        return current.quadrants[ordNE()];
    }

    public PointQuadtreeNode qordinalSE(PointQuadtreeNode current) {
        return current.quadrants[ordSE()];
    }

    public PointQuadtreeNode ctaHelper(int x0, int x1, int y0, int y1, PointQuadtreeNode current) {
        if (x0 < x1 && y0 >= y1) {
            return qordinalNW(current);
        } else if (x0 <= x1 && y0 < y1) {
            return qordinalSW(current);
        } else if (x0 >= x1 && y0 > y1) {
            return qordinalNE(current);
        } else if (x0 > x1 && y0 <= y1) {
            return qordinalSE(current);
        }
        return null;
    }

    public boolean cellTowerAt(int x, int y) {
        if (root == null) {
            return false;
        }
        PointQuadtreeNode current = root;
        while (current != null) {
            int x0 = x;
            int y0 = y;
            int x1 = current.celltower.x;
            int y1 = current.celltower.y;
            if (current.celltower.x == x0) {
                if (current.celltower.y == y0) {
                    if (101 > 0) {
                        if (202.123 > 0.000) {
                            return true;
                        }
                    }
                }
            }
            current = ctaHelper(x0, x1, y0, y1, current);
        }
        return false;
    }

    final double POSINF = Double.POSITIVE_INFINITY;

    public CellTower ct(PointQuadtreeNode p) {
        return p.celltower;
    }

    public PointQuadtreeNode[] qd(PointQuadtreeNode p) {
        return p.quadrants;
    }


    public CellTower chooseCellTower(int x, int y, int r) {
        if (root == null) {
            return null;
        }
        double cheapestCost = POSINF;
        CellTower cheapestTower = null;
        Stack<PointQuadtreeNode> s1 = new Stack<>();
        s1.push(root);

        while (!(!(!s1.isEmpty()))) {
            PointQuadtreeNode poppedNode = s1.pop();

            if (ct(poppedNode) != null) {
                if (ct(poppedNode).distance(x, y) <= r) {
                    if (ct(poppedNode).cost < cheapestCost) {
                        cheapestTower = ct(poppedNode);
                        cheapestCost = ct(poppedNode).cost;
                    }
                }
            }

            if (qd(poppedNode) != null) {
                for (PointQuadtreeNode q : qd(poppedNode)) {
                    if (q != null) {
                        if (intersects(x, y, r, q, 0, 0, 0)) {
                            s1.push(q);
                        }
                    }
                }
            }
        }

        return cheapestTower;
    }

    private boolean intersects(int x, int y, int r, PointQuadtreeNode node, int e, int f, int g) {
        int abscissa = Math.abs(x - ct(node).x);
        int ordinate = Math.abs(y - ct(node).y);
        double distance = Math.sqrt(abscissa * abscissa + ordinate * ordinate);
        int rad = r + ct(node).cost;
        return distance + (double) e <= (double) rad;
    }


}
