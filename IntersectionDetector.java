import java.util.ArrayList;
import java.util.List;

public class IntersectionDetector {
    public static Point getIntersection(Segment s1, Segment s2) {
        int x1 = s1.p1.x, y1 = s1.p1.y;
        int x2 = s1.p2.x, y2 = s1.p2.y;
        int x3 = s2.p1.x, y3 = s2.p1.y;
        int x4 = s2.p2.x, y4 = s2.p2.y;

        int denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (denom == 0) return null; // Paralelas ou coincidentes

        double px = ((x1*y2 - y1*x2)*(x3 - x4) - (x1 - x2)*(x3*y4 - y3*x4)) / (double)denom;
        double py = ((x1*y2 - y1*x2)*(y3 - y4) - (y1 - y2)*(x3*y4 - y3*x4)) / (double)denom;

        Point p = new Point((int) px, (int) py);

        if (onSegment(s1.p1, s1.p2, p) && onSegment(s2.p1, s2.p2, p))
            return p;

        return null;
    }

    private static boolean onSegment(Point a, Point b, Point p) {
        return Math.min(a.x, b.x) <= p.x && p.x <= Math.max(a.x, b.x) &&
               Math.min(a.y, b.y) <= p.y && p.y <= Math.max(a.y, b.y);
    }

    public static List<Point> findAllIntersections(List<Segment> segments) {
        List<Point> intersections = new ArrayList<>();
        for (int i = 0; i < segments.size(); i++) {
            for (int j = i + 1; j < segments.size(); j++) {
                Point p = getIntersection(segments.get(i), segments.get(j));
                if (p != null) {
                    intersections.add(p);
                }
            }
        }
        return intersections;
    }
}
