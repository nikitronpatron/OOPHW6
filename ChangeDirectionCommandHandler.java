public class ChangeDirectionCommandHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "change-direction";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        Long robotId = Long.parseLong(args[0]);
        String newDirection = args[1];
        RobotMap.Robot robotById = map.getRobotById(robotId);

        if (robotById != null) {
            try {
                robotById.changeDirection(newDirection);
                System.out.println("Робот №" + robotById.getId() + " сменил направление на " + newDirection);
            } catch (DirectionException e) {
                System.out.println("Во время смены направления случилось исключение: " + e.getMessage() + "." +
                        " Попробуйте еще раз");
            }
        } else {
            System.out.println("Робот с идентификаторо " + robotId + " не найден");
        }
    }

}