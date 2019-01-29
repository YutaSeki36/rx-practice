import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.BiFunction;

public class Composit {
    public static void main(String[] args) throws InterruptedException {
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(Flowable.range(1, 3)
                                        .doOnCancel(()-> System.out.println("No.1 canceled"))
                                        .observeOn(Schedulers.computation())
                                        .subscribe(data -> {
                                            Thread.sleep(100L);
                                            System.out.println("No.1:" + data);
                                            System.out.println("emit: " + new SimpleDateFormat("ssSSS").format(Calendar.getInstance().getTime()) + "ミリ秒: ");
                                        })
        );

        compositeDisposable.add(Flowable.range(1, 3)
                .doOnCancel(()-> System.out.println("No.2 canceled"))
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    Thread.sleep(100L);
                    System.out.println("No.2:" + data);
                    System.out.println("emit: " + new SimpleDateFormat("ssSSS").format(Calendar.getInstance().getTime()) + "ミリ秒: ");
                }
                )

        );

        Thread.sleep(450L);
//        compositeDisposable.dispose();

        BiFunction<Integer, Integer, BigDecimal> function =
                new BiFunction<Integer, Integer, BigDecimal>() {
                    @Override
                    public BigDecimal apply(Integer integer, Integer integer2) {
                        return new BigDecimal(integer + integer2);
                    }
                };
        BiFunction<Integer, Integer, BigDecimal> functionla = (Integer integer1, Integer integer2) -> new BigDecimal(integer1 + integer2);

        System.out.println(functionla.apply(5, 60));
    }
}
