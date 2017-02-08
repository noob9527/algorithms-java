package cn.staynoob.structures.dict;

import javax.naming.OperationNotSupportedException;
import java.util.Set;

/**
 * 简易字典集合(符号表)接口
 * Created by xy on 16-5-15.
 */
public interface Dictionary <K,V>{
    void put(K key,V value);
    V get(K key);
    int size();
    void delete(K key);
    boolean isEmpty();
    boolean contains(K key);

    Set<K> keySet();
}
