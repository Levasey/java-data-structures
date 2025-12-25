import com.datastructures.arrayList.MyArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList();
    }

    @Test
    @DisplayName("Тест конструктора по умолчанию")
    void testDefaultConstructor() {
        MyArrayList defaultList = new MyArrayList();
        assertEquals(0, defaultList.size());
        assertTrue(defaultList.isEmpty());
    }

    @Test
    @DisplayName("Тест конструктора с заданной емкостью")
    void testConstructorWithCapacity() {
        MyArrayList customList = new MyArrayList(20);
        assertEquals(0, customList.size());
        assertTrue(customList.isEmpty());
    }

    @Test
    @DisplayName("Тест конструктора с невалидной емкостью")
    void testConstructorWithInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new MyArrayList(-1));
    }

    @Nested
    @DisplayName("Тесты метода add()")
    class AddTests {
        @Test
        @DisplayName("Добавление одного элемента")
        void testAddSingleElement() {
            assertTrue(list.add(10));
            assertEquals(1, list.size());
            assertFalse(list.isEmpty());
        }

        @Test
        @DisplayName("Добавление нескольких элементов")
        void testAddMultipleElements() {
            for (int i = 0; i < 15; i++) {
                assertTrue(list.add(i));
            }
            assertEquals(15, list.size());
        }

        @Test
        @DisplayName("Автоматическое увеличение емкости")
        void testAutoCapacityIncrease() {
            // Добавляем больше элементов, чем начальная емкость (10)
            for (int i = 0; i < 20; i++) {
                list.add(i);
            }
            assertEquals(20, list.size());
        }
    }

    @Nested
    @DisplayName("Тесты метода get()")
    class GetTests {
        @BeforeEach
        void setUp() {
            list.add(10);
            list.add(20);
            list.add(30);
        }

        @Test
        @DisplayName("Получение элементов по индексу")
        void testGetElements() {
            assertEquals(10, list.get(0));
            assertEquals(20, list.get(1));
            assertEquals(30, list.get(2));
        }

        @Test
        @DisplayName("Получение элемента с невалидным индексом")
        void testGetWithInvalidIndex() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
        }

        @Test
        @DisplayName("Получение элемента из пустого списка")
        void testGetFromEmptyList() {
            MyArrayList emptyList = new MyArrayList();
            assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));
        }
    }

    @Nested
    @DisplayName("Тесты метода set()")
    class SetTests {
        @BeforeEach
        void setUp() {
            list.add(1);
            list.add(2);
            list.add(3);
        }

        @Test
        @DisplayName("Изменение существующего элемента")
        void testSetExistingElement() {
            int oldValue = list.set(1, 99);
            assertEquals(2, oldValue);
            assertEquals(99, list.get(1));
            assertEquals(3, list.size());
        }

        @Test
        @DisplayName("Изменение первого элемента")
        void testSetFirstElement() {
            list.set(0, 100);
            assertEquals(100, list.get(0));
        }

        @Test
        @DisplayName("Изменение последнего элемента")
        void testSetLastElement() {
            list.set(2, 300);
            assertEquals(300, list.get(2));
        }

        @Test
        @DisplayName("Изменение элемента с невалидным индексом")
        void testSetWithInvalidIndex() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 99));
            assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, 99));
        }
    }

    @Nested
    @DisplayName("Тесты метода remove()")
    class RemoveTests {
        @BeforeEach
        void setUp() {
            list.add(10);
            list.add(20);
            list.add(30);
            list.add(40);
            list.add(50);
        }

        @Test
        @DisplayName("Удаление элемента из середины")
        void testRemoveMiddleElement() {
            int removed = list.remove(2);
            assertEquals(30, removed);
            assertEquals(4, list.size());
            assertEquals(10, list.get(0));
            assertEquals(20, list.get(1));
            assertEquals(40, list.get(2));
            assertEquals(50, list.get(3));
        }

        @Test
        @DisplayName("Удаление первого элемента")
        void testRemoveFirstElement() {
            int removed = list.remove(0);
            assertEquals(10, removed);
            assertEquals(4, list.size());
            assertEquals(20, list.get(0));
        }

        @Test
        @DisplayName("Удаление последнего элемента")
        void testRemoveLastElement() {
            int removed = list.remove(list.size() - 1);
            assertEquals(50, removed);
            assertEquals(4, list.size());
        }

        @Test
        @DisplayName("Удаление с невалидным индексом")
        void testRemoveWithInvalidIndex() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
        }

        @Test
        @DisplayName("Удаление из списка с одним элементом")
        void testRemoveFromSingleElementList() {
            MyArrayList singleList = new MyArrayList();
            singleList.add(99);

            int removed = singleList.remove(0);
            assertEquals(99, removed);
            assertEquals(0, singleList.size());
            assertTrue(singleList.isEmpty());
        }
    }

    @Nested
    @DisplayName("Тесты метода clear()")
    class ClearTests {
        @Test
        @DisplayName("Очистка заполненного списка")
        void testClearFilledList() {
            list.add(1);
            list.add(2);
            list.add(3);

            list.clear();

            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("Очистка пустого списка")
        void testClearEmptyList() {
            list.clear();
            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("Добавление после очистки")
        void testAddAfterClear() {
            list.add(1);
            list.add(2);
            list.clear();

            list.add(100);
            list.add(200);

            assertEquals(2, list.size());
            assertEquals(100, list.get(0));
            assertEquals(200, list.get(1));
        }
    }

    @Nested
    @DisplayName("Тесты граничных случаев")
    class EdgeCaseTests {
        @Test
        @DisplayName("Работа с большим количеством элементов")
        void testLargeNumberOfElements() {
            int count = 10000;
            for (int i = 0; i < count; i++) {
                list.add(i);
            }

            assertEquals(count, list.size());

            // Проверяем несколько случайных элементов
            assertEquals(0, list.get(0));
            assertEquals(999, list.get(999));
            assertEquals(9999, list.get(9999));
        }

        @Test
        @DisplayName("Многократное добавление и удаление")
        void testMultipleAddRemove() {
            for (int i = 0; i < 100; i++) {
                list.add(i);
            }

            // Удаляем каждый второй элемент
            for (int i = list.size() - 1; i >= 0; i -= 2) {
                list.remove(i);
            }

            assertEquals(50, list.size());

            // Проверяем, что оставшиеся элементы корректны
            for (int i = 0; i < list.size(); i++) {
                assertEquals(i * 2, list.get(i));
            }
        }

        @Test
        @DisplayName("Тест метода isEmpty()")
        void testIsEmpty() {
            assertTrue(list.isEmpty());
            list.add(1);
            assertFalse(list.isEmpty());
            list.remove(0);
            assertTrue(list.isEmpty());
        }
    }

    @Nested
    @DisplayName("Интеграционные тесты")
    class IntegrationTests {
        @Test
        @DisplayName("Комплексный сценарий: добавление, изменение, удаление")
        void testComplexScenario() {
            // Добавление
            list.add(100);
            list.add(200);
            list.add(300);
            assertEquals(3, list.size());

            // Изменение
            list.set(1, 250);
            assertEquals(250, list.get(1));

            // Добавление ещё элементов
            list.add(400);
            list.add(500);
            assertEquals(5, list.size());

            // Удаление
            list.remove(2); // Удаляем 300
            assertEquals(4, list.size());
            assertEquals(400, list.get(2));

            // Очистка и проверка
            list.clear();
            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("Тест последовательности операций")
        void testOperationSequence() {
            assertTrue(list.isEmpty());

            // Добавляем элементы
            for (int i = 1; i <= 5; i++) {
                list.add(i * 10);
            }
            assertEquals(5, list.size());

            // Проверяем элементы
            assertEquals(10, list.get(0));
            assertEquals(50, list.get(4));

            // Удаляем второй элемент
            int removed = list.remove(1);
            assertEquals(20, removed);
            assertEquals(4, list.size());

            // Изменяем первый элемент
            list.set(0, 15);
            assertEquals(15, list.get(0));

            // Добавляем ещё
            list.add(60);
            assertEquals(5, list.size());
        }
    }

    @Test
    @DisplayName("Тест на сохранение порядка элементов")
    void testOrderPreservation() {
        int[] testData = {5, 3, 8, 1, 9, 4, 7, 2, 6, 0};

        for (int value : testData) {
            list.add(value);
        }

        for (int i = 0; i < testData.length; i++) {
            assertEquals(testData[i], list.get(i));
        }
    }

    @Test
    @DisplayName("Тест консистентности после множества операций")
    void testConsistencyAfterMultipleOperations() {
        // Создаем ожидаемый результат
        java.util.ArrayList<Integer> expected = new java.util.ArrayList<>();
        MyArrayList actual = new MyArrayList();

        // Выполняем одинаковые операции с обоими списками
        for (int i = 0; i < 100; i++) {
            expected.add(i);
            actual.add(i);
        }

        // Удаляем некоторые элементы
        for (int i = 0; i < 50; i += 3) {
            expected.remove(Integer.valueOf(i));
            // Находим индекс для удаления в нашем списке
            for (int j = 0; j < actual.size(); j++) {
                if (actual.get(j) == i) {
                    actual.remove(j);
                    break;
                }
            }
        }

        // Проверяем размер
        assertEquals(expected.size(), actual.size());

        // Проверяем элементы
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).intValue(), actual.get(i));
        }
    }
}