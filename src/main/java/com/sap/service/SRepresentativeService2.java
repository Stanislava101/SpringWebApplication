package com.sap.service;

import com.sap.model.User;

public interface SRepresentativeService2 {
    void save(User user);
    User findByUsername(String username);
}
