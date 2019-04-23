package code.repository.dev.backjoon;

import java.util.*;

public class DragonCurve {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int curveCount = Integer.valueOf(scanner.nextLine());
        Set<Point> resultPoints = new HashSet<>();

        for (int index = 0; index < curveCount; index++) {
            String[] splitDragonCurve = scanner.nextLine().split(" ");
            Stack<Point> points = new Stack<>();

            int pointX = Integer.valueOf(splitDragonCurve[0]);
            int pointY = Integer.valueOf(splitDragonCurve[1]);
            int dgree = Integer.valueOf(splitDragonCurve[2]);
            int curveNumber = Integer.valueOf(splitDragonCurve[3]);

            points.push(new Point(pointX, pointY));

            switch (dgree) {
                case 0:
                    points.push(new Point(pointX + 1, pointY));
                    break;
                case 1:
                    points.push(new Point(pointX, pointY - 1));
                    break;
                case 2:
                    points.push(new Point(pointX - 1, pointY));
                    break;
                case 3:
                    points.push(new Point(pointX, pointY + 1));
                    break;
                default:
            }

            for (int number = 0; number < curveNumber; number++) {
                Stack<Point> nextPoints = new Stack<>();
                Point standard = new Point(-1, -1);
                int count = 0;

                nextPoints.addAll(points);

                while (!points.isEmpty()) {
                    Point point1 = points.pop();

                    if (count++ == 0) {
                        standard.setX(point1.getX());
                        standard.setY(point1.getY());
                    }

                    if (points.isEmpty()) {
                        break;
                    }

                    Point point2 = points.peek();

                    if (point2.getX() - point1.getX() == 1 && point2.getY() - point1.getY() == 0) {
                        standard.setY(standard.getY() + 1);
                    } else if (point2.getX() - point1.getX() == 0 && point2.getY() - point1.getY() == 1) {
                        standard.setX(standard.getX() - 1);
                    } else if (point2.getX() - point1.getX() == -1 && point2.getY() - point1.getY() == 0) {
                        standard.setY(standard.getY() - 1);
                    } else if (point2.getX() - point1.getX() == 0 && point2.getY() - point1.getY() == -1) {
                        standard.setX(standard.getX() + 1);
                    }

                    nextPoints.push(new Point(standard.getX(), standard.getY()));
                }

                points.addAll(nextPoints);

            }

            resultPoints.addAll(points);
        }

        int resultCount = 0;

        for (Point point : resultPoints) {
            boolean right = false;
            boolean down = false;
            boolean rightDown = false;
            for (Point nextPoint : resultPoints) {
                if (point.getX() == nextPoint.getX() && point.getY() == nextPoint.getY()) {
                    continue;
                }

                if (point.getX() + 1 == nextPoint.getX() && point.getY() == nextPoint.getY()) {
                    right = true;
                    continue;
                }

                if (point.getX() == nextPoint.getX() && point.getY() + 1 == nextPoint.getY()) {
                    down = true;
                    continue;
                }

                if (point.getX() + 1 == nextPoint.getX() && point.getY() + 1 == nextPoint.getY()) {
                    rightDown = true;
                }
            }

            if (right && down && rightDown) {
                resultCount += 1;
            }
        }

        System.out.println(resultCount);
    }

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
