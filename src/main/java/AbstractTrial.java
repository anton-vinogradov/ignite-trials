import java.util.concurrent.ConcurrentHashMap;

abstract class AbstractTrial {
    private static final ConcurrentHashMap<Integer, Thread> map = new ConcurrentHashMap<>();

    static void put(int idx) {
        map.put(idx, Thread.currentThread());
    }

    static int size() {
        return map.size();
    }

    static boolean containsKey(int idx) {
        return map.containsKey(idx);
    }

    static boolean contains(Thread th) {
        return map.contains(th);
    }

    static void clear() {
        map.clear();
    }
}
