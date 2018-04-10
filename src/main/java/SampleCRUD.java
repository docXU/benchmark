import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.dbutils.*;
import org.apache.commons.dbcp.BasicDataSource;
import util.DateUtil;

/**
 * @author xuxiongwei
 */
public class SampleCRUD implements Runnable {
    /**
     * 使用固定步长避免id冲突
     */
    private int STEP_SIZE;
    private int THREAD_NUM;

    private int startId;

    private SampleCRUD(int startId, int stepSize, int threadNum) {
        this.STEP_SIZE = stepSize;
        this.THREAD_NUM = threadNum;
        this.startId = startId;
    }

    public void run() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/ibox_trainee?serverTimezone=UTC&useSSL=false&rewriteBatchedStatements=true");
            dataSource.setUsername("someoneCool");
            dataSource.setPassword("123456");

            StringBuilder sb = new StringBuilder("insert into ibox_trainee.tb_order values ");
            Random random = new Random();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < STEP_SIZE; i++) {
                //写入订单表，随机匹配商户和顾客id
                //随机写入下单时间
                Date randomDate = DateUtil.randomDate("2011-01-01 00:00:00", "2018-12-31 23:59:59");
                try {
                    sb.append("(")
                            .append(startId + i).append(", ")
                            .append(1 + random.nextInt(1000)).append(", ")
                            .append(1 + random.nextInt(10000)).append(", ")
                            .append("0").append(", ")
                            .append("'").append(sd.format(randomDate)).append("', ")
                            .append("0, ")
                            .append(random.nextInt(10000) + 1.52).append(", ")
                            .append("0, ")
                            .append("null")
                            .append("),");
                } catch (Exception e) {
                    System.out.println("构造sql集出错，继续构造下一条sql");
                    System.out.println(e.getMessage());
                }
            }

            long startTime = System.currentTimeMillis();
            new QueryRunner(dataSource).insert(sb.substring(0, sb.length() - 1) + ";", new ResultSetHandler<Object[]>() {
                public Object[] handle(ResultSet rs) {
                    return null;
                }
            });
            System.out.println("insert " + (startId) + " ~ " + (startId + STEP_SIZE) + "(不含)  | " + STEP_SIZE + " BatchSize " + " 用时 " + (System.currentTimeMillis() - startTime) + "(ms)");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            int times = 2;
            int index = 0;

            int alreadyDoneRecord = 21000000;
            final int STEP_SIZE = 50000;
            final int THREAD_NUM = 20;

            ArrayBlockingQueue<Runnable> workQueue;
            ThreadPoolExecutor pool;

            while (index < times) {
                index++;

                workQueue = new ArrayBlockingQueue<Runnable>(30);
                pool = new ThreadPoolExecutor(20, 100, 1, TimeUnit.MINUTES, workQueue);

                for (int i = 0; i < THREAD_NUM; i++) {
                    SampleCRUD thread = new SampleCRUD(alreadyDoneRecord + i * STEP_SIZE, STEP_SIZE, THREAD_NUM);
                    pool.execute(thread);
                }
                pool.shutdown();
                while (!pool.isTerminated()) {
                    Thread.sleep(500);
                }

                System.out.println((index) + "_ 部分完成.");
                alreadyDoneRecord += STEP_SIZE * THREAD_NUM;
            }
            System.out.println("总共插入 " + STEP_SIZE * THREAD_NUM * times + " 用时 " + (System.currentTimeMillis() - startTime) + "(ms)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
