package com.bravo.user.dao.repository;

import com.bravo.user.dao.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

  Profile findByUsername(final String username);
}
