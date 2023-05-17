package com.eval1.authentication;


import custom.springutils.*;
import custom.springutils.exception.LoginException;
import custom.springutils.exception.RegisterException;
import custom.springutils.service.ServiceLogin;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SessionLoginService<E extends LoginEntity, R extends LoginRepo<E>> implements ServiceLogin<E> {
    protected R repo;
    protected String sessionName;
    protected HttpSession session;

    public SessionLoginService(R repo, String sessionName, HttpSession session) {
        this.repo = repo;
        this.sessionName = sessionName;
        this.session = session;
    }

    public E register(E entity) throws Exception {
        List<E> tmp = this.repo.findByEmail(entity.getEmail());
        if (!tmp.isEmpty()) {
            throw new RegisterException("This email already has an account linked with.");
        } else {
            return this.repo.save(entity);
        }
    }

    public AuthenticatedDetails<E> login(E entity) throws Exception {
        E usr = this.getLoginDetails(entity);
        session.setAttribute(sessionName, usr);
        session.setAttribute("connected", usr);
        return new AuthenticatedDetails<>(null, usr);
    }

    protected E getLoginDetails(E entity) throws LoginException {
        List<E> list = this.repo.findByEmail(entity.getEmail());
        if (list.size() == 0) {
            throw new LoginException("User not found");
        } else {
            for(E element: list) {
                if (!element.getPassword().equals(entity.getPassword())) {
                    throw new LoginException("Wrong password");
                } else {
                    return element;
                }
            }
        }
        return null;
    }

    public boolean logout(String placeholder) {
        if(session.getAttribute("connected") == null) return false;
        session.removeAttribute("connected");
        session.removeAttribute(placeholder);
        return true;
    }

    public boolean isConnected(String placeholder) throws Exception {
        return session.getAttribute(sessionName) != null;
    }
}
