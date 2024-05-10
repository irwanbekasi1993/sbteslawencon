package sb.tes.lawencon.sbteslawencon.payload.response;

import java.io.Serializable;

public class MessageResponse implements Serializable{
private String message;
private Object data;


public MessageResponse(String message) {
    this.message = message;
}
public MessageResponse(String message, Object data) {
    this.message = message;
    this.data = data;
}
public String getMessage() {
    return message;
}
public void setMessage(String message) {
    this.message = message;
}
public Object getData() {
    return data;
}
public void setData(Object data) {
    this.data = data;
}


}
