package timingtest;

/** Array based list.
 *  @author Josh Hug
 */

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

public class AList<Item> {
    private Item[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = (Item[]) new Object[100];
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
//    private void resize(int capacity) {
//        Item[] a = (Item[]) new Object[capacity];
//        System.arraycopy(items, 0, a, 0, size);
//        items = a;
//    }
    private void resize() {
        Item[] a = (Item[]) new Object[size * 2];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length) {
            resize();
        }

        items[size] = x;
        size = size + 1;
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        Item x = getLast();
        items[size - 1] = null;
        size = size - 1;
        return x;
    }

    public static void main(String[] args) {
        System.out.println("Timing table for addLast");
        System.out.println("           N     time (s)        # ops  microsec/op");
        System.out.println("------------------------------------------------------------");
        // 测试不同规模的N值
        int[] Ns = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        for (int N : Ns) {
            AList<Integer> list = new AList<Integer>();

            // 计时开始
            long startTime = System.nanoTime();

            // 执行N次addLast操作
            for (int i = 0; i < N; i++) {
                list.addLast(i);
            }

            // 计时结束
            long endTime = System.nanoTime();

            // 计算总时间(秒)
            double timeInSeconds = (endTime - startTime) / 1e9;

            // 计算每操作微秒
            double microSecPerOp = (timeInSeconds * 1e6) / N;

            // 格式化输出
            System.out.printf("%12d %13.2f %12d %13.2f%n",
                    N, timeInSeconds, N, microSecPerOp);
        }
    }
}
