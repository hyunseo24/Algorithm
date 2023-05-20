/*
두 점 (2, 3), (5, 7) 의 직선거리는 5 이며, 직각거리는 |2-5|+|3-7|=3+4=7 이다.
2 차원 상의 점를 위한 클래스 MyPoint 를 정의할 때, 클래스 MyPoint 의 각 멤버
함수를 다음과 같은 테스트 프로그램 TestMyPoint.cpp 가 정확하게 동작하도록 구현하시오. 
(클래스 구현 practice)
*/

#include <iostream>
#include <cstdlib>
using namespace std;

class MyPoint
{
public:
    MyPoint();
    MyPoint(int coordX, int coordY);
    MyPoint(const MyPoint& p);
    int getX() const;
    int getY() const;
    void setX(int x);
    void setY(int y);
    bool operator== (const MyPoint& p) const;
    bool operator!= (const MyPoint& p) const;
    int dist2sqr(const MyPoint& p) const;
    int dist1(const MyPoint& p) const;
private:
    int x, y;
};

MyPoint::MyPoint() : x(0), y(0) {}

MyPoint::MyPoint(int coordX, int coordY) : x(coordX), y(coordY) {}

MyPoint::MyPoint(const MyPoint& p) : x(p.x), y(p.y) {}

int MyPoint::getX() const { return x; }

int MyPoint::getY() const { return y; }

void MyPoint::setX(int coordX) { x = coordX; }

void MyPoint::setY(int coordY) { y = coordY; }

bool MyPoint::operator== (const MyPoint& p) const { return (x == p.x && y == p.y); }

bool MyPoint::operator!= (const MyPoint& p) const { return !(*this == p); }

int MyPoint::dist2sqr(const MyPoint& p) const { return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y); }

int MyPoint::dist1(const MyPoint& p) const { return abs(x - p.x) + abs(y - p.y); }

int main() { 
    int numTestCases;
    cin >> numTestCases;
    for(int i=0; i<numTestCases; i++)
    {
       int coordX, coordY;
       cin >> coordX >> coordY;
       MyPoint p1(coordX, coordY);
       cin >> coordX >> coordY;
       MyPoint p2(coordX, coordY);
       if(p1 == p2)
           cout << "1" << " ";
       else
           cout << "0" << " ";
       cout << p1.dist2sqr(p2) << " " << p1.dist1(p2) << " ";
       coordX = p1.getX();
       coordY = p1.getY();
       MyPoint p3(p1);
       p3.setX(coordY);
       p3.setY(coordX);
       cout << p1.dist2sqr(p3) << " " << p1.dist1(p3) << endl;
    }
    return 0;
}
