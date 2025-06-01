package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    /**
     * 创建一个MaxArrayDeque，使用给定的比较器
     * @param c 用于比较元素的比较器
     */
    public MaxArrayDeque(Comparator<T> c) {
        super(); // 调用ArrayDeque的构造函数
        this.comparator = c;
    }

    /**
     * 返回队列中的最大元素，使用构造函数中提供的比较器
     * @return 最大元素，如果队列为空则返回null
     */
    public T max() {
        if (isEmpty()) {
            return null;
        }
        return max(this.comparator);
    }

    /**
     * 返回队列中的最大元素，使用给定的比较器
     * @param c 用于确定最大元素的比较器
     * @return 最大元素，如果队列为空则返回null
     */
    public T max(Comparator<T> c) {
        if (isEmpty() || c == null) {
            return null;
        }

        T maxItem = get(0);
        for (int i = 1; i < size(); i++) {
            T currentItem = get(i);
            if (c.compare(currentItem, maxItem) > 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }

    /**
     * 重写equals方法
     * 注意：根据说明，这个方法的实现不会被测试
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MaxArrayDeque)) {
            return false;
        }

        MaxArrayDeque<?> that = (MaxArrayDeque<?>) o;
        if (size() != that.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(that.get(i))) {
                return false;
            }
        }
        return true;
    }
}
