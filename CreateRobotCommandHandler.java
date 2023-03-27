public class CreateRobotCommandHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "create-robot";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        RobotMap.Robot robot = null;
        try {
            robot = map.createRobot(new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
            System.out.println("Робот №" + robot.getId() + " успешно создан и помещен на карту.");
        } catch (PositionException e) {
            System.out.println("Во время создания робота случилось исключение: " + e.getMessage() + "." +
                    " Попробуйте еще раз");
        }
    }
}