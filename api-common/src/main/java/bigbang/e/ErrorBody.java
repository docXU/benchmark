package bigbang.e;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Matt Xu on 2018/3/30
 */

public class ErrorBody implements Serializable {
    private int code;
    private String message;
    private Date time;

    public ErrorBody() {
    }

    public ErrorBody setCode(int code) {
        this.code = code;
        return this;
    }

    public ErrorBody setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorBody setTime(Date time) {
        this.time = time;
        return this;
    }

    @Override
    public String toString() {
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");
        return String.format("{%n   error:{%n       code:%d,%n       message: %s,%n       time: %s%n   }%n}", code, message, bf.format(time));
    }
}
