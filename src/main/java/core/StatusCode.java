package core;

public enum StatusCode {

    NO_CONTENT(204, "No Content"),
    SUCCESS(200, "Success");




    public final int code;
    public final String msg;



    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
