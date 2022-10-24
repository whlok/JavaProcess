package memoryStructure;

import javassist.*;

import java.io.IOException;

/**
 * @author wenhoulai
 */
public class Demo1_8 extends ClassLoader {
	public static void main(String[] args) {
		int j = 0;
		try {
			Demo1_8 test = new Demo1_8();
			for (int i = 0; i < 10000; i++, j++) {
				createPerson(i);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println(j);
		}
	}

	public static void createPerson(int i) throws CannotCompileException, IOException, NotFoundException {
		ClassPool pool = ClassPool.getDefault();
		// 1. 创建一个空类
		CtClass cc = pool.makeClass("Person" + i);
		// 2. 新增一个字段 private String name;
		// 字段名为name
		CtField param = new CtField(pool.get("java.lang.String"), "name", cc);
		// 访问级别是 private
		param.setModifiers(Modifier.PUBLIC);
		// 初始值是 "xiaoming"
		cc.addField(param);
		// 3. 生成 getter、setter 方法
		cc.addMethod(CtNewMethod.setter("setName", param));
		cc.addMethod(CtNewMethod.getter("getName", param));
		// 4. 添加无参的构造函数
		CtConstructor cons = new CtConstructor(new CtClass[]{}, cc);
		cons.setBody("{name = \"xiaohong\";}");
		cc.addConstructor(cons);
		// 5. 添加有参的构造函数
		cons = new CtConstructor(new CtClass[]{pool.get("java.lang.String")}, cc);
		// $0=this / $1,$2,$3... 代表方法参数
		cons.setBody("{$0.name = $1;}");
		cc.addConstructor(cons);
		//这里会将这个创建的类对象编译为.class文件
		cc.writeFile("./memoryStructure");
	}

}
