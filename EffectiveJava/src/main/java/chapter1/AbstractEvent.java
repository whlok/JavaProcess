package chapter1;

import java.util.ArrayList;

/**
 * 事件类的超类。
 *
 * <p>该类是所有事件的超类，程序启动时, 负责扫描它的所有子类，
 * 并进行初始化以及和Listener的绑定。
 *
 * <p>这里需要注意，指定类型T是该Event对应的Listener的超类（或接口），该类型不会在初始化
 * 时被处理，也不会在事件抛出时被调用。
 *
 * @auther pangchong
 */
public class AbstractEvent<T> {

	protected ArrayList<T> listenerList = null;
}
