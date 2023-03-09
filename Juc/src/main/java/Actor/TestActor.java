package Actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
//import org.apache.flink.runtime.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Future;


/**
 * @autor wenhoulai
 * Created on 2023-02-06
 */
public class TestActor {
	private static final Logger log = LoggerFactory.getLogger(TestActor.class);

	public static void main(String[] args) throws InterruptedException {
		final ActorSystem actorSystem = ActorSystem.create("actor system");

		final ActorRef actorRef = actorSystem.actorOf(Props.create(TestActor.class), "bank-actor");
		CountDownLatch addCount = new CountDownLatch(20);
		CountDownLatch minusCount = new CountDownLatch(10);

		Thread addCountT = new Thread(new Runnable() {
			@Override
			public void run() {
				while (addCount.getCount() > 0){
					actorRef.tell(Actor.Command.ADD, null);
					addCount.countDown();
				}
			}
		});

		Thread minusCountT = new Thread(new Runnable() {
			@Override
			public void run() {
				while (minusCount.getCount() > 0){
					actorRef.tell(Actor.Command.MINUS, null);
					minusCount.countDown();
				}
			}
		});

		minusCountT.start();
		addCountT.start();

		minusCount.await();
		addCount.await();

		Future<Object> count = Patterns.ask(actorRef, Command.GET, 1000L);

//		count.onComplete(
//				new OnComplete<Object>() {
//					@Override
//					public void onComplete(Throwable failure, Object success) throws Throwable {
//						if (failure != null) {
//							failure.printStackTrace();
//						} else {
//							log.info("Get result from " + success);
//						}
//					}
//				},
//				Executors.directExecutionContext());
//		actorSystem.shutdown();
	}
}
