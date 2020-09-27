package net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.ExceptionsTemplate;
import net.mkhalili96.dynamic_role_hierarchy.common.exception.dto.ErrorMap;

public class ClientVersionException extends ExceptionsTemplate {


    public ClientVersionException() {
    }

    public ClientVersionException(String inputVersion, String currentVersion) {
        super("client-version [" + inputVersion + "] must be greater or same as minimum-supported-client-version [" + currentVersion + "]");
    }

    public ClientVersionException(String inputVersion, String currentVersion, Throwable cause) {
        super("client-version [" + inputVersion + "] must be greater or same as minimum-supported-client-version [" + currentVersion + "]");
    }

    @Override
    public int getStatus() {
        return ErrorMap.CLIENT_VERSION.value();
    }

    @Override
    public ErrorMap getError() {
        return ErrorMap.CLIENT_VERSION;
    }
}
