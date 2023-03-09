package Actor;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @autor wenhoulai
 * Created on 2023-02-06
 */
public class BankActor extends UntypedActor {
	private static final Logger log = LoggerFactory.getLogger(BankActor.class);
	private int count;

	@Override
	public void preStart() throws Exception, Exception {
		super.preStart();
		count = 0;
	}
	@Override
	public void onReceive(Object message) throws Throwable {
		if(message instanceof Command){
			Command cmd = (Command) message;
			switch (cmd){
				case ADD:
					log.info("Add 1 from {} to {}", count, ++count);
					break;
				case MINUS:
					log.info("Minus 1 from {} to {}", count, --count);
					break;
				case GET:
					log.info("Return current count " + getSender());
					getSender().tell(count, this.getSelf());
					break;
				default:
					log.warn("UnSupport cmd: " + cmd);
			}
		}else {
			log.warn("Discard unknown message: {}", message);
		}
	}
}
