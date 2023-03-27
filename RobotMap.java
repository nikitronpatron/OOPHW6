import java.util.HashMap;
import java.util.Map;

public class RobotMap {

    private final int n;
    private final int m;

    private final Map<Long, Robot> robots;

    public RobotMap(int n, int m) {
        if (n < 0 || m < 0) {
            throw new IllegalArgumentException("Недопустимые значения размера карты!");
        }
        this.n = n;
        this.m = m;
        this.robots = new HashMap<>();
    }

    public Robot getRobotById(long id) {
        return robots.get(id);
    }

    public Robot createRobot(Point position) throws PositionException {
        checkPosition(position);

        Robot robot = new Robot(position);
        robots.put(robot.id, robot);
        return robot;
    }

    private void checkPosition(Point position) throws PositionException {
        if (position.getX() < 0 || position.getY() < 0 || position.getX() > n || position.getY() > m) {
            throw new PositionException("Некорректное значение точки: " + position);
        }
        if (!isFree(position)) {
            throw new PositionException("Точка " + position + " занята!");
        }
    }

    private void checkDirection(String direction) throws DirectionException {
        try {
            Direction.valueOf(direction);
        } catch (IllegalArgumentException e) {
            throw new DirectionException("Неккоректное значение направления: " + direction);
        }
    }

    private boolean isFree(Point position) {
        return robots.values().stream()
                .map(Robot::getPosition)
                .noneMatch(position::equals);

    }

    public class Robot {
        private Point position;
        private Direction direction;
        private Long id;
        public static Long idCounter = 1L;

        public Robot(Point position) {
            this.id = idCounter++;
            this.position = position;
            this.direction = Direction.TOP;
        }

        public Long getId() {
            return id;
        }

        public Point getPosition() {
            return position;
        }

        public void move() throws PositionException {

            Point newPosition = switch (direction) {
                case TOP -> new Point(position.getX() - 1, position.getY());
                case RIGHT -> new Point(position.getX(), position.getY() + 1);
                case BOTTOM -> new Point(position.getX() + 1, position.getY());
                case LEFT -> new Point(position.getX(), position.getY() - 1);
            };
            checkPosition(newPosition);

            position = newPosition;
        }

        public void changeDirection(String direction) throws DirectionException {
            checkDirection(direction);
            this.direction = Direction.valueOf(direction) ;
        }

        @Override
        public String toString() {
            return String.format("[%d] %s", id, position.toString());
        }

    }

    public enum Direction {

        TOP, RIGHT, BOTTOM, LEFT

    }
}