package part01.lesson07.task01;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FactorialBox {

    private static FactorialBox ourInstance = new FactorialBox();
    private Map<Integer, BigInteger> memory = new ConcurrentHashMap<>();
    private int maxKey = 0;
    public static FactorialBox getInstance() {
        return ourInstance;
    }

    private FactorialBox() {
        memory.put(1,BigInteger.valueOf(1));
    }

    /**
     * Поиск значения из memory по key
     * @param key - ключ
     * @return - значение
     */
    public BigInteger findValue(Integer key){
        if (memory.containsKey(key)){
            return memory.get(key);
        }
        return null;
    }

    /**
     * Добавить key и value в memory и обновить maxKey
     * @param key - ключ
     * @param value - значение
     */
    public void add(Integer key, BigInteger value) {
        memory.put(key, value);
        if (key > maxKey)
            maxKey = key;
    }

    /**
     * Вывести в консоль все значения в memory и maxKey
     */
    public void print(){
        System.out.println();
        System.out.println("Содержимое FactorialBox:");
        for (Map.Entry<Integer, BigInteger> entry : memory.entrySet()) {
            System.out.println("map: key=" + entry.getKey() + " value="+entry.getValue());
        }
        System.out.println("maxKey="+ maxKey);
        System.out.println();
    }

    public int getMaxKey() {
        return maxKey;
    }

    public void setMaxKey(int maxKey) {
        this.maxKey = maxKey;
    }

    public Map<Integer, BigInteger> getMemory() {
        return memory;
    }

    public void setMemory(Map<Integer, BigInteger> memory) {
        this.memory = memory;
    }
}
