package com.eval1.security;

import com.eval1.exception.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SecurityManager {

    HttpSession session;

    public SecurityManager(HttpSession session) {
        this.session = session;
    }

    public void isConnected() throws UnauthorizedException {
        check("connected");
    }

    public void isAdmin() throws UnauthorizedException {
        isConnected();
        check("admin");
    }

    public void isAuthor() throws UnauthorizedException {
        isConnected();
        check("author");
    }

    private void check(String role) throws UnauthorizedException {
        if (session.getAttribute(role) != null) {
            return;
        }
        throw new UnauthorizedException();
    }

}
