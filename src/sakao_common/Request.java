package sakao_common;

import java.util.ArrayList;

public class Request {
	private String operation_type;
	private String target;
	private ArrayList<String> list = new ArrayList<String>();///// VALUE

	public Request() {
	}

	public Request(String o, String t) {
		this.operation_type = o;
		this.target = t;

	}
	
	public Request(String o, String t, ArrayList<String> l) {
		this.operation_type = o;
		this.target = t;
		this.list = l;
	}
	
	
	
	public String toString() {

		return "{\"operation_type\":\"" + this.operation_type + "\"," + "\"target\":\"" + this.target + "\","
				+ "\"list\":\"" + list + "\"}";
	}

	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

}
