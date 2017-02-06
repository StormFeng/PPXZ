package midian.baselib.bean;

import midian.baselib.app.AppException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * 结果
 * 
 * @author XuYang
 * 
 */
public class NetResult extends NetBase {
	public String status = "";
	public String errorcode = "";
    public String message = "";

	public static Gson gson = new Gson();

	public boolean isOK() {
		return "SUCCESS".equals(status);
	}

	public static NetResult parse(String json) throws AppException {
		NetResult res = new NetResult();
		try {
			res = gson.fromJson(json, NetResult.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw AppException.json(e);
		}
		return res;
	}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

}
