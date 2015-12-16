package org.prokarma.web.demo.test;

import com.vzt.framework.core.annotations.Data;

/**
 * User credentials from test data.
 * @author prokarma
 * @verion 1.0
 */
public class UserCredentials {
    
    @Data(name="userid")
    private String userName;
    
    @Data(name="password")
    private String password;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserCredentials [" + (userName != null ? "userName=" + userName + ", " : "")
                + (password != null ? "password=" + password : "") + "]";
    }
}
