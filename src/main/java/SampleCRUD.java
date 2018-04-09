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
    private final static int STEP_SIZE = 50000;
    private final static int THREAD_NUM = 20;
    private static int alreadyDoneRecord = 9212046;

    private int startId;

    private SampleCRUD(int startId) {
        this.startId = startId;
    }

    private static void startInsert() throws InterruptedException {

        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(50);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(50, 100, 1, TimeUnit.MINUTES, workQueue);
        for (int i = 0; i < THREAD_NUM; i++) {
            SampleCRUD thread = new SampleCRUD(alreadyDoneRecord + i * SampleCRUD.STEP_SIZE);
            pool.execute(thread);
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
            Thread.sleep(500);
        }
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
                Date randomDate = DateUtil.randomDate("2016-01-01 00:00:00", "2018-12-31 23:59:59");
                try {
                    sb.append("(")
                            .append(startId + i).append(", ")
                            .append(random.nextInt(10)).append(", ")
                            .append(random.nextInt(100)).append(", ")
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
            synchronized (SampleCRUD.class) {
                alreadyDoneRecord += STEP_SIZE;
            }
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
            int times = 1;
            int stepPerLoop = SampleCRUD.STEP_SIZE * SampleCRUD.THREAD_NUM;
            int index = 0;
            while (index < times) {
                index++;
                SampleCRUD.startInsert();
            }
            System.out.println("总共插入 " + stepPerLoop * times + " 用时 " + (System.currentTimeMillis() - startTime) + "(ms)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
