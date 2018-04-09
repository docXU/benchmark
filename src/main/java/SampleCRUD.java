import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.dbutils.*;
import org.apache.commons.dbcp.BasicDataSource;

/**
 * @author xuxiongwei
 */
public class SampleCRUD implements Runnable {
    /**
     * 使用固定步长避免id冲突
     */
    private final static int STEP_SIZE = 20000;
    private final static int THREAD_NUM = 100;
    private static int alreadyDoneRecord = 6380010;

    private int start_id;

    private SampleCRUD(int start_id) {
        this.start_id = start_id;
    }

    private static void startInsert() throws InterruptedException {

        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(50);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(50, 101, 1, TimeUnit.MINUTES, workQueue);
        for (int i = 0; i < THREAD_NUM; i++) {
            SampleCRUD thread = new SampleCRUD(alreadyDoneRecord + i * SampleCRUD.STEP_SIZE);
            pool.execute(thread);
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
            Thread.sleep(100);
        }

    }

    public void run() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/ibox_trainee?serverTimezone=UTC&useSSL=false&rewriteBatchedStatements=true");
            dataSource.setUsername("someoneCool");
            dataSource.setPassword("123456");

            StringBuilder sb = new StringBuilder("insert into ibox_trainee.business values ");
            Random random = new Random();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < STEP_SIZE; i++) {
                sb.append("(").append(start_id + i).append(", '")
                        .append(UUID.randomUUID().toString()).append("', ")
                        .append("'深圳" + random.nextInt(10000) + "', ")
                        .append("'13049474755'").append(", ")
                        .append("0, ")
                        .append("null, ")
                        .append("'" + sd.format(new Date()) + "', ")
                        .append("0, ")
                        .append("101),");
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
            System.out.println("insert " + (start_id) + " ~ " + (start_id + STEP_SIZE) + "(不含)  | " + STEP_SIZE + " BatchSize " + " 用时 " + (System.currentTimeMillis() - startTime) + "(ms)");
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
