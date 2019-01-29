import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import model.Human;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RxPractice {
    public static void main(String[] args) throws InterruptedException {
//        Human human = new Human(3, "hitoshi", "male");
//        Human human1 = new Human();
//        System.out.println(human1.getAge());

        Flowable<Human> humanFlowable =
            Flowable.create(emitter -> {

                List<Human> humans = new ArrayList<Human>();
                humans.add(new Human(1, "masako", "female"));
                humans.add(new Human(2, "takashi", "male"));
                humans.add(new Human(4, "taka", "male"));
                humans.add(new Human(6, "takasj", "male"));
                humans.add(new Human(61, "takasj", "male"));
                humans.add(new Human(63, "takasj", "male"));
                humans.add(new Human(62, "takasj", "male"));
                humans.add(new Human(68, "takasj", "male"));
                humans.add(new Human(60, "takasj", "male"));

                for(Human humanb : humans){
                    if (emitter.isCancelled()) return;

                    emitter.onNext(humanb);
                }

                emitter.onComplete();
                }, BackpressureStrategy.BUFFER);

        humanFlowable
            .delay(4000L, TimeUnit.MILLISECONDS)
//            .filter(data -> data.getAge()%2 == 0 )
            // バックプレッシャーを使わずに通知するオブジェクトの数を制限できる
            .observeOn(Schedulers.computation(), false, 2)
            .subscribe(new ResourceSubscriber<Human>() {

//                private Subscription subscription;

//                @Override
//                public void onSubscribe(Subscription subscription) {
//                    this.subscription = subscription;
//                    this.subscription.request(1);
//                }

                @Override
                public void onNext(Human human) {
                    System.out.println(human.getAge());
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    this.subscription.request(1);
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onComplete() {

                }
            });
        Thread.sleep(50000L);
    }
}
