import com.datastructures.linkedlist.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private LinkedList list;

    @BeforeEach
    public void setUp() {
        list = new LinkedList();
    }

    @Test
    public void testAddAndGet() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testAddFirst() {
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testAddAtIndex() {
        list.addLast(1);
        list.addLast(3);
        list.add(1, 2); // Вставляем 2 на позицию 1

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testRemoveByIndex() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        list.remove(1); // Удаляем элемент с индексом 1 (значение 2)

        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertFalse(list.contains(2));
    }

    @Test
    public void testRemoveFirstElement() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        list.remove(0); // Удаляем первый элемент

        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void testRemoveLastElement() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        list.remove(2); // Удаляем последний элемент

        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testContains() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertTrue(list.contains(2));
        assertFalse(list.contains(4));
    }

    @Test
    public void testIndexOf() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(2); // Дубликат

        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.addLast(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testToString() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    public void testAddInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 1));
    }

    @Test
    public void testAddSingleParamMethod() {
        list.addLast(1);
        list.addLast(2);

        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testEmptyListOperations() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertEquals(-1, list.indexOf(1));
        assertFalse(list.contains(1));
    }

    @Test
    public void testClearEmptyList() {
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }
}