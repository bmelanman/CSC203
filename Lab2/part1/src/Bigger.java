public class Bigger {
    public static double whichIsBigger(Circle circle, Rectangle rectangle, Polygon polygon){
        double circ_perimeter = Util.perimeter(circle);
        double rect_perimeter = Util.perimeter(rectangle);
        double poly_perimeter = Util.perimeter(polygon);

        if( circ_perimeter >= rect_perimeter && circ_perimeter >= poly_perimeter){
            return circ_perimeter;
        }

        else if (rect_perimeter >= circ_perimeter && rect_perimeter >= poly_perimeter){
            return rect_perimeter;
        }

        return poly_perimeter;
    }
}
