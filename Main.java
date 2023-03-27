import java.util.List;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите команду:");
        RobotMap map = null;
        while (true) {
            String command = sc.nextLine();
            if (command.startsWith("create-map")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    map = new RobotMap(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]));
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("При создании карты возникло исключение: " + e.getMessage() + "." +
                            " Попробуйте еще раз");
                }
            } else {
                System.out.println("Команда не найдена. Попробуйте еще раз");
            }
        }

        List<CommandHandler> handlers = List.of(
                new ChangeDirectionCommandHandler(),
                new CreateRobotCommandHandler(),
                new MoveRobotCommandHandler()
        );
        CommandManager commandManager = new CommandManager(map, handlers);

        System.out.println("Карта создана!");

        while (true) {
            String command = sc.nextLine();
            if (command.startsWith("q")) {
                break;
            } else{
                commandManager.handleCommand(command);
            }
        }
    }
}