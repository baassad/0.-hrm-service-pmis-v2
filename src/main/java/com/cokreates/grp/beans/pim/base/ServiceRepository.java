package com.cokreates.grp.beans.pim.base;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository<E extends SuperEntity> extends JpaRepository<E, String> {
}
