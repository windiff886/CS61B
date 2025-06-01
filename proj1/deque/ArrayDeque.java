package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int DEFAULT_CAPACITY = 8;
    private static final double USAGE_RATIO = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        nextFirst = 0; // Start in the middle
        nextLast = 1; // Next position after nextFirst
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // 辅助方法：获取前一个索引位置
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    // 辅助方法：获取后一个索引位置
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    // 调整数组大小
    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        int current = plusOne(nextFirst);

        // 复制原数组元素到新数组
        for (int i = 0; i < size; i++) {
            newItems[i] = items[current];
            current = plusOne(current);
        }

        items = newItems;
        nextFirst = newCapacity - 1;  // 重置nextFirst到新数组末尾
        nextLast = size;  // 重置nextLast到size位置
    }

    // 在队列前端添加元素
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    // 在队列后端添加元素
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    // 从队列前端移除元素
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        nextFirst = plusOne(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null;  // 避免loitering
        size--;

        // 如果使用率过低且数组足够大，则缩小数组
        if (items.length > DEFAULT_CAPACITY && (double) size / items.length < USAGE_RATIO) {
            resize(items.length / 2);
        }

        return item;
    }

    // 从队列后端移除元素
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;  // 避免loitering
        size--;

        // 如果使用率过低且数组足够大，则缩小数组
        if (items.length > DEFAULT_CAPACITY && (double) size / items.length < USAGE_RATIO) {
            resize(items.length / 2);
        }

        return item;
    }

    // 获取指定索引位置的元素
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int actualIndex = (plusOne(nextFirst) + index) % items.length;
        return items[actualIndex];
    }

    // 打印整个双端队列
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


