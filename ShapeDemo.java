class shape{
    double calculatearea(){
        return 0;
    }
    void display(){
        System.out.println("Area of Shape:"+ calculatearea());
    }
}
class circle extends shape {
    private double radius;
    circle (double radius){
        this.radius= radius;
    }
    @Override
    double calculatearea(){
        return 3.14*radius*radius;
    }
    @Override
    void display(){
        System.out.printf("Area of circle: %.2f%n",calculatearea());
    }
}
class rectangle extends shape{
    private double length;
    private double width;
    rectangle(double length,double width){
        this.length=length;
        this.width=width;
    }
    @Override
    double calculatearea(){
        return length * width;
    }
    @Override
    void display(){
        System.out.printf("Area of rectangle: %.2f%n",calculatearea());
    }
}
class triangle extends shape{
    private double base;
    private double height;
    triangle(double base,double height){
        this.base=base;
        this.height=height;
    }
    @Override
    double calculatearea(){
        return 0.5*base*height;
    }
    @Override
    void display(){
        System.out.printf("Area of trinagle: %.2f%n",calculatearea());
    }

}

public class ShapeDemo{
    public static void main(String[] args){
        shape shape1= new circle(5);
        shape shape2=new rectangle(10,6);
        shape shape3=new triangle(8,4);
    
    shape1.display();
    shape2.display();
    shape3.display();
    }
}