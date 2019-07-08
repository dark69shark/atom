package ru.atom.geometry;

public class Bar implements Collider {
    private Point firstPoint;
    private Point secondPoint;
    private Point thirdPoint;
    private Point fourthPoint;
    private Point[] points = {firstPoint,secondPoint,thirdPoint,fourthPoint};
    public Bar(int firstCornerX, int firstCornerY, int secondCornerX, int secondCornerY){
        int ch;
        if (firstCornerX > secondCornerX){
            ch = secondCornerX;
            secondCornerX = firstCornerX;
            firstCornerX = ch;}
        if (firstCornerY > secondCornerY){
            ch = firstCornerY;
            firstCornerY = secondCornerY;
            secondCornerY = ch;
        }

        this.firstPoint = new Point(firstCornerX,firstCornerY);
        this.thirdPoint = new Point(secondCornerX, secondCornerY);
        if (firstCornerY < secondCornerY){
            this.secondPoint = new Point(firstCornerX,secondCornerY);
            this.fourthPoint = new Point(secondCornerX,firstCornerY);
        }
        else {
            this.secondPoint = new Point(secondCornerX,firstCornerY);
            this.fourthPoint = new Point(firstCornerX,secondCornerY);
        }


    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public Point getThirdPoint() {
        return thirdPoint;
    }

    public Point getFourthPoint() {
        return fourthPoint;
    }

    public Point[] getPoints() {
        return points;
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other instanceof Point) {
            Point p = (Point) other;
            return this.firstPoint.getX() <= p.getX() && p.getX() <= this.thirdPoint.getX() &&
                    this.firstPoint.getY() <= p.getY() && p.getY() <= this.thirdPoint.getY();
        }
        if (other instanceof Bar){
            Bar bar = (Bar) other;
            if (this.equals(bar)){ //если эвиваленты вернуть тру
                return true;
            }
            else if (this.firstPoint.getX() <= bar.getFirstPoint().getX() && // если один бар в другом баре
                    this.firstPoint.getY() <= bar.getFirstPoint().getY() &&
                    this.thirdPoint.getX() >= bar.getThirdPoint().getX() &&
                    this.thirdPoint.getY() >= bar.getThirdPoint().getY()){
                return true;
            }
            else if (this.isColliding(bar.getFirstPoint()) || //проерка на углы прямогулокников в обласити друг друга
                    this.isColliding(bar.getSecondPoint()) ||
                    this.isColliding(bar.getThirdPoint()) ||
                    this.isColliding(bar.getFourthPoint()) ||
                    bar.isColliding(this.firstPoint) ||
                    bar.isColliding(this.secondPoint) ||
                    bar.isColliding(this.thirdPoint) ||
                    bar.isColliding(this.fourthPoint)){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // cast from Object to Point
        Bar bar = (Bar) o;

//        Math.abs(this.firstPoint.getX() - this.thirdPoint.getX()) == Math.abs(bar.firstPoint.getX() - bar.thirdPoint.getX()) &&
//                Math.abs(this.firstPoint.getY() - this.thirdPoint.getY()) == Math.abs(bar.firstPoint.getY() - bar.thirdPoint.getY())
        return  this.firstPoint.equals(bar.getFirstPoint()) &&
                this.secondPoint.equals(bar.getSecondPoint()) &&
                this.thirdPoint.equals(bar.getThirdPoint()) &&
                this.fourthPoint.equals(bar.getFourthPoint());

//        for(int i=0;i<4;i++){
//            for(int j=0;j<4;j++){
//                if (this.points[i].equals(bar.getPoints()[j])){ continue; }
//            }
//
//        }
    //return true;
//                this.firstPoint.equals(bar.getFirstPoint()) && this.secondPoint.equals(bar.getSecondPoint()) &&
//                this.thirdPoint.equals(bar.getThirdPoint()) && this.fourthPoint.equals(bar.getFourthPoint());
    }

}
