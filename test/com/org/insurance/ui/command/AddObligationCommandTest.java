package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AddObligationCommandTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    /**
     * Допоміжний метод для створення сканера, прив'язаного до System.in.
     * Ми спочатку підміняємо System.in даними, а потім створюємо Scanner.
     */
    private Scanner prepareInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        return new Scanner(System.in);
    }

    @Test
    @DisplayName("getDescription повертає опис команди")
    void testGetDescription() {
        AddObligationCommand command = new AddObligationCommand();
        assertNotNull(command.getDescription());
        assertTrue(command.getDescription().contains("Додати облігацію"));
    }

    @Test
    @DisplayName("execute: Успішне додавання облігації (Happy Path)")
    void testExecuteSuccess() {
        // 1. Підготовка деривативу
        Derivative derivative = new Derivative("My Portfolio");
        derivative.setObligations(new ArrayList<>()); // Ініціалізуємо список
        List<Derivative> list = List.of(derivative);

        // 2. Підготовка вводу:
        // "1" -> Вибір деривативу №1
        // "1" -> Вибір типу облігації №1 (AutoObligation - зазвичай перший в Enum)
        // ... Далі йдуть дані для конструктора AutoObligation:
        // Name -> Amount -> Factor -> Period -> Rate -> Prob -> MaxCost ->
        // -> VehicleType -> DriverClass -> BonusMalus
        String inputData = """
                1
                1
                Tesla Insurance
                5000
                1.2
                12
                0.05
                0.1
                10000
                Sedan
                A
                0.9
                """;

        Scanner scanner = prepareInput(inputData);
        AddObligationCommand command = new AddObligationCommand();

        // 3. Виконання
        command.execute(scanner, list);

        // 4. Перевірка
        assertEquals(1, derivative.getObligations().size(), "Список облігацій мав збільшитись на 1");
        Obligation added = derivative.getObligations().get(0);
        assertEquals("Tesla Insurance", added.getName());

        // Перевірка повідомлення в консолі
        String output = outContent.toString();
        assertTrue(output.contains("Додано облігацію типу"));
    }

    @Test
    @DisplayName("execute: Якщо список деривативів пустий, виводиться повідомлення")
    void testExecuteNoDerivatives() {
        Scanner scanner = prepareInput(""); // Ввід не важливий, бо перевірка йде раніше
        AddObligationCommand command = new AddObligationCommand();

        command.execute(scanner, Collections.emptyList());

        String output = outContent.toString();
        assertTrue(output.contains("Немає дериватив"));
    }

    @Test
    @DisplayName("execute: Якщо обрано неіснуючий індекс деривативу, команда переривається")
    void testExecuteInvalidDerivativeIndex() {
        Derivative d = new Derivative("D1");
        List<Derivative> list = List.of(d);

        // Вводимо "5" (а є тільки 1), потім ще щось (ігнорується)
        String inputData = "5\n";

        Scanner scanner = prepareInput(inputData);
        AddObligationCommand command = new AddObligationCommand();

        command.execute(scanner, list);

        // Список не змінився
        assertNull(d.getObligations()); // Список був null і залишився null (бо ми його не ініціалізували і не дійшли до додавання)
    }

    @Test
    @DisplayName("execute: Переривання при введенні тексту замість типу облігації")
    void testExecuteInvalidTypeInput() {
        Derivative d = new Derivative("D1");
        d.setObligations(new ArrayList<>());
        List<Derivative> list = List.of(d);

        // "1" (Дериватив) -> "text" (Невірний тип)
        String inputData = "1\ntext\n";

        Scanner scanner = prepareInput(inputData);
        AddObligationCommand command = new AddObligationCommand();

        command.execute(scanner, list);

        String output = outContent.toString();
        // Якщо readInt повертає -1, ObligationType.createByIndex має повернути null
        assertTrue(output.contains("Невірний вибір типу"));
        assertEquals(0, d.getObligations().size());
    }

    @Test
    @DisplayName("execute: Автоматична ініціалізація списку зобов'язань через рефлексію (якщо він null)")
    void testAutoInitList() {
        // Створюємо дериватив, але НЕ робимо setObligations(new ArrayList<>())
        // Поле obligations буде null
        Derivative d = new Derivative("NullListDerivative");
        List<Derivative> list = List.of(d);

        // Вводимо коректні дані для створення AutoObligation
        String inputData = """
                1
                1
                AutoTest
                100
                1
                1
                0
                0
                0
                Type
                Cls
                1.0
                """;

        Scanner scanner = prepareInput(inputData);
        AddObligationCommand command = new AddObligationCommand();

        command.execute(scanner, list);

        // Перевіряємо, що список ініціалізувався і туди додалась облігація
        assertNotNull(d.getObligations(), "Список мав ініціалізуватись автоматично");
        assertEquals(1, d.getObligations().size());
        assertEquals("AutoTest", d.getObligations().get(0).getName());
    }

    @Test
    @DisplayName("readInt повертає -1 при введенні тексту замість числа")
    void testInvalidNumberInput() {
        // Цей тест непрямий, ми перевіряємо поведінку через вибір деривативу
        Derivative d = new Derivative("D1");
        List<Derivative> list = List.of(d);

        // Вводимо "abc" замість індексу деривативу
        String inputData = "abc\n";

        Scanner scanner = prepareInput(inputData);
        AddObligationCommand command = new AddObligationCommand();

        command.execute(scanner, list);

        // Нічого не додалось, бо readInt повернув -1, а -1 < 1
        assertNull(d.getObligations());
    }
}