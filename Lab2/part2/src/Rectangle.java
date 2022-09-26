
public class Rectangle {

    private Point top_left;
    private Point bottom_right;

    public Rectangle(Point top_left, Point bottom_right){
        this.top_left = top_left;
        this.bottom_right = bottom_right;
    }

    public Point getTopLeft(){
        return top_left;
    }

    public Point getBottomRight(){
        return bottom_right;
    }

    public double perimeter(){

        double width = Math.abs(this.top_left.getX() - this.bottom_right.getX());
        double length = Math.abs(this.bottom_right.getY() - this.top_left.getY());

        return (2 * width) + (2 * length);
    }
    // additional functions must be private
}
