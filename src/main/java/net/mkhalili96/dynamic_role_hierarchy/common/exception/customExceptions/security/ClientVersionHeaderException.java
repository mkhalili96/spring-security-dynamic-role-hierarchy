package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class ClientVersionHeaderException extends ExceptionsTemplate {


    public ClientVersionHeaderException() {
        super("please specify client-version in header");
    }

    @Override
    public int getStatus() {
        return ErrorMap.CLIENT_VERSION_HEADER.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.CLIENT_VERSION_HEADER;
    }
}
