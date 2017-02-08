package cn.staynoob.structures.queue;

/**
 * Created by xy on 16-5-14.
 */
public interface Queue<E> {
    /**
     * 成功返回true,没有可用空间时抛出IllegalStateException异常
     * @param e
     */
    boolean add(E e);

    /**
     * 成功返回true,失败返回false
     * @param e
     */
    boolean offer(E e);

    /**
     * 移除并返回队列头元素,如果队列为空抛出NoSuchElementException异常
     * @return
     */
    E remove();

    /**
     * 与remove类似,区别是队列为空返回null
     * @return
     */
    E poll();

    /**
     * 查看队列头元素,如果队列为空抛出NoSuchElementException异常
     * @return
     */
    E elements();

    /**
     * 与elements类似,区别是队列为空返回null
     * @return
     */
    E peek();
}
