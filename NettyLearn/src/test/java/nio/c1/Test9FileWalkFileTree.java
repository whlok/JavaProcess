package nio.c1;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 遍历文件夹。
 *
 * @author wenhoulai
 */
public class Test9FileWalkFileTree {
	public static void main(String[] args) throws IOException {
//		printDirectoryAndFile();
//		SearchSpecifiedFile(".jar");
//		MultiLevelDirectoryDeletion();
		MultiLevelDirectoryCopy();
	}


	/**
	 * 拷贝多级目录
	 *
	 * @throws IOException
	 */
	private static void MultiLevelDirectoryCopy() throws IOException {
		String source = "/Users/hugh/Documents/mufa";
		String target = "/Users/hugh/Documents/mufa_2";

		Files.walk(Paths.get(source)).forEach(path -> {
			String targetName = path.toString().replace(source, target);
			try {
				// 是目录
				if (Files.isDirectory(path)) {
					Files.createDirectory(Paths.get(targetName));
				} else if (Files.isRegularFile(path)) {
					// 是普通文件
					Files.copy(path, Paths.get(targetName));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 删除多级目录
	 *
	 * @throws IOException
	 */
	private static void MultiLevelDirectoryDeletion() throws IOException {
		// Files.delete() 删除多级目录
		Files.walkFileTree(Paths.get("/Users/hugh/Documents/mufa_2"), new SimpleFileVisitor<>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("===========> 进入" + dir);
				return super.preVisitDirectory(dir, attrs);
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println(file);
				Files.delete(file);
				return super.visitFile(file, attrs);
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				System.out.println("===========> 退出" + dir);
				Files.delete(dir);
				return super.postVisitDirectory(dir, exc);
			}
		});
	}

	/**
	 * 查询指定类型文件数量
	 *
	 * @throws IOException
	 */
	private static void SearchSpecifiedFile(String fileFormat) throws IOException {
		AtomicInteger jarCount = new AtomicInteger();
		Files.walkFileTree(Paths.get("/Users/hugh/Documents/mufa"), new SimpleFileVisitor<Path>() {

			/**
			 * Invoked for a file in a directory.
			 *
			 * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
			 * CONTINUE}.
			 */
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
					throws IOException {
				if (file.toString().endsWith(fileFormat)) {
					System.out.println("======>" + file);
					jarCount.incrementAndGet();
				}
				return super.visitFile(file, attrs);
			}
		});
		System.out.println("jar Count:" + jarCount);
	}

	/**
	 * 计算文件目录数和文件数
	 *
	 * @throws IOException
	 */
	private static void printDirectoryAndFile() throws IOException {
		AtomicInteger dirCount = new AtomicInteger();
		AtomicInteger fileCount = new AtomicInteger();

		// 匿名内部类传入的对象必须是final，如果定义一个final int则改不了数据，所以用AtomicInteger

		Files.walkFileTree(Paths.get("/Users/hugh/Documents/mufa"), new SimpleFileVisitor<Path>() {
			// 这里遍历文件使用的是访问者模式
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
					throws IOException {
				System.out.println("======>" + dir);
				dirCount.incrementAndGet();
				return super.preVisitDirectory(dir, attrs);
			}

			/**
			 * Invoked for a file in a directory.
			 *
			 * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
			 * CONTINUE}.
			 */
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
					throws IOException {
				System.out.println("======>" + file);
				fileCount.incrementAndGet();
				return super.preVisitDirectory(file, attrs);
			}

		});

		System.out.println("dircount " + dirCount);
		System.out.println("fileCount " + fileCount);
	}
}
