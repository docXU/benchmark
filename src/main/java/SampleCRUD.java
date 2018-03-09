import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.*;
import org.apache.commons.dbcp.BasicDataSource;

public class SampleCRUD {
    // 使用DBUtils的QueryRunner测试sql插入性能（内部使用PreparedStatement预处理）
    // insert 50000 | 10000 BatchSize  用时 71553(ms)
    // insert 50000 | 100 BatchSize  用时 70829(ms)
    // insert 50000 | 1 BatchSize  用时 75155(ms)
    // 批处理似乎无效
    public static void main(String args[]) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test_my?serverTimezone=UTC&useSSL=false");
        dataSource.setUsername("someoneCool");
        dataSource.setPassword("123456");
        QueryRunner run = new QueryRunner(dataSource);

        try {
            int totalData = 50000;
            int times = 5;
            do {
                int linePerBatch = totalData / times;
                Object[][] objArray = new Object[linePerBatch][];
                for (int i = 0; i < linePerBatch; i++) {
                    objArray[i] = new Object[]{null, "xxw", "123", 11};
                }

                long startTime = System.currentTimeMillis();
                for (int i = 0; i < times; i++) {
                    run.insertBatch("insert into test_my.user values (?, ?, ?, ?)", new ResultSetHandler<Object[]>() {
                        public Object[] handle(ResultSet rs) {
                            return null;
                        }
                    }, objArray);
                }
                System.out.println("insert " + totalData + " | " + linePerBatch + " BatchSize " + " 用时 " + (System.currentTimeMillis() - startTime) + "(ms)");
                times *= 100;
            } while (times <= totalData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
