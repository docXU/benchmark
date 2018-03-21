import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.dbutils.*;
import org.apache.commons.dbcp.BasicDataSource;

public class SampleCRUD implements Runnable {

    private final static int StepSize = 20000; //使用固定步长避免id冲突
    private final static int threadNums = 100;
    private static int alreadyDoneRecord = 122000000;

    private int start_id;

    private SampleCRUD(int start_id) {
        this.start_id = start_id;
    }

    private static void startInsert() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(100, 500, 1, TimeUnit.MINUTES, workQueue);
        for (int i = 0; i < threadNums; i++) {
            SampleCRUD thread = new SampleCRUD(i * SampleCRUD.StepSize);
            pool.execute(thread);
        }
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()) {
                break;
            }
            Thread.sleep(100);
        }
        System.out.println("insert " + (alreadyDoneRecord + StepSize * threadNums) + " ~ " + (alreadyDoneRecord + 2 * StepSize * threadNums) + "(不含)  | " + StepSize + " BatchSize " + " 用时 " + (System.currentTimeMillis() - startTime) + "(ms)");
    }

    public void run() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/ibox_trainee?serverTimezone=UTC&useSSL=false&rewriteBatchedStatements=true");
            dataSource.setUsername("someoneCool");
            dataSource.setPassword("123456");

            StringBuilder sb = new StringBuilder("insert into ibox_trainee.shopper values ");
            for (int i = 0; i < StepSize; i++) {
                sb.append("(").append(alreadyDoneRecord + start_id + i).append(", '")
                        .append(UUID.randomUUID().toString().replace("-", "")).append("', '")
                        .append(("" + System.currentTimeMillis()).substring(6))
                        .append(new Random().nextInt(10000)).append("', ")
                        .append("'0', ")
                        .append("'1304924151@qq.com', ")
                        .append("'123456'),");
            }

            new QueryRunner(dataSource).insert(sb.substring(0, sb.length() - 1) + ";", new ResultSetHandler<Object[]>() {
                public Object[] handle(ResultSet rs) {
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try {
            long startTime = System.currentTimeMillis();
            int times = 10;
            int stepPerLoop = SampleCRUD.StepSize * SampleCRUD.threadNums;
            int index = 0;
            while (index <= times) {
                index++;
                SampleCRUD.startInsert();
                SampleCRUD.alreadyDoneRecord += stepPerLoop;
            }
            System.out.println("总共插入 " + stepPerLoop * times + " 用时 " + (System.currentTimeMillis() - startTime) + "(ms)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
