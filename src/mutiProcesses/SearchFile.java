package mutiProcesses;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Authro 2017101044 林浚颖
 * @Date 2020/4/28
 */
public class SearchFile {

    static class SearchPath implements Runnable {

        private File path;
        private ExecutorService pool;
        private String keyword;

        SearchPath(File path, ExecutorService pool, String keyword) {
            this.path = path;
            this.pool = pool;
            this.keyword = keyword;
        }

        //处理文件，如果文件名字和内容出现关键就打印文件名
        private boolean searchFile(File file) {
            try (Scanner in = new Scanner(file, "UTF-8")) {
                boolean found = false;
                if (file.getName().contains(keyword)) {
                    found = true;
                }
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        found = true;
                    }
                }
                return found;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void run() {
            try {
                File[] files = path.listFiles();
                List<Future<?>> resultList = new ArrayList<>();

                for (File f : files) {
                    if (f.isDirectory()) {
                        SearchPath newRunnable = new SearchPath(f, pool, keyword);
                        resultList.add(pool.submit(newRunnable));   // 追踪任务结果
                    } else {
                        if (searchFile(f)) {
                            System.out.println(f);
                        }
                    }
                }
                for (Future result : resultList) {
                    result.get();       //通过对每个新任务进行追踪
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try (Scanner in = new Scanner(System.in)) {
            //输入搜索目录及关键字
            System.out.print("Enter base directory: ");
            String dir = in.nextLine();
            System.out.print("Enter keyword: ");
            String keyword = in.nextLine();

            ExecutorService pool = Executors.newCachedThreadPool();     // 使用线程池管理多线程以达到线程复用，减少花销
            Runnable searchFileByKeyword = new SearchPath(new File(dir), pool, keyword);
            Future<?> result = pool.submit(searchFileByKeyword);
            result.get();   //阻塞主线程，直到所有任务都完成

            pool.shutdown();    //关闭线程池，不再接受新的任务并将目前任务执行完后关闭
        }
    }
}
