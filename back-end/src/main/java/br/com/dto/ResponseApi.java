package br.com.dto;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

public class ResponseApi {

    private Response response;

    public ResponseApi(List<String> message, Object data) {
        StringBuilder msg = new StringBuilder();
        message.forEach(m -> msg.append(m).append("\n"));
        response = Response.ok(data, msg.toString()).status(200).build();
    }

    public static Response build(List<String> message, Object data){
        return new ResponseApi(message,data).response;
    }

    public static Response build(Object data, String... message){
        if (message != null){
            return new ResponseApi(Arrays.asList(message),data).response;
        }
        return new ResponseApi(null,data).response;
    }
}