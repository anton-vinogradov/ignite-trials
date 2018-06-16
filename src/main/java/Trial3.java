import java.util.concurrent.atomic.AtomicBoolean;

public class Trial3 extends AbstractTrial {
    private static volatile AtomicBoolean val = new AtomicBoolean();

    public static void main(String... args) throws InterruptedException {
        for (int i = 0; i < 10_000; i++) {
            assert size() == 0;

            Thread th1 = th();
            Thread th2 = th();
            Thread th3 = th();

            th1.start();
            th2.start();
            th3.start();

            th1.join();
            th2.join();
            th3.join();

            // Каждый поток, по завершении работы, должен добавить уникальный идентификатор от 1 до числа потоков.

            assert size() == 3;
            assert containsKey(1);
            assert containsKey(2);
            assert containsKey(3);
            assert contains(th1);
            assert contains(th2);
            assert contains(th3);

            clear();

            System.out.println("Checked " + i);
        }
    }

    private static Thread th() {
        return new Thread(() -> {
            // Используйте поле val для синхронизации потоков
            // и метод put(int) для добавления уникального идентификатора
            // Использование reflection запрещено

            // Правки можно вносить от этой линии
            while (!val.compareAndSet(false, true)) ;
            put(containsKey(2) ? 3 : containsKey(1) ? 2 : 1);
            val.set(false);
            // До этой
        });
    }
}
