package Day4;
import java.util.*;
interface Shape{
    double area();
    double perimeter();
}

class Circle implements Shape{
    double radius;
    Circle(double radius){
        this.radius=radius;
    }
    public double area() {
        return 3.14*(this.radius*this.radius);
    }
    
    public double perimeter() {
        return 2*3.14*this.radius;
    }

}


class Rectangle implements Shape{
    double length;
    double height;
    // Constructor Overloading 
    Rectangle(double length,double height){
        this.length=length;
        this.height=height;
    }
    Rectangle(double length){
        this.length=length;
        this.height=length;
    }
    public double area() {
        return length*height;
    }
    
    public double perimeter() {
        return 2*(length+height);
    }

}

public class OopsShape {
    Shape shape;
    OopsShape(Shape shape){
        this.shape=shape;
    }
    public static void main(String[] args) {
        int choose;
        Scanner sc=new Scanner(System.in);
        OopsShape shapeObj;
        while(true){
        System.out.println("Enter Number for 1.)Circle or 2.)Rectangle or other to exit:");
        choose=sc.nextInt();
            switch(choose){
                case 1:
                    shapeObj=new OopsShape(new Circle(sc.nextDouble()));
                    System.out.println("Perimeter:"+shapeObj.shape.perimeter()+"Area:"+shapeObj.shape.area());
                    break;
                case 2:
                    shapeObj=new OopsShape(new Rectangle(sc.nextDouble(),sc.nextDouble()));
                System.out.println("Perimeter:"+shapeObj.shape.perimeter()+"Area:"+shapeObj.shape.area());
                break;
                default:
                System.out.println("Exit BYE");
                System.exit(1);
            }
        }
    }
    
    
    
}
