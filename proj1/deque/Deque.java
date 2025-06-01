package deque;

/**
 * 双端队列类，实现了双端队列的基本操作
 * @param <T> 队列中元素的类型
 */
public class Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int DEFAULT_CAPACITY = 8;
    private static final double USAGE_RATIO = 0.25;

    /**
     * 创建一个空的双端队列
     */
    public Deque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     * 检查队列是否为空
     * @return 如果队列为空则返回true，否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回队列中元素的数量
     * @return 队列中元素的数量
     */
    public int size() {
        return size;
    }

    /**
     * 获取前一个索引位置，循环处理
     */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /**
     * 获取后一个索引位置，循环处理
     */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    /**
     * 调整数组大小
     */
    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        int current = plusOne(nextFirst);

        for (int i = 0; i < size; i++) {
            newItems[i] = items[current];
            current = plusOne(current);
        }

        items = newItems;
        nextFirst = newCapacity - 1;
        nextLast = size;
    }

    /**
     * 在队列前端添加元素
     * @param item 要添加的元素
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /**
     * 在队列后端添加元素
     * @param item 要添加的元素
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    /**
     * 从队列前端移除并返回元素
     * @return 队列前端的元素，如果队列为空则返回null
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        nextFirst = plusOne(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null;  // 避免loitering
        size--;

        if (items.length > DEFAULT_CAPACITY && (double) size / items.length < USAGE_RATIO) {
            resize(items.length / 2);
        }

        return item;
    }

    /**
     * 从队列后端移除并返回元素
     * @return 队列后端的元素，如果队列为空则返回null
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;  // 避免loitering
        size--;

        if (items.length > DEFAULT_CAPACITY && (double) size / items.length < USAGE_RATIO) {
            resize(items.length / 2);
        }

        return item;
    }

    /**
     * 获取指定索引位置的元素，但不移除它
     * @param index 要获取元素的索引位置
     * @return 指定位置的元素，如果索引不合法则返回null
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int actualIndex = (plusOne(nextFirst) + index) % items.length;
        return items[actualIndex];
    }

    /**
     * 打印队列中的元素
     */
    public void printDeque() {
        if (isEmpty()) {
            System.out.println();
            return;
        }

        int current = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[current]);
            if (i < size - 1) {
                System.out.print(" ");
            }
            current = plusOne(current);
        }
        System.out.println();
    }
}
