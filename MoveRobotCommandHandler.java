public class MoveRobotCommandHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "move-robot";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        RobotMap.Robot robot = null;
        long robotId = Long.parseLong(args[0]);
        robot = map.getRobotById(robotId);

        if(robot==null){
            System.out.println("Робота с идентификатором " + robotId + "Не найден. Задайте другой id");
        } else {
            try {
                robot.move();
                System.out.println("Робот №" + robot.getId() + " успешно двигается на шаг вперед.");
            } catch (PositionException e) {
                System.out.println("Во время движения робота случилось исключение: " +
                e.getMessage() + "." + " Попробуйте еще раз");
            }
        }
    }
}