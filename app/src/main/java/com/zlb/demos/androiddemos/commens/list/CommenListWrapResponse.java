package com.zlb.demos.androiddemos.commens.list;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.commens.list
 * @date 16/8/24上午11:07
 * @Description
 */
public class CommenListWrapResponse {
    private String status;
    private String message = "";
    CommentListResponse response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommentListResponse getResponse() {
        return response;
    }

    public void setResponse(CommentListResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "CommenListWrapResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", response=" + response +
                '}';
    }
}
