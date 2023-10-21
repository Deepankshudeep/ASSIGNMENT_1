import java.util.Scanner;

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

class Triangle {
    private Point vertex1;
    private Point vertex2;
    private Point vertex3;

    public Triangle(Point vertex1, Point vertex2, Point vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public double calculatePerimeter() {
        double side1 = vertex1.distanceTo(vertex2);
        double side2 = vertex2.distanceTo(vertex3);
        double side3 = vertex3.distanceTo(vertex1);
        return side1 + side2 + side3;
    }

    public boolean isIsosceles() {
        double side1 = vertex1.distanceTo(vertex2);
        double side2 = vertex2.distanceTo(vertex3);
        double side3 = vertex3.distanceTo(vertex1);
        return (side1 == side2) || (side2 == side3) || (side3 == side1);
    }

    public boolean isPointInside(Point point) {
        double area = 0.5 * Math.abs(
                vertex1.getX() * (vertex2.getY() - vertex3.getY()) +
                        vertex2.getX() * (vertex3.getY() - vertex1.getY()) +
                        vertex3.getX() * (vertex1.getY() - vertex2.getY()) -
                        vertex1.getX() * (vertex2.getY() - vertex3.getY()) -
                        vertex2.getX() * (vertex1.getY() - vertex3.getY()) -
                        vertex3.getX() * (vertex2.getY() - vertex1.getY())
        );

        double triangleArea = 0.5 * Math.abs(
                vertex1.getX() * (vertex2.getY() - vertex3.getY()) +
                        vertex2.getX() * (vertex3.getY() - vertex1.getY()) +
                        vertex3.getX() * (vertex1.getY() - vertex2.getY()) -
                        vertex1.getX() * (vertex2.getY() - vertex3.getY()) -
                        vertex2.getX() * (vertex1.getY() - vertex3.getY()) -
                        vertex3.getX() * (vertex2.getY() - vertex1.getY())
        );

        return area == triangleArea;
    }
}

public class Assignment1and2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of triangles: ");
        int numTriangles = scanner.nextInt();
        scanner.nextLine();

        Triangle[] triangles = new Triangle[numTriangles];

        for (int i = 0; i < numTriangles; i++) {
            System.out.println("Enter the coordinates of Triangle " + (i + 1) + ":");
            Point vertex1 = createPoint(scanner);
            Point vertex2 = createPoint(scanner);
            Point vertex3 = createPoint(scanner);

            Triangle triangle = new Triangle(vertex1, vertex2, vertex3);
            triangles[i] = triangle;

            System.out.println("Perimeter of Triangle " + (i + 1) + ": " + triangle.calculatePerimeter());
            if (triangle.isIsosceles()) {
                System.out.println("Triangle " + (i + 1) + " is an isosceles triangle.");
            }
        }


        System.out.println("Enter the coordinates of the point to check if it's inside a triangle:");
        Point pointToCheck = createPoint(scanner);
        for (int i = 0; i < numTriangles; i++) {
            if (triangles[i].isPointInside(pointToCheck)) {
                System.out.println("Point is inside Triangle " + (i + 1));
            }
        }
    }

    private static Point createPoint(Scanner scanner) {
        System.out.print("Enter x: ");
        double x = scanner.nextDouble();
        System.out.print("Enter y: ");
        double y = scanner.nextDouble();
        scanner.nextLine();
        return new Point(x, y);
    }
}

