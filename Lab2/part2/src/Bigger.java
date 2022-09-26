public class Bigger {
    public static double whichIsBigger(Circle circle, Rectangle rectangle, Polygon polygon){
        double circ_perimeter = circle.perimeter();
        double rect_perimeter = rectangle.perimeter();
        double poly_perimeter = polygon.perimeter();

        if( circ_perimeter >= rect_perimeter && circ_perimeter >= poly_perimeter){
            return circ_perimeter;
        }

        else if (rect_perimeter >= circ_perimeter && rect_perimeter >= poly_perimeter){
            return rect_perimeter;
        }

        return poly_perimeter;
    }
}
