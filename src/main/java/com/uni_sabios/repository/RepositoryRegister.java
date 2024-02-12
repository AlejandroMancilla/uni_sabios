package com.uni_sabios.repository;

import java.util.List;

import com.uni_sabios.repository.models.Register;

public interface RepositoryRegister {
    
    List<Register> list();

    Register getRegister(int registerId);

    void create(Register register);

    void modify(Register register);

}