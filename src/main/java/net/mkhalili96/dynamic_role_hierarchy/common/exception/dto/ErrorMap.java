package net.mkhalili96.dynamic_role_hierarchy.common.exception.dto;

import com.sun.istack.Nullable;
import org.springframework.http.HttpStatus;

public enum ErrorMap {

    //NOT_FOUND code start with 4040
    ROLE_ID_NOT_FOUND(404001),
    USER_ID_NOT_FOUND(404002),
    TEMP_ROLE_ID_NOT_FOUND(404003),
    USER_ROLE_NOT_FOUND(404004),
    USER_NAME_NOT_FOUND(404005),
    HIERARCHY_ROLE_NOT_FOUND(404006),

    Unauthorized(401),

    //FORBIDDEN
    INVALID_CREDENTIALS(403001),
    USER_DISABLED(403002),
    ILLEGAL_ARGUMENT(403003),
    ACCESS_DENIED(403004),
    PASSWORD_NOT_VALID(403005),
    VERSION_NOT_MATCH(403006),
    CLIENT_VERSION(403007),


    //ALREADY code start with 4001
    BAD_REQUEST(400),
    CLIENT_VERSION_HEADER(400001),
    CANT_CHANGE_USERNAME(400002),
    CANT_CHANGE_ROLLE(400003),
    USER_NAME_ALREADY_EXIST(400101),
    CANT_ASSIGN_TEMP_ROLE_USER_ALREADY_HAVE(400102),
    SAME_PASSWORD(400102),



    INTERNAL_SERVER_ERROR(500),

    ERROR(000);


    private final int value;

    private ErrorMap(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public String toString() {
        return this.value + ":" + this.name();
    }

    public static HttpStatus getHttpStatus(int statusCode) {
        String statusCodestr = String.valueOf(statusCode);
        return HttpStatus.valueOf(Integer.parseInt(statusCodestr.substring(0, 3)));
    }

    public static ErrorMap valueOf(int statusCode) {
        ErrorMap ErrorsMap = resolve(statusCode);
        if (ErrorsMap == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        } else {
            return ErrorsMap;
        }
    }

    @Nullable
    public static ErrorMap resolve(int statusCode) {
        ErrorMap[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ErrorMap ErrorsMap = var1[var3];
            if (ErrorsMap.value == statusCode) {
                return ErrorsMap;
            }
        }
        return null;
    }
}
