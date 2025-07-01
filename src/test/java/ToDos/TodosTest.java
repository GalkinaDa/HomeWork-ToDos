package ToDos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void simpleTaskMatches() {
        SimpleTask task = new SimpleTask(6, "Позвонить маме");

        boolean expected = true;
        boolean actual = task.matches("Позвонить");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void simpleTaskNotMatches() {
        SimpleTask task = new SimpleTask(6, "Позвонить маме");

        boolean expected = false;
        boolean actual = task.matches("папе");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void epicTaskMatches() {
        Epic task = new Epic(7, new String[]{"написать", "позвонить", "решить"});

        boolean expected = true;
        boolean actual = task.matches("написать");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void epicTaskNotMatches() {
        Epic task = new Epic(7, new String[]{"написать", "позвонить", "решить"});

        boolean expected = false;
        boolean actual = task.matches("встретиться");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void meetingTaskMatchesTopic() {
        Meeting task = new Meeting(17, "производительность", "анкета", "start");

        boolean expected = true;
        boolean actual = task.matches("производительность");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void meetingTaskMatchesProject() {
        Meeting task = new Meeting(17, "производительность", "анкета", "start");

        boolean expected = true;
        boolean actual = task.matches("анкета");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void meetingTaskNotMatches() {
        Meeting task = new Meeting(17, "производительность", "анкета", "start");

        boolean expected = false;
        boolean actual = task.matches("авторизация");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchTodos() {
        SimpleTask simpleTask = new SimpleTask(115, "Скачать Приложение");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, meeting};
        Task[] actual = todos.search("Приложение");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTodosOne() {
        SimpleTask simpleTask = new SimpleTask(115, "Скачать Приложение");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("версии");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTodosZero() {
        SimpleTask simpleTask = new SimpleTask(115, "Скачать Приложение");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Купить");
        Assertions.assertArrayEquals(expected, actual);
    }
}
