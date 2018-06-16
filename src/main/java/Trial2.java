import java.util.concurrent.atomic.AtomicBoolean;

public class Trial2 extends AbstractTrial {
    private static final AtomicBoolean val = new AtomicBoolean();

    public static void main(String... args) throws InterruptedException {
        for (int i = 0; i < 10_000; i++) {
            assert size() == 0;

            Thread th1 = th();
            Thread th2 = th();

            th1.start();
            th2.start();

            th1.join();
            th2.join();

            // Каждый поток, по завершении работы, должен добавить уникальный идентификатор от 1 до числа потоков.

            assert size() == 2;
            assert containsKey(1);
            assert containsKey(2);
            assert contains(th1);
            assert contains(th2);

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
            boolean oldValue = val.get();
            while (!val.compareAndSet(oldValue,!oldValue))
                oldValue=!oldValue;
            put(oldValue?1:2);
            // До этой
        });
    }
}
