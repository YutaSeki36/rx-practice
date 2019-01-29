import io.reactivex.Flowable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MultiThread {
    public static void main(String[] args) throws InterruptedException {
        // 1秒毎にデータを通知する
        Flowable.interval(4000L, TimeUnit.MILLISECONDS)

                .doOnNext(data -> System.out.println("emit: " + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()) + "ミリ秒: " + data))
                .subscribe(data -> Thread.sleep(3000L));

        Thread.sleep(9000L);
    }
}
