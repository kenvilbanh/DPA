package com.fa.DPA.repos;


import com.fa.DPA.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends BaseRepository<Role>{
    /**
     *
     * @return
     */
    @Override
    List<Role> findAll();
}
